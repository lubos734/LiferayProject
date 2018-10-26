package cz.lubos.portlet.commons;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import cz.lubos.api.CodeListDto;

/**
 * Common methods for portlet layer
 *
 */
public class PortletUtils {
	
	public static final String PAGE_PARAM = "page";
	public static final String PAGE_SUCCESS_CODE = "pageSuccessCode";
	public static final String PAGE_SUCCESS_ARGS = "pageSuccessArgs";
	public static final String PAGE_ERROR_CODE = "pageErrorCode";
	public static final String PAGE_ERROR_ARGS = "pageErrorArgs";
	public static final String PAGE_WARNING_CODE = "pageWarningCode";
	public static final String PAGE_WARNING_ARGS = "pageWarningArgs";
	public static final String PAGE_INFO_CODE = "pageInfoCode";
	public static final String PAGE_INFO_ARGS = "pageInfoArgs";
	public static final String PAGE_ERROR_JSP_PAGE = "ErrorPage";
	public static final String PAGE_NAVIGATION = "pageNavigation";
	
	// Resource method messages
	public static final String PAGE_RESOURCE_MESSAGE_ID = "pageResourceMessageId";
	public static final String PAGE_RESOURCE_MESSAGE_TEXT = "pageResourceMessageText";
	public static final String PAGE_RESOURCE_DATA = "pageResourceData";
	public static final String PAGE_RESOURCE_FIELD_ERRORS = "pageResourceFieldErrors";
	public static final String PAGE_RESOURCE_FIELD_ERROR_NAME = "name";
	public static final String PAGE_RESOURCE_FIELD_ERROR_MESSAGE = "message";
	public static final String PAGE_SUCCESS_ID = "pageSuccessId";
	public static final String PAGE_ERROR_ID = "pageErrorId";
	public static final String PAGE_INFO_ID = "pageInfoId";
	public static final String PAGE_WARNING_ID = "pageWarningId";
	// Resource method content type for json
	private static final String HEADER_CONTENT_TYPE = "Content-type";
	private static final String JSON_CONTENT_TYPE = "application/json";
	
	// Date formats
	public static final String DATE_FORMAT_FILE = "yyyyMMdd";
	public static final String DATE_TIME_FORMAT_PAGE = "dd.MM.yyyy HH:mm";
	public static final String DATE_TIME_SECONDS_FORMAT_PAGE = "dd.MM.yyyy HH:mm:ss";
	
	public static final String DATE_FORMAT_CONVERTER = "dd.MM.y";
	public static final String DATE_FORMAT_PICKER = "d.m.Y";
	public static final String INSERT_DATE_FORMAT = "dd.MM.yyyy";

	public static final String PARAMETER_NAME = "target";
	
	private static final Log log = LogFactoryUtil.getLog(PortletUtils.class);
	

	/**
	 * Write json to response
	 * @param response
	 * @param data
	 */
	public static void writeJsonToResponse(ResourceResponse response, String data) {
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		try {
			httpResponse.setHeader(HEADER_CONTENT_TYPE, JSON_CONTENT_TYPE);
			ServletResponseUtil.write(httpResponse, data);
		} catch (IOException e) {
			log.error("Could not write data to response.", e);
		}
	}
	
	/**
	 * Pass page parameter, set it to render method
	 * @param request
	 * @param response
	 */
	public static void passPageParam(ActionRequest request, ActionResponse response) {
		String pageParam = request.getParameter(PAGE_PARAM);
		if (pageParam != null) {
			response.setRenderParameter(PAGE_PARAM, pageParam);
		}
	}
	
	/**
	 * Hide default liferay error messages
	 * @param request
	 */
	public static void hideDefaultMessages(PortletRequest request) {
		PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
		SessionMessages.add(request, ((LiferayPortletConfig) portletConfig).getPortletId() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * Add page error
	 * @param request
	 * @param model
	 * @param code
	 */
	public static void addPageError(PortletRequest request, Model model, String code) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_ERROR_CODE, code);
	}
	
