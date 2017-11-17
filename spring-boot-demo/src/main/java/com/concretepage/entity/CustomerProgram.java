package com.concretepage.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CustomerProgramCode")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class , property = "@id")
public class CustomerProgram implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3658878811085764997L;
	
	public CustomerProgram() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerProgramId", updatable = false, nullable = false)
	private Integer customerProgramId;
	
	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@Column(name = "programCode")
	private String customerProgramCode;
	
	@Column(name = "programType")
	private String customerProgramType;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "CustomerProgramProject" ,  joinColumns = { @JoinColumn(name = "customerProgramId") }, 
	           inverseJoinColumns = { @JoinColumn(name = "projectId") })
	private Set<Project> projects;

	public Integer getCustomerProgramId() {
		return customerProgramId;
	}

	public void setCustomerProgramId(Integer customerProgramId) {
		this.customerProgramId = customerProgramId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

		
	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "CustomerProgram [customerProgramId=" + customerProgramId + ", customer=" + customer
				+ ", customerProgramCode=" + customerProgramCode + ", customerProgramType=" + customerProgramType + "]";
	}
	
	
	

}
