package cz.lubos.api.division.dto;

import java.util.Date;

import cz.lubos.api.DataTableFilter;
import cz.sonet.utils.jquery.datatable.TableRequestConditions;

/**
 * Filter for portlet Divison overview
 */
public class DivisionOverviewFilter extends DataTableFilter{
	
	private Integer id;
	
	private Date validDateFrom;
	
	private Date validDateTo;
	
	private Boolean showInactive;
	

	public DivisionOverviewFilter(){
		
	}
	
	public DivisionOverviewFilter(TableRequestConditions conditions) {
		super(conditions);
	}
	
	public boolean applyId() {
		return id != null;
	}

	public boolean applyValidDateFrom() {
		return validDateFrom != null;
	}
	
	public boolean applyValidDateTo() {
		return validDateTo != null;
	}
	
	public boolean applyShowActive() {
		return !showInactive;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getValidDateFrom() {
		return validDateFrom;
	}

	public void setValidDateFrom(Date validDateFrom) {
		this.validDateFrom = validDateFrom;
	}

	public Date getValidDateTo() {
		return validDateTo;
	}

	public void setValidDateTo(Date validDateTo) {
		this.validDateTo = validDateTo;
	}

	public Boolean getShowInactive() {
		return showInactive;
	}

	public void setShowInactive(Boolean showInactive) {
		this.showInactive = showInactive;
	}

	
	
	
}
