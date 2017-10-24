package com.benqzl.service.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.water.StPumpr;

public interface WaterRegimeService {
	List<StPumpr> findRegimeByPage(Map<String, Object> map);

	int pageRegimeCount(Map<String, Object> map);
	List<Map<String, Object>> findHistoryWater(Map<String, Object> map1);
	int insert(List<StPumpr> record);

	List<Map<String, Object>> findHistoryMobileWater(Map<String, Object> map1);
	
	List<StPumpr> findStPumprExport(Map<String, Object> map);
	
	int pageCountStPumprExport(Map<String, Object> map);
}
