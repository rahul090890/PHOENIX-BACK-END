package com.concretepage.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Department;
import com.concretepage.entity.Employee;
import com.concretepage.exception.HRException;
@Transactional
@Repository
public class EmployeeDAO implements IEmployeeDAO {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees() {
		String hql = "FROM Employee as emp ORDER BY emp.employeeId";
		return (List<Employee>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public List<Employee> getManagers() {
		/*CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
		Root<Employee> employee  = criteriaQuery.from(Employee.class);
		criteriaQuery.select(employee).where(arg0)
		*/
		String hql = "FROM Employee as emp where (emp.role.roleName = ? or emp.role.roleName = ? or  emp.role.roleName = ?)   and emp.employementStatus = ? ORDER BY emp.employeeId";
		 Query query = entityManager.createQuery(hql);
		 query.setParameter(1, "Director");
		 query.setParameter(2, "Project Manager");
		 query.setParameter(3, "Lead");
		 query.setParameter(4, "Active");
		return (List<Employee>) query.getResultList();
	}
	

	@Override
	public void update(Employee emp) {
		
		/*Employee persistant = entityManager.find(Employee.class, emp.getEmployeeId());
		persistant.setFirstName(emp.getFirstName());
		persistant.setLastName(emp.getLastName());
		persistant.setEmailId(emp.getEmailId());
		persistant.setLoginPassword(emp.getLoginPassword());
		persistant.setAddress(emp.getAddress());
		persistant.setDesignation(emp.getDesignation());
		persistant.setEmployeeType(emp.getEmployeeType());
		persistant.setEmployementStatus(emp.getEmployementStatus()); */
		
		Department dept = entityManager.find(Department.class, emp.getDepartment().getDepartmentId());
		emp.setDepartment(dept);
		
		Employee reportingManager = entityManager.find(Employee.class, emp.getManager().getEmployeeId());
		emp.setManager(reportingManager);
		
		emp.setUpdatedTime(Calendar.getInstance().getTime());
		
		entityManager.merge(emp);
			
	}

	@Override
	public void create(Employee emp) {
		Department dept = entityManager.find(Department.class, emp.getDepartment().getDepartmentId());
		emp.setDepartment(dept);
		
		Employee reportingManager = entityManager.find(Employee.class, emp.getManager().getEmployeeId());
		emp.setManager(reportingManager);
		entityManager.persist(emp);
	}

	@Override
	public void resetPassword(int employeeId , String newPassword) {
		Query query = entityManager.createQuery("Update Employee e set e.loginPassword =" + newPassword + "  where e.employeeId = ?"  );
		query.setParameter(1, employeeId);
		query.executeUpdate();
	}

	@Override
	public void delete(Employee emp) {
		emp = entityManager.find(Employee.class, emp.getEmployeeId());
		entityManager.remove(emp);
		
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws HRException {
		String hql = "FROM Employee as emp where emp.employeeId = ? ORDER BY emp.employeeId";
		 Query query = entityManager.createQuery(hql);
		 query.setParameter(1, employeeId);
		 if(query.getResultList().size() == 0) {
			 throw new HRException("Unable to load Employee with employeeId " + employeeId);
		 }
		 return (Employee) query.getResultList().get(0);
	}

	@Override
	public List<Integer> getReportingEmployeeIds(List<Integer> managerIds) {
		String sql = "select e.employeeId from employee e where e.managerId IN :includeList";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("includeList", managerIds);
		return query.getResultList();
		
		
	}

	@Override
	public Employee loadEmployeeByUsernameAndPassword(String username, String password) {
		String hql = "FROM Employee as emp where emp.loginId = ? and emp.loginPassword = ? ORDER BY emp.employeeId";
		 Query query = entityManager.createQuery(hql);
		 query.setParameter(1, username);
		 query.setParameter(2, password);
		 return (Employee) query.getResultList().get(0);
	}

	@Override
	public Employee findEmployeeByLoginId(String loginId) {
		String hql = "FROM Employee as emp where emp.loginId = ? ORDER BY emp.employeeId";
		 Query query = entityManager.createQuery(hql);
		 query.setParameter(1, loginId);
		 return (Employee) query.getResultList().get(0);
	}

	
	
	
	

}
