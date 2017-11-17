package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Employee;
import com.concretepage.exception.HRException;

public interface IEmployeeDAO {

	List<Employee> getEmployees();
	
	List<Employee> getManagers();
	
	void update (Employee emp);
	
	void create (Employee emp);
	
	void resetPassword(int employeeId, String newPassword);
	
	void delete(Employee emp);
	
	Employee findEmployeeById(int employeeId) throws HRException;
	
	List<Integer> getReportingEmployeeIds(List<Integer> managerIds);
	
	Employee loadEmployeeByUsernameAndPassword(String username, String password);
	
	Employee findEmployeeByLoginId(String loginId);
	
	
}
