package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.util.RealTimeUnitReport;

public interface UnitMapper {
    int deleteByPrimaryKey(String id);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);
    
    List<Unit> findByPage(Map<String,String> map);
    
    List<Station> selectStation();
    
    int pageCount(Map<String,String> map);
    
    String selectCode(Map<String,String> map);

	String nameOnlyOne(Map<String, String> map);

	List<Unit> selectAll();

	int selectcount();

	void deleteBySid(String id);

	List<Unit> findUnit(Map<String, Object> map);

	List<RealTimeUnitReport> findRealTimeReportUnitList(Map<String, Object> map);
}