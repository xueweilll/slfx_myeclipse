package com.benqzl.dao.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;

/**
 * 
* @ClassName: SelfDispatchStationsMapper  
* @Description: SelfDispatchStationsMapper(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2016年1月5日 下午1:15:52  
*
 */
public interface SelfDispatchStationsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDispatchStations record);

    int insertSelective(SelfDispatchStations record);

    SelfDispatchStations selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDispatchStations record);

    int updateByPrimaryKey(SelfDispatchStations record);

	List<SelfDispatchStations> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	SelfDispatchStations selectBySDIDAndSId(HashMap<String, String> map);

	User findUserById(String userid);

	List<Unit> findUnit(String sid);

	Unit selectUnitByNameAndSID(HashMap<String, String> map);

	List<SelfDispatchStations> selectSDStationBySDID(String id);
	
	List<String> selectstations(String id);
	
	List<SelfDispatchStations> selectStationByUserid(Map<String, Object> map);

	void insertStations(List<SelfDispatchStations> selfstations);
}