package cz.lubos.portlet.division.divisioncreate;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import cz.lubos.api.division.DivisionService;
import cz.lubos.api.division.dto.Division;
import cz.lubos.api.division.dto.DivisionOverview;
import cz.lubos.portlet.commons.OrganizationalStructureUtils;
import cz.lubos.portlet.commons.PortletUtils;
import cz.lubos.portlet.commons.RedirectUtils;
import cz.lubos.portlet.division.DivisionConstants;
import cz.lubos.portlet.preferences.AbstractPreferencesController;


/**
 * Controller for portlet DivisionCreateController
 *
 */
@Controller
@RequestMapping("VIEW")
public class DivisionCreateController {
	
//	private static final Log log = LogFactoryUtil.getLog(DivisionCreateController.class);
	
	@Autowired
	private DivisionService divisionService;
	
	/**
	 * Default render method - render Division overview
	 * @param model model
	 * @return page
	 */
	@RenderMapping
	public String renderDivisionCreate(Model model, RenderRequest request, PortletPreferences prefs) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return AbstractPreferencesController.returnPreferencesErrorPage(request, model);
		}
		
		initModel(model, request);
		
		return "division/DivisionCreate";
	}

	
	/**
	 * Action method - save division
	 * @param formCrate
	 * @param bindingResult
	 * @param request
	 * @param response
	 * @param model
	 */
	@ActionMapping(DivisionConstants.DIVISION_CREATE_ACTION_SAVE)
	public void actionSave(@Valid @ModelAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE) DivisionCreateFormCrate formCrate,
			BindingResult bindingResult, ActionRequest request, ActionResponse response, Model model) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return;
		}

		if (bindingResult.hasFieldErrors()) {
			return;
		}
		
		Integer id = formCrate.getDivisionId();
		
		if (id == null) {
			divisionService.insertDivision(createDto(formCrate, null));
			PortletUtils.addPageSuccess(request, model, "DIVISION_CREATE.SUCCESS_DIVISION_INSERT");
			model.addAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE, new DivisionCreateFormCrate());
		} else {
			divisionService.updateDivision(createDto(formCrate, id));
			PortletUtils.addPageSuccess(request, model, "DIVISION_CREATE.SUCCESS_DIVISION_UPDATE");
		}

		model.addAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE, new DivisionCreateFormCrate());
	}
	
	/**
	 * Create dto from crate
	 * @param crate
	 * @return
	 */
	private Division createDto(DivisionCreateFormCrate crate, Integer divisionId) {
		
		Division division = new Division();
		division.setId(divisionId);
		division.setParentId(crate.getParentId());
		division.setName(crate.getName().trim());
		division.setValidDateFrom(crate.getValidDateFrom());
		division.setAbbreviation(crate.getAbbreviation());
		return division;
	}
	
	
	/**
	 * Init model, set attributes
	 * @param model
	 * @param request
	 */
	private void initModel(Model model, RenderRequest request) {
		
		Integer divisionId = RedirectUtils.getParameterInt(request, RedirectUtils.OS_DIVISION_ID);
		if (divisionId == null) {
			if (!model.containsAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE)) {
				model.addAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE, new DivisionCreateFormCrate());
			} 
		} else {
			DivisionOverview division = divisionService.getDivisionById(divisionId);
			if (!model.containsAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE)) {
				model.addAttribute(DivisionConstants.DIVISION_CREATE_FORM_CRATE, createCrate(division));
			}
		}
		
		if (!model.containsAttribute(DivisionConstants.DIVISION_CREATE_SELECT_DIVISION)) {
			model.addAttribute(DivisionConstants.DIVISION_CREATE_SELECT_DIVISION, OrganizationalStructureUtils.createDivisionCascadeCodeList(divisionService.getRootNodes(), divisionService.getAllDivisions()));
		}	
	}
	
	/**
	 * Create crate from DTO 
	 * @param division
	 * @return
	 */
	private DivisionCreateFormCrate createCrate(DivisionOverview division) {
		DivisionCreateFormCrate crate = new DivisionCreateFormCrate();
		crate.setName(division.getName());
		crate.setParentId(division.getParentId());
		crate.setValidDateFrom(division.getValidDateFrom());
		crate.setAbbreviation(division.getAbbreviation());
		crate.setDivisionId(division.getId());
		return crate;
	}
	
}
