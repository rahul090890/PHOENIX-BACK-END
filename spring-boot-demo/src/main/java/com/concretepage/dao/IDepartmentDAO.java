package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Department;
import com.concretepage.exception.HRException;

public interface IDepartmentDAO {
	
	List<Department> getDepartments();
	
	List<Department> findByName(String name);
	
	void update(Department department);
	
	void create(Department department);
	
	void delete(Department department);
	
	Department findDeparmentById(Integer departmentId) throws  HRException ;

}
