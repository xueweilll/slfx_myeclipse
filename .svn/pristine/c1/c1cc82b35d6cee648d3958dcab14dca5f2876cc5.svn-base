package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.benqzl.pojo.patrol.PatrolSpecialImplement;

public interface PatrolImplementMapper {
	List<Map<String,Object>> query(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	List<Map<String,Object>> getDemo();
	int save(@Param(value="id")String id, @Param(value="implementid")String implementid,@Param(value="examintime")String examintime,@Param(value="station")String station,@Param(value="info")String info,@Param(value="userid")String userId);
	List<PatrolSpecialImplement> findByPage(Map<String, Object> map);
	int pageCount(Map<String, Object> map);
	int boobleisid(String issueid);
}
