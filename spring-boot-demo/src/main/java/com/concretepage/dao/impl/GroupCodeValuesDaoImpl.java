package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.dao.IGroupCodeValuesDao;
import com.concretepage.entity.GroupCodeValues;

@Transactional
@Repository
public class GroupCodeValuesDaoImpl implements IGroupCodeValuesDao {
	
	@PersistenceContext	
	private EntityManager entityManager;	

	@Override
	public List<GroupCodeValues> listGroupCodeValues() {
		String hql = "FROM GroupCodeValues as cv ORDER BY cv.groupName,cv.groupKey";
		return  (List<GroupCodeValues>) entityManager.createQuery(hql).getResultList();
	}

}
