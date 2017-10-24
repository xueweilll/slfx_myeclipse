package com.benqzl.service.patrol;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolNormalDetailsMapper;
import com.benqzl.dao.patrol.PatrolNormalReportDetailsMapper;
import com.benqzl.dao.patrol.PatrolNormalReportMapper;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalReport;
import com.benqzl.pojo.patrol.PatrolNormalReportDetails;
@Service("departmentPatrol")
public class DepartmentPatrolServiceImpl implements DepartmentPatrolService {
	@Autowired
	private PatrolNormalReportMapper mapper;
	@Autowired
	private PatrolNormalReportDetailsMapper detailsMapper;
	@Autowired
	private PatrolNormalDetailsMapper normalDetailsMapper;
	@Override
	public List<PatrolNormalReport> selectReportDetailsList(
			Map<String, Object> map) throws Exception {
		return mapper.selectReportDetailsList(map);
	}

	@Override
	public List<PatrolNormalReport> findByPage(Map<String, Object> map)
			throws Exception {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) throws Exception {
		return mapper.pageCount(map);
	}

	@Override
	public void insert(PatrolNormal normal) throws Exception {
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();
		for (PatrolNormalDetails details : normal.getPds()) {
			set.add(details.getPatrolid());
			list.add(details.getId());
		}
		PatrolNormalReport report = new PatrolNormalReport();
		report.setId(UUID.randomUUID().toString());
		report.setReporter(normal.getCreater());
		report.setReporttime(new Date());
		report.setState(new Long(2));
		List<PatrolNormalReportDetails> details = new ArrayList<>();
		for (String string : set) {
			PatrolNormalReportDetails detail = new PatrolNormalReportDetails();
			detail.setId(UUID.randomUUID().toString());
			detail.setPatrolid(string);
			detail.setReportid(report.getId());
			details.add(detail);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("type", 1);
		if(normal.getDegree()==1){
			report.setType(1);
			map.put("handletype", new Long(4));
		}else{
			report.setType(3);
			map.put("handletype", new Long(3));
		}
		mapper.insertSelective(report);
		detailsMapper.insert(details);
		normalDetailsMapper.updateDetailsByList(map);
	}

}
