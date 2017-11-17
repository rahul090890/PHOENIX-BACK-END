package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.concretepage.dao.ITaskDAO;
import com.concretepage.entity.Customer;
import com.concretepage.entity.Department;
import com.concretepage.entity.Task;
@Transactional
@Repository
public class TaskDAOImpl implements ITaskDAO {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasks() {
		/**CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
		 Root<Task> root = criteria.from(Task.class);
		 criteria.select(root);
		 criteria.orderBy(cb.asc(root.get("taskId")));
		 **/
		String hql = "from Task as t order by t.taskName";
		return (List<Task>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void update(Task task) {
		Department dept = entityManager.find(Department.class, task.getDepartment().getDepartmentId());
		task.setDepartment(dept);
		Customer cust = entityManager.find(Customer.class, task.getCustomer().getCustomerId());
		task.setCustomer(cust);
		entityManager.merge(task);
	}

	@Override
	public void create(Task task) {
		Department dept = entityManager.find(Department.class, task.getDepartment().getDepartmentId());
		task.setDepartment(dept);
		Customer cust = entityManager.find(Customer.class, task.getCustomer().getCustomerId());
		task.setCustomer(cust);
		entityManager.persist(task);
	}

	@Override
	public void delete(Task task) {
		task = entityManager.find(Task.class, task.getTaskId()); 
		entityManager.remove(task);
		
	}

	@Override
	public List<Task> findTasksForCustomerAndDepartment(Integer customerId, Integer departmentId) {
		String hql = "from Task t where t.customer.customerId = ? and t.department.departmentId = ? order by t.taskName";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, customerId);
		query.setParameter(2, departmentId);
		return query.getResultList();
		
	}

	@Override
	public Task findTaskById(Integer taskId) {
		return  entityManager.find(Task.class, taskId); 
	}

}
