package cz.lubos.portlet.division.divisionoverview;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import cz.lubos.api.division.dto.DivisionOverview;
import cz.lubos.portlet.commons.PortletUtils;
import cz.sonet.utils.jquery.datatable.DtoTransformer;

/**
 * Table transformer for division overview 
 */
public class DivisionOverviewTableTransformer implements DtoTransformer<DivisionOverview>{

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String ABBREVIATION = "abbreviation";
	public static final String PARENT_ID = "perentId";
	public static final String PARENT_DIVISION_NAME = "parent_division_name";
	public static final String VALID_DATE_FROM = "valid_date_from";
	public static final String VALID_DATE_TO = "valid_date_to";
	public static final String SHOW_INACTIVE = "showInactive";
	public static final String IS_TAKEN = "is_taken";
	
	
	public static final List<String> FILTER_KEYS = Arrays.asList(
												NAME,
												VALID_DATE_FROM,
												VALID_DATE_TO,
												SHOW_INACTIVE);
	
	public static final List<String> DIVISION_HEADER_LIST = Arrays.asList(
			"DIVISION_OVERVIEW.EXPORT_NAME",
			"DIVISION_OVERVIEW.EXPORT_ABBREVIATION",
			"DIVISION_OVERVIEW.EXPORT_PARENT_DIVISION_NAME",
			"DIVISION_OVERVIEW.EXPORT_VALID_DATE_FROM", 
			"DIVISION_OVERVIEW.EXPORT_VALID_DATE_TO");
	
	@Override
	public List<String> getKeys() {
		return Arrays.asList(
				ID,
				NAME,
				ABBREVIATION,
				PARENT_DIVISION_NAME,
				VALID_DATE_FROM,
				VALID_DATE_TO,
				IS_TAKEN);
	}

	@Override
	public List<String> getRowsValues(DivisionOverview record, MessageSource arg1, Locale arg2) {
		return Arrays.asList(
				String.valueOf(record.getId()),
				PortletUtils.getStringNotNull(record.getName()),
				PortletUtils.getStringNotNull(record.getAbbreviation()),
				PortletUtils.getStringNotNull(record.getParentDivisionName()),
				PortletUtils.formatDate(record.getValidDateFrom(),PortletUtils.DATE_FORMAT_CONVERTER),
				PortletUtils.formatDate(record.getValidDateTo(), PortletUtils.DATE_FORMAT_CONVERTER),
				PortletUtils.getBooleanPrintValue(record.getIsTaken())
				);
	}

}
