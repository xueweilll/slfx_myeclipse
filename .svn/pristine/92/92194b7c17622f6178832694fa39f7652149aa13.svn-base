package com.benqzl.dao.water;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.util.RealTimeUnitReport;
import com.benqzl.pojo.water.TrPumprun;

public interface TrPumprunMapper {
    int deleteByPrimaryKey(Long fId);

    int insert(List<TrPumprun> record);

    int insertSelective(TrPumprun record);

    TrPumprun selectByPrimaryKey(Long fId);

    int updateByPrimaryKeySelective(TrPumprun record);

    int updateByPrimaryKey(TrPumprun record);

	List<TrPumprun> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	List<RealTimeUnitReport> findRealTimeUnitReportByPage(Map<String, Object> map);

	int pageRealTimeUnitReportCount(Map<String, Object> map);

	List<RealTimeUnitReport> explortRealTimeUnitReport(Map<String, Object> map);
}