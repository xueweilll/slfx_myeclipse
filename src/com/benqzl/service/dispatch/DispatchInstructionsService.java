package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;

public interface DispatchInstructionsService {
	List<SelfDispatch> findDispatch(Map<String,String> map);	
	int pageCount(Map<String,String> map);
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
	String findsidByname(String string);
	List<ReceiptDispatch> receptdispatch(Map<String, String> map);
	List<Station> findSelfStation(Map<String, Object> map);
	List<SelfDispatchStations> findDepartmentStation(Map<String, Object> map);
}
