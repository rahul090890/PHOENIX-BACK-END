package com.concretepage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IEmployeeDAO;
import com.concretepage.dao.ITimesheetDao;
import com.concretepage.dao.ITimesheetSummaryDao;
import com.concretepage.dto.TimesheetDTO;
import com.concretepage.dto.TimesheetEntryDTO;
import com.concretepage.entity.Timesheet;
import com.concretepage.entity.TimesheetSummary;
import com.concretepage.exception.HRException;
import com.concretepage.service.ITimesheetService;
import com.concretepage.utils.DateUtils;

@Service
public class TimesheetServiceImpl implements ITimesheetService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ITimesheetDao timesheetDao;

	@Autowired
	ITimesheetSummaryDao summaryDao;

	@Autowired
	IEmployeeDAO employeeDao;

	@Override
	public void saveTimesheet(List<Timesheet> timesheets) throws HRException {
		Timesheet temptimesheet = timesheets.get(0);
		Boolean isTimesheetAlreadySubmitted =  timesheetDao.isTimesheetAlreadySubmitted(temptimesheet.getEmployeeId(),
				temptimesheet.getWeekStartDate(), temptimesheet.getWeekEndDate());
		if (isTimesheetAlreadySubmitted) {
			throw new HRException(" Timesheet is already submitted for the week starting " + temptimesheet.getWeekStartDate());
		}
		timesheetDao.createTimesheets(timesheets);
	}

	@Override
	public void updateTimesheet(List<Timesheet> timesheets) throws HRException {
		Timesheet temptimesheet = timesheets.get(0);
		log.info("Deleting the timesheet for the employee " + temptimesheet.getEmployeeId() + "for starting week"
				+ temptimesheet.getWeekStartDate());
		timesheetDao.deleteTimesheet(temptimesheet.getEmployeeId(), temptimesheet.getWeekStartDate(),
				temptimesheet.getWeekEndDate());
		saveTimesheet(timesheets);
	}

	@Override
	public List<TimesheetSummary> getTimesheetSummary(Integer employeeId, String startDate, String endDate) {
		return summaryDao.getTimesheetSummary(employeeId, startDate, endDate);
	}

	@Override
	public TimesheetDTO getTimesheetDetails(Integer employeeId, String weekStartDate, String weekEndDate) {
		List<Timesheet> timesheets = timesheetDao.getTimesheetsForAWeek(employeeId, weekStartDate, weekEndDate);
		log.info("The number of the timesheets retrived for employee " + employeeId + " for the week starting " + weekStartDate + " are " + timesheets.size());
		return createTimesheetDTO(timesheets);
	}

	@Override
	public List<TimesheetSummary> getTimesheetsForApproval(Integer managerId) {
		List<Integer> managerIdsAsList = new ArrayList<Integer>();
		managerIdsAsList.add(managerId);
		List<Integer> reportingEmployeeIds = employeeDao.getReportingEmployeeIds(managerIdsAsList);

		return summaryDao.getPendingTimesheetSummary(reportingEmployeeIds);
	}

	@Override
	public void approveTimesheet(Integer employeeId, String weekStartDate, String weekEndDate) {
		timesheetDao.approveTimesheet(employeeId, weekStartDate, weekEndDate);

	}

	@Override
	public void rejectTimesheet(Integer employeeId, String weekStartDate, String weekEndDate) {
		timesheetDao.rejectTimesheet(employeeId, weekStartDate, weekEndDate);

	}
	
	@Override
	public List<TimesheetSummary> getTimesheetsLessByHours(String startDate, String endDate, int lessByHours,
			String departmentCode, String employeeType) {
		return summaryDao.getTimesheetsLessByHours(startDate, endDate, lessByHours, departmentCode, employeeType);
	}
	

	private TimesheetDTO createTimesheetDTO(List<Timesheet> listOftimesheets) {

		TimesheetDTO dto = new TimesheetDTO();
		// this created to sort the TimesheetEntryDTO as per the date String for
		// each task id
		Map<String, Map<String, TimesheetEntryDTO>> timesheetsByTaskIds = new HashMap<String, Map<String, TimesheetEntryDTO>>();
		// Map<String,TimesheetEntryDTO> entriesByDate = new
		// HashMap<String,TimesheetEntryDTO>();
		for (Timesheet timesheet : listOftimesheets) {
			log.info("Parsing the timesheet " + timesheet.toString());
			String taskid = timesheet.getTaskid();
			if (null == timesheetsByTaskIds.get(taskid)) {
				// add taskId map to timesheetsByTaskIds
				Map<String, TimesheetEntryDTO> entriesByDate = new TreeMap<String, TimesheetEntryDTO>();
				String timesheetDate = timesheet.getTimesheetdate();
				entriesByDate.put(timesheetDate, createTimesheetEntryDTO(timesheet));
				timesheetsByTaskIds.put(taskid, entriesByDate);
			} else {
				Map<String, TimesheetEntryDTO> entriesByDate = timesheetsByTaskIds.get(taskid);
				String timesheetDate = timesheet.getTimesheetdate();
				entriesByDate.put(timesheetDate, createTimesheetEntryDTO(timesheet));
			}
		}

		Map<String, List<TimesheetEntryDTO>> timesheetsByTaskIdsAsList = new HashMap<String, List<TimesheetEntryDTO>>();
		// convert inner map - date map into List
		for (Map.Entry<String, Map<String, TimesheetEntryDTO>> entry : timesheetsByTaskIds.entrySet()) {
			String taskid = entry.getKey();
			log.info("Iterating through task id" + taskid);
			Map<String, TimesheetEntryDTO> mapofEntriesSortedByDate = entry.getValue();
			List<TimesheetEntryDTO> listofEntries = new ArrayList<TimesheetEntryDTO>();
			for (Map.Entry<String, TimesheetEntryDTO> entriesByDate : mapofEntriesSortedByDate.entrySet()) {
				listofEntries.add(entriesByDate.getValue());
				log.info("Adding timesheetEntry " + entriesByDate.getValue().toString());
			}
			timesheetsByTaskIdsAsList.put(taskid, listofEntries);
		}
		log.info("The total number of tasks are " + timesheetsByTaskIdsAsList.size());
		dto.setTimesheets(timesheetsByTaskIdsAsList);
		
		Timesheet temp = listOftimesheets.get(0);
		dto.setEmployeeId(temp.getEmployeeId() + "");
		dto.setEmployeeDesignation(temp.getEmployeeDesination());
		dto.setManagerId(temp.getManagerId() + "");
		dto.setManagerName(temp.getManagerName());
		dto.setManagerEmailId(temp.getManagerEmail());
		dto.setStartDateOfWeek(temp.getWeekStartDate());
		dto.setEndDateOfWeek(temp.getWeekEndDate());
		dto.setComments(temp.getComments());

		return dto;
	}

	private TimesheetEntryDTO createTimesheetEntryDTO(Timesheet timesheet) {
		TimesheetEntryDTO dto = new TimesheetEntryDTO();
		dto.setCustomerId(timesheet.getCustomerId() + "");
		dto.setCustomerName(timesheet.getCustomerName());
		
		dto.setCustomerProgramId(timesheet.getCustomerProgramId() + "");
		dto.setCustomerProgramCode(timesheet.getCustomerProgramCode());
		dto.setCustomerProgramType(timesheet.getCustomerProgramType());
		
		dto.setDepartmentId(timesheet.getDepartmentId());
				
		dto.setProjectId(timesheet.getProjectId() + "");
		dto.setProjectName(timesheet.getProjectName());
		dto.setProjectType(timesheet.getProjectType());
		
		dto.setTaskName(timesheet.getTaskName());
		dto.setTaskId(timesheet.getTaskid()+ "");
		
		dto.setHours(timesheet.getTaskHours());
		dto.setTimesheetDate(timesheet.getTimesheetdate());
		//TODO it is timestamp in database, change below to parse from timestamp format
		/*if (null != timesheet.getCreatedDate() || !(timesheet.getCreatedDate().equals("")))
			dto.setDate(DateUtils.dateAsString(timesheet.getCreatedDate()));
		*/
		return dto;
	}

	@Override
	public TimesheetDTO getTimesheetDetails(long timesheetSequence) {
		return createTimesheetDTO(timesheetDao.getTimesheetsBySequence(timesheetSequence));
	}

	

}
