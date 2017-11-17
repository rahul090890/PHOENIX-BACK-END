package com.concretepage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "timesheet_reports")
public class TimesheetReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4719706805821811378L;
	@EmbeddedId
	private TimesheetReportId reportId;
	
	
	@Column(name = "weekStartDate")
	private String weekStartDate;
	
	@Column(name = "weekEndDate")
	private String weekEndDate;
	
	@Column(name = "employeeCode")
	private String employeeCode;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "employeeType")
	private String employeeType;
	
	@Column(name = "employeeEmail")
	private String employeeEmail;
	
	@Column(name = "approverId")
	private Integer approverId;
	
	@Column(name = "approverName")
	private String approverName;
	
	@Column(name = "customerId")
	private Integer customerId;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "customerCode")
	private String customerCode;
	
	@Column(name = "customerProgramId")
	private String customerProgramId;
	
	@Column(name = "customerProgramCode")
	private String customerProgramCode;
	
	@Column(name = "customerProgramType")
	private String customerProgramType;
	
	@Column(name = "departmentId")
	private String departmentId;
	
	@Column(name = "departmentCode")
	private String departmentCode;
	
	@Column(name = "departmentName")
	private String departmentName;
	
	@Column(name = "projectId")
	private Integer projectId;
	
	@Column(name = "projectCode")
	private String projectCode;
	
	@Column(name = "projectName")
	private String projectName;
	
	@Column(name = "projectType")
	private String projectType;
	
	@Column(name = "taskName")
	private String taskName;
	
	@Column(name = "billedHours")
	private Integer billedHours;
	
	@Column(name = "timesheetStatus")
	private String timesheetStatus;
	
	@Column(name = "comments")
	private String  comments;
	
	@Column(name = "timesheetSubmittedDate")
	private String timesheetSubmittedDate;
	
	public TimesheetReport() {
		
	}

	public TimesheetReportId getReportId() {
		return reportId;
	}

	public void setReportId(TimesheetReportId reportId) {
		this.reportId = reportId;
	}

	public String getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerProgramId() {
		return customerProgramId;
	}

	public void setCustomerProgramId(String customerProgramId) {
		this.customerProgramId = customerProgramId;
	}

	public String getCustomerProgramCode() {
		return customerProgramCode;
	}

	public void setCustomerProgramCode(String customerProgramCode) {
		this.customerProgramCode = customerProgramCode;
	}

	public String getCustomerProgramType() {
		return customerProgramType;
	}

	public void setCustomerProgramType(String customerProgramType) {
		this.customerProgramType = customerProgramType;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getBilledHours() {
		return billedHours;
	}

	public void setBilledHours(Integer billedHours) {
		this.billedHours = billedHours;
	}

	public String getTimesheetStatus() {
		return timesheetStatus;
	}

	public void setTimesheetStatus(String timesheetStatus) {
		this.timesheetStatus = timesheetStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTimesheetSubmittedDate() {
		return timesheetSubmittedDate;
	}

	public void setTimesheetSubmittedDate(String timesheetSubmittedDate) {
		this.timesheetSubmittedDate = timesheetSubmittedDate;
	}
	
	
}

@Embeddable
class TimesheetReportId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 900494385684694285L;

	@Column(name = "employeeId")
	private Integer employeeId;
	
	@Column(name = "timesheetDate")
	private String timesheetDate;
	
	@Column(name = "taskId")
	private Integer taskId;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getTimesheetDate() {
		return timesheetDate;
	}

	public void setTimesheetDate(String timesheetDate) {
		this.timesheetDate = timesheetDate;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + ((timesheetDate == null) ? 0 : timesheetDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimesheetReportId other = (TimesheetReportId) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (timesheetDate == null) {
			if (other.timesheetDate != null)
				return false;
		} else if (!timesheetDate.equals(other.timesheetDate))
			return false;
		return true;
	}
	
	
}