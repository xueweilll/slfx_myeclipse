package com.benqzl.dao.patrol;


import java.util.List;

import com.benqzl.pojo.patrol.PatrolDetails;

public interface PatrolDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<PatrolDetails> record);

    int insertSelective(PatrolDetails record);

    PatrolDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolDetails record);

    int updateByPrimaryKey(PatrolDetails record);
    
    List<PatrolDetails> selectByPatrolID(String patrolid);
    
    int deleteByPatrolID(String patrolid);
    
    
}