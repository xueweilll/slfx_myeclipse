package com.benqzl.dao.patrol;


import java.util.List;

import com.benqzl.pojo.patrol.PatrolSpecialImplement;

public interface PatrolSpecialImplementMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolSpecialImplement record);

    int insertSelective(PatrolSpecialImplement record);

    PatrolSpecialImplement selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolSpecialImplement record);

    int updateByPrimaryKey(PatrolSpecialImplement record);
    
    int updateState(List<String> list);

	void updateStates(String isid);
  
}