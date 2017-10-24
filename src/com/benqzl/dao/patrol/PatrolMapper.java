package com.benqzl.dao.patrol;

import com.benqzl.pojo.patrol.Patrol;

public interface PatrolMapper {
    int deleteByPrimaryKey(String id);

    int insert(Patrol record);

    int insertSelective(Patrol record);

    Patrol selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Patrol record);

    int updateByPrimaryKey(Patrol record);
    
    Patrol selectByPPDtailsID(String patrolplandetailsid);
}