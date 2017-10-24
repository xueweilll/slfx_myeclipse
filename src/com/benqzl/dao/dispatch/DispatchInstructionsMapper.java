package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchExecute;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecutePeople;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;

public interface DispatchInstructionsMapper {
	
	List<SelfDispatch> findDispatch(Map<String, String> map);
	int pageCount(Map<String, String> map);
	List<Station> findStation();
	List<User> findUser();
	List<String> findsidByname(Station station);
	void insertDispatch(SelfDispatch sd);
	void insertInstruction(SelfDispatchInstructions instruction);
	void insertStation(SelfDispatchStations station);
	void deleteInstruction(String id);
	void deleteStation(String id);
	void deleteDispatch(String id);
	SelfDispatch findDispatchByid(Map<String, String> map);
	List<SelfDispatchInstructions> findInstructionsByid(String id);
	List<SelfDispatchStations> findStationsByid(String id);
	String findSnameByid(String id);
	void updateDispatch(SelfDispatch sd);
	String finsidByname(String string);
	List<SelfDispatchStations> findDispatch2(Map<String, Object> map);
	int pageCount2(Map<String, Object> map);
	SelfDispatchStations selectstationById(String stationid);
	SelfDispatchStations selectstationById2(String stationid);
	List<Unit> findUnit();
	List<Unit> findUnit2(String sid);
	List<Gate> findGate(String sid);
	void insertExecute(SelfDispatchExecute sde);
	List<String> findUseridsByname(User u);
	List<String> findUnitsByname(Unit unit);
	void insertExecutePeople(SelfDispatchExecutePeople sdep);
	void insertExecuteUnit(SelfDispatchExecuteUnits sdeu);
	void insertExecuteGate(SelfDispatchExecuteGate sdeg);
	void updateDispatchStations(String dispatchstationid);
	void updateDispatchStations2(String stationid);
	SelfDispatchExecute selectExecuteByStationId(String stationid);
	SelfDispatchExecute selectExecuteByStationId2(String stationid);
	void updateExecute(SelfDispatchExecute sde);
	void deleteExecutePeople(String executeid);
	void deleteExecuteUnit(String executeid);
	void deleteExecuteGate(String executeid);
	List<SelfDispatchExecuteUnits> findUnitByExecuteid(String executeid);
	List<SelfDispatchExecuteGate> findGateByExecuteid(String executeid);
	List<ReceiptDispatch> receptdispatch(Map<String, String> map);
	void updateDispatchCompleteTime(SelfDispatch sd);
	SelfDispatch findSelfDispatchBySdstationid(String dispatchstationid);
	List<SelfDispatchStations> findDispatch2NotAll(Map<String, Object> map);
	List<SelfDispatchStations> findDepartmentStation(Map<String, Object> map);
	int countExecuteByStationId2(String dispatchstationid);
}
