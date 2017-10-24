package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;

public interface DispatchGateRunService {

	  List<ReceiptDispatchExecuteGate> selectByTotal(Map<String, Object> map);
	  
      int selectByCount(Map<String, Object> map);
      
      List<ReceiptDispatchExecuteGate> selectByTotalPrint(Map<String, Object> map);
      
      List<SelfDispatchExecuteGate> findPageByTotal(Map<String, Object> map);
      
      int findPageByCount(Map<String, Object> map);
      
      List<SelfDispatchExecuteGate> findPageByTotalPrint(Map<String, Object> map);
	
}
