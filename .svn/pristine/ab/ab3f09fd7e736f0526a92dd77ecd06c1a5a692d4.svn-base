package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.Message;
import com.benqzl.pojo.oa.MessageReceiver;
import com.benqzl.pojo.system.User;

public interface MessageService {

	List<Message> findByPage(Map<String, Object> map) throws Exception;

	int pageCount(Map<String, Object> map)throws Exception;
	
	List<User> selectUsers()throws Exception;

	int insert(Message message, List<MessageReceiver> receivers)throws Exception;

	int deleteMessage(String id) throws Exception;

	List<User> selectUsersById(String id) throws Exception;

}
