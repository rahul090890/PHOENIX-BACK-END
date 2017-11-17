package com.concretepage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.entity.Project;
import com.concretepage.entity.Role;
import com.concretepage.service.IRoleService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/role")
public class RoleController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IRoleService service;
	
	@GetMapping("all")
	public ResponseEntity<List<Role>> getRoles() {
		List<Role> roles = service.getRoles();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/create/{roleName}/{parentRoleId}")
	public ResponseEntity<Void> create(
			@PathVariable("roleName") String roleName,
			@PathVariable("parentRoleId") String parentRoleId
			) {
		
		Role role = new Role();
		role.setRoleName(roleName);
		Role parentRole = new Role();
		parentRole.setRoleid(Integer.parseInt(parentRoleId));
		role.setParentRole(parentRole);
		log.info("Creating the Role " + role.toString() + " with parent Id " + parentRoleId);
		service.create(role);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@PostMapping("/update/{roleid}/{roleName}/{parentRoleId}")
	public ResponseEntity<Void> update(
			@PathVariable("roleid") String roleid,
			@PathVariable("roleName") String roleName,
			@PathVariable("parentRoleId") String parentRoleId
			) {
		
		Role role = new Role();
		role.setRoleid(Integer.parseInt(roleid));
		role.setRoleName(roleName);
		Role parentRole = new Role();
		parentRole.setRoleid(Integer.parseInt(parentRoleId));
		role.setParentRole(parentRole);
		log.info("Updating the Role " + role.toString() + " with parent Id " + parentRoleId);
		service.update(role);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping("delete/{roleid}")
	public ResponseEntity<Void> delete (@PathVariable("roleid") String roleid) {
		
		Role role = new Role();
		role.setRoleid(Integer.parseInt(roleid));
		log.info("Deleting the Role " + roleid);
		service.delete(role);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
