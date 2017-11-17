package com.concretepage.controller;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.entity.Department;
import com.concretepage.entity.Employee;
import com.concretepage.service.IDepartmentService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/department")
public class DepartmentController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IDepartmentService service;
	
	@GetMapping("all")
	public ResponseEntity<List<Department>> getAllDepartments() {
		List<Department> departments =  service.getDepartments();
		return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/create/{departmentName}/{parentDepartmentId}/{managerId}")
	public ResponseEntity<Void> create(
			@PathVariable("departmentName") String departmentName,
			@PathVariable("parentDepartmentId") String parentDepartmentId,
			@PathVariable("managerId") String managerId
			) {
		Department department = new Department();
		department.setDepartmentName(departmentName);
		
		Department parentDepartement = new Department();
		parentDepartement.setDepartmentId(Integer.parseInt(parentDepartmentId));
		department.setParentDepartment(parentDepartement);
		
		department.setManager(Integer.parseInt(managerId));
		
		department.setCreatedTime(Calendar.getInstance().getTime());
		log.info("Creating the department " + department.toString());
		service.create(department);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/update/{departmentId}/{departmentName}/{parentDepartmentId}/{managerId}")
	public ResponseEntity<Void> update(
			@PathVariable("departmentId") String departmentId,
			@PathVariable("departmentName") String departmentName,
			@PathVariable("parentDepartmentId") String parentDepartmentId,
			@PathVariable("managerId") String managerId
			) {
		Department department = new Department();
		department.setDepartmentId(Integer.parseInt(departmentId));
		department.setDepartmentName(departmentName);
		
		Department parentDepartement = new Department();
		parentDepartement.setDepartmentId(Integer.parseInt(parentDepartmentId));
		department.setParentDepartment(parentDepartement);
		
		department.setManager(Integer.parseInt(managerId));
		
		department.setCreatedTime(Calendar.getInstance().getTime());
		log.info("updating the department" + department.toString());
		service.update(department);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping("delete/{departmentId}")
	public ResponseEntity<Void> delete (@PathVariable("departmentId") String departmentId) {
		
		Department department = new Department();
		department.setDepartmentId(Integer.parseInt(departmentId));
		log.info("deleting department " + department.toString());
		service.delete(department);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
