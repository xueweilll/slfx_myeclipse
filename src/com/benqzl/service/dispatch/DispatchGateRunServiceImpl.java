package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchExecuteGateMapper;
import com.benqzl.dao.dispatch.SelfDispatchExecuteGateMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;

@Service("dispatchGateRun")
public class DispatchGateRunServiceImpl implements DispatchGateRunService {

	@Autowired
	private ReceiptDispatchExecuteGateMapper rdegmapper;
	
	@Autowired
	private SelfDispatchExecuteGateMapper sdegmapper;
	
	@Override
	public List<ReceiptDispatchExecuteGate> selectByTotal(Map<String, Object> map) {
		return rdegmapper.selectByTotal(map);
	}

	@Override
	public int selectByCount(Map<String, Object> map) {
		return rdegmapper.selectByCount(map);
	}
	

	@Override
	public List<SelfDispatchExecuteGate> findPageByTotal(Map<String, Object> map) {
		return sdegmapper.findPageByTotal(map);
	}

	@Override
	public int findPageByCount(Map<String, Object> map) {
		return sdegmapper.findPageByCount(map);
	}

	@Override
	public List<ReceiptDispatchExecuteGate> selectByTotalPrint(
			Map<String, Object> map) {
		return rdegmapper.selectByTotalPrint(map);
	}

	@Override
	public List<SelfDispatchExecuteGate> findPageByTotalPrint(
			Map<String, Object> map) {
		return sdegmapper.findPageByTotalPrint(map);
	}

}
