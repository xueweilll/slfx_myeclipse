package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchDepartment;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.pojo.util.RealTimeUnitReport;
import com.benqzl.pojo.util.UserStatistics;

public interface ReceiptService {
	
	int deleteByPrimaryKey(String id);

    int insert(Receipt record);

    int insertSelective(Receipt record);

    Receipt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Receipt record);

    int updateByPrimaryKey(Receipt record);

	List<Receipt> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);
	
	List<User> userList();

	List<Receipt> findDocket(List<String> list);
	
	int deleteState(String id);
	
	int aduitState(Map<String, Object> map);
	
	int commitState(String id);
	
	void insertAreaDispatch(ReceiptDispatch rd);

	Receipt findReceiptDocket(HashMap<String, String> map);
	
	List<UserStatistics> findStatisticsByPage(Map<String, Object> map) throws Exception;
	
	List<UserStatistics> findStatisticsPrint(Map<String, Object> map);

	int pageStatisticsCount(Map<String, Object> map);

	List<UserStatistics> findStations(String sid);

	List<Station> findStation();

	void insertDepartment(List<SelfDispatchDepartment> selfdepartment);

	List<UserStatistics> findSelfStatisticsByPage(Map<String, Object> map);
	
	List<UserStatistics> findSelfStatisticsPrint(Map<String, Object> map);

	int findSelfStatisticsCount(Map<String, Object> map);

	List<UserStatistics> findSelfStatisticsByExport(Map<String, Object> map);

	List<UserStatistics> findStatisticsByExport(Map<String, Object> map);

	List<Receipt> findEDEndList(Map<String, Object> map);

	void updateState(HashMap<String, Object> map);

	int findEDEndCount(Map<String, Object> map);

	List<RealTimeUnitReport> findRealTimeUnitReportByPage(Map<String, Object> map);

	int pageRealTimeUnitReportCount(Map<String, Object> map);

	List<RealTimeUnitReport> explortRealTimeUnitReport(Map<String, Object> map);
}
