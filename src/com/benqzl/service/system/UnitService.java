package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.util.RealTimeUnitReport;

public interface UnitService {
	List<Station> selectStation();
	int insert(Unit unit);
	int updateByPrimaryKeySelective(Unit unit);
	Unit selectByPrimaryKey(String id);
	int deleteByPrimaryKey(String id);
	List<Unit> findByPage(Map<String,String> map);
	int pageCount(Map<String,String> map);
	String selectCode(Map<String,String> map);
	String nameOnlyOne(Map<String, String> map);
	List<Unit> selectAll();
	int selectcount();
	List<Unit> findUnit(Map<String, Object> map);
	List<RealTimeUnitReport> findRealTimeReportUnitList(Map<String, Object> map);
}
