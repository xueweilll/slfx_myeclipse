package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalReport;

public interface DepartmentPatrolService {
	
    List<PatrolNormalReport> selectReportDetailsList(Map<String, Object> map)throws Exception;
    
    List<PatrolNormalReport> findByPage(Map<String, Object> map)throws Exception;
    
    int pageCount(Map<String, Object> map)throws Exception;

	void insert(PatrolNormal normal)throws Exception;
}
