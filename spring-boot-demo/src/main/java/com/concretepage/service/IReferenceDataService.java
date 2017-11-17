package com.concretepage.service;

import java.util.List;
import java.util.Map;

import com.concretepage.entity.GroupCodeValues;
import com.concretepage.entity.GroupName;
import com.concretepage.entity.LeaveMaster;

public interface IReferenceDataService {
	
	Map<String, List<GroupCodeValues>> getGroupCodeValues();
	
	List<GroupCodeValues> loadGroupCodeValues();
	
	List<GroupCodeValues> getCodeValues(GroupName groupName);
	
	List<LeaveMaster> getLeaveMasterForRoleName(String roleName);
	
	void init();

}
