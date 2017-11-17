package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IDepartmentDAO;
import com.concretepage.entity.Department;
import com.concretepage.exception.HRException;
import com.concretepage.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDepartmentDAO dao;
	
	@Override
	public List<Department> getDepartments() {
		return dao.getDepartments();
	}

	@Override
	public void create(Department department) {
		dao.create(department);
		
	}

	@Override
	public void update(Department department) {
		dao.update(department);
		
	}

	@Override
	public void delete(Department department) {
		dao.delete(department);
		
	}

	@Override
	public Department getDepartmentById(Integer departmentId) throws  HRException {
		return dao.findDeparmentById(departmentId);
	}

}
