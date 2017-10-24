package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceAduit;
import com.benqzl.pojo.patrol.MaintenanceDetails;


public interface MainTenanceAduitService {
	List<Maintenance> findByPage(Map<String, Object> map) throws Exception;
	int pageCount(Map<String, Object> map) throws Exception;
	void insertAduit(MaintenanceAduit aduit, String step, String state, String suggest);
	void disagreeAduit(MaintenanceAduit aduit, String step, String state, String suggest);
	Maintenance findById(String id);
	List<MaintenanceDetails> findDetailsById(Map<String, Object> map);
	Maintenance findStepMemo(Map<String, Object> map);
	MaintenanceAduit findMemo(Map<String, Object> map);
	List<Maintenance> findAduitByPage(Map<String, Object> map);
	int pageAduitCount(Map<String, Object> map);	
}
