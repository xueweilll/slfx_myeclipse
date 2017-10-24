package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.MessageCenter;;;

public interface MessageCenterMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<MessageCenter> centers);
    
    List<MessageCenter> findByPage(Map<String, Object> map);
    
    int pageCount(String id);

    int deleteByTip(String id);
    
    int deleteByIdList(List<String> list);

	List<MessageCenter> findByMessageCenter(Map<String, Object> map);

	int deleteAll(String string);

}