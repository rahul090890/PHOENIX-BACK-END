package com.concretepage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.concretepage.email.builder.HREmailSender;
import com.concretepage.entity.Department;
import com.concretepage.entity.Employee;
import com.concretepage.entity.Role;
import com.concretepage.exception.HRException;
import com.concretepage.service.IEmployeeService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/employee")
public class EmployeeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private HREmailSender emailSender;
	
	@GetMapping("all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = service.getEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	@GetMapping("find/{employeeId}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable("employeeId") String employeeId ) throws HRException {
		Employee employee = service.getEmployee(Integer.parseInt(employeeId));
		log.info("Finding Employee " + employeeId.toString());
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping("findByloginId/{loginId:.+}")
	public ResponseEntity<Employee> getEmployeesByLoginId(@PathVariable("loginId") String loginId ) throws HRException {
		Employee employee = service.getEmployeeByLoginId(loginId);
		log.info("Finding Employee " + loginId);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@GetMapping("managers")
	public ResponseEntity<List<Employee>> getManagers (){
		List<Employee> list = service.getManagers();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("update/{employeeId}/{firstName}/{lastName}/{emailId}/{loginId}/{loginPassword}/{managerId}/{address}/{designation}/{employeeType}/{departmentId}/{employementStatus}/{dateOfJoin}/{roleId}/{employeeCode}")
	public ResponseEntity<Void> update(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName,
			@PathVariable("emailId") String emailId,
			@PathVariable("loginId") String loginId,
			@PathVariable("loginPassword") String loginPassword,
			@PathVariable("managerId") String managerId,
			@PathVariable("address") String address,
			@PathVariable("designation") String designation,
			@PathVariable("employeeType") String employeeType,
			@PathVariable("departmentId") String departmentId,
			@PathVariable("employementStatus") String employementStatus,
			@PathVariable("dateOfJoin") String dateOfJoin,
			@PathVariable("roleId") String roleId,
			@PathVariable("employeeCode") String employeeCode
			) throws HRException {
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.parseInt(employeeId));
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmailId(emailId);
		emp.setLoginId(loginId);
		emp.setLoginPassword(loginPassword);
		Employee manager = new Employee();
		manager.setEmployeeId(Integer.parseInt(managerId));
		emp.setManager(manager);
		emp.setAddress(address);
		emp.setDesignation(designation);
		emp.setEmployeeType(employeeType);
		Department dept = new Department();
		dept.setDepartmentId(Integer.parseInt(departmentId));
		emp.setDepartment(dept);
		emp.setEmployementStatus(employementStatus);
		emp.setDateOfJoin(dateOfJoin);
		Role role = new Role();
		role.setRoleid(Integer.parseInt(roleId));
		emp.setRole(role);
		emp.setEmployeeCode(employeeCode);
		log.info("updating the employee " + emp.toString());
		service.update(emp);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("create/{firstName}/{lastName}/{emailId}/{loginId}/{loginPassword}/{managerId}/{address}/{designation}/{employeeType}/{departmentId}/{employementStatus}/{dateOfJoin}/{roleId}/{employeeCode}")
	public ResponseEntity<Void> create(
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName,
			@PathVariable("emailId") String emailId,
			@PathVariable("loginId") String loginId,
			@PathVariable("loginPassword") String loginPassword,
			@PathVariable("managerId") String managerId,
			@PathVariable("address") String address,
			@PathVariable("designation") String designation,
			@PathVariable("employeeType") String employeeType,
			@PathVariable("departmentId") String departmentId,
			@PathVariable("employementStatus") String employementStatus,
			@PathVariable("dateOfJoin") String dateOfJoin,
			@PathVariable("roleId") String roleId,
			@PathVariable("employeeCode") String employeeCode
			)throws HRException {
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmailId(emailId);
		emp.setLoginId(loginId);
		emp.setLoginPassword(loginPassword);
		Employee manager = new Employee();
		manager.setEmployeeId(Integer.parseInt(managerId));
		emp.setManager(manager);
		emp.setAddress(address);
		emp.setDesignation(designation);
		emp.setEmployeeType(employeeType);
		Department dept = new Department();
		dept.setDepartmentId(Integer.parseInt(departmentId));
		emp.setDepartment(dept);
		emp.setEmployementStatus(employementStatus);
		emp.setDateOfJoin(dateOfJoin);
		Role role = new Role();
		role.setRoleid(Integer.parseInt(roleId));
		emp.setRole(role);
		emp.setEmployeeCode(employeeCode);
		log.info("Creating the employee " + emp.toString());		
		service.create(emp);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("resetPassword/{employeeId}")
	public ResponseEntity<Void> resetPassword(@PathVariable("employeeId") String employeeId) throws HRException {
		Employee employee = service.getEmployee(Integer.parseInt(employeeId));
		log.info("Resetting the password for employee " + employee.toString());
		String newPassword = service.resetPassword(employeeId);
		sendResetPasswordEmail(employee,newPassword);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping("delete/{employeeId}")
	public ResponseEntity<Void> delete (@PathVariable("employeeId") String employeeId) throws HRException {
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.parseInt(employeeId));
		log.info("Deleting the employee " + employeeId);
		service.delete(emp);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private void sendResetPasswordEmail(Employee employee ,  String newpassword)  {
		Map<String, String> dynamicFields = new HashMap<String,String>();
		dynamicFields.put("newPassword", newpassword);
		try {
		emailSender.prepareAndSend(employee.getEmailId(), "resetPassword", dynamicFields, "passwordReset");
		} catch(Exception e ) {
			log.error("Error in sending email " + e);
		}
		
	}
	
									
}
