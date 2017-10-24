package com.benqzl.service.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.OperateLoggerMapper;
import com.benqzl.pojo.system.OperateLogger;
import com.benqzl.pojo.system.User;
@Service("operaterLoggerService")
public class OperaterLoggerServiceImpl implements OperaterLoggerService {
    @Autowired
    private OperateLoggerMapper operateLoggerMapper;
	@Override
	public List<OperateLogger> selectAll() {
		return operateLoggerMapper.selectAll();
	}

	@Override
	public List<OperateLogger> findByPage(Map<String, String> map) {
		return operateLoggerMapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, String> map) {
		return operateLoggerMapper.pageCount(map);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return operateLoggerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OperateLogger record) {
		return operateLoggerMapper.insert(record);
	}

	@Override
	public int insertSelective(OperateLogger record) {
		return operateLoggerMapper.insertSelective(record);
	}

	@Override
	public OperateLogger selectByPrimaryKey(String id) {
		return operateLoggerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OperateLogger record) {
		return operateLoggerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OperateLogger record) {
		return operateLoggerMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<User> selectUserbyParentID() {
		User user1=new User();
		user1.setUserid("0");
		user1.setUsername("全部");
		List<User> userplus=new ArrayList<>();
		List<User> list=operateLoggerMapper.selectUserbyParentID();
		userplus.add(user1);
		for(User list1:list){	
			userplus.add(list1);	
		}		
		return userplus;
	}

}
