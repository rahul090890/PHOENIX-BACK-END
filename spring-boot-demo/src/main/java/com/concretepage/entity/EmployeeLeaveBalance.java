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
@Table(name = "EmployeeLeaveBalance")
public class EmployeeLeaveBalance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5047061810871618544L;
	
	public EmployeeLeaveBalance() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leaveBalanceId", updatable = false, nullable = false)
	private Integer leaveBalanceId;
	
	@Column(name = "employeeId")
	private int employeeId;
	
	@Column(name = "annualYear")
	private Integer annualYear;
	
	@Column(name = "leaveType")
	private String leaveType;
	
	@Column(name = "pendingApproval")
	private Integer pendingApproval;
	
	@Column(name = "eligible")
	private Integer eligible;
	
	@Column(name = "updatedTime")
	private Date updatedTime;
	
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	@Column(name = "updatedUser")
	private String updatedUser;
	
		

	public Integer getLeaveBalanceId() {
		return leaveBalanceId;
	}

	public void setLeaveBalanceId(Integer leaveBalanceId) {
		this.leaveBalanceId = leaveBalanceId;
	}

	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getAnnualYear() {
		return annualYear;
	}

	public void setAnnualYear(Integer annualYear) {
		this.annualYear = annualYear;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	
	public Integer getPendingApproval() {
		return pendingApproval;
	}

	public void setPendingApproval(Integer pendingApproval) {
		this.pendingApproval = pendingApproval;
	}

	public Integer getEligible() {
		return eligible;
	}

	public void setEligible(Integer eligible) {
		this.eligible = eligible;
	}

	@Override
	public String toString() {
		return "EmployeeLeaveBalance [leaveBalanceId=" + leaveBalanceId + ", employeeId=" + employeeId + ", annualYear="
				+ annualYear + ", leaveType=" + leaveType + ", pendingApproval=" + pendingApproval + ", eligible="
				+ eligible + "]";
	}
	
	

}
