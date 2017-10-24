package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolImplementDetails;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.patrol.PatrolSpecialProjectReport;

public interface PatrolSpecialProjectReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolSpecialProjectReport record);

    int insertSelective(PatrolSpecialProjectReport record);

    PatrolSpecialProjectReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolSpecialProjectReport record);

    int updateByPrimaryKey(PatrolSpecialProjectReport record);
    
    List<PatrolSpecialDeaprtmentReport> findBypage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    List<PatrolSpecialProjectReport> findManageByPage(Map<String, Object> map);
    
    int pageManageCount(Map<String, Object> map);
    
    /**
     * 开始  冯庆国
     */
    List<PatrolSpecialDeaprtmentReport> selectByIssueId(Map<String, Object> map);//查询签发单上报记录
    
    List<PatrolSpecialIssue> findIssueByPage(Map<String, Object> map);//签发单分页查询
    
    int pageIssueCount(Map<String, Object> map);
    
    List<PatrolImplementDetails> selectValsByIsid(String id);
    
    int updateFlow(Map<String, Object> map);
    
    /**
     * 结束  冯庆国
     */
}