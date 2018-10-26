package cz.lubos.api.division.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dto for table organizational_structure.division
 */
public class Division {

	private Integer id;
	
	private Integer parentId;
	
	private String name;
	
	private Date validDateFrom;
	
	private Date validDateTo;
	
	private String abbreviation;
	
	private List<Division> subDivisions;
	
	private BigInteger level;
	

	// ctors
	public Division() {
		this.id = -1;
		this.subDivisions = new ArrayList<>();
	};

	public Division(int id, String name, Integer parentId) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.subDivisions = new ArrayList<>();
	}

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
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public List<Division> getSubDivisions() {
		return subDivisions;
	}

	public void setSubDivisions(List<Division> subDivisions) {
		this.subDivisions = subDivisions;
	}

	public BigInteger getLevel() {
		return level;
	}

	public void setLevel(BigInteger level) {
		this.level = level;
	}
	
}
