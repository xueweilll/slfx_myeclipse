package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.DispatchInstructionsMapper;
import com.benqzl.dao.dispatch.SelfDispatchExecuteMapper;
import com.benqzl.dao.dispatch.SelfDispatchExecutePeopleMapper;
import com.benqzl.dao.dispatch.SelfDispatchExecuteUnitsMapper;
import com.benqzl.dao.dispatch.SelfDispatchInstructionsMapper;
import com.benqzl.dao.dispatch.SelfDispatchMapper;
import com.benqzl.dao.dispatch.SelfDispatchStationsMapper;
import com.benqzl.dao.system.UserMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchExecute;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecutePeople;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;

/**
 * 
* @ClassName: SelfDispatchStationsServiceImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2016年1月4日 上午11:01:58  
*
 */
@Service("selfDispatchStationsService")
public class SelfDispatchStationsServiceImpl implements SelfDispatchStationsService {

	@Autowired
	private SelfDispatchMapper sdmapper;
	
	@Autowired
	private SelfDispatchStationsMapper mapper;
	
	@Autowired
	SelfDispatchExecuteMapper executeMapper;
	
	@Autowired
	SelfDispatchExecutePeopleMapper peopleMapper;
	
	@Autowired
	SelfDispatchInstructionsMapper sdiMapper;
	
	@Autowired
	SelfDispatchExecuteUnitsMapper sdeuMapper;
	 
