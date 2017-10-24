package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;

public interface SelfDispatchExecuteGateMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<SelfDispatchExecuteGate> record);

    int insertSelective(SelfDispatchExecuteGate record);

    SelfDispatchExecuteGate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDispatchExecuteGate record);

    int updateByPrimaryKey(SelfDispatchExecuteGate record);
    
    List<SelfDispatchExecuteGate> findPageByTotal(Map<String, Object> map);
    
    List<SelfDispatchExecuteGate> findPageByTotalPrint(Map<String, Object> map);
    
    int findPageByCount(Map<String, Object> map);
}