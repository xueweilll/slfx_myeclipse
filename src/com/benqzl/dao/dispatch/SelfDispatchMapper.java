package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;
import com.benqzl.pojo.dispatch.SelfDispatch;
//import com.benqzl.pojo.dispatch.SelfDispatchDepartment;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;

public interface SelfDispatchMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDispatch record);

    int insertSelective(SelfDispatch record);

    SelfDispatch selectByPrimaryKey(String id);
    
    SelfDispatch selectDispatchIssuedInfo(String id);
    
    int updateByPrimaryKeyIssued(Map<String, Object> map);

    int updateByPrimaryKeySelective(SelfDispatch record);

    int updateByPrimaryKey(SelfDispatch record);

	List<SelfDispatch> selectDispatchIssued(String id);

	//List<SelfDispatch> selectDispatchIssuedzl(String id);

	//List<SelfDispatch> selectDispatchIssuedmx(String id);
	List<SelfDispatchInstructions> instructions(String id);
	
	List<SelfDispatchStations> stations(String id);
	
	List<SelfDispatch> selectDispatchIssuedmx(String id);
	
	List<SelfDispatch>  selectDispatchIssuedzl(String id);

	List<SelfDispatch> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);
	
	List<SelfDispatch> findAll(Map<String, Object> map);
	
	int updateComplete(String id);
	
	List<ReceiptDispatchTotal> selectTotalBySDID(String id);

	List<SelfDispatch> findByPage1(Map<String, Object> map);

	List<SelfDispatch> findByReceiptPagePrint(Map<String, Object> map);

	String selectCode(Map<String, Object> map);
}