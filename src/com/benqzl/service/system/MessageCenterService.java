package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.MessageCenter;

public interface MessageCenterService {
	int deleteByPrimaryKey(String id) throws Exception;

    int insert(List<MessageCenter> centers) throws Exception;
    
    List<MessageCenter> findByPage(Map<String, Object> map) throws Exception;
    
    int pageCount(String id) throws Exception;

	void deleteAll(String string);
    
    
    
}
