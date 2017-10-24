package com.benqzl.service.patrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolPlan;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.system.Station;

public interface PatrolPlanService {

	  PatrolSpecialIssue findPatrolSpecialIssueByid(String id)throws Exception;

	
		List<PatrolSpecialIssue> findByPage(Map<String, Object> map); 
		
		List<PatrolSpecialIssue> findByPagere(Map<String, Object> map); 
		
		int pageCount(Map<String, Object> map);
		
		int pageCountre(Map<String, Object> map);
	        
	    void update(PatrolSpecialIssue patrolSpecialIssue)throws Exception;

		void distory(String id);
		
		void insert(PatrolSpecialIssue patrolSpecialIssue);
	
		String selectUser(Map<String, Object> mapuser);
		
		
	
	/*	void updateByIsId(String isid);*/
	
		
	
		
		
		
	List<PatrolPlan> findPatrolPlanList(Map<String, Object> map);

	int findPatrolPlanCount(Map<String, Object> map);

	List<Station> findStations();

	void insertSelective(PatrolPlan pp);

	Station selectStationByName(String stationname);

	void insertSelective(PatrolPlanDetails ppd);

	PatrolPlan selectByPrimaryKey(String id);

	List<PatrolPlanDetails> findStationByPatrolPlanID(String patrolPlanID);

	void updatePatrolPlan(PatrolPlan pp);

	void deletePatrolPlanDetailsByPatrolPlanID(String patrolplanid);

	void deletePatrolPlan(String id);
	
	String selectSidByPPID(String id);
	
	String selectMaxCode(HashMap<String,String> code);

	

}