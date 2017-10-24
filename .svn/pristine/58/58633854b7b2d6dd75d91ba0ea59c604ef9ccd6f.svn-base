package com.benqzl.service.patrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolSpecialFolwMapper;
import com.benqzl.pojo.patrol.PatrolSpecialFolw;
@Service("PatrolSpecialFolwService")
public class PatrolSpecialFolwServiceImpl implements PatrolSpecialFolwService {

	@Autowired
	PatrolSpecialFolwMapper patrolSpecialFolwMapper;
	
	@Override
	public void issue(String isid) {
		PatrolSpecialFolw patrolSpecialFolw = new PatrolSpecialFolw();
		patrolSpecialFolw.setId(UUID.randomUUID().toString());
		patrolSpecialFolw.setIsid(isid);
		patrolSpecialFolw.setQf((long)0);
		patrolSpecialFolw.setFj((long)0);
		patrolSpecialFolw.setZx((long)0);
		patrolSpecialFolw.setSs((long)0);
		patrolSpecialFolw.setHz((long)0);
		patrolSpecialFolw.setSb((long)0);
		patrolSpecialFolw.setSy((long)0);
		patrolSpecialFolwMapper.insert(patrolSpecialFolw);
	}
  
	@Override
	public void update(String isid) {
		PatrolSpecialFolw patrolSpecialFolw = new PatrolSpecialFolw();
		patrolSpecialFolw.setId(UUID.randomUUID().toString());
		patrolSpecialFolw.setIsid(isid);
		patrolSpecialFolw.setQf((long) 1);
		patrolSpecialFolw.setFj((long)0);
		patrolSpecialFolw.setZx((long)0);
		patrolSpecialFolw.setSs((long)0);
		patrolSpecialFolw.setHz((long)0);
		patrolSpecialFolw.setSb((long)0);
		patrolSpecialFolw.setSy((long)0);
		patrolSpecialFolwMapper.updateFlow(patrolSpecialFolw);
	}
	
	@Override
	public void deleteflow(String isid) {
		patrolSpecialFolwMapper.deleteflow(isid);
		  
	}

	@Override
	public void resolve(String isid) {
		Map<String,Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("qf", 1);
		map.put("fj", 1);
		map.put("zx", 0);
		map.put("ss", 0);
		map.put("hz", 0);
		map.put("sb", 0);
		map.put("sy", 0);
		patrolSpecialFolwMapper.updateByIsId(map);
	}

	@Override
	public void execute(String isid) {
		Map<String,Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("qf", 1);
		map.put("fj", 1);
		map.put("zx", 1);
		map.put("ss", 0);
		map.put("hz", 0);
		map.put("sb", 0);
		map.put("sy", 0);
		patrolSpecialFolwMapper.updateByIsId(map);
	}

	@Override
	public void implement(String isid) {
		Map<String,Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("qf", 1);
		map.put("fj", 1);
		map.put("zx", 1);
		map.put("ss", 1);
		map.put("hz", 0);
		map.put("sb", 0);
		map.put("sy", 0);
		patrolSpecialFolwMapper.updateByIsId(map);
	}

	@Override
	public void collect(String isid) {
		Map<String,Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("qf", 1);
		map.put("fj", 1);
		map.put("zx", 1);
		map.put("ss", 1);
		map.put("hz", 1);
		map.put("sb", 0);
		map.put("sy", 0);
		patrolSpecialFolwMapper.updateByIsId(map);
	}

	@Override
	public void report(String isid) {
		Map<String,Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("qf", 1);
		map.put("fj", 1);
		map.put("zx", 1);
		map.put("ss", 1);
		map.put("hz", 1);
		map.put("sb", 1);
		map.put("sy", 0);
		patrolSpecialFolwMapper.updateByIsId(map);
	}

	@Override
	public void approve(String isid) {
		Map<String,Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("qf", 1);
		map.put("fj", 1);
		map.put("zx", 1);
		map.put("ss", 1);
		map.put("hz", 1);
		map.put("sb", 1);
		map.put("sy", 1);
		patrolSpecialFolwMapper.updateByIsId(map);
	}

	@Override
	public List<String> findDetailsByRid(String rid) {
		
		return patrolSpecialFolwMapper.findDetailsByRid(rid);
	}

	

	
}
