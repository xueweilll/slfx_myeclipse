package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialResolve;
import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;
import com.benqzl.pojo.system.Department;

public interface PatrolSpecialResolveService {
	//根据id查询
	PatrolSpecialResolve findegpatrolById(String id) throws Exception;
	//根据id更新数据
	int updateByPrimaryKeySelective(PatrolSpecialResolve record) throws Exception;
	//插入数据
	int insertSelective(PatrolSpecialResolve record) throws Exception;
	//查询部门
	List<Department> selectDepartmentResultMap(Map<String, Object> map2) throws Exception;
	//插入resolveDetail集合类型
	void insertResolveDetail(List<PatrolSpecialResolveDetails> list) throws Exception;
	//根据isid删除数据
	int deleteByIsid(String isid) throws Exception;
	//查询部门map
	List<PatrolSpecialResolveDetails> findResolveDetails(Map<String, Object> map) throws Exception;
}