package com.concretepage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timesheetSummary")
public class TimesheetSummary implements Serializable {

	@Override
	public String toString() {
		return "TimesheetSummary [id=" + id + ", weekEndDate=" + weekEndDate + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", totalHours=" + totalHours + ", createdDate=" + createdDate + "]";
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6912413407090547119L;

	public TimesheetSummary() {
	}

	@EmbeddedId
	private TimesheetSummaryId id;

	public TimesheetSummaryId getId() {
		return id;
	}

	public void setId(TimesheetSummaryId id) {
		this.id = id;
	}

	@Column(name = "weekEndDate")
	private String weekEndDate;
	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "totalhours")
	private Float totalHours;

	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "employeeCode")
	private String employeeCode;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "employeeType")
	private String employeeType;
	
	@Column(name = "employementStatus")
	private String employementStatus;
	
	@Column(name = "managerFirstName")
	private String managerFirstName;
	
	@Column(name = "managerLastName")
	private String managerLastName;
	
	@Column(name = "departmentCode")
	private String departmentCode;
	
	@Column(name = "departmentName")
	private String departmentName;

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

	public Float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Float totalHours) {
		this.totalHours = totalHours;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployementStatus() {
		return employementStatus;
	}

	public void setEmployementStatus(String employementStatus) {
		this.employementStatus = employementStatus;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
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
	

}

@Embeddable
class TimesheetSummaryId implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7366848450288199887L;
	@Column(name = "employeeId")
	private int employeeId;
	@Column(name = "weekStartDate")
	private String weekStartDate;

	@Column(name = "timesheetStatus")
	private String timesheetStatus;
	
	@Column(name = "timesheetSequence")
	private long timesheetSequence;

	public long getTimesheetSequence() {
		return timesheetSequence;
	}

	public void setTimesheetSequence(long timesheetSequence) {
		this.timesheetSequence = timesheetSequence;
	}

	public String getTimesheetStatus() {
		return timesheetStatus;
	}

	public void setTimesheetStatus(String timesheetStatus) {
		this.timesheetStatus = timesheetStatus;
	}

	public TimesheetSummaryId() {
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + (int) (timesheetSequence ^ (timesheetSequence >>> 32));
		result = prime * result + ((timesheetStatus == null) ? 0 : timesheetStatus.hashCode());
		result = prime * result + ((weekStartDate == null) ? 0 : weekStartDate.hashCode());
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
		TimesheetSummaryId other = (TimesheetSummaryId) obj;
		if (employeeId != other.employeeId)
			return false;
		if (timesheetSequence != other.timesheetSequence)
			return false;
		if (timesheetStatus == null) {
			if (other.timesheetStatus != null)
				return false;
		} else if (!timesheetStatus.equals(other.timesheetStatus))
			return false;
		if (weekStartDate == null) {
			if (other.weekStartDate != null)
				return false;
		} else if (!weekStartDate.equals(other.weekStartDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimesheetSummaryId [employeeId=" + employeeId + ", weekStartDate=" + weekStartDate
				+ ", timesheetStatus=" + timesheetStatus + ", timesheetSequence=" + timesheetSequence + "]";
	}
	

}
