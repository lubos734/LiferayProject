package cz.lubos.portlet.preferences;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.springframework.ui.Model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import cz.lubos.portlet.commons.PortletUtils;

public class AbstractPreferencesController {

private static final Log log = LogFactoryUtil.getLog(AbstractPreferencesController.class);
	
	protected static final String PREFERENCES_MANAGEMENT_GROUPS = "sonetPreferencesManagementGroups"; 
	protected static final String PREFERENCES_MANAGEMENT_USERS = "sonetPreferencesManagementUsers"; 
	protected static final String PREFERENCES_CRATE = "sonetPreferencesSettingsCrate";
	protected static final String PREFERENCES_SAVE = "sonetPreferencesSettingsSave";
	
	protected static final String PREFERENCES_ERROR_CODE = "PREFERENCES.PERMISSION_ERROR";
	protected static final String PREFERENCES_SECURITY_ERROR_CODE = "PREFERENCES.SECURITY_ERROR";
	protected static final String PREFERENCES_SAVE_CODE = "PREFERENCES.PREFERENCES_SUCCESS";
	
	
	/**
	 * Return preferences error page
	 * @param request
	 * @param model
	 * @return
	 */
	public static String returnPreferencesErrorPage(PortletRequest request, Model model) {
		return PortletUtils.returnErrorPage(request, model, PREFERENCES_SECURITY_ERROR_CODE);
	}
	
	/**
	 * Check if logged user is authorized to view page.
	 * @param request request action or render request
	 * @param model model
	 * @param pagePath 
	 * @return page given in pagePath param if user is allowed to see content, err page otherwise
	 */
	public static String checkPermissionPageReturn(PortletRequest request, Model model, String pagePath) {
		if (checkPermissions(request)) {
			return pagePath;
		} else {
			return PortletUtils.returnErrorPage(request, model, PREFERENCES_SECURITY_ERROR_CODE);
		}
	}
	

	
	/**
	 * Check if logged user is authorized to view page.
	 * @param request action or render request
	 * @return false if user is not in defined users or isnt in defined groups
	 */
	public static boolean checkPermissions(PortletRequest request) {
		
		PortletPreferences prefs = request.getPreferences();
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		
		String permissionGroupsStr = prefs.getValue(PREFERENCES_MANAGEMENT_GROUPS, "");
		String permissionUsersStr = prefs.getValue(PREFERENCES_MANAGEMENT_USERS, "");
		
		
		if (permissionGroupsStr.trim().isEmpty() && permissionUsersStr.trim().isEmpty()) {
			return true;
		} else if (permissionChecker.isOmniadmin()) {
			return true;
		} else {
			String[] permissionUsers = permissionUsersStr.split(",");
			for (String s: permissionUsers) {
				if (user.getEmailAddress().equals(s.trim())) {
					return true;
				}
			}
			
			try {
				List<Group> userGroups = user.getGroups();
				String[] permissionGroups = permissionGroupsStr.split(",");
				for (String s: permissionGroups) {
					String sTrim = s.trim();
					for (Group g: userGroups) {
						if (g.getName().equals(sTrim)) {
							return true;
						}
					}
				}
			} catch (SystemException e) {
				log.error(e);
			}
		}
		return false;
	}
	
}
