package cz.lubos.service.dbo;

import java.util.Date;

/**
 * Dbo for table organizational_structure.job_place_employee
 */
public class JobPlaceEmployeeDbo {

	private Integer id;
	
	private Integer jobPlaceId;
	
	private Integer employeeId;
	
	private Date validDateFrom;
	
	private Date validDateTo;
	
	private Integer employmentTypeId;
	
	private Date trialPeriodDate;
	
	private Boolean fixedTermContract;
	
	private Date fixedTermContractDateTo;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJobPlaceId() {
		return jobPlaceId;
	}

	public void setJobPlaceId(Integer jobPlaceId) {
		this.jobPlaceId = jobPlaceId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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

	public Integer getEmploymentTypeId() {
		return employmentTypeId;
	}

	public void setEmploymentTypeId(Integer employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}

	public Date getTrialPeriodDate() {
		return trialPeriodDate;
	}

	public void setTrialPeriodDate(Date trialPeriodDate) {
		this.trialPeriodDate = trialPeriodDate;
	}

	public Boolean getFixedTermContract() {
		return fixedTermContract;
	}

	public void setFixedTermContract(Boolean fixedTermContract) {
		this.fixedTermContract = fixedTermContract;
	}

	public Date getFixedTermContractDateTo() {
		return fixedTermContractDateTo;
	}

	public void setFixedTermContractDateTo(Date fixedTermContractDateTo) {
		this.fixedTermContractDateTo = fixedTermContractDateTo;
	}

	
	
	
}
