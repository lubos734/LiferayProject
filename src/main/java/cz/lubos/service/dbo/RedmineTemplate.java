package cz.lubos.service.dbo;

/**
 * Dbo for table redmine.template
 */
public class RedmineTemplate {

	private Integer id;

    private String process;

    private String division;

    private Integer parentId;

    private Integer parentIssue;

    private String subject;

    private String description;

    private String project;

    private String state;

    private String priority;

    private String assignedTo;

    private Float estimatedHours;

    private Integer doneRatio;

    private Integer daysToFinish;

    private String watchers;
    
    private Boolean isProcessed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentIssue() {
		return parentIssue;
	}

	public void setParentIssue(Integer parentIssue) {
		this.parentIssue = parentIssue;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Float getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Float estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Integer getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(Integer doneRatio) {
		this.doneRatio = doneRatio;
	}

	public Integer getDaysToFinish() {
		return daysToFinish;
	}

	public void setDaysToFinish(Integer daysToFinish) {
		this.daysToFinish = daysToFinish;
	}

	public String getWatchers() {
		return watchers;
	}

	public void setWatchers(String watchers) {
		this.watchers = watchers;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}


    
}