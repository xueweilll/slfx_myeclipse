package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.GateMapper;
import com.benqzl.pojo.system.Gate;

@Service("gateService")
public class GateServiceImpl implements GateService{

	@Autowired
	private GateMapper mapper;
	
	@Override
	public List<Gate> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageCount(map);
	}

	@Override
	public void insert(Gate gate) {
		// TODO Auto-generated method stub
		mapper.insert(gate);
	}

	@Override
	public Gate selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(Gate gate) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(gate);
	}

	@Override
	public void deleteIsdel(String id) {
		// TODO Auto-generated method stub
		mapper.deleteIsdel(id);
	}

	@Override
	public int exsitCode(String code) {
		// TODO Auto-generated method stub
		return mapper.exsitCode(code);
	}

	@Override
	public List<Gate> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public String nameOnlyOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.nameOnlyOne(map);
	}

	@Override
	public String codeOnlyOne(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.codeOnlyOne(map);
	}

	@Override
	public int selectcount() {
		// TODO Auto-generated method stub
		return mapper.selectcount();
	}

}
