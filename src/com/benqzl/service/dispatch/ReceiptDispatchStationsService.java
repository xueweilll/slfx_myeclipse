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

import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecute;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecutePeople;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;


/**
 * 
* @ClassName: ReceiptDispatchStationsService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2016年1月11日 下午12:52:10  
*
 */
public interface ReceiptDispatchStationsService {

	List<ReceiptDispatchStations> findRdExecuteList(Map<String, Object> map);
	
	List<ReceiptDispatchInstructions> selectInstructionsByRPID(String rpid);
	
	int findRdExecuteCount(Map<String, Object> map);
	
	int insertSelective(ReceiptDispatchExecute rde) throws Exception;
	
	int insertSelective(ReceiptDispatchExecutePeople sdep) throws Exception;
	
	ReceiptDispatchStations selectByRDIDAndSId(HashMap<String, String> map);
	
	ReceiptDispatchExecute selectExecuteByStationId(String sid);
	
	User findUserById(String userid);
	
	List<User> findUser();
	
	List<ReceiptDispatchExecuteUnits> findUnitByExecuteid(String executeid);
	
	List<ReceiptDispatchExecuteGate> findGateByExecuteid(String executeid);
	
	ReceiptDispatchStations selectByPrimaryKey(String id);
	
	int updateExecute(ReceiptDispatchExecute rde) throws Exception;
	
	void deleteExecutePeople(String executeid);
	
	void deleteExecuteUnit(String executeid);
	
	void deleteExecuteGate(String executeid);
	
	int updateByPrimaryKeySelective(ReceiptDispatchStations rds);
	
	List<Unit> findUnits(String sid);
	
	List<Gate> findGates(String sid);
	
	Unit selectUnitByName(HashMap<String, String> map);
	
	Gate selectGateByName(HashMap<String, String> map);
	
	int insertSelective(ReceiptDispatchExecuteUnits rdeu) throws Exception;
	
	int insertSelective(ReceiptDispatchExecuteGate rdeg) throws Exception;
	
	List<ReceiptDispatchExecuteUnits> selectUnitByExecuteId(String executeid);
	
	ReceiptDispatchExecute selectExecuteByDispatchstationId(
			String dispatchstationid);
	
	List<ReceiptDispatchExecutePeople> selectExecutePeopleByExecuteId(
			String executeid);
	
	int updateRDStationState(Map<String,Object> map);
	
	int updateRDStationState_look(Map<String,Object> map);
	
	ReceiptDispatch selectReceiptDispatchByRdid(String rdid);

	int countExecuteByStationId(String rdstationid);
	
	List<ReceiptDispatchStations> getKeepCountList(Map<String, Object> mu);
}
