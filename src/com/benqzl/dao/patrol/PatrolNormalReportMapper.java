package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDepartmentSearch;
import com.benqzl.pojo.patrol.PatrolNormalReport;

public interface PatrolNormalReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolNormalReport record);

    int insertSelective(PatrolNormalReport record);

    PatrolNormalReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolNormalReport record);

    int updateByPrimaryKey(PatrolNormalReport record);
    
    List<PatrolNormal> findByDepartment(Map<String, Object> map);
    
    int countByDepartment(Map<String, Object> map);
    
    //工程科弹窗详情
    List<PatrolNormalReport> selectReportDetailsList(Map<String, Object> map);
    
    //工程科分页查询
    List<PatrolNormalReport> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    List<PatrolNormalDepartmentSearch> findByDepartmentPage(Map<String, Object> map);
    
    int findByDepartmentCount(Map<String, Object> map);
}