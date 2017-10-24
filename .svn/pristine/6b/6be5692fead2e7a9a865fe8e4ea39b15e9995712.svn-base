package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.PatrolSpecialDeaprtmentReportDetailsMapper;
import com.benqzl.dao.patrol.PatrolSpecialDeaprtmentReportMapper;
import com.benqzl.dao.patrol.PatrolSpecialImplementMapper;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialImplement;

@Service("patrolSpecialDepartmentReportService")
public class PatrolSpecialDepartmentReportServiceImpl implements
		PatrolSpecialDepartmentReportService {
	
	@Autowired
	private PatrolSpecialDeaprtmentReportMapper drmapper;
	
	@Autowired
	private PatrolSpecialDeaprtmentReportDetailsMapper drdmapper;
	
	@Autowired
	private PatrolSpecialImplementMapper psimapper;
	
	@Override
	public List<PatrolSpecialImplement> findBypage(Map<String, Object> map) {
		return drmapper.findBypage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return drmapper.pageCount(map);
	}

	@Override
	public List<Map<String, Object>> selectVal(List<String> patrolplandetailids) {
		return drmapper.selectVal(patrolplandetailids);
	}

	@Override
	public List<Map<String, Object>> selectTable(
			List<Integer> patrolplandetailidsTable) {
		return drmapper.selectTable(patrolplandetailidsTable);
	}

	@Override
	public void insert(PatrolSpecialDeaprtmentReport p, List<String> list) {
		drmapper.insert(p);
		drdmapper.insert(p.getDetails());
		//psimapper.updateState(list);
		psimapper.updateStates(p.getIsid());//更新实施单状态
	}

	@Override
	public List<Map<String, Object>> selectVals(String isid) {
		 return drmapper.selectVals(isid);
	}



}
