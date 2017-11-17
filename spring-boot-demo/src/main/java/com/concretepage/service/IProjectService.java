package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Project;
import com.concretepage.entity.Task;

public interface IProjectService {
	
	List<Project> getProjects();
	
	void create(Project project);
	
	void update(Project project);
	
	void delete(Integer projectId);
	
	List<Project> getProjectForCustomerAndDepartment(Integer customerId, Integer departmentId);
	
	Project findProjectById(Integer projectId);

}
