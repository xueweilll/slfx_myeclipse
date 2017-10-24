/**    
 * @Title: SelfDispatchStationsService.java  
 * @Package com.benqzl.service  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author shimh    
 * @date 2016年1月4日 上午10:55:10  
 * @version V1.0    
 */
package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchExecute;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecutePeople;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;

/**
 * 
* @ClassName: SelfDispatchStationsService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2016年1月5日 下午1:13:57  
*
 */
public interface SelfDispatchStationsService {

	List<SelfDispatchStations> findByPage(Map<String, Object> map);
	
	List<SelfDispatch> findAll(Map<String, Object> map);
	
	List<SelfDispatchInstructions> selectBySDID(String sdid);

	int pageCount(Map<String, Object> map);
	

	int insertSelective(SelfDispatchExecute sde) throws Exception;

	int insertSelective(SelfDispatchExecutePeople sdep) throws Exception;
	
	SelfDispatchStations selectBySDIDAndSId(HashMap<String, String> map);
	
	SelfDispatchExecute selectBySid(String sid);

	User findUserById(String userid);

	SelfDispatchStations selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(SelfDispatchStations sds);
	
	List<Unit> findUnit(String sid);
	
	Unit selectUnitByName(HashMap<String, String> map);
	
	int insertSelective(SelfDispatchExecuteUnits sdeu) throws Exception;

	List<String> selectstations(String id);

	SelfDispatchExecute selectExecuteByDispatchstationId(String dispatchstationid);

	List<SelfDispatchExecutePeople> selectExecutePeopleByExecuteId(String executeid);

	User selectUserByID(String uid);

	List<SelfDispatchExecuteUnits> selectUnitByExecuteId(String executeid);
	
	int updateExecuteByDispatchstationid(SelfDispatchExecute sde);

	List<SelfDispatchStations> findDispatch2(Map<String, Object> map);
	
	int pageCount2(Map<String, Object> map);

	SelfDispatchStations selectstationById(String stationid);

	List<SelfDispatchInstructions> findInstructionsByid(String sdid);

	List<User> findUser();

	List<Unit> findUnit2(String sid);

	List<Gate> findGate(String sid);

	void insertExecute(SelfDispatchExecute sde);

	List<String> findUseridsByname(User u);

	List<String> findUnitsByname(Unit unit);

	void insertExecutePeople(SelfDispatchExecutePeople sdep);

	void insertExecuteUnit(SelfDispatchExecuteUnits sdeu);

	void insertExecuteGate(SelfDispatchExecuteGate sdeg);

	void updateDispatchStations(String dispatchstationid);

	void updateDispatchStations2(String stationid);

	SelfDispatchExecute selectExecuteByStationId(String stationid);

	SelfDispatchExecute selectExecuteByStationId2(String stationid);

	void updateExecute(SelfDispatchExecute sde);

	void deleteExecutePeople(String executeid);

	void deleteExecuteUnit(String executeid);

	void deleteExecuteGate(String executeid);

	List<SelfDispatchExecuteUnits> findUnitByExecuteid(String executeid);

	List<SelfDispatchExecuteGate> findGateByExecuteid(String executeid);

	void updateDispatchCompleteTime(SelfDispatch sd);

	SelfDispatch findSelfDispatchBySdstationid(String dispatchstationid);

	List<SelfDispatchStations> findDispatch2NotAll(Map<String, Object> map);

	List<ReceiptDispatchTotal> selectTotalBySDID(String id);

	List<SelfDispatchStations> selectStationByUserid(Map<String, Object> map);

	void delete(String id);

	void insertStations(List<SelfDispatchStations> selfstations);
	
	SelfDispatchStations selectstationById2(String stationid);

	int countExecuteByStationId2(String dispatchstationid);
	
}
