package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialImplement;

public interface PatrolSpecialDepartmentReportService {
	 
    List<PatrolSpecialImplement> findBypage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
	List<Map<String, Object>> selectVal(@Param(value="detailids") List<String> patrolplandetailids);
	
	List<Map<String, Object>> selectTable(@Param(value="detailidTable") List<Integer> patrolplandetailidsTable);
	
	void insert(PatrolSpecialDeaprtmentReport p, List<String> list);

	List<Map<String, Object>> selectVals(String isid);
}
