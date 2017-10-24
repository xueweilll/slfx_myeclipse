package com.benqzl.service.material;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.material.MaterialManageMapper;
import com.benqzl.dao.material.MonthReportMapper;
import com.benqzl.dao.material.StorageMapper;
import com.benqzl.pojo.material.MaterialManage;
import com.benqzl.pojo.material.MonthReport;
import com.benqzl.pojo.material.Storage;
@Service("TableService")
public class TableServiceImpl implements TableService {
    
	@Autowired
	private MaterialManageMapper material;
	
    @Autowired
    private MonthReportMapper month;
	@Autowired
	private StorageMapper storageMapper;
	
	@Override
	public List<MaterialManage> findByPage(Map<String, Object> map) {
		return material.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return material.pageCount(map);
	}

	@Override
	public List<MonthReport> findMonthByPage(Map<String, Object> map) {
		return month.findMonthByPage(map);
	}

	@Override
	public int pageMonthCount(Map<String, Object> map) {
		return month.findMonthCount(map);
	}

	@Override
	public List<MonthReport> print(Map<String, Object> map) {
		return month.print(map);
	}

	@Override
	public List<MonthReport> findYear() {
	MonthReport user1=new MonthReport();
		user1.setMaterialid("全部");
		

		List<MonthReport> lists=month.findYear();
		lists.add(0, user1);
		
		return lists;
	}

	@Override
	public List<Storage> findStorageByPage(Map<String, Object> map) {
		return storageMapper.findByPage(map);
	}

	@Override
	public int pageStorageCount(Map<String, Object> map) {
		return storageMapper.pageCount(map);
	}


}
