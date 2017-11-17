package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.ICustomerDAO;
import com.concretepage.dao.ICustomerProgramDao;
import com.concretepage.entity.Customer;
import com.concretepage.entity.CustomerProgram;
import com.concretepage.exception.HRException;
import com.concretepage.service.ICustomerProgramService;

@Service
public class CustomerProgramServiceImpl implements ICustomerProgramService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICustomerProgramDao customerProgramdao;
	
	@Autowired
	private ICustomerDAO customerDao;
	
	@Override
	public void addCustomerProgram(CustomerProgram customerProgram) throws HRException {
		Customer customer = customerDao.findByCustomerId(customerProgram.getCustomer().getCustomerId());
		customerProgram.setCustomer(customer);
		customerProgramdao.insertCustomerProgram(customerProgram);
	}

	@Override
	public List<CustomerProgram> getAllCustomerPrograms() {
		return customerProgramdao.fetchAllCustomerPrograms();
	}

	@Override
	public void updateCustomerProgram(CustomerProgram customerProgram) {
		customerProgramdao.updateCustomerProgram(customerProgram);

	}

	@Override
	public CustomerProgram getCustomerProgramById(Integer customerProgramId) throws  HRException {
		return customerProgramdao.findCustomerProgramById(customerProgramId);
	}

}
