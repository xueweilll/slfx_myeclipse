package com.benqzl.dao.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.MessageReceiver;

public interface MessageReceiverMapper {
	int insert(List<MessageReceiver> receivers);
	int deleteByPrimaryKey(String messageId);
	List<MessageReceiver> findByPage(Map<String, Object> map);
	int pageCount(Map<String, Object> map);
	int updateState(String id);
	String selectContents(String id);
}
