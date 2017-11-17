package com.concretepage.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class TimesheetEntryDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2419185755309198092L;
	
		
	private String customerId;
	private String customerName;
	
	private String customerProgramId;
	private String customerProgramCode;
	private String customerProgramType;
	
	private String departmentId;
	
	private String projectId;
	private String projectName;
	private String projectType;
	
	private String taskId;
	private String taskName;
	private int hours;
	private String date;
	private String timesheetStatus;
	private String timesheetDate;
	
	public TimesheetEntryDTO() {}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerProgramId() {
		return customerProgramId;
	}
	public void setCustomerProgramId(String customerProgramId) {
		this.customerProgramId = customerProgramId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
	public String getTimesheetStatus() {
		return timesheetStatus;
	}

	public void setTimesheetStatus(String timesheetStatus) {
		this.timesheetStatus = timesheetStatus;
	}

	
	public String getTimesheetDate() {
		return timesheetDate;
	}

	public void setTimesheetDate(String timesheetDate) {
		this.timesheetDate = timesheetDate;
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

	@Override
	public String toString() {
		return "TimesheetEntryDTO [customerId=" + customerId + ", customerName=" + customerName + ", customerProgramId="
				+ customerProgramId + ", departmentId=" + departmentId + ", projectId=" + projectId + ", taskId="
				+ taskId + ", taskName=" + taskName + ", hours=" + hours + ", date=" + date + ", timesheetStatus="
				+ timesheetStatus + "]";
	}

	
	
	

}
