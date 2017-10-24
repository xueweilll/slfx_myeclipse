package com.benqzl.service.patrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.MaintenanceAduitMapper;
import com.benqzl.dao.patrol.MaintenanceDetailsMapper;
import com.benqzl.dao.patrol.MaintenanceMapper;
import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceAduit;
import com.benqzl.pojo.patrol.MaintenanceDetails;
@Service("MainTenanceAuitService")
public class MainTenanceAduitServiceImpl implements MainTenanceAduitService {
	@Autowired
	private MaintenanceAduitMapper aduitMapper;
	@Autowired
	private MaintenanceMapper mapper;
	@Autowired
	private MaintenanceDetailsMapper detailsMapper;
	@Override
	public List<Maintenance> findByPage(Map<String, Object> map)
			throws Exception {
		
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) throws Exception {
		
		return mapper.pageCount(map);
	}
	@Override
	public void insertAduit(MaintenanceAduit aduit,String step,String state,String suggest) {
		aduitMapper.insert(aduit);
		String id= aduit.getMaintenanceid();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("step", step);
		map.put("state", state);
		map.put("stepmemo", suggest);
		mapper.updateTenanceAuit(map);
		
	}

	@Override
	public void disagreeAduit(MaintenanceAduit aduit,String step,String state,String suggest) {
		aduitMapper.insert(aduit);
		String id=aduit.getMaintenanceid();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("step", step);
		map.put("state", state);
		map.put("stepmemo", suggest);
		mapper.updateTenanceAuit(map);
		
	}

	@Override
	public Maintenance findById(String id) {
		return mapper.findById(id);
	}

	@Override
	public List<MaintenanceDetails> findDetailsById(Map<String, Object> map) {
		
		return detailsMapper.findDetailsById(map);
	}

	@Override
	public Maintenance findStepMemo(Map<String,Object> map) {
		return mapper.findStepMemo(map);
	}

	@Override
	public MaintenanceAduit findMemo(Map<String, Object> map) {
		return aduitMapper.findMemo(map);
	}

	@Override
	public List<Maintenance> findAduitByPage(Map<String, Object> map) {
		return aduitMapper.findByPage(map);
	}

	@Override
	public int pageAduitCount(Map<String, Object> map) {
		return aduitMapper.pageCount(map);
	}

}
