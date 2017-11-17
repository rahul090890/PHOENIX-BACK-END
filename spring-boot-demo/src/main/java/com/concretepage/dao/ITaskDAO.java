package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Task;

public interface ITaskDAO {

	List<Task> getTasks();
	List<Task> findTasksForCustomerAndDepartment(Integer customerId, Integer departmentId);
	void update(Task task);
	void create(Task task);
	void delete(Task task);
	Task findTaskById(Integer taskId);
}
