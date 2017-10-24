package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDepartmentSearch;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalReport;
import com.benqzl.pojo.patrol.PatrolNormalReportDetails;

public interface PatrolNormalReportService {

	List<PatrolNormal> findByDepartment(Map<String, Object> map);
	
    List<PatrolNormalDepartmentSearch> findByDepartmentPage(Map<String, Object> map);
    
    int findByDepartmentCount(Map<String, Object> map);
	    
    int countByDepartment(Map<String, Object> map);
	
    List<PatrolNormalDetails> selectByDepartment(Map<String, Object> map);
    
    void insert(PatrolNormalReport report,
			List<PatrolNormalReportDetails> list, List<String> ls, int type);

	void insert2(PatrolNormalReport report,
			List<PatrolNormalReportDetails> list, List<String> ids, int i);
}
