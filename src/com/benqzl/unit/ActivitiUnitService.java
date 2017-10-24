package com.benqzl.unit;

import java.util.List;
import java.util.Map;

public interface ActivitiUnitService {
	List<String> findTaskStationsByPatrolplan(String userid,String typeDate) throws Exception;
	int statrtByKey(String key,String name,String id) throws Exception;
	int statrtByKey(String key,Map<String, Object> map) throws Exception;
	int completeTask(String taskName,String userid,String id,String name,Object object) throws Exception;
	int completeTasks(String taskName,String userid,String id,Map<String, Object> map) throws Exception;
	int completeTaskByDorS(String taskName,String userid,String id,String dorsId,String name,Object object) throws Exception;
	int completeTask(String taskName,String userid,String id) throws Exception;
	int completeTaskByPerson(String taskName,String userid,String id) throws Exception;
	List<String> findeReceiptIds(String userid,String type,String typeDate) throws Exception;
	List<String> findeSelfDispatchIds(String userid,String typeDate) throws Exception;
	void messagePush(String taskName,String userid,String id) throws Exception;
	public void messagePushByDorSid(String taskName,String userid,String id,String dorsId) throws Exception;
	List<String> userids(String taskName,String userid,String id)throws Exception;
	String selectMaxCode(String prefix);
	List<String> findMaintainId(String userid,String typeDate)throws Exception;
	//查找防洪调度枢纽ID 和 主表ID
	List<Map<String, Object>> findStations(String userid,String type)throws Exception;
	
	//查找所ID
	List<Map<String, Object>> findDistricts(String userid,String type)throws Exception;
	
	//查找objIds
	
	List<String> findObjIds(String userid,String taskName,String type) throws Exception;
	/**
	 * 巡检特别检查执行部门查询
	 */
	List<Map<String, Object>> findDistrictsByPartrol(String userid,String taskName)throws Exception;
	/**
	 *  巡检特别检查执行人查询
	 */
	List<Map<String, Object>> findStationsByPartrol(String userid)throws Exception;
	/**
	 * 巡检特别检查objIds查询
	 */
	List<String> findObjIdsByPartrol(String userid,String taskName) throws Exception;
	/**
	 * 巡检特别检查完成子流程任务
	 */
	int completeTaskByDorSByPartrol(String taskName,String userid,String id,String dorsId,String name,Object object) throws Exception;
	/**
	 * 巡检特别检查完成子流程任务消息推送
	 */
	public void messagePushByDorSidByPartrol(String taskName,String userid,String id,String dorsId) throws Exception;
 }
