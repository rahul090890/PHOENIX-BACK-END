package com.concretepage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concretepage.dto.TimesheetDTO;
import com.concretepage.dto.TimesheetEntryDTO;
import com.concretepage.entity.Customer;
import com.concretepage.entity.CustomerProgram;
import com.concretepage.entity.Department;
import com.concretepage.entity.Employee;
import com.concretepage.entity.Project;
import com.concretepage.entity.Timesheet;
import com.concretepage.entity.TimesheetStatus;
import com.concretepage.entity.TimesheetSummary;
import com.concretepage.exception.HRException;
import com.concretepage.service.ICustomerProgramService;
import com.concretepage.service.ICustomerService;
import com.concretepage.service.IDepartmentService;
import com.concretepage.service.IEmployeeService;
import com.concretepage.service.IProjectService;
import com.concretepage.service.ITimesheetService;
import com.concretepage.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/timesheet")
public class TimesheetController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ITimesheetService timesheetService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICustomerProgramService customerProgramService;
	

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IDepartmentService departmentService;
	

	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("save/{json}")
	public ResponseEntity<List<TimesheetSummary>> saveTimesheet(@PathVariable("json") String json) throws HRException {
		log.info("Input JSON for  creating timesheet " + json);
		ObjectMapper mapper = new ObjectMapper();
		TimesheetDTO dto = null;
		try {
			 dto = mapper.readValue(json, TimesheetDTO.class);
		} catch (IOException e) {
			System.out.println("Error in parsing Input JSON");
			e.printStackTrace();
			throw new HRException("Unable to save timesheet...." + json);
		}
		
		List<Timesheet> timesheets = toTimesheet(dto);
		
		timesheetService.saveTimesheet(timesheets);
		List<TimesheetSummary> timesheetSummary = timesheetService.getTimesheetSummary(Integer.parseInt(dto.getEmployeeId()), DateUtils.startDateOfCurrentMonthAsString(), DateUtils.lastDateOfCurrentMonthAsString());
		log.info("Successfully saved timesheet " + dto.getEmployeeId() + "for the starting week " + dto.getStartDateOfWeek());	
		return new ResponseEntity<List<TimesheetSummary>>(timesheetSummary,HttpStatus.OK);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@PostMapping("update/{json}")
	public ResponseEntity<List<TimesheetSummary>> updateTimesheet(@PathVariable("json") String json) throws HRException {
		log.info("Input JSON for  updating timesheet " + json);
		ObjectMapper mapper = new ObjectMapper();
		TimesheetDTO dto = null;
		try {
			 dto = mapper.readValue(json, TimesheetDTO.class);
		} catch (IOException e) {
			System.out.println("Error in parsing Input JSON");
			e.printStackTrace();
			throw new HRException("Unable to save timesheet...." + json);
		}
		
		List<Timesheet> timesheets = toTimesheet(dto);
		timesheetService.updateTimesheet(timesheets);
		log.info("Successfully updated timesheet " + dto.getEmployeeId() + "for the starting week " + dto.getStartDateOfWeek());
		List<TimesheetSummary> timesheetSummary = timesheetService.getTimesheetSummary(Integer.parseInt(dto.getEmployeeId()), DateUtils.startDateOfCurrentMonthAsString(), DateUtils.lastDateOfCurrentMonthAsString());
			
		return new ResponseEntity<List<TimesheetSummary>>(timesheetSummary,HttpStatus.OK);
	}
	
		
	
	@GetMapping("summary/{employeeId}/{startDate}/{endDate}")
	public ResponseEntity<List<TimesheetSummary>> getTimesheetSummary(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate
			) {
		List<TimesheetSummary> timesheetSummary = timesheetService.getTimesheetSummary(Integer.parseInt(employeeId), startDate, endDate);
		return new ResponseEntity<List<TimesheetSummary>>(timesheetSummary,HttpStatus.OK);
		
	}
	
	@GetMapping("summaryLessByHours")
	public ResponseEntity<List<TimesheetSummary>> getTimesheetLessThanHours(
			@RequestParam(required = true, value = "startDate") String startDate,
			@RequestParam(required = true, value = "endDate") String endDate,
			@RequestParam(required = true, value = "lessByHours") int lessByHours,
			@RequestParam(required = false, value = "departmentCode") String departmentCode,
			@RequestParam(required = false, value = "employeeType") String employeeType
			) throws HRException {
		if(lessByHours <= 0) {
			throw new HRException(" Billed Hours should be more than zero");
		}
		List<TimesheetSummary> timesheetSummary = timesheetService.getTimesheetsLessByHours(startDate, endDate, lessByHours, departmentCode, employeeType);
		return new ResponseEntity<List<TimesheetSummary>>(timesheetSummary,HttpStatus.OK);
		
	}
	
	

	@GetMapping("details/{employeeId}/{weekstartDate}/{weekendDate}")
	public ResponseEntity<TimesheetDTO> getTimesheetDetails(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("weekstartDate") String weekstartDate,
			@PathVariable("weekendDate") String weekendDate
			) {
		TimesheetDTO dto = timesheetService.getTimesheetDetails(Integer.parseInt(employeeId), weekstartDate, weekendDate);
		return new ResponseEntity<TimesheetDTO>(dto,HttpStatus.OK);
		
	}
	
	@GetMapping("detailsBySequence/{timesheetSequence}")
	public ResponseEntity<TimesheetDTO> getTimesheetDetails(
			@PathVariable("timesheetSequence") String timesheetSequence
			) {
		TimesheetDTO dto = timesheetService.getTimesheetDetails(Long.parseLong(timesheetSequence));
		return new ResponseEntity<TimesheetDTO>(dto,HttpStatus.OK);
		
	}
	
	
	@GetMapping("timesheetsToApprove/{managerId}")
	public ResponseEntity<List<TimesheetSummary>> getTimesheetsToApprove(
			@PathVariable("managerId") String managerId
				) {
		log.info("Getting the timesheets to approve for " + managerId);
		List<TimesheetSummary> timesheetSummary = timesheetService.getTimesheetsForApproval(Integer.parseInt(managerId));
		return new ResponseEntity<List<TimesheetSummary>>(timesheetSummary,HttpStatus.OK);
		
	}
	
	@PostMapping("approve/{employeeId}/{weekstartDate}/{weekendDate}")
	public ResponseEntity<Void> approveTimesheet(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("weekstartDate") String weekStartDate,
			@PathVariable("weekendDate") String weekEndDate
			) {
		log.info("Approving the timesheet for the employee " + employeeId + " for the starting week " + weekStartDate);
		timesheetService.approveTimesheet(Integer.parseInt(employeeId), weekStartDate, weekEndDate);
		log.info(" Successfully Approved the timesheet for the employee " + employeeId + " for the starting week " + weekStartDate);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PostMapping("reject/{employeeId}/{weekstartDate}/{weekendDate}")
	public ResponseEntity<Void> rejectTimesheet(
			@PathVariable("employeeId") String employeeId,
			@PathVariable("weekstartDate") String weekStartDate,
			@PathVariable("weekendDate") String weekEndDate
			) {
		log.info("Rejecting the timesheet for the employee " + employeeId + "starting week " + weekStartDate);
		timesheetService.rejectTimesheet(Integer.parseInt(employeeId), weekStartDate, weekEndDate);
		log.info("Rejected the timesheet for the employee " + employeeId + "starting week " + weekStartDate);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	
	private List<Timesheet> toTimesheet(TimesheetDTO dto) throws HRException {
		Employee employee = employeeService.getEmployee(Integer.parseInt(dto.getEmployeeId()));
		String comments = dto.getComments();
		List<Timesheet> timesheets = new ArrayList<Timesheet>();
		Date currentTime = Calendar.getInstance().getTime();
		Map<String, List<TimesheetEntryDTO>> timesheetEntries = dto.getTimesheets();
		for (Map.Entry<String, List<TimesheetEntryDTO>> eachTask : timesheetEntries.entrySet()) {
			String taskId = eachTask.getKey();
			List<TimesheetEntryDTO> listOfEntries = eachTask.getValue();
			for (TimesheetEntryDTO entryDTO : listOfEntries) {
				Timesheet timesheet = createTimesheet(entryDTO);
				timesheet.setTaskid(taskId);
				timesheet.setTimesheetdate(entryDTO.getTimesheetDate());
				timesheet.setEmployeeId(employee.getEmployeeId());
				timesheet.setEmployeeDesination(employee.getDepartmentName());
				if(null != employee.getManager()) {
					timesheet.setManagerId(employee.getManager().getEmployeeId());
					timesheet.setManagerName(
							employee.getManager().getFirstName() + "-" + employee.getManager().getLastName());
					timesheet.setManagerEmail(employee.getManager().getEmailId());
					
				}
				timesheet.setWeekStartDate(dto.getStartDateOfWeek());
				timesheet.setWeekEndDate(dto.getEndDateOfWeek());
				timesheet.setCreatedBy(employee.getEmployeeId() + "");
				timesheet.setCreatedTimeStamp(currentTime);
				timesheet.setTimesheetStatus(TimesheetStatus.DRAFT.name());
				timesheet.setComments(comments);
				log.info("Timesheet is added" + timesheet.toString());
				timesheets.add(timesheet);
			}
		}

		return timesheets;
	}

	
	private Timesheet createTimesheet(TimesheetEntryDTO entryDTO) throws HRException {
		Timesheet timesheet = new Timesheet();
		
		timesheet.setCustomerId(Integer.parseInt(entryDTO.getCustomerId()));
		Customer customer = customerService.findCustomerById(timesheet.getCustomerId());
		timesheet.setCustomerName(customer.getCustomerName());
		
		timesheet.setCustomerProgramId(Integer.parseInt(entryDTO.getCustomerProgramId()));
		CustomerProgram customerProgram = customerProgramService.getCustomerProgramById(timesheet.getCustomerProgramId());
		timesheet.setCustomerProgramCode(customerProgram.getCustomerProgramCode());
		timesheet.setCustomerProgramType(customerProgram.getCustomerProgramType());
		Department department = departmentService.getDepartmentById(Integer.parseInt((entryDTO.getDepartmentId())));
		timesheet.setDepartmentId(department.getDepartmentCode());
		
		timesheet.setProjectId(Integer.parseInt(entryDTO.getProjectId()));
		Project project = projectService.findProjectById(timesheet.getProjectId());
		timesheet.setProjectName(project.getProjectName());
		timesheet.setProjectType(project.getProjectType());
		
		timesheet.setTaskid(entryDTO.getTaskId());
		timesheet.setTaskName(entryDTO.getTaskName());
		timesheet.setTaskHours(entryDTO.getHours());
		timesheet.setTimesheetdate(entryDTO.getTimesheetDate());

		return timesheet;

	}
	/**
	private TimesheetDTO createTimesheetDTO() {
		TimesheetDTO dto = new TimesheetDTO();
		Map<String, TimesheetEntryDTO> maps = new HashMap<String, TimesheetEntryDTO>();

		TimesheetEntryDTO entry2 = new TimesheetEntryDTO();
		entry2.setCustomerId("1");
		entry2.setCustomerProgramId("2");
		entry2.setDepartmentId("3");
		entry2.setProjectId("4");
		entry2.setTaskId("Task-6");
		entry2.setTaskName("Development");
		entry2.setHours(4);

		TimesheetEntryDTO entry1 = new TimesheetEntryDTO();
		entry1.setCustomerId("1");
		entry1.setCustomerProgramId("2");
		entry1.setDepartmentId("3");
		entry1.setProjectId("4");
		entry1.setTaskId("3");
		entry1.setTaskName("Development-1");
		entry1.setHours(5);

		maps.put("2017-01-01", entry2);
		maps.put("2017-01-02", entry1);

		dto.setEmployeeId("3");
		dto.setComments("testDTO");
		dto.addTimesheetForDate("Task-6", maps);
		dto.setStartDateOfWeek("2017-01-01");
		dto.setEndDateOfWeek("2017-01-07");

		return dto;
	}

	@GetMapping("marshel")
	public ResponseEntity<TimesheetDTO> getTimesheetDTO() {
		return new ResponseEntity<TimesheetDTO>(createTimesheetDTO(), HttpStatus.OK);
	}

	@PostMapping("unmarshel/{json}")
	public ResponseEntity<Void> processJSON(@PathVariable("json") String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			TimesheetDTO dto = mapper.readValue(json, TimesheetDTO.class);
			System.out.println(dto.toString());
			Map<String, TimesheetEntryDTO> maps = dto.getTimesheets().get("Task-6");
			for (Map.Entry<String, TimesheetEntryDTO> entry : maps.entrySet()) {
				System.out.println("Key is" + entry.getKey());
				System.out.println("Value is " + entry.getValue().toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	**/
}
