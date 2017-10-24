package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolNormal;

public interface PatrolNormalMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolNormal record);

    int insertSelective(PatrolNormal record);

    PatrolNormal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolNormal record);

    int updateByPrimaryKey(PatrolNormal record);

	List<PatrolNormal> findpatrolnormal(Map<String, Object> map);

	int findpatrolnormalcount(Map<String, Object> map);

	void deleteByNormalPrimaryKey(String id);

	PatrolNormal validatenormal(PatrolNormal normal);

	PatrolNormal findmobileyanzheng(PatrolNormal patrol);

	void updateMobileCommit(String id);
	
	void updateState(Map<String, Object> map);
}