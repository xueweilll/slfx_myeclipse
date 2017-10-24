package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceAduit;

public interface MaintenanceAduitMapper {
    int insert(MaintenanceAduit record);

    int insertSelective(MaintenanceAduit record);

	List<Maintenance> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	void updateTenanceAuit(String reid);
	
	int deleteByPrimaryKey(String id);

	int updateByPrimaryKeySelective(MaintenanceAduit record);

    int updateByPrimaryKey(MaintenanceAduit record);
    
    MaintenanceAduit selectByPrimaryKey(String id);

	MaintenanceAduit findMemo(Map<String, Object> map);
}