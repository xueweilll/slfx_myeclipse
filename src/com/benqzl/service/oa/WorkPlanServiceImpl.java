package com.benqzl.service.oa;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;









import com.benqzl.dao.oa.WorkPlanMapper;
import com.benqzl.pojo.oa.WorkPlan;

@Service("workplanService")
public class WorkPlanServiceImpl implements WorkPlanService {

	@Autowired
	private WorkPlanMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WorkPlan record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(WorkPlan record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public WorkPlan selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkPlan record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WorkPlan record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<WorkPlan> selectByMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectByMonth(map);
	}

	@Override
	public int deleteState(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteState(id);
	}

	@Override
	public List<WorkPlan> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageCount(map);
	}

}
