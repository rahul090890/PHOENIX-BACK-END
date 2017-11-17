package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IProjectDAO;
import com.concretepage.entity.Project;
import com.concretepage.entity.Task;
import com.concretepage.service.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IProjectDAO dao;
	@Override
	public List<Project> getProjects() {
		return dao.getProjects();
	}
	@Override
	public void create(Project project) {
		dao.create(project);
		
	}
	@Override
	public void update(Project project) {
		dao.update(project);
		
	}
	@Override
	public void delete(Integer projectId) {
		dao.delete(projectId);
		
	}
	@Override
	public Project findProjectById(Integer projectId) {
		return dao.findProjectById(projectId);
	}
	@Override
	public List<Project> getProjectForCustomerAndDepartment(Integer customerId,
			Integer departmentId) {
		
			return dao.findProjectForCustomerAndDepartment(customerId, departmentId);
		
	}

}
