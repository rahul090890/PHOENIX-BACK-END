package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.EmployeeLeave;
import com.concretepage.entity.EmployeeLeaveBalance;
import com.concretepage.exception.HRException;

public interface ILeaveService {
	
	List<EmployeeLeave> leaveHistory(final int employeeId);
	
	List<EmployeeLeave> pendingApprovals(final int managerId);
	
	EmployeeLeave applyLeave(final EmployeeLeave leave) throws HRException;
	
	EmployeeLeave approveLeave(int leaveId, String comments) throws HRException;
	
	EmployeeLeave rejectLeave(int leaveId, String comments) throws HRException ;
	
	List<EmployeeLeaveBalance> getLeaveBalancesForEmployeeAndYear(Integer employeeId , Integer year);
	
	public void incrementLeaveBalanceForAllEmployees( Integer year, String leaveType, Integer noofDayEligible);
	
	public List<EmployeeLeave> getAllEmployeeLeaveApplied(int year,String employeeCode);
	

}
