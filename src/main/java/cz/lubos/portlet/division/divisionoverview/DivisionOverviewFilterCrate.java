package cz.lubos.portlet.division.divisionoverview;

import java.util.Date;

/**
 * Filter crate for division overview
 */
public class DivisionOverviewFilterCrate {
	
	
	private Integer id;
	
	private String name;
	
	private Date validDateFrom;
	
	private Date validDateTo;
	
	private Boolean showInactive = false;
	
	private Date deactivateValidTo;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDeactivateValidTo() {
		return deactivateValidTo;
	}

	public void setDeactivateValidTo(Date deactivateValidTo) {
		this.deactivateValidTo = deactivateValidTo;
	}


	
	

}
