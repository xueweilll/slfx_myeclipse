package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialIssue;

public interface PatrolSpecialIssueMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolSpecialIssue record);

    int insertSelective(PatrolSpecialIssue record);
    //根据id查找
    PatrolSpecialIssue findIssueById(String id);

    int updateByPrimaryKeySelective(PatrolSpecialIssue record);

    int updateByPrimaryKey(PatrolSpecialIssue record);
    
    //查询应急巡检
  	List<PatrolSpecialIssue> selectEgpatrolByPage1(Map<String,Object> map);
  	//总页数
  	int pageCount(Map<String,Object> map);
  	//查询定期巡检
  	List<PatrolSpecialIssue> selectRegularEgpatrolByPage(Map<String,Object> map);
  	//PageCount总页数
  	int regularPageCount(Map<String,Object> map);
  	List<PatrolSpecialIssue> selectByCombobox();

	PatrolSpecialIssue findIssueByIsid(String isid);

	List<PatrolSpecialIssue> selectByIssue(Map<String, Object> map);
}