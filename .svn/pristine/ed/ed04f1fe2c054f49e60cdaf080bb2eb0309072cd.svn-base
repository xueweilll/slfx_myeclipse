package com.benqzl.dao.water;

import java.util.List;
import java.util.Map;



import com.benqzl.pojo.water.StPumpr;

public interface StPumprMapper {
    int deleteByPrimaryKey(Long fId);

    int insert(List<StPumpr> record);

    int insertSelective(StPumpr record);

    StPumpr selectByPrimaryKey(Long fId);

    int updateByPrimaryKeySelective(StPumpr record);

    int updateByPrimaryKey(StPumpr record);

	List<StPumpr> findRegimeByPage(Map<String, Object> map);

	int pageRegimeCount(Map<String, Object> map);

	List<StPumpr> findHistoryWater(Map<String, Object> map);

	List<StPumpr> findWaterStation(Map<String, Object> map1);

	List<StPumpr> findStPumprExport(Map<String, Object> map);
	int pageCountStPumprExport(Map<String, Object> map);
}