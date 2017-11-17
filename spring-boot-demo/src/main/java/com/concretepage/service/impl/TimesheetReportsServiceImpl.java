package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.ITimesheetReportDao;
import com.concretepage.entity.TimesheetReport;
import com.concretepage.exception.HRException;
import com.concretepage.service.ITimesheetReportsService;

@Service
public class TimesheetReportsServiceImpl implements ITimesheetReportsService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ITimesheetReportDao dao;

	@Override
	public List<TimesheetReport> getTimesheetReportForEmployee(String startDate, String endDate, String employeeCode,
			String firstName, String lastName) throws HRException {
		return dao.getTimesheetReportForEmployee(startDate, endDate, employeeCode, firstName, lastName);
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForDepartment(String startDate, String endDate,
			String departmentCode, String departmentName) {
		return dao.getTimesheetReportsForDepartment(startDate, endDate, departmentCode, departmentName);
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForProject(String startDate, String endDate, String projectCode,
			String projectName, String projectType) {
		return dao.getTimesheetReportsForProject(startDate, endDate, projectCode, projectName, projectType);
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForCustomer(String startDate, String endDate, String customerCode,
			String customerName) {
		return dao.getTimesheetReportsForCustomer(startDate, endDate, customerCode, customerName);
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForCustomerProgram(String startDate, String endDate,
			String customerProgramCode, String programType) {
		return dao.getTimesheetReportsForCustomerProgram(startDate, endDate, customerProgramCode, programType);
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForTask(String startDate, String endDate, String taskName) {
		return dao.getTimesheetReportsForTask(startDate, endDate, taskName);
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForStatus(String startDate, String endDate,
			String timesheetStatus) {
		return dao.getTimesheetReportsForStatus(startDate, endDate, timesheetStatus);
	}

}
