package com.benqzl.service.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialIssue;

public interface PatrolSpecialIssueService {
	//应急巡检
	List<PatrolSpecialIssue> selectEgpatrolByPage(Map<String,Object> map) throws Exception;
	
	int pageCount(Map<String,Object> map) throws Exception;
	
	PatrolSpecialIssue findIssueById(String id) throws Exception;
	//定期巡检
	List<PatrolSpecialIssue> selectRegularEgpatrolByPage(Map<String,Object> map) throws Exception;
	//pageCount
	int regularPageCount(Map<String,Object> map) throws Exception;
}