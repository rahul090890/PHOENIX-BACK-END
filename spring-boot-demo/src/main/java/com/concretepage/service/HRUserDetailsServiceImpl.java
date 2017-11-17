package com.concretepage.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.concretepage.entity.Employee;
import com.concretepage.entity.Role;

@Service
public class HRUserDetailsServiceImpl implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		log.info("Loading the user details for loginid " + loginId);
		Employee employee = employeeService.getEmployeeByLoginId(loginId);
		return createUserDetails(employee);		
	}

	
	private UserDetails  createUserDetails(Employee employee) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Role role = employee.getRole();
		String roleName = "Resource";
		if(null != role) {
			roleName = role.getRoleName();
		}
		log.info("User Role is  " + roleName);
		grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
		
		return new org.springframework.security.core.userdetails.User(employee.getLoginId(), employee.getLoginPassword(), grantedAuthorities);
		
	}
	
}
