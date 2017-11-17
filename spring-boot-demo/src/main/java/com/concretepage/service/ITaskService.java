package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Task;

public interface ITaskService {
	
	List<Task> getTasks();
	
	void create(Task task);
	
	void update(Task task);
	
	void delete(Task task);
	
	List<Task> getTasksForCustomerAndDepartment(Integer customerId, Integer departmentId);
	
	Task findTaskById(Integer taskid);

}
