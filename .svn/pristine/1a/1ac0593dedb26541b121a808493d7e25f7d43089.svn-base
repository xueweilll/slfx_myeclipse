package com.benqzl.dao.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.water.TrWarnlog;

public interface TrWarnlogMapper {
    int deleteByPrimaryKey(Long fId);

    int insert(List<TrWarnlog> record);

    int insertSelective(TrWarnlog record);

    TrWarnlog selectByPrimaryKey(Long fId);

    int updateByPrimaryKeySelective(TrWarnlog record);

    int updateByPrimaryKey(TrWarnlog record);

	List<TrWarnlog> findByPage(Map<String, String> map);

	int pageCount(Map<String, String> map);

	List<Station> findStation();
}