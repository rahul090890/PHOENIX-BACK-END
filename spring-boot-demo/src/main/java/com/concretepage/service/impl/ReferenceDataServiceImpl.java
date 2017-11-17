package com.concretepage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IGroupCodeValuesDao;
import com.concretepage.dao.ILeaveMasterDao;
import com.concretepage.entity.GroupCodeValues;
import com.concretepage.entity.GroupName;
import com.concretepage.entity.LeaveMaster;
import com.concretepage.service.IReferenceDataService;

@Service
public class ReferenceDataServiceImpl implements IReferenceDataService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	Map<String, List<GroupCodeValues>> groupCodeValues = new ConcurrentHashMap<String,List<GroupCodeValues>>();
	Map<String, List<LeaveMaster>> leaveMastersByRoleName = new ConcurrentHashMap<String, List<LeaveMaster>>();
	
	@Autowired
	IGroupCodeValuesDao dao;
	
	@Autowired
	ILeaveMasterDao leaveMasterDao;
	
	
	@Override
	public Map<String, List<GroupCodeValues>> getGroupCodeValues() {
	
		return this.groupCodeValues;
	}
	
	@PostConstruct
	@Override
	public void init() {
		loadGroupCodeValuesFromDatabase();
		loadLeaveMasterFromDatabase();
	}
	
	private void loadGroupCodeValuesFromDatabase() {
		if(groupCodeValues.size() == 0) {
			List<GroupCodeValues> listOfgroupCodeValues = loadGroupCodeValues();
			log.info("Loading dropdown values from database");
			for(GroupCodeValues codeValue : listOfgroupCodeValues) {
				String groupName = codeValue.getGroupName();
				if(null == groupCodeValues.get(groupName)) {
					List<GroupCodeValues> list = new ArrayList<GroupCodeValues>();
					list.add(codeValue);
					log.debug(" Adding the group " + groupName + " with the value " + codeValue.toString());
					groupCodeValues.put(groupName, list);
				} else {
					groupCodeValues.get(groupName).add(codeValue);
				}
			}
			log.info("The number of groups added are" + groupCodeValues.size() );
		} 
	}
	
	private void loadLeaveMasterFromDatabase() {
		log.info("Leave Master is loaded from database");
		List<LeaveMaster> leaves = leaveMasterDao.getLeaveMaster();
		for(LeaveMaster l : leaves) {
			String roleName = l.getRoleName();
			if(null != leaveMastersByRoleName.get(roleName)) {
				leaveMastersByRoleName.get(roleName).add(l);
			} else {
				List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
				masters.add(l);
				leaveMastersByRoleName.put(roleName,masters);
			}
		}
	}

	@Override
	public List<GroupCodeValues> loadGroupCodeValues() {
		return dao.listGroupCodeValues();
	}

	@Override
	public List<GroupCodeValues> getCodeValues(GroupName groupName) {
		return this.groupCodeValues.get(groupName.name());
	}

	@Override
	public List<LeaveMaster> getLeaveMasterForRoleName(String roleName) {
		return this.leaveMastersByRoleName.get(roleName);
	}


}
