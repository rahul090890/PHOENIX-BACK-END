package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Department;
import com.concretepage.exception.HRException;

public interface IDepartmentService {

	List<Department> getDepartments();
	
	void create(Department department);
	
	void update(Department department);
	
	void delete(Department department);
	
	Department getDepartmentById(Integer departmentId) throws  HRException;
}
