package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialExcute;
import com.benqzl.pojo.patrol.PatrolSpecialExcuteDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;
import com.benqzl.pojo.system.Station;

public interface EgpatorlDepartemntSendService {

	List<PatrolSpecialResolveDetails> findegpatroldepartment(Map<String, Object> map);

	int findegpatroldepartmentcount(Map<String, Object> map);

	void insertegpatorlDepartemnt(PatrolSpecialExcute patrol);

	void insertegpatorlDepartemntlDetails(List<PatrolSpecialExcuteDetails> list);

	void deleteByEgPatrolIsId(String id);

	void insertegpatorlSpecialExcute(PatrolSpecialExcute patrol);

	List<Station> findstation(String did);

	PatrolSpecialExcute findexcute(Map<String, Object> map);

	List<PatrolSpecialExcuteDetails> findExcuteDetails(Map<String, Object> map);

	PatrolSpecialIssue findIssue(String isid);

}
