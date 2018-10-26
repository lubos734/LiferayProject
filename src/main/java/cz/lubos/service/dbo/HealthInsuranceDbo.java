package cz.lubos.service.dbo;

import java.util.Date;

/**
 * Dbo for table organizational_structure.health_insurance
 */
public class HealthInsuranceDbo {

	private Integer id;
	
	private Integer employeeId;
	
	private String insuranceCode;
	
	private Date validDateFrom;
	
	private Date validDateTo;
	
	public  HealthInsuranceDbo() {
	}
	
	public HealthInsuranceDbo(Integer employeeId, String insuranceCode, Date validDateFrom) {
		this.employeeId = employeeId;
		this.insuranceCode = insuranceCode;
		this.validDateFrom = validDateFrom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
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
	
	
	

}
