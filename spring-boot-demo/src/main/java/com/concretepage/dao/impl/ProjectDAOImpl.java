package com.concretepage.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.concretepage.dao.IProjectDAO;
import com.concretepage.entity.Project;

@Transactional
@Repository
public class ProjectDAOImpl implements IProjectDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Project> getProjects() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Project> criteria = cb.createQuery(Project.class);
		 Root<Project> root = criteria.from(Project.class);
		 criteria.select(root);
		 criteria.orderBy(cb.asc(root.get("projectName")));
		 return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public List<Project> findProjectByName(String projectName) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Project> criteria = cb.createQuery(Project.class);
		Root<Project> root = criteria.from(Project.class);
		criteria.select(root);
		criteria.where(cb.like(root.get("projectName"), projectName));
		return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public void update(Project project) {
		String hql = "update Project p set p.projectName = ? , p.customer.customerId = ? , p.department.departmentId = ? , p.projectType = ? , p.projectStatus = ? , p.updatedTime = ? , location = ?, customerProjectCode = ? where projectid = ?";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, project.getProjectName());
		query.setParameter(2, project.getCustomer().getCustomerId());
		query.setParameter(3, project.getDepartment().getDepartmentId());
		query.setParameter(4, project.getProjectType());
		query.setParameter(5, project.getProjectStatus());
		query.setParameter(6, Calendar.getInstance().getTime());
		query.setParameter(7, project.getLocation());
		query.setParameter(8, project.getCustomerProjectCode());
		query.setParameter(9, project.getProjectid());
		
		query.executeUpdate();
	}

	@Override
	public void create(Project project) {
		entityManager.persist(project);
	}

	@Override
	public void delete(Integer projectId) {
		String hql = "delete from Project where projectId = ?";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, projectId);
		query.executeUpdate();
	}

	@Override
	public Project findProjectById(Integer projectId) {
	
		return entityManager.find(Project.class, projectId);
	}

	@Override
	public List<Project> findProjectForCustomerAndDepartment(Integer customerId,
			Integer departmentId) {
		String hql = "from Project t where t.customer.customerId = ? and t.department.departmentId = ? order by t.projectName";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, customerId);
		query.setParameter(2, departmentId);
		return query.getResultList();
		
	}

}
