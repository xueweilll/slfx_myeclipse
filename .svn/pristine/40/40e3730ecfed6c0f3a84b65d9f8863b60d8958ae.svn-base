package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchDepartment;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;


public interface DispatchIssuedListService {
	 void updateByPrimaryKeyIssued(Map<String, Object> map, Map<String, Object> mu);
    SelfDispatch selectDispatchIssuedInfo(String id);
	List<SelfDispatch> selectDispatchIssued(String string);
	List<SelfDispatch> selectDispatchIssuedzl(String id);
	List<SelfDispatch> selectDispatchIssuedmx(String id);
	
	List<SelfDispatch> findByPage(Map<String, Object> map);
	int pageCount(Map<String, Object> map);
	List<SelfDispatchStations> selectSDStationBySDID(String id);

	List<SelfDispatchInstructions> selectSDInstructionsBySDID(String id);
	
	int updateComplete(String id);
	SelfDispatch selectByPrimaryKey(String id);
	List<SelfDispatchDepartment> findByPages(Map<String, Object> map);
	int pageCounts(Map<String, Object> map);
	List<SelfDispatch> findByPage1(Map<String, Object> map);
	List<SelfDispatch> findByReceiptPagePrint(Map<String, Object> map);
}
