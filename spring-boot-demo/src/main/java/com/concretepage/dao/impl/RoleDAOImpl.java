package com.concretepage.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.concretepage.dao.IRoleDAO;
import com.concretepage.entity.Department;
import com.concretepage.entity.Role;
@Transactional
@Repository
public class RoleDAOImpl implements IRoleDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Role> getRoles() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Role> criteria = cb.createQuery(Role.class);
		 Root<Role> root = criteria.from(Role.class);
		 criteria.select(root);
		 criteria.orderBy(cb.asc(root.get("roleid")));
		 return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public void update(Role role) {
		Role parentRole = entityManager.find(Role.class, role.getParentRole().getRoleid());
		role.setParentRole(parentRole);
		role.setUpdatedTime(Calendar.getInstance().getTime());
		entityManager.merge(role);
		
	}

	@Override
	public void create(Role role) {
		Role parentRole = entityManager.find(Role.class, role.getParentRole().getRoleid());
		role.setParentRole(parentRole);
		role.setCreatedTime(Calendar.getInstance().getTime());
		entityManager.persist(role);
	}

	@Override
	public void delete(Role role) {
		role = entityManager.find(Role.class, role.getRoleid());
		entityManager.remove(role);
		
	}

	@Override
	public Role getRoleById(int roleId) {
		return entityManager.find(Role.class, roleId);
	}

}
