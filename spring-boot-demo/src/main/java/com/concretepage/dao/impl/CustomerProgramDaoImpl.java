package com.concretepage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.dao.ICustomerProgramDao;
import com.concretepage.entity.CustomerProgram;
import com.concretepage.exception.HRException;
@Transactional
@Repository

public class CustomerProgramDaoImpl implements ICustomerProgramDao {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public void insertCustomerProgram(CustomerProgram customerProgram) {
		entityManager.persist(customerProgram);

	}

	@Override
	public List<CustomerProgram> fetchAllCustomerPrograms() {
		String hql = "FROM CustomerProgram as prg ORDER BY prg.customerProgramId";
		return (List<CustomerProgram>) entityManager.createQuery(hql).getResultList();	
	}

	@Override
	public void updateCustomerProgram(CustomerProgram customerProgram) {
		/*String hql = "update CustomerProgram p set p.customer.customerId = ? , p.customerProgramCode = ? , p.customerProgramType = ? where p.customerProgramId = ? ";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, customerProgram.getCustomer().getCustomerId());
		query.setParameter(2, customerProgram.getCustomerProgramCode());
		query.setParameter(3, customerProgram.getCustomerProgramType());
		query.setParameter(4, customerProgram.getCustomerProgramId());
		
		query.executeUpdate();
		*/
		entityManager.merge(customerProgram);		
	}

	@Override
	public CustomerProgram findCustomerProgramById(Integer customerProgramId) throws  HRException {
		String hql = "FROM CustomerProgram as prg where prg.customerProgramId = ?  ORDER BY prg.customerProgramId";
		Query query = entityManager.createQuery(hql);
		query.setParameter(1, customerProgramId);
		List<CustomerProgram> customerPrograms = query.getResultList();
		if(null == customerPrograms || customerPrograms.size() == 0) {
			throw new HRException("Customer program Id is not available " + customerProgramId);
		}
		return (CustomerProgram) query.getResultList().get(0);	
	}

}
