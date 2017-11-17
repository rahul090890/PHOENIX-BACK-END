package com.concretepage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IRoleDAO;
import com.concretepage.entity.Role;
import com.concretepage.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRoleDAO dao;
	
	@Override
	public List<Role> getRoles() {
		return dao.getRoles();
	}

	@Override
	public void create(Role role) {
		dao.create(role);
		
	}

	@Override
	public void update(Role role) {
		dao.update(role);
		
	}

	@Override
	public void delete(Role role) {
		dao.delete(role);
		
	}

}
