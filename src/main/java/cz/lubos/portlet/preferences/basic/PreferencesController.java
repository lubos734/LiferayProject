package cz.lubos.portlet.preferences.basic;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import cz.lubos.portlet.commons.PortletUtils;
import cz.lubos.portlet.preferences.AbstractPreferencesController;

/**
 * Controller for preferences which constains select for client
 *
 */
@Controller
@RequestMapping("EDIT")
public class PreferencesController extends AbstractPreferencesController {

	private static final Log log = LogFactoryUtil.getLog(PreferencesController.class);
	
	public static final String PREFERENCES_PAGE = "preferences/Preferences";
	
	/**
	 * Render preferences
	 * @param model
	 * @param request
	 * @return
	 */
	@RenderMapping
	public String render(Model model, RenderRequest request, PortletPreferences prefs)  {
		
		if (!model.containsAttribute(PREFERENCES_CRATE)) {
			PreferencesCrate preferencesCrate = new PreferencesCrate();
			
			preferencesCrate.setPermissionGroups(prefs.getValue(PREFERENCES_MANAGEMENT_GROUPS, ""));
			preferencesCrate.setPermissionUsers(prefs.getValue(PREFERENCES_MANAGEMENT_USERS, ""));
			model.addAttribute(PREFERENCES_CRATE, preferencesCrate);
		}
		
		return PREFERENCES_PAGE;
	}
	
	/**
	 * Save preferences
	 * @param preferencesCrate
	 * @param bindingResult
	 * @param request
	 * @param response
	 * @param model
	 */
	@ActionMapping(PREFERENCES_SAVE)
    public void storeConfiguration(@ModelAttribute(PREFERENCES_CRATE) PreferencesCrate preferencesCrate, BindingResult bindingResult,
    		PortletPreferences prefs, ActionRequest request, ActionResponse response, Model model) {

        try {
			prefs.setValue(PREFERENCES_MANAGEMENT_GROUPS, preferencesCrate.getPermissionGroups());
			prefs.setValue(PREFERENCES_MANAGEMENT_USERS, preferencesCrate.getPermissionUsers());
			prefs.store();
			PortletUtils.addPageSuccess(request, model, PREFERENCES_SAVE_CODE);
		} catch (ReadOnlyException | ValidatorException | IOException e) {
			log.error(e);
			PortletUtils.addPageError(request, model, PREFERENCES_ERROR_CODE);
		}
      
    }
	
}

















