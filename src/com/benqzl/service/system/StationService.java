package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Station;

public interface StationService {

	List<Station> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    void insert(Station station);
	
    Station selectByPrimaryKey(String id);
    
    void updateByPrimaryKey(Station station);
    
    void deleteIsdel(String id);
    
    List<Station> stationByFk();
    
    int exsitCode(String code);
    
    int exsitName(String name);
    
    String selectByAll();
    
    List<Station> selectBYCode(Map<String,Object> map);

	Station findStationInformation(Map<String, Object> map);

	List<Station> findStations(Map<String, Object> map);

	List<Station> findStation();

	List<String> findDepartmentIdsBySids(List<String> list);

	List<Station> findStationByPAndB();

	Station findStationName();

	int selectcount();

	List<Station> selectByDepartment(String sid);

	List<Station> patrolnormalstation(String userid);
	
	List<Station> selectByAppCamera();

	List<Station> selectSpecialByDepartment(Map<String, Object> map);
}
