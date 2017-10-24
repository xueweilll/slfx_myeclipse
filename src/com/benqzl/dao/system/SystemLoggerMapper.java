package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.SystemLogger;

public interface SystemLoggerMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemLogger record);

    int insertSelective(SystemLogger record);

    SystemLogger selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLogger record);

    int updateByPrimaryKey(SystemLogger record);
    
	List<SystemLogger> findByPage(Map<String, String> map);

	int pageCount(Map<String, String> map);
}