package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Project;

public interface IProjectDAO {

	List<Project> getProjects();
	
	List<Project> findProjectByName(String projectName);
	
	Project findProjectById(Integer projectId);
	
	void update(Project project);
	
	void create(Project project);
	
	void delete(Integer projectId);

	List<Project> findProjectForCustomerAndDepartment(Integer customerId,
			Integer departmentId);
}
