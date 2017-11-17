package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Role;

public interface IRoleDAO {
	List<Role> getRoles();
	Role getRoleById(int roleId);
	void update(Role role);
	void create (Role role);
	void delete(Role role);

}
