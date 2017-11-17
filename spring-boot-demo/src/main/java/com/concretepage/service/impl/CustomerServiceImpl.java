package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.ICustomerDAO;
import com.concretepage.entity.Customer;
import com.concretepage.exception.HRException;
import com.concretepage.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICustomerDAO dao;
	
	@Override
	public List<Customer> getCustomers() {
		return dao.getCustomers();
	}

	@Override
	public List<Customer> findByName(String customerName) {
		return dao.findByName(customerName);
	}

	@Override
	public void update(Customer customer) {
		dao.update(customer);
		
	}

	@Override
	public void create(Customer customer) {
		dao.create(customer);
		
	}

	@Override
	public void delete(Customer customer) {
		dao.delete(customer);
		
	}

	@Override
	public Customer findCustomerById(Integer customerId) throws HRException {
		return dao.findByCustomerId(customerId);
	}

}
