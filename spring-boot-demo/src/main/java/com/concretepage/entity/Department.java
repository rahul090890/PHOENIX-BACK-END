package com.concretepage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "Department")
@Cacheable
public class Department implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -542356761037403736L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "departmentId", updatable = false, nullable = false)
	private int departmentId;
	
	@Column(name = "departmentName")
	private String departmentName;
		
	@ManyToOne(cascade={CascadeType.ALL} , fetch = FetchType.EAGER )
	@JoinColumn(name="parentDepartmentId")
	private Department parentDepartment;
	
	@Column(name = "managerId")
	private Integer manager;
	
	@Column(name = "departmentCode")
	private String departmentCode;
	
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "createdTime")
	private Date createdTime;
	
	@Column(name = "updatedTime")
	private Date updatedTime;
	
	public Department() {}
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", parentDepartment="
				+ parentDepartment + ", manager=" + manager + ", departmentCode=" + departmentCode + ", startDate="
				+ startDate + ", endDate=" + endDate + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime
				+ "]";
	}

	


}

