package cz.lubos.portlet.division.divisioncreate;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Crate for division create
 */
public class DivisionCreateFormCrate {
	
	
	@Size(min = 1, max=255, message = "{ERROR.FIELD_SIZE_1_255}")
	@NotNull(message = "{ERROR.FIELD_NOT_NULL}")
	private String name;
	
	@NotNull(message = "{ERROR.FIELD_NOT_NULL}")
	private Integer parentId;
	
	@NotNull(message = "{ERROR.FILED_NOT_DATE}")
	private Date validDateFrom;
	
	@Size(min = 1, max=5, message = "{ERROR.FIELD_SIZE_1_5}")
	@NotNull(message = "{ERROR.FIELD_NOT_NULL}")
	private String abbreviation;
	
	private Date validDateTo;
	
	private Integer divisionId;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getValidDateFrom() {
		return validDateFrom;
	}

	public void setValidDateFrom(Date validDateFrom) {
		this.validDateFrom = validDateFrom;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Date getValidDateTo() {
		return validDateTo;
	}

	public void setValidDateTo(Date validDateTo) {
		this.validDateTo = validDateTo;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	
	
	
	

}
