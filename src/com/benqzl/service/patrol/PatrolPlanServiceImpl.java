package com.benqzl.service.patrol;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolPlanDetailsMapper;
import com.benqzl.dao.patrol.PatrolPlanMapper;
import com.benqzl.pojo.patrol.PatrolPlan;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.system.Station;

@Service("patrolPlanService")
public class PatrolPlanServiceImpl implements PatrolPlanService {

	@Autowired
	private PatrolPlanMapper ppmapper;
	
	@Autowired
	private PatrolPlanDetailsMapper ppdmapper;

	
	@Override
	public List<PatrolPlan> findPatrolPlanList(Map<String, Object> map) {
		return ppmapper.findPatrolPlanList(map);
	}

	@Override
	public int findPatrolPlanCount(Map<String, Object> map) {
		return ppmapper.findPatrolPlanCount(map);
	}

	@Override
	public List<Station> findStations() {
		return ppmapper.findStations();
	}

	@Override
	public void insertSelective(PatrolPlan pp) {
		ppmapper.insertSelective(pp);
	}

	@Override
	public Station selectStationByName(String stationname) {
		return ppmapper.selectStationByName(stationname);
	}

	@Override
	public void insertSelective(PatrolPlanDetails ppd) {
		ppdmapper.insertSelective(ppd);
	}

	@Override
	public PatrolPlan selectByPrimaryKey(String id) {
		return ppmapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PatrolPlanDetails> findStationByPatrolPlanID(String patrolPlanID) {
		return ppmapper.findStationByPatrolPlanID(patrolPlanID);
	}

	@Override
	public void updatePatrolPlan(PatrolPlan pp) {
		ppmapper.updateByPrimaryKeySelective(pp);
	}

	@Override
	public void deletePatrolPlanDetailsByPatrolPlanID(String patrolplanid) {
		ppdmapper.deletePatrolPlanDetailsByPatrolPlanID(patrolplanid);
	}

	@Override
	public void deletePatrolPlan(String id) {
		ppmapper.deleteByPrimaryKey(id);
	}

	@Override
	public String selectSidByPPID(String id) {
		PatrolPlanDetails details =  ppdmapper.selectByPrimaryKey(id);
		//List<String> list= new ArrayList<String>();
		
		return details.getSid();
	}

	@Override
	public String selectMaxCode(HashMap<String,String> prefix) {
		return ppmapper.selectMaxCode(prefix);
	}

	@Override
	public PatrolSpecialIssue findPatrolSpecialIssueByid(String id)
			throws Exception {
		// TODO Auto-generated method stub
		return ppmapper.findPatrolSpecialIssueByid(id);
	}

	@Override
	public List<PatrolSpecialIssue> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ppmapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ppmapper.pageCount(map);
	}
	
	@Override
	public int pageCountre(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ppmapper.pageCountre(map);
	}

	@Override
	public void update(PatrolSpecialIssue patrolSpecialIssue) throws Exception {
		ppmapper.update(patrolSpecialIssue);
		
	}

	@Override
	public void distory(String id) {
		// TODO Auto-generated method stub
		 ppmapper.distory(id);
	}

	@Override
	public void insert(PatrolSpecialIssue patrolSpecialIssue) {
		// TODO Auto-generated method stub
		ppmapper.insert(patrolSpecialIssue);
	}

	@Override
	public String selectUser(Map<String, Object> mapuser) {
		// TODO Auto-generated method stub
	
		return 	ppmapper.selectUser(mapuser);
	}

	@Override
	public List<PatrolSpecialIssue> findByPagere(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ppmapper.findByPagere(map);
	}


}
