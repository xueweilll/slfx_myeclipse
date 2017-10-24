package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceDetails;

public interface MaintenanceService {

	List<Maintenance> findByPage(Map<String, Object> map);
	
	int pageCount(Map<String, Object> map);
	
	int insertSelective(Maintenance record);

	int insertMaintenanceDtails(List<MaintenanceDetails> list);
	
	int deleteByMid(String maintenanceid);
	
	int updateMaintenance(Maintenance record);
	
	Maintenance selectByPK(String id);
	
	List<MaintenanceDetails> selectByMid(String maintenanceid);
}
