package com.benqzl.dao.patrol;

import java.util.List;

import com.benqzl.pojo.patrol.PatrolNormalPeople;

public interface PatrolNormalPeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolNormalPeople record);

    int insertSelective(PatrolNormalPeople record);

    PatrolNormalPeople selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolNormalPeople record);

    int updateByPrimaryKey(PatrolNormalPeople record);

	void inseinsertPatrolNormalPeople(List<PatrolNormalPeople> people);

	List<PatrolNormalPeople> getfindUser(String id);

	void deleteByPatrolId(String id);
}