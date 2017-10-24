package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.SelfDispatchExecute;
import com.benqzl.pojo.util.UserStatistics;

public interface SelfDispatchExecuteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SelfDispatchExecute record);

    int insertSelective(SelfDispatchExecute record);

    SelfDispatchExecute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDispatchExecute record);

    int updateByPrimaryKey(SelfDispatchExecute record);

	SelfDispatchExecute selectBySid(String sid);

	SelfDispatchExecute selectExecuteByDispatchstationId(String dispatchstationid);

	int updateExecuteByDispatchstationid(SelfDispatchExecute sde);

	List<UserStatistics> findSelfStatisticsByPage(Map<String, Object> map);

	int findSelfStatisticsCount(Map<String, Object> map);
	
	List<UserStatistics> findSelfStatisticsPrint(Map<String, Object> map);

	List<UserStatistics> findSelfStatisticsByExport(Map<String, Object> map);
}