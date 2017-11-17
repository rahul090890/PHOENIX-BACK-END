package com.concretepage.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class TimesheetDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3800124261709634751L;
	
	
	private String  employeeId;
	private String employeeName;
	private String employeeDesignation;
	private String managerId;
	private String managerName;
	private String managerEmailId;
	private String startDateOfWeek;
	private String endDateOfWeek;
	//Map<TaskId --> List of TimesheetEntryDTO
	private Map<String, List<TimesheetEntryDTO>> timesheets = new HashMap<String,List<TimesheetEntryDTO>>();
	private String comments;
	
	
	public TimesheetDTO() {
		
	}
	
	
	public String getEmployeeId() {
		return employeeId;
	}

	@Override
	public String toString() {
		return "TimesheetDTO [employeeId=" + employeeId +  ", comments=" + comments
				+ "]";
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public Map<String, List<TimesheetEntryDTO>> getTimesheets() {
		return timesheets;
	}
	public void setTimesheets (Map<String, List<TimesheetEntryDTO>> timesheetEntry) {
		this.timesheets = timesheetEntry;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void addTimesheetForDate(String dateString,List<TimesheetEntryDTO> timesheetEntry) {
		this.timesheets.put(dateString, timesheetEntry);
	}


	public String getStartDateOfWeek() {
		return startDateOfWeek;
	}


	public void setStartDateOfWeek(String startDateOfWeek) {
		this.startDateOfWeek = startDateOfWeek;
	}


	public String getEndDateOfWeek() {
		return endDateOfWeek;
	}


	public void setEndDateOfWeek(String endDateOfWeek) {
		this.endDateOfWeek = endDateOfWeek;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeDesignation() {
		return employeeDesignation;
	}


	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}


	public String getManagerId() {
		return managerId;
	}


	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}


	public String getManagerName() {
		return managerName;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public String getManagerEmailId() {
		return managerEmailId;
	}


	public void setManagerEmailId(String managerEmailId) {
		this.managerEmailId = managerEmailId;
	}
	
	
	
}
