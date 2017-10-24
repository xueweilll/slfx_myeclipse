package com.benqzl.service.water;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.TrWarnlogMapper;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.water.TrWarnlog;
@Service("WarningService")
public class WarningServiceImpl implements WarningService {
	@Autowired
    private TrWarnlogMapper twm;
	@Override
	public List<TrWarnlog> findByPage(Map<String, String> map) {
		return twm.findByPage(map);
	}
	@Override
	public int pageCount(Map<String, String> map) {
		return twm.pageCount(map);
	}
	@Override
	public List<Station> findStation() {		
		return twm.findStation();
	}
	@Override
	public int insert(List<TrWarnlog> record) {
		// TODO Auto-generated method stub
		return twm.insert(record);
	}
	
}
