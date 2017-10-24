package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.SelfDispatchDepartment;

public interface SelfDispatchDepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDispatchDepartment record);

    int insertSelective(SelfDispatchDepartment record);

    SelfDispatchDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDispatchDepartment record);

    int updateByPrimaryKey(SelfDispatchDepartment record);

	void insertDepartment(List<SelfDispatchDepartment> selfdepartment);

	List<SelfDispatchDepartment> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);
	
	int updateById(Map<String, Object> map);

	List<SelfDispatchDepartment> findByPage1(Map<String, Object> map);
}