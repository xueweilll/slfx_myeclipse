package com.benqzl.service.activiti;

import java.util.List;
import java.util.Map;

public interface ActivitiUnitService {
	int statrtByKey(String key,Map<String, Object> map) throws Exception;//启动流程
	
	int completeTask(String taskName,String userid,String id,Map<String, Object> map) throws Exception;//完成任务
	
	int completeTaskByStation(String userid, String id,String sid,Map<String, Object> map) throws Exception;//完成任务
	
	int completeTaskByDepartment(String userid,String id,String did, Map<String, Object> map) throws Exception;//完成任务
	
	List<String> findObjIds(String taskName,String userid,String type)throws Exception;
	
	List<Map<String, Object>> findByStation(String userid,String type)throws Exception;
	
	List<Map<String, Object>> findByDepartment(String userid,String type)throws Exception;
	
	String selectMaxCode(String prefix) throws Exception;
	
	boolean subflowIsOver(String id) throws Exception;
	
	boolean subflowIsFirst(String id) throws Exception;

	String selectCode(String string);
}
