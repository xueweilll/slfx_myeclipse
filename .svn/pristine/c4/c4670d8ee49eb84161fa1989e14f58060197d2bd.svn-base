
package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolDetailsMapper;
import com.benqzl.dao.patrol.PatrolEnumidMapper;
import com.benqzl.dao.patrol.PatrolMapper;
import com.benqzl.dao.patrol.PatrolNormalDetailsMapper;
import com.benqzl.dao.patrol.PatrolNormalMapper;
import com.benqzl.dao.patrol.PatrolNormalPeopleMapper;
import com.benqzl.dao.patrol.PatrolPeopleMapper;
import com.benqzl.dao.patrol.PatrolPlanDetailsMapper;
import com.benqzl.dao.patrol.PatrolPlanMapper;
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

@Service("patrolReceiptService")
public class PatrolReceiptServiceImpl implements PatrolReceiptService {

	@Autowired
	private PatrolPlanMapper ppmapper;
	
	@Autowired
	private PatrolPlanDetailsMapper ppdmapper;
	
	@Autowired
	private PatrolMapper pmapper;
	
	@Autowired
	private PatrolDetailsMapper pdmapper;
	
	@Autowired
	private PatrolPeopleMapper pdpmapper;
	
	@Autowired
	private PatrolNormalMapper normalmapper;
	@Autowired
	private PatrolNormalDetailsMapper normaldetailmapper;
	@Autowired
	private PatrolNormalPeopleMapper normalpeoplemapper;
	@Autowired
	private PatrolEnumidMapper normalenumidmapper;
	
	@Override
	public List<PatrolPlan> findPatrolReceiptList(Map<String, Object> map) {
		return ppmapper.findPatrolReceiptList(map);
	}

	@Override
	public int findPatrolReceiptCount(Map<String, Object> map) {
		return ppmapper.findPatrolReceiptCount(map);
	}

	@Override
	public List<PatrolPlanDetails> selectPatrolPlanDetailsByPatrolPlanId(
			String patrolPlanId) {
		return ppdmapper.selectPatrolPlanDetailsByPatrolPlanId(patrolPlanId);
	}

	@Override
	public void updatePatrolPlanDetails(PatrolPlanDetails ppd) {
		ppdmapper.updateByPrimaryKeySelective(ppd);
	}

	@Override
	public Employee selectUserSidByUserId(String userId) {
		return ppdmapper.selectUserSidByUserId(userId);
	}

	@Override
	public List<PatrolPlanDetails> selectPatrolPlanDetailsByPatrolPlanId2(
			String patrolPlanId) {
		return ppdmapper.selectPatrolPlanDetailsByPatrolPlanId2(patrolPlanId);
	}

	@Override
	public PatrolPlanDetails selectPatrolPlanDetailsByPatrolPlanIdAndSid(
			Map<String, Object> map) {
		return ppdmapper.selectPatrolPlanDetailsByPatrolPlanIdAndSid(map);
	}

	@Override
	public void insertSelective(Patrol patrol) {
		pmapper.insertSelective(patrol);
	}

	@Override
	public void insertPatrolDetails(List<PatrolDetails> pd) {
		pdmapper.insert(pd);
	}

	@Override
	public Patrol selectByPPDtailsID(String patrolplandetailsid) {
		return pmapper.selectByPPDtailsID(patrolplandetailsid);
	}

	@Override
	public List<PatrolDetails> selectByPatrolID(String patrolid) {
		return pdmapper.selectByPatrolID(patrolid);
	}

	@Override
	public int deleteByPatrolID(String patrolid) {
		return pdmapper.deleteByPatrolID(patrolid);
	}

	@Override
	public int updateByPrimaryKeySelective(Patrol record) {
		return pmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertPatrolPeople(List<PatrolPeople> record) {
		return pdpmapper.insert(record);
	}

	@Override
	public List<PatrolPlanDetails> findPRList(Map<String, Object> map) {
		return ppdmapper.findPRList(map);
	}

	@Override
	public int findPRCount(Map<String, Object> map) {
		return ppdmapper.findPRCount(map);
	}

	@Override
	public int updatePPDStateByPk(Map<String, Object> map) {
		return ppdmapper.updateStateByPk(map);
	}

	@Override
	public int selectIScomplete(String id) {
		return ppdmapper.selectIScomplete(id);
	}

	@Override
	public void insertpatrolNormal(PatrolNormal patrol) {
		normalmapper.insertSelective(patrol);
	}

	@Override
	public void insertPatrolNormalDetails(List<PatrolNormalDetails> list) {
		normaldetailmapper.insertPatrolNormalDetails(list);
	}

	@Override
	public void insertPatrolNormalPeople(List<PatrolNormalPeople> people) {
		normalpeoplemapper.inseinsertPatrolNormalPeople(people);
	}

	@Override
	public List<PatrolNormal> findpatrolnormal(Map<String, Object> map) {
		return normalmapper.findpatrolnormal(map);
	}

	@Override
	public int findpatrolnormalcount(Map<String, Object> map) {
		return normalmapper.findpatrolnormalcount(map);
	}

	@Override
	public PatrolNormal selectByPatrolNormalId(String id) {
		return normalmapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PatrolNormalDetails> selectByNormalDetails(String id) {
		return normaldetailmapper.selectByNormalDetails(id);
	}

	@Override
	public List<PatrolEnumid> selectByNormalEnumid(String id) {
		return normalenumidmapper.selectByNormalEnumid(id);
	}

	@Override
	public void deleteByPatrolNormalID(String id) {
		normaldetailmapper.deleteByNormalDetialKey(id);
		normalmapper.deleteByNormalPrimaryKey(id);
		
		
	}

	@Override
	public List<PatrolNormalPeople> getfindUser(String id) {
		return normalpeoplemapper.getfindUser(id);
	}

	@Override
	public PatrolNormal validatenormal(PatrolNormal normal) {
		return normalmapper.validatenormal(normal);
	}

	@Override
	public PatrolNormal findmobileyanzheng(PatrolNormal patrol) {
		return normalmapper.findmobileyanzheng(patrol);
	}
    //更新手机端日常巡检状态变为提交
	@Override
	public void updateMobileCommit(String id) {
		normalmapper.updateMobileCommit(id);		
	}

	@Override
	public void deleteByPatrolNormalById(String id) {
		normalpeoplemapper.deleteByPatrolId(id);
		normaldetailmapper.deleteByNormalDetialKey(id);
		normalmapper.deleteByNormalPrimaryKey(id);
		
	}

	@Override
	public List<PatrolEnumid> selectEnum() {
		return normalenumidmapper.selectEnum();
	}

	

}
