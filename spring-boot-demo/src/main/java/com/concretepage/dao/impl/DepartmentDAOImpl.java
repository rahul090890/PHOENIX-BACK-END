package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.concretepage.dao.IDepartmentDAO;
import com.concretepage.entity.Department;
import com.concretepage.entity.Employee;
import com.concretepage.exception.HRException;

@Transactional
@Repository
public class DepartmentDAOImpl implements IDepartmentDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Department> getDepartments() {
		/**CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
		 Root<Department> root = criteria.from(Department.class);
		 criteria.select(root);
		 criteria.orderBy(cb.asc(root.get("departmentId")));
		 return entityManager.createQuery(criteria).getResultList(); **/
		
		String hql = "from Department d where d.parentDepartment.departmentId is not null order by departmentName";
		Query query = entityManager.createQuery(hql);
		List<Department> departments = (List<Department>) query.getResultList();
		return departments;
	}

	@Override
	public List<Department> findByName(String departmentName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
		Root<Department> root = criteria.from(Department.class);
		criteria.select(root);
		criteria.where(cb.equal(root.get("departmentName"), departmentName));
		return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public void update(Department department) {
			
		Department parentDepartment = entityManager.find(Department.class, department.getParentDepartment().getDepartmentId());
		department.setParentDepartment(parentDepartment);
		
		entityManager.merge(department);
	}

	@Override
	public void create(Department department) {

		Department parentDepartment = entityManager.find(Department.class, department.getParentDepartment().getDepartmentId());
		department.setParentDepartment(parentDepartment);
		
		entityManager.persist(department);
		
	}

	@Override
	public void delete(Department department) {
		department = entityManager.find(Department.class, department.getDepartmentId());		
		entityManager.remove(department);
		
	}

	@Override
	public Department findDeparmentById(Integer departmentId) throws  HRException {
		String hql = "from Department d where d.departmentId = ?";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, departmentId);
		List<Department> departments = (List<Department>) query.getResultList();
		if(null == departments || departments.size() == 0) {
			throw new HRException(" Department is not available with id " + departmentId);
		}
		
		return departments.get(0);			
	}

}
