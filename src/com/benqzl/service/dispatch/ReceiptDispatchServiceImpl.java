package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchCallbackMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchDepartmentMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchExecuteMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchInstructionsMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchStationsMapper;
import com.benqzl.dao.dispatch.ReceiptDispatchSuperviseMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchCallback;
import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.dispatch.ReceiptDispatchSupervise;
import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;

@Service("receiptDispatchService")
public class ReceiptDispatchServiceImpl implements ReceiptDispatchService{

	@Autowired
	ReceiptDispatchMapper rdmapper;
	
	@Autowired
	ReceiptDispatchStationsMapper rdsmapper;
	
	@Autowired
	ReceiptDispatchInstructionsMapper rdimapper;
	
	@Autowired
	ReceiptDispatchCallbackMapper rdcmapper;
	
	@Autowired
	ReceiptDispatchSuperviseMapper rdvmapper;
	
	@Autowired
	ReceiptDispatchExecuteMapper rdeMapper;
	@Autowired
	ReceiptDispatchDepartmentMapper rddmapper;
	
	@Override
	public List<ReceiptDispatch> findRdSendList(Map<String, Object> map) {
		return rdmapper.findRdSendList(map);
	}

	@Override
	public int findRdSendCount(Map<String, Object> map) {
		return rdmapper.findRdSendCount(map);
	}

	@Override
	public ReceiptDispatch selectByPk(String id) {
		return rdmapper.selectByPk(id);
	}

	@Override
	public List<ReceiptDispatchStations> selectStationsByRDID(Map<String,Object> map) {
		return rdsmapper.selectByRDID(map);
	}

	@Override
	public List<ReceiptDispatchInstructions> selectInstructionsByRDID(String id) {
		return rdimapper.selectByRDID(id);
	}

	@Override
	public void updateByPrimaryKeySend(Map<String, Object> map) {
		 rdmapper.updateByPrimaryKeySend(map);
		 rddmapper.updateById(map);
	}

	@Override
	public int insertCallback(ReceiptDispatchCallback callback) {
		return rdcmapper.insert(callback);
	}

	@Override
	public int insertSupervise(ReceiptDispatchSupervise supervise) {
		return rdvmapper.insert(supervise);
	}

	@Override
	public List<ReceiptDispatch> findRdList(Map<String, Object> map) {
		return rdcmapper.findRdList(map);
	}

	@Override
	public int findRdCount(Map<String, Object> map) {
		return rdcmapper.findRdCount(map);
	}

	@Override
	public int updateByPrimaryKeyState(Map<String, Object> map) {
		return rdmapper.updateByPrimaryKeyTransmit(map);
	}

	@Override
	public List<ReceiptDispatchTotal> selectTotalByRDID(String id) {
		return rdeMapper.selectTotalByRDID(id);
	}

	@Override
	public List<ReceiptDispatchStations> selectStationByUserid(
			Map<String, Object> mu) {
		return rdsmapper.selectStationByUserid(mu);
	}

	@Override
	public void updateBySend(HashMap<String, Object> map) {
		 rdmapper.updateBySend(map);
		
	}

	@Override
	public List<ReceiptDispatch> findRdSendPrint(Map<String, Object> map) {
		return rdmapper.findRdSendPrint(map);
	}

	@Override
	public ReceiptDispatch selectByMobile(String id) {
		return rdmapper.selectByMobile(id);
	}

}
