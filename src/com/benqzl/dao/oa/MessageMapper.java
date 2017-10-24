package com.benqzl.dao.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.Message;
import com.benqzl.pojo.system.User;

public interface MessageMapper {
	int deleteByPrimaryKey(String id);

    int insertSelective(Message record);

    User selectByPrimaryKey(String id);

//    int updateByPrimaryKeySelective(Message record);
//
//    int updateByPrimaryKey(Message record);
    
    List<Message> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    List<User> selectUsers();

	List<User> selectUsersById(String id);

}
