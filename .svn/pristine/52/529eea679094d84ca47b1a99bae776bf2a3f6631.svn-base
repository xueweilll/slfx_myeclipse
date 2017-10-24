package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.MaintenanceDetails;

public interface MaintenanceDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<MaintenanceDetails> record);

    int insertSelective(MaintenanceDetails record);

    MaintenanceDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaintenanceDetails record);

    int updateByPrimaryKey(MaintenanceDetails record);
	
	int deleteByMid(String maintenanceid);
	
	List<MaintenanceDetails> selectByMid(String maintenanceid);

	List<MaintenanceDetails> findDetailsById(Map<String, Object> map);
}