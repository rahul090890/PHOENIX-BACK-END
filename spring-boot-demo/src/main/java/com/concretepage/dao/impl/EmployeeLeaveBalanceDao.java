package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.dao.IEmployeeLeaveBalanceDao;
import com.concretepage.entity.EmployeeLeaveBalance;

@Transactional
@Repository
public class EmployeeLeaveBalanceDao implements IEmployeeLeaveBalanceDao {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	
	@Override
	public int incrementLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDayPendingApproval, Integer noofDayEligible) {
		String hql = "update EmployeeLeaveBalance b set b.pendingApproval = b.pendingApproval + ? , b.eligible = eligible + ?  where b.employeeId = ? and b.annualYear = ? and b.leaveType = ?";
		Query q = entityManager.createQuery(hql);
		q.setParameter(1, noOfDayPendingApproval);
		q.setParameter(2, noofDayEligible);
		q.setParameter(3, employeeId);
		q.setParameter(4, year);
		q.setParameter(5, leaveType);
		return q.executeUpdate();
		
	}
	
	@Override
	public int incrementPendingApprovalLeaveBalance(Integer employeeId, Integer year,
			String leaveType, Integer noOfDay) {
		
		String hql = "update EmployeeLeaveBalance b set b.pendingApproval = b.pendingApproval + ? where b.employeeId = ? and b.annualYear = ? and b.leaveType = ?";
		Query q = entityManager.createQuery(hql);
		q.setParameter(1, noOfDay);
		q.setParameter(2, employeeId);
		q.setParameter(3, year);
		q.setParameter(4, leaveType);
		return q.executeUpdate();
		
	}

	@Override
	public int decreaseEligibleLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDay) {
		String hql = "update EmployeeLeaveBalance b set b.pendingApproval = b.pendingApproval - ? , b.eligible = eligible - ?  where b.employeeId = ? and b.annualYear = ? and b.leaveType = ?";
		Query q = entityManager.createQuery(hql);
		q.setParameter(1, noOfDay);
		q.setParameter(2, noOfDay);
		q.setParameter(3, employeeId);
		q.setParameter(4, year);
		q.setParameter(5, leaveType);
		return q.executeUpdate();
		
	}

	@Override
	public List<EmployeeLeaveBalance> getLeaveBalance(Integer employeeId, Integer year) {
		String hql = "FROM EmployeeLeaveBalance as bal where bal.employeeId = ? and bal.annualYear = ?   ORDER BY bal.leaveBalanceId";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, employeeId);
		query.setParameter(2, year);
		
		return (List<EmployeeLeaveBalance>) query.getResultList();
	}

	@Override
	public int insertLeaveBalance(Integer employeeId, Integer year, String leaveType, Integer noOfDayPending, Integer noOfDayEligible) {
	
		/*String hql = "insert into EmployeeLeaveBalance (employeeId,annualYear,leaveType,pendingApproval,eligible) values (?,?,?,?,?)";
		Query q = entityManager.createQuery(hql);
		q.setParameter(1, employeeId);
		q.setParameter(2, year);
		q.setParameter(3, leaveType);
		q.setParameter(4, noOfDayPending);
		q.setParameter(5, noOfDayEligible);
		return q.executeUpdate();*/
		
		EmployeeLeaveBalance leaveBalance = new EmployeeLeaveBalance();
		leaveBalance.setEmployeeId(employeeId);
		leaveBalance.setAnnualYear(year);
		leaveBalance.setLeaveType(leaveType);
		leaveBalance.setPendingApproval(noOfDayPending);
		leaveBalance.setEligible(noOfDayEligible);
		
		entityManager.persist(leaveBalance);
		
		return 1;
	}
	
	@Override
	public int incrementLeaveBalanceForAllEmployees( Integer year, String leaveType, Integer noofDayEligible) {
		String hql = "update EmployeeLeaveBalance b set b.eligible = eligible + ?  where  b.annualYear = ? and b.leaveType = ?";
		Query q = entityManager.createQuery(hql);
		q.setParameter(1, noofDayEligible);
		q.setParameter(2, year);
		q.setParameter(3, leaveType);
		return q.executeUpdate();
		
	}

}
