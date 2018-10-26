package cz.lubos.api.division.dto;

import java.util.Date;

/**
 * Dto for view organizational_structure.vw_division_overview;
 */
public class DivisionOverview {
	
	private Integer id;
	
	private Integer parentId;
	
	private String name;
	
	private String abbreviation;
	
	private String parentDivisionName;
	
	private Date validDateFrom;
	
	private Date validDateTo;
	
	private Boolean isTaken;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getParentDivisionName() {
		return parentDivisionName;
	}

	public void setParentDivisionName(String parentDivisionName) {
		this.parentDivisionName = parentDivisionName;
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

	public Boolean getIsTaken() {
		return isTaken;
	}

	public void setIsTaken(Boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	

}
