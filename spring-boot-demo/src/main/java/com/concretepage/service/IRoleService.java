package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Role;

public interface IRoleService {
	
	List<Role> getRoles();
	
	void create(Role role);
	
	void update(Role role);
	
	void delete(Role role);

}
