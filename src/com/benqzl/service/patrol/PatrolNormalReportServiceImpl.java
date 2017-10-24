package com.benqzl.service.patrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolNormalDetailsMapper;
import com.benqzl.dao.patrol.PatrolNormalMapper;
import com.benqzl.dao.patrol.PatrolNormalReportDetailsMapper;
import com.benqzl.dao.patrol.PatrolNormalReportMapper;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDepartmentSearch;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalReport;
import com.benqzl.pojo.patrol.PatrolNormalReportDetails;

@Service("patrolNormalReportService")
public class PatrolNormalReportServiceImpl implements PatrolNormalReportService {

	@Autowired
	private PatrolNormalReportMapper mapper;

	@Autowired
	private PatrolNormalDetailsMapper pndmapper;

	@Autowired
	private PatrolNormalReportDetailsMapper detailsMapper;
	
	@Autowired
	private PatrolNormalMapper pnmapper;

	@Override
	public List<PatrolNormal> findByDepartment(Map<String, Object> map) {
		return mapper.findByDepartment(map);
	}

	@Override
	public int countByDepartment(Map<String, Object> map) {
		return mapper.countByDepartment(map);
	}

	@Override
	public List<PatrolNormalDetails> selectByDepartment(Map<String, Object> map) {
		return pndmapper.selectByDepartmentss(map);
	}
	
	@Override
	public void insert(PatrolNormalReport report,
			List<PatrolNormalReportDetails> list, List<String> ls, int type) {
		mapper.insertSelective(report);
		detailsMapper.insert(list);
		List<String> patrols = new ArrayList<>();
		for(PatrolNormalReportDetails pnrd : list){
			patrols.add(pnrd.getPatrolid());
		}
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("list", patrols);
		pmap.put("state", 2);
		pnmapper.updateState(pmap);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("handletype", new Long(1));
		map1.put("list", patrols);
		pndmapper.updateDetailsByList(map1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ls);
		map.put("type", 1);
		map.put("handletype", new Long(2));
		pndmapper.updateDetailsByList(map);
		
	}

	@Override
	public void insert2(PatrolNormalReport report,
			List<PatrolNormalReportDetails> list, List<String> ls, int type) {
//		mapper.insertSelective(report);
//		detailsMapper.insert(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ls);
		if (type == 0) {
			map.put("handletype", new Long(1));
			pndmapper.updateDetailsByList(map);
		} else {
			map.put("handletype", new Long(2));
			pndmapper.updateDetailsByList(map);
		}
	}

	@Override
	public List<PatrolNormalDepartmentSearch> findByDepartmentPage(
			Map<String, Object> map) {
		return mapper.findByDepartmentPage(map);
	}

	@Override
	public int findByDepartmentCount(Map<String, Object> map) {
		return mapper.findByDepartmentCount(map);
	}

}
