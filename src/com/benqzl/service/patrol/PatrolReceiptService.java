package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.Patrol;
import com.benqzl.pojo.patrol.PatrolDetails;
import com.benqzl.pojo.patrol.PatrolEnumid;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalPeople;
import com.benqzl.pojo.patrol.PatrolPeople;
import com.benqzl.pojo.patrol.PatrolPlan;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.system.Employee;

public interface PatrolReceiptService {

	List<PatrolPlan> findPatrolReceiptList(Map<String, Object> map);

	int findPatrolReceiptCount(Map<String, Object> map);

	List<PatrolPlanDetails> selectPatrolPlanDetailsByPatrolPlanId(
			String patrolPlanId);

	void updatePatrolPlanDetails(PatrolPlanDetails details);

	Employee selectUserSidByUserId(String userId);

	List<PatrolPlanDetails> selectPatrolPlanDetailsByPatrolPlanId2(
			String patrolPlanId);

	PatrolPlanDetails selectPatrolPlanDetailsByPatrolPlanIdAndSid(
			Map<String, Object> map);

	void insertSelective(Patrol patrol);

	void insertPatrolDetails(List<PatrolDetails> pd);

	Patrol selectByPPDtailsID(String patrolplandetailsid);

	List<PatrolDetails> selectByPatrolID(String patrolid);

	int deleteByPatrolID(String patrolid);

	int updateByPrimaryKeySelective(Patrol record);

	int insertPatrolPeople(List<PatrolPeople> record);

	List<PatrolPlanDetails> findPRList(Map<String, Object> map);

	int findPRCount(Map<String, Object> map);

	int updatePPDStateByPk(Map<String, Object> map);

	int selectIScomplete(String id);

	void insertpatrolNormal(PatrolNormal patrol);

	void insertPatrolNormalDetails(List<PatrolNormalDetails> list);

	void insertPatrolNormalPeople(List<PatrolNormalPeople> people);

	List<PatrolNormal> findpatrolnormal(Map<String, Object> map);

	int findpatrolnormalcount(Map<String, Object> map);

	PatrolNormal selectByPatrolNormalId(String id);

	List<PatrolNormalDetails> selectByNormalDetails(String id);

	List<PatrolEnumid> selectByNormalEnumid(String id);

	void deleteByPatrolNormalID(String id);

	List<PatrolNormalPeople> getfindUser(String id);

	PatrolNormal validatenormal(PatrolNormal normal);

	PatrolNormal findmobileyanzheng(PatrolNormal patrol);

	void updateMobileCommit(String id);

	void deleteByPatrolNormalById(String id);

	List<PatrolEnumid> selectEnum();

}