package com.benqzl.dao.patrol;

import java.util.List;

import com.benqzl.pojo.patrol.PatrolPeople;

public interface PatrolPeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<PatrolPeople> record);

    int insertSelective(PatrolPeople record);

    PatrolPeople selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolPeople record);

    int updateByPrimaryKey(PatrolPeople record);

	void deleteByPatrolId(String id);
}