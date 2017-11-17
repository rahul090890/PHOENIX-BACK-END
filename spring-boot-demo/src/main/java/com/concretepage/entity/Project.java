package com.concretepage.entity;

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
import javax.persistence.Transient;

@Entity
@Table(name = "Project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "projectid", updatable = false, nullable = false)
	private int   projectid;
	
	@Column(name = "projectName")
	private String   projectName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customerId")	
	private Customer customer;
	
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name="customerProgramId")
	@Transient
	private CustomerProgram customerProgram;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="departmentId")
	private Department department;
		
	@Column(name = "projectType")
	private String   projectType;
	
	@Column(name = "projectStatus")
	private String   projectStatus;
	
	@Column(name = "createdTime")
	private Date   createdTime;
	
	@Column(name = "updatedTime")
	private Date   updatedTime;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "customerProjectCode")
	private String customerProjectCode;
	
	public Project() {}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerProgram getCustomerProgram() {
		return customerProgram;
	}

	public void setCustomerProgram(CustomerProgram customerProgram) {
		this.customerProgram = customerProgram;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

	public String getCustomerProjectCode() {
		return customerProjectCode;
	}

	public void setCustomerProjectCode(String customerProjectCode) {
		this.customerProjectCode = customerProjectCode;
	}

	@Override
	public String toString() {
		return "Project [projectid=" + projectid + ", projectName=" + projectName + ", projectType=" + projectType
				+ ", projectStatus=" + projectStatus + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projectid;
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
		Project other = (Project) obj;
		if (projectid != other.projectid)
			return false;
		return true;
	}
	
	

}
