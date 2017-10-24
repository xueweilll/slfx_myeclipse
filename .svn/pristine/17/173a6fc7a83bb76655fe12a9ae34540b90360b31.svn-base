package com.benqzl.dao.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.water.TrGaterun;

public interface TrGaterunMapper {
    int deleteByPrimaryKey(Long fId);

    int insert(List<TrGaterun> record);

    int insertSelective(TrGaterun record);

    TrGaterun selectByPrimaryKey(Long fId);

    int updateByPrimaryKeySelective(TrGaterun record);

    int updateByPrimaryKey(TrGaterun record);

	List<TrGaterun> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);
}