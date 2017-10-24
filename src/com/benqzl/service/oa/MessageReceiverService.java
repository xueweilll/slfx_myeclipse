package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.MessageReceiver;

public interface MessageReceiverService {

	List<MessageReceiver> findByPage(Map<String, Object> map)throws Exception;

	int pageCount(Map<String, Object> map)throws Exception;

	int deleteMessage(String id)throws Exception;

	int updateState(String id, String userid) throws Exception;

	String selectContents(String id) throws Exception;

}
