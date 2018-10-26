package cz.lubos.service.dbo;

import java.util.Date;

/**
 * Dbo for table organizational_structure.bank_account
 */
public class BankAccountDbo {

	private Integer employeeId;
	
	private String accountPrefix;
	
	private String accountNumber;
	
	private String bankCode;
	
	private Date validDateFrom;
	
	private Date validDateTo;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getAccountPrefix() {
		return accountPrefix;
	}

	public void setAccountPrefix(String accountPrefix) {
		this.accountPrefix = accountPrefix;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
