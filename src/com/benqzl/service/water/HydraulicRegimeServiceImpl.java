package com.benqzl.service.water;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.TrGaterunMapper;
import com.benqzl.dao.water.TrPumprunMapper;
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;

@Service("hydraulicRegimeService")
public class HydraulicRegimeServiceImpl implements HydraulicRegimeService{
	@Autowired
	private TrPumprunMapper pumprunMapper;
	@Autowired
	private TrGaterunMapper gaterunMapper;
	
	@Override
	public int insertGaterun(List<TrGaterun> record) {
		// TODO Auto-generated method stub
		return gaterunMapper.insert(record);
	}

	@Override
	public int insertPumprun(List<TrPumprun> record) {
		// TODO Auto-generated method stub
		return pumprunMapper.insert(record);
	}

	
}
