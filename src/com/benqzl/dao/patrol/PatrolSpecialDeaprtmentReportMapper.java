package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialImplement;

public interface PatrolSpecialDeaprtmentReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolSpecialDeaprtmentReport record);

    int insertSelective(PatrolSpecialDeaprtmentReport record);

    PatrolSpecialDeaprtmentReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolSpecialDeaprtmentReport record);

    int updateByPrimaryKey(PatrolSpecialDeaprtmentReport record);
    
    List<PatrolSpecialImplement> findBypage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    List<Map<String, Object>> selectVal(List<String> patrolplandetailids);
    
    List<Map<String, Object>> selectTable(List<Integer> patrolplandetailidsTable);

	List<Map<String, Object>> selectVals(String isid);
    
}