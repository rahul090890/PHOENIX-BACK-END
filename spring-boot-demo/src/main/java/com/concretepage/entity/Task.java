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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Task")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Task implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -7544315399161997052L;
	
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taskId", updatable = false, nullable = false)
	private int   taskId;
	
	@Column(name = "taskName")
	private String   taskName;
	
	@ManyToOne(cascade={CascadeType.ALL} , fetch = FetchType.EAGER )
	@JoinColumn(name="departmentId")
	private Department department;
	
	@ManyToOne(cascade={CascadeType.ALL} , fetch = FetchType.EAGER )
	@JoinColumn(name="customerId")
	@JsonManagedReference
	private Customer   customer;
	
	@Column(name = "createdTime")
	private Date   createdTime;
	
	@Column(name = "updatedTime")
	private Date   updatedTime;
	
	public Task() {}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + "]";
	} 
	
	

}
