package com.benqzl.service.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.water.TrWarnlog;

public interface WarningService {

	List<TrWarnlog> findByPage(Map<String, String> map);

	int pageCount(Map<String, String> map);

	List<Station> findStation();

	int insert(List<TrWarnlog> record);

}
