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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concretepage.entity.TimesheetReport;
import com.concretepage.exception.HRException;
import com.concretepage.service.ITimesheetReportsService;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/timesheetReport")
public class TImesheetReportController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITimesheetReportsService service;

	@GetMapping("byEmployee")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByEmployee(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "employeeCode") String employeeCode,
			@RequestParam(required = false, value = "firstName") String firstName,
			@RequestParam(required = false, value = "lastName") String lastName) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportForEmployee(startDate, endDate, employeeCode,
				firstName, lastName);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}

	@GetMapping("byDepartment")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByDepartment(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "departmentCode") String departmentCode,
			@RequestParam(required = false, value = "departmentName") String departmentName) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportsForDepartment(startDate, endDate, departmentCode,
				departmentName);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}

	@GetMapping("byProject")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByProject(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "projectCode") String projectCode,
			@RequestParam(required = false, value = "projectName") String projectName,
			@RequestParam(required = false, value = "projectType") String projectType) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportsForProject(startDate, endDate, projectCode,
				projectName, projectType);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}

	@GetMapping("byCustomer")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByCustomer(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "customerCode") String customerCode,
			@RequestParam(required = false, value = "customerName") String customerName

	) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportsForCustomer(startDate, endDate, customerCode, customerName);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}
	
	@GetMapping("byCustomerProgram")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByCustomerProgram(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "customerProgramCode") String customerProgramCode,
			@RequestParam(required = false, value = "customerProgramType") String customerProgramType
			) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportsForCustomerProgram(startDate, endDate, customerProgramCode, customerProgramType);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}
	
	@GetMapping("byTask")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByTask(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "taskName") String taskName
			) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportsForTask(startDate, endDate, taskName);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}
	
	@GetMapping("byStatus")
	public ResponseEntity<List<TimesheetReport>> getTimesheetSummaryByStatus(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = false, value = "status") String status
			) throws HRException {
		List<TimesheetReport> timesheets = service.getTimesheetReportsForTask(startDate, endDate, status);
		return new ResponseEntity<List<TimesheetReport>>(timesheets, HttpStatus.OK);

	}

}
