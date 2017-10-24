package com.benqzl.dao.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;

public interface ReceiptDispatchStationsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<ReceiptDispatchStations> record);

    ReceiptDispatchStations selectByPrimaryKey(String id);

	List<Station> findStationsId(Map<String, String> map);

	void deleteByRPID(String id);
	
	List<ReceiptDispatchStations> selectByRDID(Map<String, Object> map);

	List<String> findStationsIds(String string);

	void insertStations(List<ReceiptDispatchStations> list);

	ReceiptDispatchStations selectByRDIDAndSId(HashMap<String, String> map);

	List<Unit> findUnit(String sid);
	
	List<Gate> findGate(String sid);

	int updateByPrimaryKeySelective(ReceiptDispatchStations rds);


	List<ReceiptDispatchStations> findrestations(String id);

	void deleteAreaStation(String reid);

	Unit selectUnitByNameAndSID(HashMap<String, String> map);
	
	Gate selectGateByNameAndSID(HashMap<String, String> map);

	void updateStations(List<ReceiptDispatchStations> dispatchStations);

	List<ReceiptDispatchStations> selectStationByUserid(Map<String, Object> mu);
	
	List<ReceiptDispatchStations> getKeepCountList(Map<String, Object> mu);
}