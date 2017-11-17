package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.CustomerProgram;
import com.concretepage.exception.HRException;

public interface ICustomerProgramDao {
	
	void insertCustomerProgram(final CustomerProgram customerProgram);
	
	List<CustomerProgram> fetchAllCustomerPrograms();
	
	void updateCustomerProgram(final CustomerProgram customerProgram);
	
	CustomerProgram findCustomerProgramById(Integer customerProgramId) throws  HRException;
	
	
}
