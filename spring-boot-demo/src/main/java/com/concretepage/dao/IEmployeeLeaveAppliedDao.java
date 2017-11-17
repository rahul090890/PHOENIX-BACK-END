package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.EmployeeLeave;

public interface IEmployeeLeaveAppliedDao {

	List<EmployeeLeave> getEmployeeLeaveApplied(int employeeId);
	
	List<EmployeeLeave> getEmployeeLeaveAppliedForManager(int managerId);
	
	void applyLeave(final EmployeeLeave leave);
	
	EmployeeLeave updateLeaveStatus(EmployeeLeave leave, String Status);
	
	EmployeeLeave getEmployeeLeave(int leaveId);
	
	List<EmployeeLeave> getAllEmployeeLeaveApplied(int year, String employeeCode);
	
}
