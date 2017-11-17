package com.concretepage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timesheet")
public class Timesheet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405021865016180318L;
	
	public Timesheet() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "timesheetId", updatable = false, nullable = false)
	private Integer timesheetId; 
	
	@Column(name = "weekStartDate")
	private String weekStartDate; 
	
	@Column(name = "weekEndDate")
	private String weekEndDate;
	
	@Column(name = "employeeId")
	private Integer employeeId; 
	
	@Column(name = "employeeDesination")
	private String employeeDesination;
	
	@Column(name = "employeeLocation")
	private String employeeLocation;
	
	@Column(name = "managerId")
	private Integer managerId; 
	
	@Column(name = "managerName")
	private String managerName;
	
	@Column(name = "managerEmail")
	private String managerEmail;
	
	@Column(name = "customerId")
	private Integer customerId;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "customerProgramId")
	private Integer customerProgramId;
	
	@Column(name = "customerProgramCode")
	private String customerProgramCode;
	
	@Column(name = "customerProgramType")
	private String customerProgramType;
	
	@Column(name = "departmentId")
	private String departmentId ;
	
	@Column(name = "projectId")
	private Integer projectId;
	
	@Column(name = "projectName")
	private String projectName;
	
	@Column(name = "projectType")
	private String projectType ;
	
	@Column(name = "taskid")
	private String taskid;
	
	@Column(name = "taskName")
	private String taskName;
	
	@Column(name = "timesheetdate")
	private String timesheetdate;
	
	@Column(name = "taskHours")
	private Integer taskHours;
	
	@Column(name = "timesheetStatus")
	private String timesheetStatus;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "approvedEmployeeId")
	private Integer approvedEmployeeId;
	
	@Column(name = "approvedUserName")
	private String approvedUserName; 
	
	@Column(name = "overallStatus")
	private String overallStatus;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "createdBy")
	private String createdBy; 
	
	@Column(name = "createdTimeStamp")
	private Date createdTimeStamp;
	
	@Column(name = "updatedBy")
	private String updatedBy;
	
	@Column(name = "updatedTimeStamp")
	private Date updatedTimeStamp;
	
	@Column(name = "timesheetSequence")
	private long timesheetSequence;

	public long getTimesheetSequence() {
		return timesheetSequence;
	}

	public void setTimesheetSequence(long timesheetSequence) {
		this.timesheetSequence = timesheetSequence;
	}

	public Integer getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
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

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeDesination() {
		return employeeDesination;
	}

	public void setEmployeeDesination(String employeeDesination) {
		this.employeeDesination = employeeDesination;
	}

	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
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

	public Integer getCustomerProgramId() {
		return customerProgramId;
	}

	public void setCustomerProgramId(Integer customerProgramId) {
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTimesheetdate() {
		return timesheetdate;
	}

	public void setTimesheetdate(String timesheetdate) {
		this.timesheetdate = timesheetdate;
	}

	public Integer getTaskHours() {
		return taskHours;
	}

	public void setTaskHours(Integer taskHours) {
		this.taskHours = taskHours;
	}

	public String getTimesheetStatus() {
		return timesheetStatus;
	}

	public void setTimesheetStatus(String timesheetStatus) {
		this.timesheetStatus = timesheetStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getApprovedEmployeeId() {
		return approvedEmployeeId;
	}

	public void setApprovedEmployeeId(Integer approvedEmployeeId) {
		this.approvedEmployeeId = approvedEmployeeId;
	}

	public String getApprovedUserName() {
		return approvedUserName;
	}

	public void setApprovedUserName(String approvedUserName) {
		this.approvedUserName = approvedUserName;
	}

	public String getOverallStatus() {
		return overallStatus;
	}

	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

		
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	@Override
	public String toString() {
		return "Timesheet [timesheetId=" + timesheetId + ", weekStartDate=" + weekStartDate + ", weekEndDate="
				+ weekEndDate + ", employeeId=" + employeeId + ", managerId=" + managerId + ", managerEmail="
				+ managerEmail + ", customerId=" + customerId + ", customerProgramId=" + customerProgramId
				+ ", departmentId=" + departmentId + ", projectId=" + projectId + ", taskid=" + taskid
				+ ", timesheetdate=" + timesheetdate + ", taskHours=" + taskHours + ", timesheetStatus="
				+ timesheetStatus + ", comments=" + comments + "]";
	}
	
	

}
