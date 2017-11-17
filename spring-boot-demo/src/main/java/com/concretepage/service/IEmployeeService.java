package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Employee;
import com.concretepage.exception.HRException;

public interface IEmployeeService {
	
	List<Employee> getEmployees();
	
	List<Employee> getManagers();
	
	void update(Employee employee) throws HRException ;
	
	void create (Employee employee) throws HRException ;
	
	void delete(Employee employee);
	
	String resetPassword(String employeeId);
	
	Employee getEmployee(int employeeId) throws HRException;
	
	Employee autheticateUser(String username, String password);
	
	Employee getEmployeeByLoginId(String loginId);

}
