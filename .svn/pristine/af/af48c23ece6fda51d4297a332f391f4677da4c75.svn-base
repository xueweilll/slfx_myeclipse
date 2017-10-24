package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.Maintenance;

public interface MaintenanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Maintenance record);

    int insertSelective(Maintenance record);

    Maintenance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Maintenance record);

    int updateByPrimaryKey(Maintenance record);

	List<Maintenance> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	Maintenance findById(String id);

	void updateTenanceAuit(Map<String, Object> map);

	Maintenance findStepMemo(Map<String, Object> map);
}