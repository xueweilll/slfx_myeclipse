package com.benqzl.dao.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.water.StPptnR;

public interface StPptnRMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<StPptnR> record);

    int insertSelective(StPptnR record);

    StPptnR selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StPptnR record);

    int updateByPrimaryKey(StPptnR record);
    
    List<StPptnR> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);

	List<StPptnR> findRainWater(Map<String, Object> map1);
	
	StPptnR selectByFid(String fid);
}