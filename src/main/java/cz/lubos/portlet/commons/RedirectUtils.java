package cz.lubos.portlet.commons;

import java.util.Enumeration;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.PortalUtil;

/**
 * Posman utils for redirecting.
 * Contains methods for parsing redirect parameters from url 
 */
public class RedirectUtils {

	public static final String OS_DIVISION_ID = "osDivisionId";
	public static final String OS_JOB_POSITION_ID = "osJobPositionId";
	public static final String OS_JOB_PLACE_ID = "osJobPlaceId";
	public static final String OS_EMPLOYEE_ID = "osEmployeeId";
	public static final String OS_INSTANCE_ID = "osInstanceId";
	
	public static final String OS_TEMPLATE_ID = "osTemplateId";
	public static final String OS_JOB_PLACE_EMPLOYEE_ID = "osJobPlaceEmployeeId";
	
	public static final String OS_REDIRECT_NAME = "osRedirectName";
	public static final String OS_REDIRECT_VALUE = "osRedirectValue";

	public static final String OS_TRAINING_ID = "osTrainingId";
	public static final String OS_MEDICAL_EXAM_ID = "osMedicalExamId";
	
	public static final String REDIRECT_URL = "redirectUrl";
	
	public static final String REDIRECT_UPDATE_URL = "redirectUpdateUrl";
	public static final String REDIRECT_CREATE_URL = "redirectCreateUrl";
	
	private static final String PORTLET_PARAM_REGEX_START = "^(.*_%s)$|^(%<s)$";
	
//	private static final Log log = LogFactoryUtil.getLog(RedirectUtils.class);
	
	/**
	 * Searches for parameter in url.
	 * @param request portlet request
	 * @param parameterName parameter name
	 * @return Integer with value of param or NULL if param does not exists or value is not Integer
	 */
	public static Integer getParameterInt(PortletRequest request, String parameterName) {
		// get original http request
		HttpServletRequest httpOrigReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		// create regex with param name
		String regex = String.format(PORTLET_PARAM_REGEX_START, parameterName);
		
		// check if any of the params matches with regex
		Enumeration<String> parameterNames = httpOrigReq.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameter = parameterNames.nextElement();
			if (parameter.matches(regex)) {
				// return Integer value or null
				try {
					return Integer.valueOf(httpOrigReq.getParameter(parameter));
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		
		// param does not exist
		return null;
	}
	
	/**
	 * Searches for parameter in url.
	 * @param request portlet request
	 * @param parameterName parameter name
	 * @return String with value of param or NULL if param does not exists or value is not Integer
	 */
	public static String getParameterString(PortletRequest request, String parameterName) {
		// get original http request
		HttpServletRequest httpOrigReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		// create regex with param name
		String regex = String.format(PORTLET_PARAM_REGEX_START, parameterName);
		
		// check if any of the params matches with regex
		Enumeration<String> parameterNames = httpOrigReq.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameter = parameterNames.nextElement();
			if (parameter.matches(regex)) {
				// return Integer value or null
				try {
					return httpOrigReq.getParameter(parameter);
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		
		// param does not exist
		return null;
	}
}
