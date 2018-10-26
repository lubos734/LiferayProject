package cz.lubos.portlet.division.divisionoverview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import cz.lubos.api.division.DivisionService;
import cz.lubos.api.division.dto.DivisionOverview;
import cz.lubos.api.division.dto.DivisionOverviewFilter;
import cz.lubos.api.jobposition.JobPositionService;
import cz.lubos.portlet.commons.OrganizationalStructureUtils;
import cz.lubos.portlet.commons.PortletUtils;
import cz.lubos.portlet.commons.RedirectUtils;
import cz.lubos.portlet.commons.exportutils.ExportUtils;
import cz.lubos.portlet.division.DivisionConstants;
import cz.lubos.portlet.preferences.AbstractPreferencesController;
import cz.sonet.utils.jquery.datatable.ServerSideUtils;
import cz.sonet.utils.jquery.datatable.TableData;
import cz.sonet.utils.jquery.datatable.TableRequestConditions;
import cz.sonet.utils.jquery.datatable.exceptions.MissingRequestParametersException;


/**
 * Controller for portlet DivisionOverviewController
 *
 */
@Controller
@RequestMapping("VIEW")
public class DivisionOverviewController {

	private static final Log log = LogFactoryUtil.getLog(DivisionOverviewController.class);
	
	@Autowired
	private DivisionService divisionService;
	
	@Autowired
	private JobPositionService jobPositionService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ExportUtils exportUtils;
	
	
	private ServerSideUtils<DivisionOverview> serverSideUtils = new ServerSideUtils<>(new DivisionOverviewTableTransformer());
	
