package com.benqzl.dao.patrol;

import com.benqzl.pojo.patrol.PatrolImplementTable;

public interface PatrolImplementTableMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolImplementTable record);

    int insertSelective(PatrolImplementTable record);

    PatrolImplementTable selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolImplementTable record);

    int updateByPrimaryKey(PatrolImplementTable record);
}