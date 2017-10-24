package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchDepartment;
import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.util.ReceiptAndDispatch;

public interface BigSurroundService {

	List<Receipt> findReceipts(List<String> strings) throws Exception;

	Receipt findReceiptById(String id) throws Exception;

	boolean insert(ReceiptDispatch receiptDispatch) throws Exception;

	List<ReceiptAndDispatch> findByPage(Map<String, Object> map) throws Exception;

	int pageCount(Map<String, Object> map) throws Exception;

	List<String> findReceiptIds(List<String> strings,String type) throws Exception;

	ReceiptDispatch findDispatchById(String id) throws Exception;

	List<Station> findStations(String id) throws Exception;

	List<Map<String, Object>> findInstructions(String id) throws Exception;

	void delete(String id) throws Exception;

	void insertSelective(ReceiptDispatch rd);

	void insertAreaInstruction(List<ReceiptDispatchInstructions> receiptDispatchInstructions) throws Exception;

	void insertAreaStation(List<ReceiptDispatchStations> list);

	Receipt findByTransmitID(String id);

	int updateByPrimaryKeyTransmit(Map<String, Object> map);

	List<String> findStationsIds(String string);

	void insertArea(ReceiptDispatch receipt, String stations);

	void updateState(Map<String, Object> maps);

	List<ReceiptDispatchInstructions> findReceiptInstructions(String id);

	List<ReceiptDispatchStations> findrestations(String id);

	void updateArea(ReceiptDispatch receiptDispatch, String stations);

	void deleteAreaInstruction(String reid);

	void insertAreaInstructions(
			List<ReceiptDispatchInstructions> receiptDispatchInstructions,
			String reid);

	void deleteAreaStation(String reid);

	void insertAreaStations(List<ReceiptDispatchStations> dispatchStations,
			String reid);

	void insertAreaDispacth(ReceiptDispatch receipt, String stations);

	void updateAreas(String reid);	
	
	void updateStateByReceiptId(String id);
	
	void insertDepartment(List<ReceiptDispatchDepartment> rddepartment);

	void deleteDepartment(String reid);

	void updateAreasMap(Map<String, Object> maps);
}
