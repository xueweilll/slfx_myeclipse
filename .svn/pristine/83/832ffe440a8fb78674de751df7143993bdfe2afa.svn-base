package com.benqzl.dao.dispatch;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchExecute;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;
import com.benqzl.pojo.system.User;
import com.benqzl.pojo.util.UserStatistics;

public interface ReceiptDispatchExecuteMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReceiptDispatchExecute record);

    int insertSelective(ReceiptDispatchExecute record);

    ReceiptDispatchExecute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatchExecute record);

    int updateByPrimaryKey(ReceiptDispatchExecute record);

	ReceiptDispatchExecute selectExecuteByDispatchstationId(
			String dispatchstationid);
	
	int updateRDStationState(Map<String,Object> map);
	int updateRDStationState_look(Map<String,Object> map);

	ReceiptDispatchExecute selectExecuteByStationId(String sid);

	List<User> findUser();

	List<ReceiptDispatchExecuteUnits> findUnitByExecuteid(String executeid);

	List<ReceiptDispatchExecuteGate> findGateByExecuteid(String executeid);

	void deleteExecutePeople(String executeid);

	void deleteExecuteUnit(String executeid);

	void deleteExecuteGate(String executeid);


	List<UserStatistics> findStatisticsByPage(Map<String, Object> map);

	List<UserStatistics> findStatisticsPrint(Map<String, Object> map);
	
	List<ReceiptDispatchTotal> selectTotalByRDID(String id);

	int pageStatisticsCount(Map<String, Object> map);

	List<UserStatistics> findStations(String sid);

	List<UserStatistics> findStatisticsByExport(Map<String, Object> map);

	int countExecuteByStationId(String rdstationid);

}