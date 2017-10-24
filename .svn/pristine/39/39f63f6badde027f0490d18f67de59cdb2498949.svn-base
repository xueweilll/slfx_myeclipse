package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.SelfDispatch;

public interface DispatchExecuteService {
	public List<Receipt> findByPageByReceipt(Map<String, Object> map) throws Exception;
	public int pageCountByReceipt(Map<String, Object> map) throws Exception;
	public List<SelfDispatch> findByPageBySelfReceipt(Map<String, Object> map) throws Exception;
	public int pageCountBySelfReceipt(Map<String, Object> map) throws Exception;
	public List<Map<String, Object>> findHisTask(String id) throws Exception;
	public List<String> selectPDById(String id) throws Exception;
	public String selectPDByIdByD(String id, String type)throws Exception;;
}
