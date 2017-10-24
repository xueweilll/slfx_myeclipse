package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolSpecialResolveDetailsMapper;
import com.benqzl.dao.patrol.PatrolSpecialResolveMapper;
import com.benqzl.pojo.patrol.PatrolSpecialResolve;
import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;
import com.benqzl.pojo.system.Department;

@Service("PatrolSpecialResolveService")
public class PatrolSpecialResolveServiceImpl implements PatrolSpecialResolveService{
	@Autowired
	private PatrolSpecialResolveMapper patrolSpecialResolveMapper;
	@Autowired
	private PatrolSpecialResolveDetailsMapper patrolSpecialResolveDetailsMapper;
	@Override
	public PatrolSpecialResolve findegpatrolById(String id) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialResolveMapper.findegpatrolById(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PatrolSpecialResolve record)
			throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialResolveMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public int insertSelective(PatrolSpecialResolve record) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialResolveMapper.insert(record);
	}

	@Override
	public List<Department> selectDepartmentResultMap(Map<String, Object> map2) throws Exception {
		// TODO Auto-generated method stub
		List<Department> list=patrolSpecialResolveMapper.selectDepartmentResultMap(map2);
		return list;
	}

	@Override
	public void insertResolveDetail(List<PatrolSpecialResolveDetails> list)
			throws Exception {
		// TODO Auto-generated method stub
		patrolSpecialResolveDetailsMapper.insertResolveDetail(list);
	}

	@Override
	public int deleteByIsid(String isid) throws Exception {
		// TODO Auto-generated method stub
		patrolSpecialResolveDetailsMapper.deleteByResolvedetailsKey(isid);
		patrolSpecialResolveMapper.deleteByIsid(isid);
		return 0;
	}

	@Override
	public List<PatrolSpecialResolveDetails> findResolveDetails(
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return patrolSpecialResolveDetailsMapper.findResolveDetails(map);
	}

	
	

	
	
}
