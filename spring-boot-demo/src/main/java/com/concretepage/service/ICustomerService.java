package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Customer;
import com.concretepage.exception.HRException;

public interface ICustomerService {

	List<Customer> getCustomers();
	public List<Customer> findByName(String customerName);
	public void update(Customer customer) ;
	public void create(Customer customer) ;
	public void delete(Customer customer);
	Customer findCustomerById(Integer customerId) throws HRException;
	
}
