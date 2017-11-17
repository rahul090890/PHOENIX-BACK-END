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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.dao.IEmployeeLeaveAppliedDao;
import com.concretepage.entity.EmployeeLeave;
import com.concretepage.entity.LEAVESTATUS;
import com.concretepage.entity.TimesheetStatus;
import com.concretepage.utils.DateUtils;

@Transactional
@Repository
public class EmployeeLeaveAppliedDao implements IEmployeeLeaveAppliedDao {

	@PersistenceContext	
	private EntityManager entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<EmployeeLeave> getEmployeeLeaveApplied(int employeeId) {
		String hql = "from EmployeeLeave as l where l.employee.employeeId = ? order by l.appliedDate ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, employeeId);
		return (List<EmployeeLeave>) query.getResultList();
		
	}

	@Override
	public List<EmployeeLeave> getEmployeeLeaveAppliedForManager(int managerId) {
		String hql = "from EmployeeLeave as l where l.approvalManagerId = ? and l.leaveStatus = ? order by l.appliedDate ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, managerId);
		query.setParameter(2, LEAVESTATUS.PENDING_APPROVAL.name());
		return (List<EmployeeLeave>) query.getResultList();
	}

	@Override
	public void applyLeave(EmployeeLeave leave) {
		entityManager.persist(leave);
	}

	@Override
	public EmployeeLeave updateLeaveStatus(EmployeeLeave leave, String Status) {
		leave.setLeaveStatus(Status);
		entityManager.persist(leave);
		return leave;
	}

	@Override
	public EmployeeLeave getEmployeeLeave(int leaveId) {
		EmployeeLeave leave = entityManager.find(EmployeeLeave.class, leaveId);
		return leave;
	}

	@Override
	public List<EmployeeLeave> getAllEmployeeLeaveApplied(int year, String employeeCode) {
	
		CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmployeeLeave> criteriaQuery = cBuilder.createQuery(EmployeeLeave.class);
		Root<EmployeeLeave> root = criteriaQuery.from(EmployeeLeave.class);
		criteriaQuery.select(root);
		
		Predicate criteria = null;
		if(year >0 ) {
			criteria = cBuilder.greaterThanOrEqualTo(root.get("fromDate"), DateUtils.firstDayOfYear(year));
		}
		
		if(null != employeeCode) {
			criteria = cBuilder.like(root.get("employee").get("employeeCode"), "%" + employeeCode + "%");
		}
			
		
		criteriaQuery.where(criteria);

		List<Order> orderList = new ArrayList();
		orderList.add(cBuilder.desc(root.get("fromDate")));
		criteriaQuery.orderBy(orderList);

		log.info("Retriving the timesheets for the year " + year );
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
