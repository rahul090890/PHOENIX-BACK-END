package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Customer;
import com.concretepage.exception.HRException;

public interface ICustomerDAO {

	List<Customer> getCustomers();
	
	List<Customer> findByName(final String customerName);
	
	Customer findByCustomerId(final Integer customerId) throws HRException;
	
	void update(final Customer customer);
	
	void create(final Customer customer);
	
	void delete(final Customer customer);
}
