package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.ITaskDAO;
import com.concretepage.entity.Task;
import com.concretepage.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private ITaskDAO dao;
	
	@Override
	public List<Task> getTasks() {
		return dao.getTasks();
	}

	@Override
	public void create(Task task) {
		dao.create(task);
	}

	@Override
	public void update(Task task) {
		dao.update(task);
		
	}

	@Override
	public void delete(Task task) {
	dao.delete(task);
		
	}

	@Override
	public List<Task> getTasksForCustomerAndDepartment(Integer customerId, Integer departmentId) {
		return dao.findTasksForCustomerAndDepartment(customerId, departmentId);
	}

	@Override
	public Task findTaskById(Integer taskid) {
		return dao.findTaskById(taskid);
	}

}
