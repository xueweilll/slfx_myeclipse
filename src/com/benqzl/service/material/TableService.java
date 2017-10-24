package com.benqzl.service.material;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.material.MaterialManage;
import com.benqzl.pojo.material.MonthReport;
import com.benqzl.pojo.material.Storage;

public interface TableService {

	List<MaterialManage> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	List<MonthReport> findMonthByPage(Map<String, Object> map);

	int pageMonthCount(Map<String, Object> map);

	List<MonthReport> print(Map<String, Object> map);

	List<MonthReport> findYear();
	public List<Storage> findStorageByPage(Map<String, Object> map);
	public int pageStorageCount(Map<String, Object> map);

}
