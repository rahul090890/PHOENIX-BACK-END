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
import com.concretepage.entity.Department;
import com.concretepage.entity.Role;
import com.concretepage.entity.Task;
import com.concretepage.service.ITaskService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/task")
public class TaskController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ITaskService service;

	@GetMapping("all")
	public ResponseEntity<List<Task>> getTasks() {
		List<Task> tasks = service.getTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("find/{customerId}/{departmentId}")
	public ResponseEntity<List<Task>> getTasksForCustomerAndDepartment(
			@PathVariable("customerId") String customerId,
			@PathVariable("departmentId") String departmentId
			) {
		List<Task> tasks = service.getTasksForCustomerAndDepartment(Integer.parseInt(customerId), 
																	Integer.parseInt(departmentId));
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("/create/{taskName}/{departmentId}/{customerId}")
	public ResponseEntity<Void> create(@PathVariable("taskName") String taskName,
			@PathVariable("departmentId") String departmentId, @PathVariable("customerId") String customerId) {

		Task task = new Task();
		task.setTaskName(taskName);
		
		Department dept = new Department();
		dept.setDepartmentId(Integer.parseInt(departmentId));
		task.setDepartment(dept);
		
		Customer customer = new Customer();
		customer.setCustomerId(Integer.parseInt(customerId));
		task.setCustomer(customer);
		log.info("Creating the Task " + task.toString() + "for the customer " + customerId + "for the department " + departmentId);
		service.create(task);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("/update/{taskId}/{taskName}/{departmentId}/{customerId}")
	public ResponseEntity<Void> update (@PathVariable("taskId") String taskId, @PathVariable("taskName") String taskName,
			@PathVariable("departmentId") String departmentId, @PathVariable("customerId") String customerId) {

		Task task = new Task();
		task.setTaskId(Integer.parseInt(taskId));
		task.setTaskName(taskName);
		
		Department dept = new Department();
		dept.setDepartmentId(Integer.parseInt(departmentId));
		task.setDepartment(dept);
		
		Customer customer = new Customer();
		customer.setCustomerId(Integer.parseInt(customerId));
		task.setCustomer(customer);
		log.info("Updating the Task " + task.toString() + "for the customer " + customerId + "for the department " + departmentId);
		service.update(task);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping("delete/{taskId}")
	public ResponseEntity<Void> delete (@PathVariable("taskId") String taskId) {
		
		Task task = new Task();
		task.setTaskId(Integer.parseInt(taskId));
		log.info("Deleting the task " + taskId);
		service.delete(task);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
