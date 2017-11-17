package com.concretepage.controller;

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

import com.concretepage.entity.Customer;
import com.concretepage.entity.CustomerProgram;
import com.concretepage.entity.Department;
import com.concretepage.entity.Project;
import com.concretepage.entity.Task;
import com.concretepage.exception.HRException;
import com.concretepage.service.ICustomerProgramService;
import com.concretepage.service.ICustomerService;
import com.concretepage.service.IDepartmentService;
import com.concretepage.service.IProjectService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/project")
public class ProjectController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IProjectService service;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICustomerProgramService customerProgramService;
	
	@Autowired 
	private IDepartmentService departmentService;
	
	
	@GetMapping("all")
	public ResponseEntity<List<Project>> getProjects() {
		List<Project> projects = service.getProjects();
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
	@GetMapping("find/{customerId}/{departmentId}")
	public ResponseEntity<List<Project>> getTasksForCustomerAndDepartment(
			@PathVariable("customerId") String customerId,
			@PathVariable("departmentId") String departmentId
			) {
		List<Project> projects = service.getProjectForCustomerAndDepartment(Integer.parseInt(customerId), 
																	Integer.parseInt(departmentId));
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/create/{projectName}/{customerId}/{customerProgramId}/{departmentId}/{projectType}/{projectStatus}/{location}/{customerProjectCode}")
	public ResponseEntity<Void> create(
			@PathVariable("projectName") String projectName,
			@PathVariable("customerId") String customerId,
			@PathVariable("customerProgramId") String customerProgramId,
			@PathVariable("departmentId") String departmentId,
			@PathVariable("projectType") String projectType,
			@PathVariable("projectStatus") String projectStatus,
			@PathVariable("location") String location,
			@PathVariable("customerProjectCode") String customerProjectCode
			) throws HRException{
		Project project = new Project();
		project.setProjectName(projectName);
		
		Customer customer = customerService.findCustomerById(Integer.parseInt(customerId));
		if(null == customer) {
			throw new HRException("Invalid Customer" + customerId);
		}
		project.setCustomer(customer);
		
		CustomerProgram customerProgram = customerProgramService.getCustomerProgramById(Integer.parseInt(customerProgramId));
		project.setCustomerProgram(customerProgram);
		
		Department department = departmentService.getDepartmentById(Integer.parseInt(departmentId));
		project.setDepartment(department);
		
		project.setProjectType(projectType);
		project.setProjectStatus(projectStatus);
		project.setLocation(location);
		project.setCustomerProjectCode(customerProjectCode);
		log.info("Creating the project " + project.toString() + " with department " + departmentId + "for the customer Program " + customerProgramId + " for the customer " + customerId);
		service.create(project);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/update/{projectid}/{projectName}/{customerId}/{customerProgramId}/{departmentId}/{projectType}/{projectStatus}/{location}/{customerProjectCode}")
	public ResponseEntity<Void> update(
			@PathVariable("projectid") String projectid,
			@PathVariable("projectName") String projectName,
			@PathVariable("customerId") String customerId,
			@PathVariable("customerProgramId") String customerProgramId,
			@PathVariable("departmentId") String departmentId,
			@PathVariable("projectType") String projectType,
			@PathVariable("projectStatus") String projectStatus,
			@PathVariable("location") String location,
			@PathVariable("customerProjectCode") String customerProjectCode
			) {
		Project project = new Project();
		project.setProjectid(Integer.parseInt(projectid));
		project.setProjectName(projectName);
	
		Customer customer = new Customer();
		customer.setCustomerId(Integer.parseInt(customerId));
		project.setCustomer(customer);
		
		CustomerProgram cp = new CustomerProgram();
		cp.setCustomerProgramId(Integer.parseInt(customerProgramId));
		project.setCustomerProgram(cp);
		
		Department dept = new Department();
		dept.setDepartmentId(Integer.parseInt(departmentId));
		project.setDepartment(dept);
				
		project.setProjectType(projectType);
		project.setProjectStatus(projectStatus);
		project.setLocation(location);
		project.setCustomerProjectCode(customerProjectCode);
		log.info("Updating the project " + project.toString() + " with department " + departmentId + "for the customer Program " + customerProgramId + " for the customer " + customerId);
		service.update(project);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping("delete/{projectid}")
	public ResponseEntity<Void> delete (@PathVariable("projectid") String projectid) {
		log.info("Deleting the project " + projectid);
		service.delete(Integer.parseInt(projectid));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
