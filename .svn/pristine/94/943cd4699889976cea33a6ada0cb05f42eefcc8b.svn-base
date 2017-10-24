package com.benqzl.dao.dispatch;

import java.util.HashMap;
import java.util.List;

import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;

public interface ReceiptDispatchExecuteUnitsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<ReceiptDispatchExecuteUnits> record);

    int insertSelective(ReceiptDispatchExecuteUnits record);

    ReceiptDispatchExecuteUnits selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatchExecuteUnits record);

    int updateByPrimaryKey(ReceiptDispatchExecuteUnits record);

	List<ReceiptDispatchExecuteUnits> selectUnitByExecuteId(String executeid);
	
	List<ReceiptDispatchExecuteUnits> selectByCheck(HashMap<String, Object> map);
}