package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import com.benqzl.dao.patrol.PatrolSpecialIssueMapper;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
@Service("PatrolSpecialIssueService")
public class PatrolSpecialIssueServiceImpl implements PatrolSpecialIssueService{
	@Autowired
	private PatrolSpecialIssueMapper patrolSpecialIssueMapper;

	@Override
	public List<PatrolSpecialIssue> selectEgpatrolByPage(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialIssueMapper.selectEgpatrolByPage1(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialIssueMapper.pageCount(map);
	}

	@Override
	public PatrolSpecialIssue findIssueById(String id) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialIssueMapper.findIssueById(id);
	}

	@Override
	public List<PatrolSpecialIssue> selectRegularEgpatrolByPage(
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialIssueMapper.selectRegularEgpatrolByPage(map);
	}

	@Override
	public int regularPageCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialIssueMapper.regularPageCount(map);
	}

	

	

}
