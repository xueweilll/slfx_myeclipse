package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Actor;

public interface ActorService {
	 int deleteByPrimaryKey(String id);

	    int insert(Actor record);

	    int insertSelective(Actor record);

	    Actor selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(Actor record);

	    int updateByPrimaryKey(Actor record);
	    
	    List<Actor> selectAll();

		List<Actor> findByPage(Map<String, Object> map);
		
		int pageCount(Map<String, Object> map);

		int exsitName(String name);
		
		Actor findActor(String id);
}
