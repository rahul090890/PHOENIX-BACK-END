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

@Entity
@Table(name = "Role")
@Cacheable
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class , property = "@id")
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2594672225350649077L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleid", updatable = false, nullable = false)
	private int   roleid;
	
	@Column(name = "roleName")
	private String   roleName;
	
	@ManyToOne(cascade={CascadeType.ALL} , fetch = FetchType.EAGER )
	@JoinColumn(name="parentRoleId")
	//@JsonManagedReference
	//@JsonBackReference
	private Role parentRole;
	
	@Column(name = "createdTime")
	private Date   createdTime;
	
	@Column(name = "updatedTime")
	private Date   updatedTime;
	
	public Role() {}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public Role getParentRole() {
		return parentRole;
	}

	public void setParentRole(Role parentRole) {
		this.parentRole = parentRole;
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
		return "Role [roleid=" + roleid + ", roleName=" + roleName + "]";
	}
	
	
	

}
