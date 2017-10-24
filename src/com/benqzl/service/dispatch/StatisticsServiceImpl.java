package com.benqzl.service.dispatch;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.UnitExt;

public class StatisticsServiceImpl implements StatisticsService{

	List<ReceiptDispatchExecuteUnits> receiptDispatchExecuteUnits1;
	List<ReceiptDispatchExecuteUnits> receiptDispatchExecuteUnits2;
	List<ReceiptDispatchExecuteUnits> receiptDispatchExecuteUnits3;
	List<ReceiptDispatchExecuteUnits> receiptDispatchExecuteUnits4;
	
	@Override
	public List<UnitExt> UnitExts(Date dt1, Date dt2)throws Exception {
		if(dt1.compareTo(dt2)>-1){
			throw new Exception("起始时间必须小于结束时间");
		}
		
		//receiptDispatchExecuteUnits1
		
		return null;
	}

	

}
