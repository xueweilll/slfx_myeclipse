package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolApprovalMapper;
import com.benqzl.pojo.patrol.Patrol;
import com.benqzl.pojo.patrol.PatrolDetails;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolPeople;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.system.User;
@Service("PatrolApprovalService")
public class PatrolApprovalServiceImpl implements PatrolApprovalService {
	@Autowired
	private PatrolApprovalMapper mapper;
	@Override
	public List<PatrolPlanDetails> findByPage(Map<String, Object> map) {		
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public Patrol findPatrolByppdid(String patrolplandetailid) {		
		return mapper.findPatrolByppdid(patrolplandetailid);
	}

	@Override
	public List<PatrolDetails> findPatrolDetailsByPage(Map<String, String> map) {		
		return mapper.findPatrolDetailsByPage(map);
	}

	@Override
	public int pageCount2(Map<String, String> map) {		
		return mapper.pageCount2(map);
	}

	@Override
	public void approval(String patrolplandetailsid) {
		mapper.approval(patrolplandetailsid);
	}

	@Override
	public void deletePatrolPeople(String patrolid) {
		mapper.deletePatrolPeople(patrolid);
	}

	@Override
	public void approval2(String patrolid) {
		mapper.approval2(patrolid);
	}

	@Override
	public List<User> findPatrolPeople(String sid) {
		return mapper.findPatrolPeople(sid);
	}

	@Override
	public List<Patrol> findPatrolDetailProblemByPage(Map<String, String> map) {
		return mapper.findPatrolDetailProblemByPage(map);
	}

	@Override
	public int pageCount3(Map<String, String> map) {		
		return mapper.pageCount3(map);
	}

	@Override
	public Patrol findPatrolById(String patrolid) {		
		return mapper.findPatrolById(patrolid);
	}

	@Override
	public List<PatrolDetails> findPatrolDetailsProblemByPage(
			Map<String, String> map) {
		return mapper.findPatrolDetailsProblemByPage(map);
	}

	@Override
	public void updatePatrolDetails(Map<String, String> map) {
		mapper.updatePatrolDetails(map);
	}

	@Override
	public void insertPatrolPeople(PatrolPeople pp) {
		mapper.insertPatrolPeople(pp);
	}

	@Override
	public void updatePatrolDone(String patrolid) {
		mapper.updatePatrolDone(patrolid);
	}

	@Override
	public List<String> findOperaterByPatrolid(String patrolid) {
		return mapper.findOperaterByPatrolid(patrolid);
	}

	@Override
	public List<String> findOperatetimeByPatrolid(String patrolid) {
		return mapper.findOperatetimeByPatrolid(patrolid);
	}

	@Override
	public List<Patrol> findPatrolDetailProblemByPage2(Map<String, String> map) {
		return mapper.findPatrolDetailProblemByPage2(map);
	}

	@Override
	public List<Patrol> findPatrolDetailProblemByPage3(Map<String, String> map) {
		return mapper.findPatrolDetailProblemByPage3(map);
	}

	@Override
	public Patrol findPatrolById2(String patrolid) {
		return mapper.findPatrolById2(patrolid);
	}

	@Override
	public void verify(Map<String, String> map) {
		mapper.verify(map);
	}

	@Override
	public List<PatrolPeople> findPeopleByPatrolid(Map<String, String> map) {
		return mapper.findPeopleByPatrolid(map);
	}

	@Override
	public void updatePatrol(String patrolid) {
		mapper.updatePatrol(patrolid);
	}

	@Override
	public String findPPidByPatrolid(String patrolid) {		
		return mapper.findPPidByPatrolid(patrolid);
	}

	@Override
	public List<PatrolPlanDetails> findNotAll(Map<String, Object> map) {
		return mapper.findNotAll(map);
	}

	@Override
	public int pageNotAll(Map<String, Object> map) {
		return mapper.pageNotAll(map);
	}
	
	@Override
	public List<Patrol> findPatrolDetailProblemByPage3NotAll(
			Map<String, String> map) {
		return mapper.findPatrolDetailProblemByPage3NotAll(map);
	}

	@Override
	public int pageCount3NotAll(Map<String, String> map) {		
		return mapper.pageCount3NotAll(map);
	}

	@Override
	public String findSidByUserId(String userid) {
		return mapper.findSidByUserId(userid);
	}

	@Override
	public List<Patrol> findOtherPatrolsByPatrolid(String patrolid) {
		return mapper.findOtherPatrolsByPatrolid(patrolid);
	}

	@Override
	public int pageCount4(Map<String, String> map) {
		return mapper.pageCount4(map);
	}

	@Override
	public int pageCount5(Map<String, String> map) {		
		return mapper.pageCount5(map);
	}

	@Override
	public List<PatrolDetails> findPatrolDetailsProblemByPage3(
			Map<String, String> map) {
		return mapper.findPatrolDetailsProblemByPage3(map);
	}

	@Override
	public int pageCount6(Map<String, String> map) {
		return mapper.pageCount6(map);
	}

	@Override
	public String findPDidByPatrolid(String patrolid) {		
		return mapper.findPDidByPatrolid(patrolid);
	}

	@Override
	public List<PatrolDetails> findPatrolDetailsProblemByPage4(
			Map<String, String> map) {		
		return mapper.findPatrolDetailsProblemByPage4(map);
	}

	@Override
	public String findSidByPatrolid(String patrolid) {		
		return mapper.findSidByPatrolid(patrolid);
	}

	@Override
	public List<PatrolNormal> findPatrolNormal(Map<String, String> map) {		
		return mapper.findPatrolNormal(map);
	}

	@Override
	public int findPatrolNormalcount(Map<String, String> map) {		
		return mapper.findPatrolNormalcount(map);
	}
	
}
