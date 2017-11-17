package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.EmployeeLeaveBalance;

public interface IEmployeeLeaveBalanceDao {
	
	int incrementPendingApprovalLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDay);
	
	int decreaseEligibleLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDay);
	
	List<EmployeeLeaveBalance> getLeaveBalance(Integer employeeId, Integer year);
	
	int insertLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDayPending, Integer noOfDayEligible);
	
	int incrementLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDayPendingApproval, Integer noofDayEligible);
	
	public int incrementLeaveBalanceForAllEmployees( Integer year, String leaveType, Integer noofDayEligible);
	
	
}
