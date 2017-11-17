package com.concretepage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeLeaveApplied")
public class EmployeeLeave implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6705233848266904197L;
	
	public EmployeeLeave() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leaveId", updatable = false, nullable = false)
	private int leaveId;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn(name="employeeId")
	private Employee employee;
	
	@Column(name = "leaveType")
	private String leaveType;
	
	@Column(name = "fromDate")
	private String fromDate;
	
	@Column(name = "toDate")
	private String toDate;
	
	@Column(name = "fromHours" , nullable = true)
	private Integer fromHours;
	
	@Column(name = "toHours" , nullable = true)
	private Integer toHours;
	
	@Column(name = "noOfDays")
	private int noOfDays;
	
	@Column(name = "appliedDate")
	private Date appliedDate;
	
	@Column(name = "leaveStatus")
	private String leaveStatus;
	
	@Column(name = "comments" , nullable = true)
	private String comments;
	
	@Column(name = "approvalManagerId")
	private int approvalManagerId;
	
	@Column(name = "approvalManagerEmail")
	private String approvalManagerEmail;

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Integer getFromHours() {
		return fromHours;
	}

	public void setFromHours(Integer fromHours) {
		this.fromHours = fromHours;
	}

	public Integer getToHours() {
		return toHours;
	}

	public void setToHours(Integer toHours) {
		this.toHours = toHours;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getApprovalManagerId() {
		return approvalManagerId;
	}

	public void setApprovalManagerId(int approvalManagerId) {
		this.approvalManagerId = approvalManagerId;
	}

	public String getApprovalManagerEmail() {
		return approvalManagerEmail;
	}

	public void setApprovalManagerEmail(String approvalManagerEmail) {
		this.approvalManagerEmail = approvalManagerEmail;
	}

	@Override
	public String toString() {
		return "EmployeeLeave [leaveId=" + leaveId + ", employee=" + employee + ", leaveType=" + leaveType
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", fromHours=" + fromHours + ", toHours=" + toHours
				+ ", noOfDays=" + noOfDays + ", appliedDate=" + appliedDate + ", leaveStatus=" + leaveStatus
				+ ", comments=" + comments + ", approvalManagerId=" + approvalManagerId + ", approvalManagerEmail="
				+ approvalManagerEmail + "]";
	}

	

}
