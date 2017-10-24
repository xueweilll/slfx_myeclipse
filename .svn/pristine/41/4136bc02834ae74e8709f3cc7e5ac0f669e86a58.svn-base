package com.benqzl.service.water;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.TrWarnlogMapper;
import com.benqzl.pojo.water.TrWarnlog;

@Service("warnCenterService")
public class WarnCenterServiceImpl implements WarnCenterService{

	@Autowired
	private TrWarnlogMapper mapper;
	
	@Override
	public int insert(List<TrWarnlog> record) {
		return mapper.insert(record);
	}

}
