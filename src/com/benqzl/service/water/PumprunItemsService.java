package com.benqzl.service.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.water.PumprunItems;

public interface PumprunItemsService {
	int insert(List<PumprunItems> startItems,List<String> endItems);
	
	List<Map<String, Object>> findAllByTime(Map<String, Object> map);
	
	List<Map<String, Object>> findByUnit(Map<String, Object> map);
}
