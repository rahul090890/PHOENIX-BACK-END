package com.concretepage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.entity.GroupCodeValues;
import com.concretepage.service.IReferenceDataService;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("hr/refData")
public class ReferenceDataController {

	@Autowired
	IReferenceDataService service;
	
	@GetMapping("list")
	public ResponseEntity<Map<String,List<GroupCodeValues>>> getDropDownValues() {
		Map<String,List<GroupCodeValues>> groupCodeValues = service.getGroupCodeValues();
		return new ResponseEntity<Map<String,List<GroupCodeValues>>>(groupCodeValues, HttpStatus.OK);
	}
}
