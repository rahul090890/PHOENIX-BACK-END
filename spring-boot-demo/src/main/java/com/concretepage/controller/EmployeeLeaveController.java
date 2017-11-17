package com.concretepage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concretepage.entity.Employee;
import com.concretepage.entity.EmployeeLeave;
import com.concretepage.entity.EmployeeLeaveBalance;
import com.concretepage.entity.LEAVESTATUS;
import com.concretepage.exception.HRException;
import com.concretepage.service.IEmployeeService;
import com.concretepage.service.ILeaveService;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/leave")
public class EmployeeLeaveController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ILeaveService leaveService;
	
	@Autowired
	IEmployeeService employeeService;
	
	@GetMapping("history/{employeeId}")
	public ResponseEntity<List<EmployeeLeave>> getleaveHistory(@PathVariable("employeeId") String employeeId) {
		List<EmployeeLeave> list = leaveService.leaveHistory(Integer.parseInt(employeeId));
		return new ResponseEntity<List<EmployeeLeave>>(list, HttpStatus.OK);
	}
	
	@GetMapping("balance/{employeeId}/{year}")
	public ResponseEntity<List<EmployeeLeaveBalance>> getLeaveBalances(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("year") String year
			) {
		List<EmployeeLeaveBalance> list = leaveService.getLeaveBalancesForEmployeeAndYear(Integer.parseInt(employeeId), Integer.parseInt(year));
		return new ResponseEntity<List<EmployeeLeaveBalance>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("pendingApproval/{managerId}")
	public ResponseEntity<List<EmployeeLeave>> getPendingApprovals(@PathVariable("managerId") String managerId) {
		List<EmployeeLeave> list = leaveService.pendingApprovals((Integer.parseInt(managerId)));
		return new ResponseEntity<List<EmployeeLeave>>(list, HttpStatus.OK);
	}
	
	@PostMapping("approve/{leaveId}/{managerId}/{comments}")
	public ResponseEntity<EmployeeLeave> approveLeave(
			@PathVariable("leaveId") String leaveId,
			@PathVariable("managerId") String managerId,
			@PathVariable("comments") String comments
			) throws HRException {
		log.info("Approving the leave " + leaveId);
		EmployeeLeave leave = leaveService.approveLeave(Integer.parseInt(leaveId), comments);
		log.info("Approved the leave " + leave.toString());
		return new ResponseEntity<EmployeeLeave>(leave, HttpStatus.OK);
	}
	
	@PostMapping("reject/{leaveId}/{managerId}/{comments}")
	public ResponseEntity<EmployeeLeave> rejectLeave(
			@PathVariable("leaveId") String leaveId,
			@PathVariable("managerId") String managerId,
			@PathVariable("comments") String comments
			)throws HRException {
		log.info("Rejecting the leave " + leaveId);
		EmployeeLeave leave = leaveService.rejectLeave(Integer.parseInt(leaveId), comments);
		log.info("Rejected the leave " + leave.toString());
		return new ResponseEntity<EmployeeLeave>(leave, HttpStatus.OK);
	}
	//TODO check from and To dates validations
	@PostMapping("apply/{employeeId}/{leaveType}/{fromDate}/{toDate}/{noOfDays}/{comments}")
	public ResponseEntity<EmployeeLeave> applyLeave(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("leaveType") String leaveType,
			@PathVariable("fromDate") String fromDate, // format YYYY-MM-DD
			@PathVariable("toDate") String toDate,
			@PathVariable("noOfDays") String noOfDays,
			@PathVariable("comments") String comments
			) throws HRException {
		EmployeeLeave leave = new EmployeeLeave();
		Employee emp = employeeService.getEmployee(Integer.parseInt(employeeId));
		leave.setEmployee(emp);
		leave.setLeaveType(leaveType);
		leave.setFromDate(fromDate);
		leave.setToDate(toDate);
		leave.setNoOfDays(Integer.parseInt(noOfDays));
		leave.setFromHours(0);
		leave.setToHours(0);
		leave.setComments(comments);
		leave.setLeaveStatus(LEAVESTATUS.PENDING_APPROVAL.name());
		if(null != emp.getManager()) {
			leave.setApprovalManagerId(emp.getManager().getEmployeeId());
			leave.setApprovalManagerEmail(emp.getManager().getEmailId());
		} // else set for default email id and manager id
		log.info("Applying the leave " + leave.toString() + "for the employee " + employeeId);
		leave = leaveService.applyLeave(leave);
		return new ResponseEntity<EmployeeLeave>(leave, HttpStatus.OK);
	}
	
	/**
	 * Increments leave available for all employees
	 * @param leaveType
	 * @param year
	 * @param noOfDays
	 * @return
	 * @throws HRException
	 */
	@PostMapping("creditLeavesAllEmployees/{leaveType}/{year}/{noOfDays}")
	public ResponseEntity<Void> applyLeave(
			@PathVariable("leaveType") String leaveType,
			@PathVariable("year") String year, 
			@PathVariable("noOfDays") String noOfDays
			) throws HRException {
		leaveService.incrementLeaveBalanceForAllEmployees(Integer.parseInt(year),leaveType,Integer.parseInt(noOfDays));
		
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@GetMapping("history")
	public ResponseEntity<List<EmployeeLeave>> getAllLeaveApplied(
			@RequestParam(required = true, value = "year") int year,
			@RequestParam(required = false, value = "employeeCode") String employeeCode
			) {
		List<EmployeeLeave> list = leaveService.getAllEmployeeLeaveApplied(year,employeeCode);
		return new ResponseEntity<List<EmployeeLeave>>(list, HttpStatus.OK);
	}
	

}
