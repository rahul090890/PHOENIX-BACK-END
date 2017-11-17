package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.dao.ILeaveMasterDao;
import com.concretepage.entity.LeaveMaster;

@Transactional
@Repository
public class LeaveMasterDao implements ILeaveMasterDao {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<LeaveMaster> getLeaveMaster() {
		String hql = "FROM LeaveMaster order by roleName";
		return (List<LeaveMaster>) entityManager.createQuery(hql).getResultList();
	}

}