	/**
	 * Default render method - render Division overview
	 * @param model model
	 * @return page
	 */
	@RenderMapping
	public String renderDivisionOverview(Model model, RenderRequest request, PortletPreferences prefs) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return AbstractPreferencesController.returnPreferencesErrorPage(request, model);
		}
		
		initModel(model, request, prefs);
		
		return "division/DivisionOverview";
	}
	
	
	/**
	 * Resource method - export xls
	 * @param divisionOverviewFilter
	 * @param result
	 * @param request
	 * @param response
	 */
	@ResourceMapping(DivisionConstants.DIVISION_OVERVIEW_RESOURCE_EXPORT_XLS)
	public void resourceExport(@ModelAttribute(DivisionConstants.DIVISION_OVERVIEW_FILTER) DivisionOverviewFilterCrate divisionOverviewFilter,
			BindingResult result, ResourceRequest request, ResourceResponse response) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return;
		}

		Locale locale =  request.getLocale();
		
		List<DivisionOverview> divisions = divisionService.getDivisionsData(createDto(null, divisionOverviewFilter));
		
		if(divisions == null || divisions.isEmpty()) {
			PortletUtils.addPageResourceError(response, messageSource.getMessage("COMMON.ERROR_NO_DATA_XLS", null, locale), null);
			return;
		}
		
		List<String> headerList = PortletUtils.createLocalizedList(DivisionOverviewTableTransformer.DIVISION_HEADER_LIST, locale, messageSource);
		List<List<Object>> dataList = createDivisionDataList(divisions);
		exportUtils.sendReport("DIVISION_OVERVIEW.FILE_NAME_XLS", messageSource, request, response, headerList, dataList);
	}
	
	/**
	 * Resource method deactivate division
	 * @param formCrate
	 * @param result
	 * @param request
	 * @param response
	 */
	@ResourceMapping(DivisionConstants.DIVISION_OVERVIEW_RESOURCE_REMOVE)
	public void resourceRemove(DivisionOverviewDeactivateCrate formCrate,
			BindingResult result, ResourceRequest request, ResourceResponse response) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return;
		}

		Integer divisionId = formCrate.getDeactivateId();
		
		Date validDateFrom = divisionService.getValidFrom(divisionId);
		Date validDateTo = formCrate.getDeactivateDateTo();
		
		if (validDateTo == null || validDateFrom.compareTo(validDateTo) >= 0) {
			PortletUtils.addPageResourceError(response, messageSource.getMessage("DIVISION_OVERVIEW.ERROR_VALID_FROM_TO", null, request.getLocale()), null);
			return;
		}
		
		divisionService.deactivateDivision(divisionId, validDateTo);
		PortletUtils.addPageResourceSuccess(response, messageSource.getMessage("DIVISION_OVERVIEW.SUCCESS_DEACTIVATE", null, request.getLocale()),  null);
		
	}
	
	
	/**
	 * Resource method - create table
	 * @param divisionOverviewFilter
	 * @param result
	 * @param request
	 * @param resourceResponse
	 */
	@ResourceMapping(DivisionConstants.DIVISION_OVERVIEW_RESOURCE_TABLE)
	public void resourceTableData(@ModelAttribute(DivisionConstants.DIVISION_OVERVIEW_FILTER) DivisionOverviewFilterCrate divisionOverviewFilter,
			BindingResult result, ResourceRequest request, ResourceResponse resourceResponse) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return;
		}
		
		String data = "";
		try {
			TableRequestConditions conditions = serverSideUtils.parseRequest(request.getParameterMap(), null);

			TableData<DivisionOverview> tableData = divisionService.getDivsionOverviewData(createDto(conditions, divisionOverviewFilter));
			
			data = serverSideUtils.createTableServerSideResponseString(conditions.getDraw(), tableData, messageSource, request.getLocale());

		} catch (MissingRequestParametersException e) {
			data = serverSideUtils.createEmptyTableServerSideResponseString();
		}

		try {
			ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), data);
		} catch (IOException e) {
			log.error("Could not write json table data to response.", e);
		}
	}
	
	
	/**
	 * Resource method - Control deactivate division
	 * @param request
	 * @param resourceResponse
	 */
	@ResourceMapping(DivisionConstants.DIVISION_OVERVIEW_RESOURCE_CONTROL_DEACTIVATE)
	public void resourceControlDeactivate(ResourceRequest request, ResourceResponse resourceResponse) {
		
		// permissions
		if (!AbstractPreferencesController.checkPermissions(request)) {
			return;
		}
		
		Locale locale = request.getLocale();
		
		Integer divisionId = ParamUtil.getInteger(request, DivisionConstants.DIVISION_ID);
		
		String data = "";
		
		List<String> childrenDivisions = divisionService.getChildrenDivision(divisionId);
		if (childrenDivisions != null && !childrenDivisions.isEmpty()) {
			data = messageSource.getMessage("DIVISION_OVERVIEW.ERROR_EXISTS_CHILD", null, locale) + PortletUtils.createResponseFromList(childrenDivisions);
		} else {
			// job positions
			List<String> jobPositions = jobPositionService.getJobPositionsByDivsionId(divisionId);
			if (jobPositions != null && !jobPositions.isEmpty()) {
				data = messageSource.getMessage("DIVISION_OVERVIEW.ERROR_EXISTS_JOB_POSITIONS", null, locale) + PortletUtils.createResponseFromList(jobPositions); 
			} 
		} 
				
		PortletUtils.writeJsonToResponse(resourceResponse, data);
	}
	
	
	/**
	 * Create data list for export
	 * @param divisionOverview
	 * @return
	 */
	private List<List<Object>> createDivisionDataList(List<DivisionOverview> divisionOverview) {
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		
		for (DivisionOverview data : divisionOverview) {
			List<Object> row = new ArrayList<>();
			row.add(data.getName());
			row.add(data.getAbbreviation());
			row.add(data.getParentDivisionName());
			row.add(PortletUtils.formatDate(data.getValidDateFrom(), PortletUtils.DATE_FORMAT_CONVERTER));
			row.add(PortletUtils.formatDate(data.getValidDateTo(), PortletUtils.DATE_FORMAT_CONVERTER));
			dataList.add(row);
		}
		
		return dataList;
	}
	
	/**
	 * Create filter Dto
	 * @param conditions
	 * @param filterCrate
	 * @return
	 */
	private DivisionOverviewFilter createDto(TableRequestConditions conditions, DivisionOverviewFilterCrate filterCrate) {
		DivisionOverviewFilter filter;
		if (conditions == null) {
			filter = new DivisionOverviewFilter();
		} else {
			filter = new DivisionOverviewFilter(conditions);
		}
		
		filter.setId(filterCrate.getId());
		filter.setValidDateFrom(filterCrate.getValidDateFrom());
		filter.setValidDateTo(filterCrate.getValidDateTo());
		filter.setShowInactive(filterCrate.getShowInactive());
		
		return filter;
	}
	
	
	/**
	 * Init model, set attributes
	 * @param model
	 * @param request
	 */
	private void initModel(Model model, RenderRequest request, PortletPreferences prefs) {
		if (!model.containsAttribute(DivisionConstants.DIVISION_OVERVIEW_FILTER)) {
			model.addAttribute(DivisionConstants.DIVISION_OVERVIEW_FILTER, new DivisionOverviewFilterCrate());
		} 
		
		model.addAttribute(DivisionConstants.DIVISION_OVERVIEW_SELECT_DIVISION, OrganizationalStructureUtils.createDivisionCascadeCodeList(divisionService.getRootNodes(), divisionService.getAllDivisions()));
		
		model.addAttribute(RedirectUtils.REDIRECT_URL, prefs.getValue(RedirectUtils.REDIRECT_URL, ""));
			
	}

	
}
