package com.concretepage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "GroupCodeValues")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class GroupCodeValues {
	
	private static final long serialVersionUID = -2862671438138322401L;
	
	public GroupCodeValues() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "groupid", updatable = false, nullable = false)
	private int groupid;
	
	@Column(name = "groupName")
	private String groupName;
	
	@Column(name = "groupKey")
	private String groupKey;
	
	@Column(name = "groupValue")
	private String groupValue;
	
	@Column(name = "active")
	private int groupStatus;
	
	
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	public String getGroupValue() {
		return groupValue;
	}
	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
	}
	public int getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(int groupStatus) {
		this.groupStatus = groupStatus;
	}
	@Override
	public String toString() {
		return "GroupCodeValues [groupid=" + groupid + ", groupName=" + groupName + ", groupKey=" + groupKey
				+ ", groupValue=" + groupValue + ", groupStatus=" + groupStatus + "]";
	}
	

}
