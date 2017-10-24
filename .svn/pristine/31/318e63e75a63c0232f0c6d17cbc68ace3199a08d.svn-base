package com.benqzl.dao.patrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolPlan;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.system.Station;

public interface PatrolPlanMapper {
   
	   void insert(PatrolSpecialIssue patrolSpecialIssue);
	    
	    PatrolSpecialIssue findPatrolSpecialIssueByid(String id);

		List<PatrolSpecialIssue> findByPagere(Map<String, Object> map);
		
		List<PatrolSpecialIssue> findByPage(Map<String, Object> map);
		
		int pageCount(Map<String, Object> map);
		
		int pageCountre(Map<String, Object> map);
	    
	    void update(PatrolSpecialIssue patrolSpecialIssue);

		void distory(String id);
	
	    String selectUser(Map<String, Object> mapuser);
	
	 
	
	
	   
	   
	   
	   int deleteByPrimaryKey(String id);

    int insert(PatrolPlan record);

    int insertSelective(PatrolPlan record);

    PatrolPlan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolPlan record);

    int updateByPrimaryKey(PatrolPlan record);

	List<PatrolPlan> findPatrolPlanList(Map<String, Object> map);

	int findPatrolPlanCount(Map<String, Object> map);

	List<PatrolPlan> findPatrolReceiptList(Map<String, Object> map);

	int findPatrolReceiptCount(Map<String, Object> map);

	Station selectStationByName(String stationname);

	List<Station> findStations();

	List<PatrolPlanDetails> findStationByPatrolPlanID(String patrolPlanID);
	
	String selectMaxCode(HashMap<String,String> map);
}