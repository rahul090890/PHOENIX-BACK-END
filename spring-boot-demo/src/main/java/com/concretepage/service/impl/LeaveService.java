package com.concretepage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IEmployeeDAO;
import com.concretepage.dao.IEmployeeLeaveAppliedDao;
import com.concretepage.dao.IEmployeeLeaveBalanceDao;
import com.concretepage.email.builder.HREmailSender;
import com.concretepage.entity.Employee;
import com.concretepage.entity.EmployeeLeave;
import com.concretepage.entity.EmployeeLeaveBalance;
import com.concretepage.entity.LEAVESTATUS;
import com.concretepage.exception.HRException;
import com.concretepage.service.ILeaveService;
import com.concretepage.utils.DateUtils;

@Service
public class LeaveService implements ILeaveService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IEmployeeLeaveAppliedDao leaveDao;

	@Autowired
	IEmployeeDAO employeeDao;

	@Autowired
	IEmployeeLeaveBalanceDao leaveBalanceDao;

	@Autowired
	HREmailSender emailSender;

	@Override
	public List<EmployeeLeave> leaveHistory(int employeeId) {
		return leaveDao.getEmployeeLeaveApplied(employeeId);
	}

	@Override
	public List<EmployeeLeave> pendingApprovals(int managerId) {
		return leaveDao.getEmployeeLeaveAppliedForManager(managerId);
	}

	@Override
	public EmployeeLeave applyLeave(EmployeeLeave leave) throws HRException {
		Employee emp = employeeDao.findEmployeeById(leave.getEmployee().getEmployeeId());
		leave.setEmployee(emp);
		// check if TO Date is greater than From Date
		Date toDate = DateUtils.String_YYYY_MM_DD_ToDate(leave.getToDate());
		Date fromDate = DateUtils.String_YYYY_MM_DD_ToDate(leave.getFromDate());
		if (toDate.before(fromDate)) {
			throw new HRException("From Date is more than To Date");
		}
		// check if balance is enough for applying leave
		if (!hasEmployeeEnoughBalance(leave)) {
			throw new HRException("Employee does not have enough Leave Balance");
		}
		// though # days are set from request, calculate to be sure
		// leave.setNoOfDays(DateUtils.daysBetweenDates(fromDate, toDate));
		try {
			emailSender.prepareAndSend(leave.getEmployee().getEmailId(), "LeaveApplied",
					prepareDynamicFieldsForLeaveEmails(leave), "leaveApplied");
		} catch (Exception e) {
			log.error("Error in sending email " + e);
		}
		leaveDao.applyLeave(leave);
		leaveBalanceDao.incrementLeaveBalance(leave.getEmployee().getEmployeeId(), DateUtils.getCurrentYear(),
				leave.getLeaveType(), leave.getNoOfDays(), 0);
		return leave;
	}

	@Override
	public EmployeeLeave approveLeave(int leaveId, String comments) throws HRException {
		EmployeeLeave leave = leaveDao.getEmployeeLeave(leaveId);
		// check if balance is enough for applying leave
		if (!hasEmployeeEnoughBalance(leave)) {
			throw new HRException("Employee does not have enough Leave Balance");
		}

		// pending approval to approval - decrease PA and eligibility
		if (leave.getLeaveStatus().equals(LEAVESTATUS.PENDING_APPROVAL.name())) {
			leaveBalanceDao.incrementLeaveBalance(leave.getEmployee().getEmployeeId(), DateUtils.getCurrentYear(),
					leave.getLeaveType(), -leave.getNoOfDays(), -leave.getNoOfDays());
			// rejected to approval - only decrease eligibility
		} else if (leave.getLeaveStatus().equals(LEAVESTATUS.REJECTED.name())) {
			leaveBalanceDao.incrementLeaveBalance(leave.getEmployee().getEmployeeId(), DateUtils.getCurrentYear(),
					leave.getLeaveType(), 0, -leave.getNoOfDays());
		}
		try {
			emailSender.prepareAndSend(leave.getEmployee().getEmailId(), "LeaveApplied",
					prepareDynamicFieldsForLeaveEmails(leave), "leaveApproved");
		} catch (Exception e) {
			log.error("Error in sending email " + e);
		}
		leave.setComments(comments);
		return leaveDao.updateLeaveStatus(leave, LEAVESTATUS.APPROVED.name());
	}

	@Override
	public EmployeeLeave rejectLeave(int leaveId, String comments) throws HRException {
		EmployeeLeave leave = leaveDao.getEmployeeLeave(leaveId);
		// can only reject, if it is pending approval. Approved leaves can not
		// be rejected
		// pending approval to reject -- decrease pending approval
		if (leave.getLeaveStatus().equals(LEAVESTATUS.PENDING_APPROVAL.name())) {
			leaveBalanceDao.incrementLeaveBalance(leave.getEmployee().getEmployeeId(), DateUtils.getCurrentYear(),
					leave.getLeaveType(), -leave.getNoOfDays(), 0);
		} else {
			throw new HRException("Leave is already is approved, can not be rejected");
		}
		emailSender.prepareAndSend(leave.getEmployee().getEmailId(), "LeaveApplied",
				prepareDynamicFieldsForLeaveEmails(leave), "leaveRejected");
		leave.setComments(comments);
		return leaveDao.updateLeaveStatus(leave, LEAVESTATUS.REJECTED.name());
	}

	@Override
	public List<EmployeeLeaveBalance> getLeaveBalancesForEmployeeAndYear(Integer employeeId, Integer year) {
		return leaveBalanceDao.getLeaveBalance(employeeId, year);
	}

	/**
	 * private void sendEmailforLeaveApplied(EmployeeLeave leave) {
	 * Map<String,String> dynamicFields = new HashMap<String,String>();
	 * dynamicFields.put("leaveFromDate", leave.getFromDate());
	 * dynamicFields.put("leaveToDate", leave.getToDate());
	 * dynamicFields.put("managerName",
	 * leave.getEmployee().getManager().getFirstName() + " " +
	 * leave.getEmployee().getManager().getLastName());
	 * 
	 * emailSender.prepareAndSend(leave.getEmployee().getEmailId(),
	 * "LeaveApplied", dynamicFields, "leaveApplied"); }
	 **/

	private Map<String, String> prepareDynamicFieldsForLeaveEmails(EmployeeLeave leave) {
		Map<String, String> dynamicFields = new HashMap<String, String>();
		dynamicFields.put("leaveFromDate", leave.getFromDate());
		dynamicFields.put("leaveToDate", leave.getToDate());
		dynamicFields.put("managerName",
				leave.getEmployee().getManager().getFirstName() + " " + leave.getEmployee().getManager().getLastName());

		return dynamicFields;
	}

	private Boolean hasEmployeeEnoughBalance(EmployeeLeave employeeLeave) {
		Boolean hasEmployeeBalance = true;
		Date leaveToDate = DateUtils.String_YYYY_MM_DD_ToDate(employeeLeave.getToDate());
		List<EmployeeLeaveBalance> leaveBalance = leaveBalanceDao
				.getLeaveBalance(employeeLeave.getEmployee().getEmployeeId(), leaveToDate.getYear());
		for (EmployeeLeaveBalance balance : leaveBalance) {
			if (balance.getLeaveType().equals(employeeLeave.getLeaveType())) {
				if (balance.getEligible() < employeeLeave.getNoOfDays()) {
					hasEmployeeBalance = false;
				}
			}
		}

		return hasEmployeeBalance;
	}

	@Override
	public void incrementLeaveBalanceForAllEmployees(Integer year, String leaveType, Integer noofDayEligible) {
		leaveBalanceDao.incrementLeaveBalanceForAllEmployees(year, leaveType, noofDayEligible);

	}

	@Override
	public List<EmployeeLeave> getAllEmployeeLeaveApplied(int year,String employeeCode) {
		return leaveDao.getAllEmployeeLeaveApplied(year,employeeCode);
	}

}
