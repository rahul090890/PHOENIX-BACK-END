package com.concretepage.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "Employee")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class , property = "@id")

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7478294171859629122L;

	public Employee() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeId", updatable = false, nullable = false)
	private int employeeId;
	
	@NotNull
	@Column(name = "firstName")
	private String firstName;
		
	@Column(name = "lastName")
	private String lastName;
	
	@NotNull
	@Column(name = "emailId")
	private String emailId;
	
	@NotNull
	@Column(name = "loginId")
	private String loginId;
	
	@NotNull
	@Column(name = "loginPassword")
	private String loginPassword;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="managerId")
	//@JsonManagedReference
	//@JsonBackReference
	private Employee manager;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "designation")
	private String designation;
	
	@NotNull
	@Column(name = "employeeType")
	private String employeeType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="departmentId")
	//@JsonManagedReference
	//@JsonBackReference
	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="roleId")
	private Role role;
	
	@Column(name = "employeeCode")
	private String employeeCode;
	
	@Transient
	private String departmentName;
	
	@NotNull
	@Column(name = "employementStatus")
	private String employementStatus;
	
	@NotNull
	@Column(name = "dateOfJoin")
	private String dateOfJoin;
	
	@Column(name = "createdTime")
	private Date createdTime; 
	
	@Column(name = "updatedTime")
	private Date updatedTime;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getEmployementStatus() {
		return employementStatus;
	}
	public void setEmployementStatus(String employementStatus) {
		this.employementStatus = employementStatus;
	}
	
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getDepartmentName() {
		return department.getDepartmentName();
	}
		
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
			
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", loginId=" + loginId + ", address=" + address + ", designation="
				+ designation + ", employeeType=" + employeeType + ", departmentName=" + departmentName
				+ ", employementStatus=" + employementStatus + ", dateOfJoin=" + dateOfJoin + "]";
	}
	
	
}
