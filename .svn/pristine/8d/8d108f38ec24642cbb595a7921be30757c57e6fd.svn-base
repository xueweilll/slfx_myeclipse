package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;

public interface ReceiptDispatchExecuteGateMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<ReceiptDispatchExecuteGate> record);

    int insertSelective(ReceiptDispatchExecuteGate record);

    ReceiptDispatchExecuteGate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatchExecuteGate record);

    int updateByPrimaryKey(ReceiptDispatchExecuteGate record);
    
    List<ReceiptDispatchExecuteGate> selectByTotal(Map<String, Object> map);
    
    int selectByCount(Map<String, Object> map);
    
    List<ReceiptDispatchExecuteGate> selectByTotalPrint(Map<String, Object> map);
}