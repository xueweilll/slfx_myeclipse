package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolNormalDetails;

public interface PatrolNormalDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolNormalDetails record);

    int insertSelective(PatrolNormalDetails record);

    PatrolNormalDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolNormalDetails record);

    int updateByPrimaryKey(PatrolNormalDetails record);

	void insertPatrolNormalDetails(List<PatrolNormalDetails> list);


	List<PatrolNormalDetails> selectByNormalDetails(String id);

	void deleteByNormalDetialKey(String id);

	
	List<PatrolNormalDetails> selectByDepartment(List<String> list);
	
	List<PatrolNormalDetails> selectByDepartmentss(Map<String, Object> map);
	
	int updateDetailsByList(Map<String, Object> map);

}