package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchCallback;

public interface ReceiptDispatchCallbackMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReceiptDispatchCallback record);

    int insertSelective(ReceiptDispatchCallback record);

    ReceiptDispatchCallback selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatchCallback record);

    int updateByPrimaryKey(ReceiptDispatchCallback record);
    
    List<ReceiptDispatch> findRdList(Map<String, Object> map);
    
    int findRdCount(Map<String, Object> map);
    
    List<ReceiptDispatchCallback> selectByRDID(String rdid);
}