	/**
	 * Add page error message with arguments
	 * @param request porlet request
	 * @param model model
	 * @param code error code
	 * @param args error arguments
	 */
	public static void addPageErrorWithArguments(PortletRequest request, Model model, String code, List<String> args) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_ERROR_CODE, code);
		model.addAttribute(PAGE_ERROR_ARGS, args);
	}
	
	/**
	 * Add page success message
	 * @param request
	 * @param model
	 * @param errorCode
	 */
	public static void addPageSuccess(PortletRequest request, Model model, String code) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_SUCCESS_CODE, code);
	}
	
	/**
	 * Add page success message with arguments
	 * @param request porlet request
	 * @param model model
	 * @param code error code
	 * @param args error arguments
	 */
	public static void addPageSuccessWithArguments(PortletRequest request, Model model, String code, List<String> args) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_SUCCESS_CODE, code);
		model.addAttribute(PAGE_SUCCESS_ARGS, args);
	}
	
	/**
	 * Add page warning message
	 * @param request
	 * @param model
	 * @param errorCode
	 */
	public static void addPageWarning(PortletRequest request, Model model, String code) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_WARNING_CODE, code);
	}
	
	/**
	 * Add page warning message with arguments
	 * @param request porlet request
	 * @param model model
	 * @param code error code
	 * @param args error arguments
	 */
	public static void addPageWarningsWithArguments(PortletRequest request, Model model, String code, List<String> args) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_WARNING_CODE, code);
		model.addAttribute(PAGE_WARNING_ARGS, args);
	}
	
	/**
	 * Add page info message
	 * @param request
	 * @param model
	 * @param errorCode
	 */
	public static void addPageInfo(PortletRequest request, Model model, String code) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_INFO_CODE, code);
	}
	
	/**
	 * Add page info message with arguments
	 * @param request porlet request
	 * @param model model
	 * @param code error code
	 * @param args error arguments
	 */
	public static void addPageInfoWithArguments(PortletRequest request, Model model, String code, List<String> args) {
		hideDefaultMessages(request);
		model.addAttribute(PAGE_INFO_CODE, code);
		model.addAttribute(PAGE_INFO_ARGS, args);
	}
	
	
	/**
	 * Add page success for resource method
	 * @param response
	 * @param text
	 */
	public static void addPageResourceSuccess(ResourceResponse response, String text, JSONObject data) {
		createResourceJson(response, text, PAGE_SUCCESS_ID, data, null);
	}
	
	/**
	 * Add page error for resource method
	 * @param response
	 * @param text
	 */
	public static void addPageResourceError(ResourceResponse response, String text, JSONObject data) {
		createResourceJson(response, text, PAGE_ERROR_ID, data, null);
	}
	
	/**
	 * Add page error for resource method with validation errors
	 * @param response
	 * @param text
	 */
	public static void addPageResourceError(ResourceResponse response, String text, JSONObject data, BindingResult bindingResult) {
		createResourceJson(response, text, PAGE_ERROR_ID, data, createFieldErrors(bindingResult));
	}
	
	
	/**
	 * Add page warning for resource method
	 * @param response
	 * @param text
	 */
	public static void addPageResourceWarning(ResourceResponse response, String text, JSONObject data) {
		createResourceJson(response, text, PAGE_WARNING_ID, data, null);
	}
	
	/**
	 * Add page info for resource method
	 * @param response
	 * @param text
	 */
	public static void addPageResourceInfo(ResourceResponse response, String text, JSONObject data) {
		createResourceJson(response, text, PAGE_INFO_ID, data, null);
	}

	/**
	 * Copy field errors from one BindingResult to another and concat prefix to fields
	 * @param from
	 * @param to
	 * @param fieldPrefix
	 */
	public static void copyFieldErrors(BindingResult from, BindingResult to, String fieldPrefix) {
		if (from.hasErrors()) {
			for (FieldError e:from.getFieldErrors()) {
				to.rejectValue(fieldPrefix + e.getField(), e.getCode());
			}	
		}
	}

	/**
	 * Copy field errors from one BindingResult to another
	 * @param from source BindingResult
	 * @param to output BindingResult
	 */
	public static void copyFieldErrors(BindingResult from, BindingResult to) {
		copyFieldErrors(from, to, "");
	}
	
	/**
	 * Copy field Errors to BindingResult
	 * @param from
	 * @param to
	 * @param fieldPrefix
	 */
	public static void copyFieldErrors(BindingResult from, Errors to, String fieldPrefix) {
		if (from.hasErrors()) {
			for (FieldError e: from.getFieldErrors()) {
				to.rejectValue(fieldPrefix + e.getField(), e.getCode());
			}	
		}
	}
	
	/**
	 * Copy field Errors to BindingResult
	 * @param from
	 * @param to
	 */
	public static void copyFieldErrors(BindingResult from, Errors to) {
		copyFieldErrors(from, to, "");
	}
	
	
	/**
	 * Add error message to model, return error page
	 * @param request
	 * @param model
	 * @param errorCode
	 * @return
	 */
	public static String returnErrorPage(PortletRequest request, Model model, String errorCode) {
		PortletUtils.addPageError(request, model, errorCode);
		return PAGE_ERROR_JSP_PAGE;
	}
	
	/**
	 * Return theme display object from portlet request
	 * @param request portlet request
	 * @return theme display object
	 */
	public static ThemeDisplay getThemeDisplay(PortletRequest request) {
		return (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	/**
	 * Return user object from portlet request
	 * @param request portlet request
	 * @return user object
	 */
	public static User getUser(PortletRequest request) {
		return getThemeDisplay(request).getUser();
	}
	
	/**
	 * Return email of current logged user
	 * @param request
	 * @return
	 */
	public static String getUserEmail(PortletRequest request) {
		return getUser(request).getEmailAddress();
	}
	
	/**
	 * Return screen name
	 * @param request
	 * @return
	 */
	public static String getUserScreenName(PortletRequest request) {
		return getUser(request).getScreenName();
	}

	/**
     * Create file name
     * @param bank
     * @param application
     * @return
     */
     public static String createFileNameXlsx(String bank, String application) {
        String fileName =  formatDate(new Date(), DATE_FORMAT_FILE);
        if (!StringUtils.isEmpty(bank)) {
            fileName = fileName.concat("_" + bank);
        }
        fileName = fileName.concat("_" + application);
        fileName = fileName.concat(".xlsx");
        return fileName;
     }
     
     public static String createTabNameXlsx(Integer tab){
    	 String tabName = "Tab";
    	 tabName = tabName.concat(String.format("%02d", tab));
    	 return tabName;
     }
     
     /**
     * Format date to String
     * @param date
     * @param dateFormat
     * @return
     */
     public static String formatDate(Date date, String dateFormat) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(dateFormat).format(date);
        }
     }
     
     /**
      * Parse date from string to Date object
      * @param date string containing date
      * @param dateFormat date format
      * @return Date object
      */
     public static Date parseDate(String date, String dateFormat, Log log) {
    	 if (date == null || date.isEmpty()) {
 			return null;
 		}
 		else {
 			try {
 				return new SimpleDateFormat(dateFormat).parse(date);
 			} catch (ParseException e) {
 				if (log != null) {
 					log.error(e);
 				}
 				return null;
 			}
 		}
     }
     
     /**
     * Control String to null
     * @param value
     * @return empty String if value is null, otherwise value
     */
     public static String getStringNotNull(String value) {
        return value != null ? value : "";
     }
     
     /**
      * Check String to null and localize
      * @param value
      * @return empty String if value is null, otherwise value
      */
      public static String getLocalizedStringNotNull(String value, MessageSource messageSource, Locale locale) {
         return value == null ? "" : messageSource.getMessage(value, null, locale);
      }
     
     /**
      * Control Object to null 
      * @param value
      * @return empty String if value is null, otherwise String from value
      */
     public static String getObjectNotNull(Object value) {
    	 return value != null ? value.toString() : "";
     }
     
     /**
 	 * Create localized list from list of keys to message source
 	 * @param keysList
 	 * @param locale
 	 * @return
 	 */
 	public static List<String> createLocalizedList(List<String> keysList, Locale locale, MessageSource messageSource) {
 		
 		List<String> localizedList = new ArrayList<>();
 		for (String key : keysList) {
 			localizedList.add(messageSource.getMessage(key, null, locale));
 		}
 		return localizedList;
 	}
 	
	/**
	 * Get print String value from Boolean
	 * @param value
	 * @return
	 */
	public static String getBooleanPrintValue(Boolean value) {
		if (value == null || !value) {
			return "\u2717";
		} else {
			return "\u2713";
		}
	}

	/**
	 * Get print String value from Boolean
	 * @param value
	 * @return
	 */
	public static String getBooleanStringValue(Boolean value, MessageSource messageSource, Locale locale) {
		if (value == null || !value) {
			return messageSource.getMessage("COMMON.STRING_NO", null, locale);
		} else {
			return messageSource.getMessage("COMMON.STRING_YES",null, locale);
		}
	}
	
	/**
	 * Create map that contains enum as key and localized text as value. User for creating localized comboboxes on page
	 * @param values array of enum values obtained by calling .values() on enum
	 * @param messageSource message source
	 * @param locale current locale
	 * @return localized map
	 */
 	public static <K extends Enum<K>> Map<K, String> createLocalizedEnum(K[] values, MessageSource messageSource, Locale locale) {
		Map<K, String> localizedMap = new LinkedHashMap<>();
		for (K value : values) {
			localizedMap.put(value, messageSource.getMessage(value.name(), null, locale));
		}
		return localizedMap;
	} 
 	
 	/**
	 * Create localized list from list of CodeListDto
	 * @param dataList list of dtos
	 * @param locale current locale
	 * @param messageSource message source
	 * @return localized list
	 */
 	public static <K> List<CodeListDto<K>> localizeCodeList(List<CodeListDto<K>> dataList, Locale locale, MessageSource messageSource) {
		for (CodeListDto<K> codeListDto : dataList){
			codeListDto.setName(messageSource.getMessage(codeListDto.getName(), null, locale));
		}
		return dataList;
 	}
 	
 	/**
 	 * Removes all spaces from string
 	 * @param string
 	 * @return
 	 */
 	public static String removeSpaces(String string) {
 		return string.replaceAll("\\s+","");
 	}
 	
	/**
	 * Create JSON for resource method message
	 * @param response
	 * @param text
	 * @param alertId
	 */
	private static void createResourceJson(ResourceResponse response, String text, String alertId, JSONObject data, JSONArray fieldErrors) {

		JSONObject o = JSONFactoryUtil.createJSONObject();
		if (text != null) {
			o.put(PAGE_RESOURCE_MESSAGE_ID, response.getNamespace()+alertId);
			o.put(PAGE_RESOURCE_MESSAGE_TEXT, text);
		}
		if (data != null) {
			o.put(PAGE_RESOURCE_DATA, data);
		}
		if (fieldErrors != null) {
			o.put(PAGE_RESOURCE_FIELD_ERRORS, fieldErrors);
		}
		writeJsonToResponse(response, o.toJSONString());
	}
	
	private static JSONArray createFieldErrors(BindingResult bindingResult) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		for (FieldError error: bindingResult.getFieldErrors()) {
			JSONObject o = JSONFactoryUtil.createJSONObject();
			o.put(PAGE_RESOURCE_FIELD_ERROR_NAME, error.getField());
			o.put(PAGE_RESOURCE_FIELD_ERROR_MESSAGE, error.getDefaultMessage());
			array.put(o);
		}
		return array;
	}
	
	public static String createResponseFromList(List<String> dataList) {
		StringBuilder builder = new StringBuilder();

		for (String value : dataList) {
			builder.append(value + ", ");
		}
		
		String data = builder.toString();

		return data.substring(0, data.length() - 2);
	}
}
