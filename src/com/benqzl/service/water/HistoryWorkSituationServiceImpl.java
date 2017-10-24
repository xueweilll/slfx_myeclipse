package com.benqzl.service.water;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.TrGaterunMapper;
import com.benqzl.dao.water.TrPumprunMapper;
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;
@Service("historyWorkSituationService")
public class HistoryWorkSituationServiceImpl implements
		HistoryWorkSituationService {
	@Autowired
	private TrPumprunMapper pumprunMapper;
	@Autowired
	private TrGaterunMapper gaterunMapper;
	@Override
	public List<TrGaterun> findTrGaterunByPage(Map<String, Object> map)
			throws Exception {
		return gaterunMapper.findByPage(map);
	}

	@Override
	public int pageCountTrGaterun(Map<String, Object> map) throws Exception {
		return gaterunMapper.pageCount(map);
	}

	@Override
	public List<TrPumprun> findTrPumprunByPage(Map<String, Object> map)
			throws Exception {
		return pumprunMapper.findByPage(map);
	}

	@Override
	public int pageCountTrPumprun(Map<String, Object> map) throws Exception {
		return pumprunMapper.pageCount(map);
	}

	

}
