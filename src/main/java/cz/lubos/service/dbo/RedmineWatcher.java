package cz.lubos.service.dbo;

/**
 * Dbo for table redmine.watcher
 */
public class RedmineWatcher {

	private Integer employeeId;
	
	private Integer ticketDefinitionId;
	
	public RedmineWatcher(){
	}
	
	public RedmineWatcher(Integer employeeId, Integer ticketDefinitionId){
		this.employeeId = employeeId;
		this.ticketDefinitionId = ticketDefinitionId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getTicketDefinitionId() {
		return ticketDefinitionId;
	}

	public void setTicketDefinitionId(Integer ticketDefinitionId) {
		this.ticketDefinitionId = ticketDefinitionId;
	}
	
	
}
