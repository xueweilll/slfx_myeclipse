package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchExecuteGateMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchExecutePeopleMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchExecuteUnitsMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecutePeople;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;

@Service("receiptDispatchExecuteService")
public class ReceiptDispatchExecuteServiceImpl implements
		ReceiptDispatchExecuteService {

	@Autowired
	private ReceiptDispatchExecuteUnitsMapper rdeumapper;
	
	@Autowired
	private ReceiptDispatchExecuteGateMapper rdegmapper;
	
	@Autowired
	private ReceiptDispatchExecutePeopleMapper rdepmapper;
	
	@Override
	public void insertUnits(List<ReceiptDispatchExecuteUnits> list) {
		rdeumapper.insert(list);
	}

	@Override
	public void insertGates(List<ReceiptDispatchExecuteGate> list) {
		rdegmapper.insert(list);
	}

	@Override
	public void insertPeoples(List<ReceiptDispatchExecutePeople> list) {
		rdepmapper.insert(list);
	}

	@Override
	public List<ReceiptDispatchExecuteUnits> selectByCheck(
			HashMap<String, Object> map) {
		return rdeumapper.selectByCheck(map);
	}

}
