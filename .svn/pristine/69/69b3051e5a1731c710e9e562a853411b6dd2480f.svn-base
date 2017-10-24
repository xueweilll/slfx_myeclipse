package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.OperateLogger;
import com.benqzl.pojo.system.User;

public interface OperateLoggerMapper {
	int deleteByPrimaryKey(String id);

	int insert(OperateLogger record);

	int insertSelective(OperateLogger record);

	OperateLogger selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(OperateLogger record);

	int updateByPrimaryKey(OperateLogger record);

	List<OperateLogger> selectAll();

	List<OperateLogger> findByPage(Map<String, String> map);

	int pageCount(Map<String, String> map);
	
	List<User> selectUserbyParentID();
}