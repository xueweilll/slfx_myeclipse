package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Prickle;

public interface PrickleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Prickle record);

    int insertSelective(Prickle record);

    Prickle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Prickle record);

    int updateByPrimaryKey(Prickle record);

	int pagePrickleCount(Map<String, Object> map);

	List<Prickle> findPrickleByPage(Map<String, Object> map);

	int insertPrickle(Prickle record);

	int updatePrickleByPrimaryKey(Prickle record);

	int deletePrickleByPrimaryKey(String id);

	Prickle selectPrickleByPrimaryKey(String id);

	List<Prickle> findPrickleName();

	int validateprickle(String prickleid);
}