	@Autowired
	DispatchInstructionsMapper dim;
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<SelfDispatchStations> findByPage(Map<String, Object> map) {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public int insertSelective(SelfDispatchExecute sde) throws Exception {
		return executeMapper.insertSelective(sde);
	}

	@Override
	public int insertSelective(SelfDispatchExecutePeople sdep) throws Exception {
		return peopleMapper.insertSelective(sdep);
	}

	@Override
	public SelfDispatchStations selectBySDIDAndSId(HashMap<String, String> map) {
		return mapper.selectBySDIDAndSId(map);
	}

	@Override
	public SelfDispatchExecute selectBySid(String sid) {
		return executeMapper.selectBySid(sid);
	}

	@Override
	public User findUserById(String userid) {
		return mapper.findUserById(userid);
	}

	@Override
	public List<SelfDispatch> findAll(Map<String, Object> map) {
		return sdmapper.findAll(map);
	}

	@Override
	public List<SelfDispatchInstructions> selectBySDID(String sdid) {
		return sdiMapper.selectBySDID(sdid);
	}

	@Override
	public SelfDispatchStations selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(SelfDispatchStations sds) {
		return mapper.updateByPrimaryKeySelective(sds);
	}

	@Override
	public List<Unit> findUnit(String sid) {
		return mapper.findUnit(sid);
	}

	@Override
	public Unit selectUnitByName(HashMap<String, String> map) {
		return mapper.selectUnitByNameAndSID(map);
	}

	@Override
	public int insertSelective(SelfDispatchExecuteUnits sdeu) throws Exception {
		return sdeuMapper.insertSelective(sdeu);
	}

	@Override
	public List<String> selectstations(String id) {
		return mapper.selectstations(id);
	}

	@Override
	public SelfDispatchExecute selectExecuteByDispatchstationId(String dispatchstationid) {
		return executeMapper.selectExecuteByDispatchstationId(dispatchstationid);
	}

	@Override
	public List<SelfDispatchExecutePeople> selectExecutePeopleByExecuteId(
			String executeid) {
		return peopleMapper.selectExecutePeopleByExecuteId(executeid);
	}

	@Override
	public User selectUserByID(String uid) {
		return userMapper.selectByPrimaryKey(uid);
	}

	@Override
	public List<SelfDispatchExecuteUnits> selectUnitByExecuteId(String executeid) {
		return sdeuMapper.selectUnitByExecuteId(executeid);
	}

	@Override
	public int updateExecuteByDispatchstationid(SelfDispatchExecute sde) {
		return executeMapper.updateExecuteByDispatchstationid(sde);
	}

	@Override
	public List<SelfDispatchStations> findDispatch2(Map<String, Object> map) {
		return dim.findDispatch2(map);
	}

	@Override
	public int pageCount2(Map<String, Object> map) {
		return dim.pageCount2(map);
	}

	@Override
	public SelfDispatchStations selectstationById(String stationid) {		
		return dim.selectstationById(stationid);
	}
	
	@Override
	public SelfDispatchStations selectstationById2(String stationid) {		
		return dim.selectstationById2(stationid);
	}

	@Override
	public List<SelfDispatchInstructions> findInstructionsByid(String sdid) {
		return dim.findInstructionsByid(sdid);
	}

	@Override
	public List<User> findUser() {		
		return dim.findUser();
	}

	@Override
	public List<Unit> findUnit2(String sid) {		
		return dim.findUnit2(sid);
	}

	@Override
	public List<Gate> findGate(String sid) {
		return dim.findGate(sid);
	}

	@Override
	public void insertExecute(SelfDispatchExecute sde) {
		dim.insertExecute(sde);
	}

	@Override
	public List<String> findUseridsByname(User u) {
		return dim.findUseridsByname(u);
	}

	@Override
	public List<String> findUnitsByname(Unit unit) {		
		return dim.findUnitsByname(unit);
	}

	@Override
	public void insertExecutePeople(SelfDispatchExecutePeople sdep) {
		dim.insertExecutePeople(sdep);
	}

	@Override
	public void insertExecuteUnit(SelfDispatchExecuteUnits sdeu) {
		dim.insertExecuteUnit(sdeu);
	}

	@Override
	public void insertExecuteGate(SelfDispatchExecuteGate sdeg) {
		dim.insertExecuteGate(sdeg);
	}

	@Override
	public void updateDispatchStations(String dispatchstationid) {
		dim.updateDispatchStations(dispatchstationid);
	}

	@Override
	public void updateDispatchStations2(String stationid) {
		dim.updateDispatchStations2(stationid);
	}

	@Override
	public SelfDispatchExecute selectExecuteByStationId(String stationid) {
		return dim.selectExecuteByStationId(stationid);
	}

	@Override
	public SelfDispatchExecute selectExecuteByStationId2(String stationid) {		
		return dim.selectExecuteByStationId2(stationid);
	}

	@Override
	public void updateExecute(SelfDispatchExecute sde) {
		dim.updateExecute(sde);
	}

	@Override
	public void deleteExecutePeople(String executeid) {
		dim.deleteExecutePeople(executeid);
	}

	@Override
	public void deleteExecuteUnit(String executeid) {
		dim.deleteExecuteUnit(executeid);
	}

	@Override
	public void deleteExecuteGate(String executeid) {
		dim.deleteExecuteGate(executeid);
	}

	@Override
	public List<SelfDispatchExecuteUnits> findUnitByExecuteid(String executeid) {		
		return dim.findUnitByExecuteid(executeid);
	}

	@Override
	public List<SelfDispatchExecuteGate> findGateByExecuteid(String executeid) {		
		return dim.findGateByExecuteid(executeid);
	}

	@Override
	public void updateDispatchCompleteTime(SelfDispatch sd) {
		dim.updateDispatchCompleteTime(sd);
	}

	@Override
	public SelfDispatch findSelfDispatchBySdstationid(String dispatchstationid) {
		return dim.findSelfDispatchBySdstationid(dispatchstationid);
	}

	@Override
	public List<SelfDispatchStations> findDispatch2NotAll(
			Map<String, Object> map) {		
		return dim.findDispatch2NotAll(map);
	}

	@Override
	public List<ReceiptDispatchTotal> selectTotalBySDID(String id) {
		return sdmapper.selectTotalBySDID(id);
	}

	@Override
	public List<SelfDispatchStations> selectStationByUserid(
			Map<String, Object> map) {
		return mapper.selectStationByUserid(map);
	}

	@Override
	public void delete(String id) {
		mapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void insertStations(List<SelfDispatchStations> selfstations) {
		mapper.insertStations(selfstations);
		
	}

	@Override
	public int countExecuteByStationId2(String dispatchstationid) {
		return dim.countExecuteByStationId2(dispatchstationid);
	}


}
