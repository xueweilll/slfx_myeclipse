package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.UnitMapper;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.util.RealTimeUnitReport;
@Service("UnitService")
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitMapper unitmapper;
	
	@Override
	public List<Station> selectStation(){
		return unitmapper.selectStation();
	}
	@Override
	public int insert(Unit unit){
		return unitmapper.insert(unit);
	}
	@Override
	public int updateByPrimaryKeySelective(Unit unit){
		return unitmapper.updateByPrimaryKeySelective(unit);
	}
	@Override
	public Unit selectByPrimaryKey(String id){
		return unitmapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(String id){
		return unitmapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<Unit> findByPage(Map<String,String> map){
		return unitmapper.findByPage(map);
	}
	@Override
	public int pageCount(Map<String,String> map) {		
		return unitmapper.pageCount(map);
	}
	@Override
	public String selectCode(Map<String,String> map) {		
		return unitmapper.selectCode(map);
	}
	@Override
	public String nameOnlyOne(Map<String, String> map) {		
		return unitmapper.nameOnlyOne(map);
	}
	@Override
	public List<Unit> selectAll() {
		return unitmapper.selectAll();
	}
	@Override
	public int selectcount() {
		// TODO Auto-generated method stub
		return unitmapper.selectcount();
	}
	
	/* 
	* <p>Title: findUnit</p> 
	* <p>Description:查询实时报表，枢纽对应机组 </p> 
	* @param map
	* @return 
	* @see com.benqzl.service.system.UnitService#findUnit(java.util.Map) 
	*/
	@Override
	public List<Unit> findUnit(Map<String, Object> map) {
		return unitmapper.findUnit(map);
	}
	@Override
	public List<RealTimeUnitReport> findRealTimeReportUnitList(Map<String, Object> map) {
		
		return unitmapper.findRealTimeReportUnitList(map);
	}
}
