package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.system.Employee;

public interface PatrolPlanDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolPlanDetails record);

    int insertSelective(PatrolPlanDetails record);

    PatrolPlanDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolPlanDetails record);

    int updateByPrimaryKey(PatrolPlanDetails record);

	void deletePatrolPlanDetailsByPatrolPlanID(String patrolplanid);

	List<PatrolPlanDetails> selectPatrolPlanDetailsByPatrolPlanId(String patrolPlanId);

	void updateByPatrolPlanId(PatrolPlanDetails ppd);

	Employee selectUserSidByUserId(String userId);

	List<PatrolPlanDetails> selectPatrolPlanDetailsByPatrolPlanId2(
			String patrolPlanId);

	PatrolPlanDetails selectPatrolPlanDetailsByPatrolPlanIdAndSid(
			Map<String, Object> map);
	
	List<PatrolPlanDetails> findPRList(Map<String, Object> map);
	
	int findPRCount(Map<String, Object> map);
	
	int updateStateByPk(Map<String, Object> map);
	
	int selectIScomplete(String id);
}