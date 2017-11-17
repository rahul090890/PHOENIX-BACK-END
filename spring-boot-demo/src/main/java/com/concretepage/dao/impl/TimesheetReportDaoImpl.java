package com.concretepage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.concretepage.dao.ITimesheetReportDao;
import com.concretepage.entity.TimesheetReport;
import com.concretepage.exception.HRException;

@Transactional
@Repository
public class TimesheetReportDaoImpl implements ITimesheetReportDao {

	@PersistenceContext
	private EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<TimesheetReport> getTimesheetReportForEmployee(String startDate, String endDate, String employeeCode,
			String firstName, String lastName) throws HRException {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != employeeCode) {
			Predicate emplCode = (cBuilder.equal(root.get("employeeCode"), employeeCode));
			dates = cBuilder.or(dates, emplCode);
		}
		if (null != firstName) {
			Predicate firstNameQuery = (cBuilder.like(root.get("firstName"), "%" + firstName + "%"));
			dates = cBuilder.or(dates, firstNameQuery);
		}
		if (null != lastName) {
			Predicate lastNameQuery = (cBuilder.like(root.get("lastName"), "%" + lastName + "%"));
			dates = cBuilder.or(dates, lastNameQuery);
		}
		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("firstName")));
		orderList.add(cBuilder.desc(root.get("lastName")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate + "employeeCode "
				+ employeeCode + "firstname " + firstName + "lastName" + lastName);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForDepartment(String startDate, String endDate,
			String departmentCode, String departmentName) {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != departmentCode) {
			Predicate depCode = (cBuilder.like(root.get("departmentCode"), "%" + departmentCode + "%"));
			dates = cBuilder.and(dates, depCode);
		}

		if (null != departmentName) {
			Predicate depCode = (cBuilder.like(root.get("departmentName"), "%" + departmentName + "%"));
			dates = cBuilder.and(dates, depCode);
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("departmentCode")));
		orderList.add(cBuilder.desc(root.get("departmentName")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate + "departmentCode "
				+ departmentCode + "departmentName " + departmentName);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForProject(String startDate, String endDate, String projectCode,
			String projectName, String projectType) {

		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != projectCode) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("projectCode"), "%" + projectCode + "%")));
		}
		if (null != projectName) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("projectName"), "%" + projectName + "%")));
		}
		if (null != projectType) {
			dates = cBuilder.and(dates, (cBuilder.equal(root.get("projectType"), projectType)));
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("projectCode")));
		orderList.add(cBuilder.desc(root.get("projectName")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate + "projectCode "
				+ projectCode + "projectName " + projectName);
		return entityManager.createQuery(criteriaQuery).getResultList();

	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForCustomer(String startDate, String endDate, String customerCode,
			String customerName) {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != customerCode) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("customerCode"), "%" + customerCode + "%")));
		}
		if (null != customerName) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("customerName"), "%" + customerName + "%")));
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("customerCode")));
		orderList.add(cBuilder.desc(root.get("customerName")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate + "customerCode "
				+ customerCode + "customerName " + customerName);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForCustomerProgram(String startDate, String endDate,
			String customerProgramCode, String programType) {
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != customerProgramCode) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("customerProgramCode"), "%" + customerProgramCode + "%")));
		}
		if (null != programType) {
			dates = cBuilder.and(dates, (cBuilder.equal(root.get("customerProgramType"), programType)));
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("customerProgramCode")));
		orderList.add(cBuilder.desc(root.get("customerProgramType")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate
				+ "customerProgramCode " + customerProgramCode + "programType " + programType);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForTask(String startDate, String endDate, String taskName) {

		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != taskName) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("taskName"), "%" + taskName + "%")));
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("taskName")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate + "taskName "
				+ taskName);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<TimesheetReport> getTimesheetReportsForStatus(String startDate, String endDate,
			String timesheetStatus) {

		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetReport> criteriaQuery = cBuilder.createQuery(TimesheetReport.class);
		Root<TimesheetReport> root = criteriaQuery.from(TimesheetReport.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("reportId").get("timesheetDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("reportId").get("timesheetDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);

		if (null != timesheetStatus) {
			dates = cBuilder.and(dates, (cBuilder.equal(root.get("timesheetStatus"),  timesheetStatus)));
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("reportId").get("timesheetDate")));
		orderList.add(cBuilder.desc(root.get("timesheetStatus")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate + "timesheetStatus "
				+ timesheetStatus);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
