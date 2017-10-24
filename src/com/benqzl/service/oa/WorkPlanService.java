package com.benqzl.service.oa;


import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.WorkPlan;

public interface WorkPlanService {
	
	int deleteByPrimaryKey(String id);

    int insert(WorkPlan record);

    int insertSelective(WorkPlan record);

    WorkPlan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkPlan record);

    int updateByPrimaryKey(WorkPlan record);
    
    List<WorkPlan> selectByMonth(Map<String,Object> map); 
    
    int deleteState(String id);
    
    List<WorkPlan> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
}
