package com.benqzl.dao.patrol;

import com.benqzl.pojo.patrol.PatrolImplementClass;

public interface PatrolImplementClassMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolImplementClass record);

    int insertSelective(PatrolImplementClass record);

    PatrolImplementClass selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolImplementClass record);

    int updateByPrimaryKey(PatrolImplementClass record);
}