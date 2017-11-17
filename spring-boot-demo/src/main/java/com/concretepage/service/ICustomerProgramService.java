package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.CustomerProgram;
import com.concretepage.exception.HRException;


public interface ICustomerProgramService {
	
	void addCustomerProgram(CustomerProgram customerProgram) throws HRException;
	List<CustomerProgram> getAllCustomerPrograms();
	void updateCustomerProgram(CustomerProgram customerProgram);
	CustomerProgram getCustomerProgramById(Integer customerProgramId) throws  HRException;

}
