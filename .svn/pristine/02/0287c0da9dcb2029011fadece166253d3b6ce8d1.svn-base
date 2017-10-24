package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialResolve;
import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.Station;

public interface PatrolSpecialResolveMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolSpecialResolve record);

    int insertSelective(PatrolSpecialResolve record);

    PatrolSpecialResolve selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolSpecialResolve record);

    int updateByPrimaryKey(PatrolSpecialResolve record);

	List<PatrolSpecialResolve> findegpatroldepartment(Map<String, Object> map);

	int findegpatroldepartmentcount(Map<String, Object> map);

	List<Station> findstation();
	//根据id查询
	PatrolSpecialResolve findegpatrolById(String id);
	//查部门
	List<Department> selectDepartmentResultMap(Map<String, Object> map2) throws Exception;
	//根据isid删除数据
	int deleteByIsid(String isid);
}