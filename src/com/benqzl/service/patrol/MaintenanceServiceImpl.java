package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.MaintenanceDetailsMapper;
import com.benqzl.dao.patrol.MaintenanceMapper;
import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceDetails;

@Service("maintenanceService")
public class MaintenanceServiceImpl  implements MaintenanceService{

	@Autowired
	private MaintenanceMapper mapper;
	
	@Autowired
	private MaintenanceDetailsMapper dmapper;
	
	@Override
	public List<Maintenance> findByPage(Map<String, Object> map) {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public int insertSelective(Maintenance record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int insertMaintenanceDtails(List<MaintenanceDetails> list) {
		return dmapper.insert(list);
	}

	@Override
	public int deleteByMid(String maintenanceid) {
		return dmapper.deleteByMid(maintenanceid);
	}

	@Override
	public int updateMaintenance(Maintenance record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Maintenance selectByPK(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<MaintenanceDetails> selectByMid(String maintenanceid) {
		return dmapper.selectByMid(maintenanceid);
	}

}
