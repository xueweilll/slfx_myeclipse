package com.benqzl.service.patrol.engineering;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolSpecialProjectReportMapper;
import com.benqzl.pojo.patrol.PatrolImplementDetails;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.patrol.PatrolSpecialProjectReport;

@Service("patrolSpecialProjectReportService")
public class PatrolSpecialProjectReportServiceImpl implements
		PatrolSpecialProjectReportService {

	@Autowired
	private PatrolSpecialProjectReportMapper mapper;
	
	@Override
	public List<PatrolSpecialDeaprtmentReport> findBypage(
			Map<String, Object> map) {
		return mapper.findBypage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public List<PatrolSpecialProjectReport> findManageByPage(
			Map<String, Object> map) {
		return mapper.findManageByPage(map);
	}

	@Override
	public int pageManageCount(Map<String, Object> map) {
		return mapper.pageManageCount(map);
	}

	@Override
	public List<PatrolSpecialDeaprtmentReport> selectByIssueId(
			Map<String, Object> map) throws Exception {
		return mapper.selectByIssueId(map);
	}

	@Override
	public List<PatrolSpecialIssue> findIssueByPage(Map<String, Object> map)
			throws Exception {
		return mapper.findIssueByPage(map);
	}

	@Override
	public int pageIssueCount(Map<String, Object> map) throws Exception {
		return mapper.pageIssueCount(map);
	}

	@Override
	public List<PatrolImplementDetails> selectValsByIsid(String id)
			throws Exception {
		return mapper.selectValsByIsid(id);
	}

	@Override
	public int updateFlow(Map<String, Object> map) throws Exception {
		return mapper.updateFlow(map);
	}
	

}
