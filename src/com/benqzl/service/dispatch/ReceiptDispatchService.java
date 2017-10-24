package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchCallback;
import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.dispatch.ReceiptDispatchSupervise;
import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;

public interface ReceiptDispatchService {

	List<ReceiptDispatch> findRdSendList(Map<String, Object> map);

	int findRdSendCount(Map<String, Object> map);

	ReceiptDispatch selectByPk(String id);
	
	ReceiptDispatch selectByMobile(String id);

	List<ReceiptDispatchStations> selectStationsByRDID(Map<String, Object> map);

	List<ReceiptDispatchInstructions> selectInstructionsByRDID(String id);

	void updateByPrimaryKeySend(Map<String, Object> map);

	int insertCallback(ReceiptDispatchCallback callback);

	int insertSupervise(ReceiptDispatchSupervise supervise);

	List<ReceiptDispatch> findRdList(Map<String, Object> map);

	int findRdCount(Map<String, Object> map);
	
	int updateByPrimaryKeyState(Map<String, Object> map);
	

	List<ReceiptDispatchTotal> selectTotalByRDID(String id);

	List<ReceiptDispatchStations> selectStationByUserid(Map<String, Object> mu);

	void updateBySend(HashMap<String, Object> map);

	List<ReceiptDispatch> findRdSendPrint(Map<String, Object> map);
}
