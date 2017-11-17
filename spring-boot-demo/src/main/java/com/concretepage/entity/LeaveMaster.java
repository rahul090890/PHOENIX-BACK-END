package com.concretepage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LeaveMaster")
public class LeaveMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8383662069017277508L;
	
	public LeaveMaster() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leaveMasterId", updatable = false, nullable = false)
	private Integer leaveMasterId;
	
	@Column(name = "roleId")
	private Integer roleId;
	
	@Column(name = "roleName")
	private String roleName;
	
	@Column(name = "leaveType")
	private String leaveType;
	
	@Column(name = "annualEligibility")
	private Integer annualEligibility;

	public Integer getLeaveMasterId() {
		return leaveMasterId;
	}

	public void setLeaveMasterId(Integer leaveMasterId) {
		this.leaveMasterId = leaveMasterId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getAnnualEligibility() {
		return annualEligibility;
	}

	public void setAnnualEligibility(Integer annualEligibility) {
		this.annualEligibility = annualEligibility;
	}

	@Override
	public String toString() {
		return "LeaveMaster [leaveMasterId=" + leaveMasterId + ", roleId=" + roleId + ", roleName=" + roleName
				+ ", leaveType=" + leaveType + ", annualEligibility=" + annualEligibility + "]";
	}
	
	
	

}
