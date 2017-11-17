package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.concretepage.dao.ITimesheetDao;
import com.concretepage.entity.Timesheet;
import com.concretepage.entity.TimesheetStatus;
import com.concretepage.utils.HRUtils;

@Transactional
@Repository
public class TimesheetDaoImpl implements ITimesheetDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void createTimesheet(Timesheet timesheet) {
		entityManager.persist(timesheet);
		
	}
//str_to_date(s.weekStartDate, '%Y-%m-%d')
	@Override
	public List<Timesheet> getTimesheetsBeforeDate(Integer employeeId, String timesheetStartDate, String timesheetEndDate) {
		String hql = "FROM Timesheet as t where t.employeeId = ? and str_to_date(t.timesheetdate,'%Y-%m-%d') <= str_to_date( ? ,'%Y-%m-%d') and str_to_date(t.timesheetdate,'%Y-%m-%d') > str_to_date( ? ,'%Y-%m-%d') ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, employeeId);
		query.setParameter(2, timesheetStartDate);
		query.setParameter(3, timesheetEndDate);
		return (List<Timesheet>)query.getResultList();
	}

	@Override
	public void createTimesheets(List<Timesheet> timesheets) {
		long timsheetSequence = HRUtils.createAUniqueId(timesheets.get(0).getEmployeeId()+"");
		for(Timesheet timesheet : timesheets) {
			timesheet.setTimesheetSequence(timsheetSequence);
			log.info("Saving the timesheet for the date" + timesheet.getTimesheetdate() + "for the employee" + timesheet.getEmployeeId());
			entityManager.persist(timesheet);
		}
		
	}
	@Override
	public List<Timesheet> getTimesheetsForAWeek(Integer employeeId, String weekStartDate, String weekEndDate) {
		String hql = "FROM Timesheet as t where t.employeeId = ? and t.weekStartDate = ? and t.weekEndDate = ? ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, employeeId);
		query.setParameter(2, weekStartDate);
		query.setParameter(3, weekEndDate);
		return (List<Timesheet>)query.getResultList();
	}
	@Override
	public void approveTimesheet(Integer employeeId, String weekStartDate, String weekEndDate) {
		String hql = "update Timesheet t set t.timesheetStatus = ? where t.employeeId = ? and t.weekStartDate = ? and t.weekEndDate = ? ";
		Query query = entityManager.createNativeQuery(hql);
		query.setParameter(1, TimesheetStatus.APPROVED.name());
		query.setParameter(2, employeeId);
		query.setParameter(3, weekStartDate);
		query.setParameter(4, weekEndDate);
		
		query.executeUpdate();
		
	}
	@Override
	public void rejectTimesheet(Integer employeeId, String weekStartDate, String weekEndDate) {
		String hql = "update Timesheet t set t.timesheetStatus = ? where t.employeeId = ? and t.weekStartDate = ? and t.weekEndDate = ? ";
		Query query = entityManager.createNativeQuery(hql);
		query.setParameter(1, TimesheetStatus.REJECTED.name());
		query.setParameter(2, employeeId);
		query.setParameter(3, weekStartDate);
		query.setParameter(4, weekEndDate);
		
		query.executeUpdate();
		
	}
	@Override
	public void deleteTimesheet(Integer employeeId, String weekStartDate, String weekEndDate) {
		String hql = "delete from Timesheet t where t.employeeId = ? and t.weekStartDate = ? and t.weekEndDate = ? ";
		Query query = entityManager.createNativeQuery(hql);
		query.setParameter(1, TimesheetStatus.REJECTED.name());
		query.setParameter(2, employeeId);
		query.setParameter(3, weekStartDate);
		query.setParameter(4, weekEndDate);
		
		query.executeUpdate();
		
	}
	@Override
	public boolean isTimesheetAlreadySubmitted(Integer employeeId, String timesheetStartDate, String timesheetEndDate) {
		boolean isTimesheetAlreadySubmitted = true;
		String hql = "FROM Timesheet as t where t.employeeId = ? and t.weekStartDate = ? and t.weekEndDate = ? and t.timesheetStatus <> ? ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, employeeId);
		query.setParameter(2, timesheetStartDate);
		query.setParameter(3, timesheetEndDate);
		query.setParameter(4, TimesheetStatus.REJECTED.name());
		List<Timesheet> timesheets =  (List<Timesheet>)query.getResultList();
		if(timesheets.size() ==0)  {
			isTimesheetAlreadySubmitted = false;
		}
		
		return isTimesheetAlreadySubmitted;
	}
	@Override
	public List<Timesheet> getTimesheetsBySequence(long timesheetSequence) {
		String hql = "FROM Timesheet as t where timesheetSequence = ? ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, timesheetSequence);
		return (List<Timesheet>)query.getResultList();
	}
	

}
