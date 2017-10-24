package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolSpecialExcuteDetailsMapper;
import com.benqzl.dao.patrol.PatrolSpecialExcuteMapper;
import com.benqzl.dao.patrol.PatrolSpecialIssueMapper;
import com.benqzl.dao.patrol.PatrolSpecialResolveDetailsMapper;
import com.benqzl.dao.system.StationMapper;
import com.benqzl.pojo.patrol.PatrolSpecialExcute;
import com.benqzl.pojo.patrol.PatrolSpecialExcuteDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;
import com.benqzl.pojo.system.Station;

@Service("egpatorlDepartemntSendService")
public class EgpatorlDepartemntSendServiceImpl implements
		EgpatorlDepartemntSendService {

	@Autowired
	private PatrolSpecialResolveDetailsMapper psresolvedetails;
	@Autowired
	private PatrolSpecialExcuteMapper psexcute;
	@Autowired
	private PatrolSpecialExcuteDetailsMapper pseccutedetails;
	@Autowired
	private StationMapper stationmapper;
	@Autowired
	private PatrolSpecialIssueMapper issuemapper;
	@Override
	public List<PatrolSpecialResolveDetails> findegpatroldepartment(
			Map<String, Object> map) {
		return psresolvedetails.findegpatroldepartment(map);
	}

	@Override
	public int findegpatroldepartmentcount(Map<String, Object> map) {
		return psresolvedetails.findegpatroldepartmentcount(map);
	}

	@Override
	public void insertegpatorlDepartemnt(PatrolSpecialExcute patrol) {
		
	}

	@Override
	public void insertegpatorlDepartemntlDetails(
			List<PatrolSpecialExcuteDetails> list) {
		pseccutedetails.insertegpatorlDepartemntlDetails(list);
	}

	@Override
	public void deleteByEgPatrolIsId(String isid) {
		pseccutedetails.deleteBypseccutedetailsKey(isid);
		psexcute.deleteBypsexcuteKey(isid);
	}


	@Override
	public void insertegpatorlSpecialExcute(PatrolSpecialExcute patrol) {
		psexcute.insertSelective(patrol);		
	}


	@Override
	public List<Station> findstation(String did) {
		return stationmapper.findstationlist(did);
	}

	@Override
	public PatrolSpecialExcute findexcute(Map<String, Object> map) {
		return psexcute.findexcute(map);
	}

	@Override
	public List<PatrolSpecialExcuteDetails> findExcuteDetails(
			Map<String, Object> map) {
		return pseccutedetails.findExcuteDetails(map);
	}

	@Override
	public PatrolSpecialIssue findIssue(String isid) {
		return issuemapper.findIssueByIsid(isid);
	}

}
