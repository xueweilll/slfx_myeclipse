package com.benqzl.service.dispatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.SelfDispatchExecuteGateMapper;
import com.benqzl.dao.dispatch.SelfDispatchExecutePeopleMapper;
import com.benqzl.dao.dispatch.SelfDispatchExecuteUnitsMapper;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecutePeople;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteUnits;

@Service("selfDispatchExecuteService")
public class SelfDispatchExecuteServiceImpl implements
		SelfDispatchExecuteService {

	@Autowired
	private SelfDispatchExecutePeopleMapper sdepmapper;
	
	@Autowired
	private SelfDispatchExecuteUnitsMapper sdeumapper;
	
	@Autowired
	private SelfDispatchExecuteGateMapper sdegmapper;

	@Override
	public void insertUnits(List<SelfDispatchExecuteUnits> list) {
		sdeumapper.insert(list);
	}

	@Override
	public void insertGates(List<SelfDispatchExecuteGate> list) {
		sdegmapper.insert(list);
	}

	@Override
	public void insertPeople(List<SelfDispatchExecutePeople> list) {
		sdepmapper.insert(list);
	}
	
	
	
}
