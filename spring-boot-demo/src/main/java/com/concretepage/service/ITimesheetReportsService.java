package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.TimesheetReport;
import com.concretepage.exception.HRException;

public interface ITimesheetReportsService {
	List<TimesheetReport> getTimesheetReportForEmployee(String startDate, String endDate, String employeeCode,
			String firstName, String lastName) throws HRException;

	List<TimesheetReport> getTimesheetReportsForDepartment(String startDate, String endDate, String departmentCode,
			String departmentName);

	List<TimesheetReport> getTimesheetReportsForProject(String startDate, String endDate, String projectCode,
			String projectName, String projectType);

	List<TimesheetReport> getTimesheetReportsForCustomer(String startDate, String endDate, String customerCode,
			String customerName);

	List<TimesheetReport> getTimesheetReportsForCustomerProgram(String startDate, String endDate,
			String customerProgramCode, String programType);

	List<TimesheetReport> getTimesheetReportsForTask(String startDate, String endDate, String taskName);
	
	List<TimesheetReport> getTimesheetReportsForStatus(String startDate, String endDate,String timesheetStatus);
}
