package com.concretepage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IEmployeeDAO;
import com.concretepage.dao.IEmployeeLeaveBalanceDao;
import com.concretepage.dao.IRoleDAO;
import com.concretepage.entity.Employee;
import com.concretepage.entity.LeaveMaster;
import com.concretepage.entity.Role;
import com.concretepage.exception.HRException;
import com.concretepage.utils.DateUtils;

@Service
public class EmployeeService implements IEmployeeService {
	
	private final String DEFAULT_PASSWORD = "Phe0nix@HYD"; 
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IEmployeeDAO dao;
	
	@Autowired
	private IReferenceDataService refDataService;
	
	@Autowired
	private IEmployeeLeaveBalanceDao leaveBalanceDao;
	
	@Autowired
	private IRoleDAO roleDao;
	
	@Override
	public List<Employee> getEmployees() {
		return dao.getEmployees();
	}

	@Override
	public void update(Employee employee) {
		Role role = roleDao.getRoleById(employee.getRole().getRoleid());
		employee.setRole(role);
		dao.update(employee);
	}

	@Override
	public void create(Employee employee) throws HRException  {
		Role role = roleDao.getRoleById(employee.getRole().getRoleid());
		employee.setRole(role);
		dao.create(employee);
		applyLeaveBalances(employee);
	}

	@Override
	public String resetPassword(String employeeId) {
		dao.resetPassword(Integer.parseInt(employeeId), DEFAULT_PASSWORD);
		return DEFAULT_PASSWORD;
		
	}
	
	private void applyLeaveBalances(Employee employee) throws HRException {
		
		List<LeaveMaster> leaveMasters = refDataService.getLeaveMasterForRoleName(employee.getRole().getRoleName());
		if(null == leaveMasters) {
			throw new HRException("Unable to find leave master for the role " + employee.getRole().getRoleName() + " for the employee " + employee.getLoginId());
		}
		for(LeaveMaster l :  leaveMasters) {
			log.info("Adding leave balance for the employee " + employee.getEmployeeCode() + l.toString() );
			leaveBalanceDao.insertLeaveBalance(employee.getEmployeeId(), DateUtils.getCurrentYear(), l.getLeaveType(), 0, 
					calculateProratedLeaveEligibility(employee.getDateOfJoin(),l.getAnnualEligibility())
					);
		}
	}
	
	private Integer calculateProratedLeaveEligibility(String doj, Integer annualEligibility) {
		Calendar lastDayOftheYear = Calendar.getInstance();
		//lastDayOftheYear.set(DateUtils.getCurrentYear(),12,30,0,0);
		lastDayOftheYear.set(Calendar.YEAR,DateUtils.getCurrentYear());
		lastDayOftheYear.set(Calendar.MONTH, 12);
		lastDayOftheYear.set(Calendar.DAY_OF_MONTH, 31);
		
		
		Date dateOfJoin = DateUtils.String_YYYY_MM_DD_ToDate(doj);
		System.out.println(lastDayOftheYear.toString());
		
		int remainingDays = (int) TimeUnit.DAYS.convert((lastDayOftheYear.getTimeInMillis() - dateOfJoin.getTime()),TimeUnit.MILLISECONDS);
		return (int)Math.round((annualEligibility)*remainingDays)/365;
		
	}

	@Override
	public void delete(Employee employee) {
		dao.delete(employee);
		
	}

	@Override
	public List<Employee> getManagers() {
		return dao.getManagers();
	}

	@Override
	public Employee getEmployee(int employeeId) throws HRException {
		return dao.findEmployeeById(employeeId);
	}

	@Override
	public Employee autheticateUser(String username, String password) {
		return dao.loadEmployeeByUsernameAndPassword(username, password);
	}

	@Override
	public Employee getEmployeeByLoginId(String loginId) {
		return dao.findEmployeeByLoginId(loginId);
	}

}
