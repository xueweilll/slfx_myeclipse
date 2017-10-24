package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchExecuteGateMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchExecuteMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchExecutePeopleMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchExecuteUnitsMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchInstructionsMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchStationsMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecute;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecutePeople;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;

/**
 * 
* @ClassName: ReceiptDispatchStationsServiceImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2016年1月11日 下午12:52:18  
*
 */
@Service("receiptDispatchStationsService")
public class ReceiptDispatchStationsServiceImpl implements ReceiptDispatchStationsService {
	
	@Autowired
	private ReceiptDispatchMapper rdmapper;
	
	@Autowired
	private ReceiptDispatchExecuteMapper rdemapper;
	
	@Autowired
	private ReceiptDispatchExecutePeopleMapper rdepmapper;
	
	@Autowired
	private ReceiptDispatchExecuteUnitsMapper rdeumapper;
	
	@Autowired
	private ReceiptDispatchStationsMapper rdsmapper;
	
/*	@Autowired
	private ReceiptDispatchInstructionsMapper rdimapper;*/

	@Override
	public List<ReceiptDispatchStations> findRdExecuteList(Map<String, Object> map) {
		return rdmapper.findRdExecuteList(map);
	}

	@Override
	public int findRdExecuteCount(Map<String, Object> map) {
		return rdmapper.findRdExecuteCount(map);
	}
	
	@Autowired
	ReceiptDispatchInstructionsMapper rdiMapper;
	
	@Autowired
	ReceiptDispatchStationsMapper rdsMapper;
	
	@Autowired
	ReceiptDispatchExecuteUnitsMapper rdeuMapper;
	
	@Autowired
	ReceiptDispatchExecuteGateMapper rdegMapper;
	
	@Override
	public User findUserById(String userid) {
		return rdmapper.findUserById(userid);
	}
	
	@Override
	public List<ReceiptDispatchInstructions> selectInstructionsByRPID(String rpid) {
		return rdiMapper.selectInstructionsByRPID(rpid);
	}
	
	@Override
	public ReceiptDispatchStations selectByRDIDAndSId(
			HashMap<String, String> map) {
		return rdsMapper.selectByRDIDAndSId(map);
	}
	
	@Override
	public int updateByPrimaryKeySelective(ReceiptDispatchStations rds) {
		return rdsmapper.updateByPrimaryKeySelective(rds);
	}

	@Override
	public List<Unit> findUnits(String sid) {
		return rdsmapper.findUnit(sid);
	}
	
	@Override
	public List<Gate> findGates(String sid) {
		return rdsmapper.findGate(sid);
	}

	@Override
	public Unit selectUnitByName(HashMap<String, String> map) {
		return rdsmapper.selectUnitByNameAndSID(map);
	}
	
	@Override
	public Gate selectGateByName(HashMap<String, String> map) {
		return rdsmapper.selectGateByNameAndSID(map);
	}

	@Override
	public ReceiptDispatchExecute selectExecuteByDispatchstationId(
			String dispatchstationid) {
		return rdemapper.selectExecuteByDispatchstationId(dispatchstationid);
	}

	@Override
	public List<ReceiptDispatchExecutePeople> selectExecutePeopleByExecuteId(
			String executeid) {
		return rdepmapper.selectExecutePeopleByExecuteId(executeid);
	}

	@Override
	public List<ReceiptDispatchExecuteUnits> selectUnitByExecuteId(
			String executeid) {
		return rdeumapper.selectUnitByExecuteId(executeid);
	}

	@Override
	public int updateRDStationState(Map<String,Object> map) {
		return rdemapper.updateRDStationState(map);
	}
	
	@Override
	public int updateRDStationState_look(Map<String,Object> map) {
		return rdemapper.updateRDStationState_look(map);
	}

	@Override
	public int insertSelective(ReceiptDispatchExecute rde) throws Exception {
		return rdemapper.insertSelective(rde);
	}

	@Override
	public int insertSelective(ReceiptDispatchExecuteUnits rdeu)
			throws Exception {
		return rdeuMapper.insertSelective(rdeu);
	}
	
	@Override
	public int insertSelective(ReceiptDispatchExecuteGate rdeg)
			throws Exception {
		return rdegMapper.insertSelective(rdeg);
	}
	
	@Override
	public int insertSelective(ReceiptDispatchExecutePeople rdep)
			throws Exception {
		return rdepmapper.insertSelective(rdep);
	}

	@Override
	public ReceiptDispatchStations selectByPrimaryKey(String id) {
		return rdsmapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateExecute(ReceiptDispatchExecute rde)throws Exception {
		return rdemapper.updateByPrimaryKeySelective(rde);
	}

	@Override
	public ReceiptDispatchExecute selectExecuteByStationId(String sid) {
		return rdemapper.selectExecuteByStationId(sid);
	}

	@Override
	public List<User> findUser() {
		return rdemapper.findUser();
	}

	@Override
	public List<ReceiptDispatchExecuteUnits> findUnitByExecuteid(String executeid) {
		return rdemapper.findUnitByExecuteid(executeid);
	}
	
	@Override
	public List<ReceiptDispatchExecuteGate> findGateByExecuteid(String executeid) {
		return rdemapper.findGateByExecuteid(executeid);
	}
	
	@Override
	public void deleteExecutePeople(String executeid) {
		rdemapper.deleteExecutePeople(executeid);
	}
	
	@Override
	public void deleteExecuteUnit(String executeid) {
		rdemapper.deleteExecuteUnit(executeid);
	}
	
	@Override
	public void deleteExecuteGate(String executeid) {
		rdemapper.deleteExecuteGate(executeid);
	}

	@Override
	public ReceiptDispatch selectReceiptDispatchByRdid(String rdid) {
		return rdmapper.selectByPrimaryKey(rdid);
	}

	@Override
	public int countExecuteByStationId(String rdstationid) {
		return rdemapper.countExecuteByStationId(rdstationid);
	}

	@Override
	public List<ReceiptDispatchStations> getKeepCountList(Map<String, Object> mu) {
		return rdsmapper.getKeepCountList(mu);
	}
	
}
