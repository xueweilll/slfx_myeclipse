package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.Patrol;
import com.benqzl.pojo.patrol.PatrolDetails;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolPeople;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.system.User;

public interface PatrolApprovalService {

	List<PatrolPlanDetails> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	Patrol findPatrolByppdid(String patrolplandetailid);

	List<PatrolDetails> findPatrolDetailsByPage(Map<String, String> map);

	int pageCount2(Map<String, String> map);

	void approval(String patrolplandetailsid);

	void deletePatrolPeople(String patrolid);

	void approval2(String patrolid);

	List<User> findPatrolPeople(String sid);

	List<Patrol> findPatrolDetailProblemByPage(Map<String, String> map);

	int pageCount3(Map<String, String> map);

	Patrol findPatrolById(String patrolid);

	List<PatrolDetails> findPatrolDetailsProblemByPage(Map<String, String> map);

	void updatePatrolDetails(Map<String, String> map);

	void insertPatrolPeople(PatrolPeople pp);

	void updatePatrolDone(String patrolid);

	List<String> findOperaterByPatrolid(String patrolid);

	List<String> findOperatetimeByPatrolid(String patrolid);

	List<Patrol> findPatrolDetailProblemByPage2(Map<String, String> map);

	List<Patrol> findPatrolDetailProblemByPage3(Map<String, String> map);

	Patrol findPatrolById2(String patrolid);

	void verify(Map<String, String> map);

	List<PatrolPeople> findPeopleByPatrolid(Map<String, String> map);

	void updatePatrol(String patrolid);

	String findPPidByPatrolid(String patrolid);

	List<PatrolPlanDetails> findNotAll(Map<String, Object> map);

	int pageNotAll(Map<String, Object> map);

	List<Patrol> findPatrolDetailProblemByPage3NotAll(Map<String, String> map);

	int pageCount3NotAll(Map<String, String> map);

	String findSidByUserId(String userid);

	List<Patrol> findOtherPatrolsByPatrolid(String patrolid);

	int pageCount4(Map<String, String> map);

	int pageCount5(Map<String, String> map);

	List<PatrolDetails> findPatrolDetailsProblemByPage3(Map<String, String> map);

	int pageCount6(Map<String, String> map);

	String findPDidByPatrolid(String patrolid);

	List<PatrolDetails> findPatrolDetailsProblemByPage4(Map<String, String> map);

	String findSidByPatrolid(String patrolid);

	List<PatrolNormal> findPatrolNormal(Map<String, String> map);

	int findPatrolNormalcount(Map<String, String> map);
	
}
