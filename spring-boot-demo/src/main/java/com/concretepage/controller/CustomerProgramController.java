package com.concretepage.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.entity.Customer;
import com.concretepage.entity.CustomerProgram;
import com.concretepage.entity.Project;
import com.concretepage.exception.HRException;
import com.concretepage.service.ICustomerProgramService;
import com.concretepage.service.IProjectService;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/customerProgram")

public class CustomerProgramController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICustomerProgramService service;
	
	@Autowired
	private IProjectService projectService;
	
	
	@GetMapping("all")
	public ResponseEntity<List<CustomerProgram>> getCustomerProgram() {
		List<CustomerProgram> customerPrograms = service.getAllCustomerPrograms();
		return new ResponseEntity<List<CustomerProgram>>(customerPrograms, HttpStatus.OK);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/create/{customerId}/{customerProgamCode}/{customerProgramType}/{delimitedProjectIds}")
	public ResponseEntity<Void> create(
			@PathVariable("customerId") String customerId,
			@PathVariable("customerProgamCode") String customerProgamCode,
			@PathVariable("customerProgramType") String customerProgramType,
			@PathVariable("delimitedProjectIds") String delimitedProjectIds
			
			) throws HRException {
		CustomerProgram program = new CustomerProgram();
		Customer c = new Customer();
		c.setCustomerId(Integer.parseInt(customerId));
		program.setCustomer(c);
		program.setCustomerProgramCode(customerProgamCode);
		program.setCustomerProgramType(customerProgramType);
		String [] projectIds = delimitedProjectIds.split(",");
		Set<Project> projects = new HashSet<Project>();
		for(String projectId : projectIds) {
			projects.add(projectService.findProjectById(Integer.parseInt(projectId)));
			log.info("Added Project Id " + projectId + "to customer Project Code " + customerProgamCode);
		}
		program.setProjects(projects);
		log.info("Creating the customer program " + program.toString());
		service.addCustomerProgram(program);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/update/{customerProgramId}/{customerId}/{customerProgamCode}/{customerProgramType}/{delimitedProjectIds}")
	public ResponseEntity<Void> update(
			@PathVariable("customerProgramId") String customerProgramId,
			@PathVariable("customerId") String customerId,
			@PathVariable("customerProgamCode") String customerProgamCode,
			@PathVariable("customerProgramType") String customerProgramType,
			@PathVariable("delimitedProjectIds") String delimitedProjectIds
			) throws HRException {
		CustomerProgram program = new CustomerProgram();
		program.setCustomerProgramId(Integer.parseInt(customerProgramId));
		Customer c = new Customer();
		c.setCustomerId(Integer.parseInt(customerId));
		program.setCustomer(c);
		program.setCustomerProgramCode(customerProgamCode);
		program.setCustomerProgramType(customerProgramType);
		String [] projectIds = delimitedProjectIds.split(",");
		Set<Project> projects = new HashSet<Project>();
		for(String projectId : projectIds) {
			projects.add(projectService.findProjectById(Integer.parseInt(projectId)));
			log.info("Added Project Id " + projectId + "to customer Project Code " + customerProgamCode);
		}
		program.setProjects(projects);
		service.updateCustomerProgram(program);
		log.info("Creating the customer program " + program.toString() +" with customer id " + customerId );
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}


}
