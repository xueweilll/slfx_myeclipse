package com.benqzl.dao.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.User;
import com.benqzl.pojo.util.ReceiptAndDispatch;

public interface ReceiptDispatchMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReceiptDispatch record);

    int insertSelective(ReceiptDispatch record);

    ReceiptDispatch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatch record);

    int updateByPrimaryKey(ReceiptDispatch record);

	List<ReceiptAndDispatch> findByPage(Map<String, Object> map);
	
	int pageCount(Map<String, Object> map);
	
	List<String> findReceiptIds(String id);

    List<ReceiptDispatch> findRdSendList(Map<String, Object> map);
	
	int findRdSendCount(Map<String, Object> map);
	
	ReceiptDispatch selectByPk(String id);
	
	ReceiptDispatch selectByMobile(String id);
	
	int updateByPrimaryKeySend(Map<String, Object> map);
	
	int updateByPrimaryKeyTransmit(Map<String, Object> map);
	
	int updateByPrimaryKeyState(Map<String, Object> map);

	void insertArea(ReceiptDispatch receipt);

	List<ReceiptDispatchStations> findRdExecuteList(Map<String, Object> map);

	int findRdExecuteCount(Map<String, Object> map);

	User findUserById(String userid);

	void updateState(Map<String, Object> map);

	void updateArea(ReceiptDispatch receiptDispatch);

	void updateAreas(String reid);
	
	void updateStateByReceiptId(String id);

	void updateBySend(HashMap<String, Object> map);

	List<ReceiptDispatch> findRdSendPrint(Map<String, Object> map);

	void updateAreasMap(Map<String, Object> maps);
	
	String selectCode(Map<String, Object> maps);
}