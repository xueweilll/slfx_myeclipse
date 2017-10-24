package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.SystemLoggerMapper;
import com.benqzl.pojo.system.SystemLogger;
@Service("systemLoggerService")
public class SystemLoggerServiceImpl implements SystemLoggerService {
	
	@Autowired
    private SystemLoggerMapper systemLoggerMapper;


	@Override
	public List<SystemLogger> findByPage(Map<String, String> map) {
		// TODO Auto-generated method stub
		return systemLoggerMapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return systemLoggerMapper.pageCount(map);
	}

}
