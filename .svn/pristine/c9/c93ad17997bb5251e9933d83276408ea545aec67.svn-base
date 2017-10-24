package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Station;

public interface StationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Station record);

    int insertSelective(Station record);

    Station selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);
    
    List<Station> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    void deleteIsdel(String id);
    
    List<Station> stationByFk();
    
    int exsitCode(String code);
    
    int exsitName(String name);
    
    List<Station> selectByAll();
    
    List<Station> selectBYCode(Map<String,Object> map);

	Station findStationInformation(Map<String, Object> map);

	List<Station> findStation();

	List<Station> findStations(Map<String, Object> map);

	List<String> findDepartmentIdsBySids(List<String> list);

	List<Station> findSelStation(Map<String, Object> map);

	List<Station> findStationByPAndB();

	Station findStationName();

	int selectcount();

	List<Station> selectByDepartment(String sid);

	List<Station> patrolnormalstation(String userid);

	List<Station> findstationids(String did);

	List<Station> findstationlist(String did);
	
	List<Station> selectByAppCamera();

	List<Station> selectSpecialByDepartment(Map<String, Object> map);

}