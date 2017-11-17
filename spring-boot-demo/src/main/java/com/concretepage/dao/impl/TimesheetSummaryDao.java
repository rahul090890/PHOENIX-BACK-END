package com.concretepage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.concretepage.dao.ITimesheetSummaryDao;
import com.concretepage.entity.TimesheetReport;
import com.concretepage.entity.TimesheetStatus;
import com.concretepage.entity.TimesheetSummary;

@Transactional
@Repository
public class TimesheetSummaryDao implements ITimesheetSummaryDao {

	@PersistenceContext
	private EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<TimesheetSummary> getTimesheetSummary(Integer employeeId, String startDate, String endDate) {
		String hql = "from TimesheetSummary as s where s.id.employeeId = ? and str_to_date(s.id.weekStartDate, '%Y-%m-%d') >= str_to_date( ? ,'%Y-%m-%d')  and str_to_date(s.id.weekStartDate, '%Y-%m-%d') < str_to_date( ? ,'%Y-%m-%d') ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, employeeId);
		query.setParameter(2, startDate);
		query.setParameter(3, endDate);

		return (List<TimesheetSummary>) query.getResultList();
	}

	@Override
	public List<TimesheetSummary> getPendingTimesheetSummary(List<Integer> employeeIds) {
		String hql = "from TimesheetSummary as s where s.id.employeeId IN :employeeList and  s.id.timesheetStatus = :timesheetStatus order by s.id.employeeId ";
		Query query = entityManager.createQuery(hql);
		query.setParameter("employeeList", employeeIds);
		query.setParameter("timesheetStatus", TimesheetStatus.DRAFT.name());

		return (List<TimesheetSummary>) query.getResultList();
	}

	@Override
	public List<TimesheetSummary> getTimesheetsLessByHours(String startDate, String endDate, int lessByHours,
			String departmentCode, String employeeType) {
		
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TimesheetSummary> criteriaQuery = cBuilder.createQuery(TimesheetSummary.class);
		Root<TimesheetSummary> root = criteriaQuery.from(TimesheetSummary.class);
		criteriaQuery.select(root);

		Predicate fromDate = cBuilder.greaterThanOrEqualTo(root.get("id").get("weekStartDate"), startDate);
		Predicate toDate = cBuilder.lessThanOrEqualTo(root.get("id").get("weekStartDate"), endDate);
		Predicate dates = cBuilder.and(fromDate, toDate);
		dates = cBuilder.and(dates,(cBuilder.notEqual(root.get("id").get("timesheetStatus"), TimesheetStatus.REJECTED.name())));
		//less than passed hours
		dates = cBuilder.and(dates,(cBuilder.lessThan(root.get("totalHours"), lessByHours)));
		
		if (null != departmentCode) {
			dates = cBuilder.and(dates, (cBuilder.like(root.get("departmentCode"), "%" + departmentCode + "%")));
		}
		if (null != employeeType) {
			dates = cBuilder.and(dates, (cBuilder.equal(root.get("employeeType"), employeeType)));
		}

		criteriaQuery.where(dates);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("id").get("weekStartDate")));
		orderList.add(cBuilder.desc(root.get("departmentCode")));
		orderList.add(cBuilder.desc(root.get("employeeCode")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the start date" + startDate + "endDate " + endDate
				+ "departmentCode " + departmentCode + "employeeType " + employeeType + " less hours by " + lessByHours);
		return entityManager.createQuery(criteriaQuery).getResultList();
		
	}

	

}
