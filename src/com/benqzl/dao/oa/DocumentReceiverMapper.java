package com.benqzl.dao.oa;

import java.util.List;

import com.benqzl.pojo.oa.DocumentReceiver;

public interface DocumentReceiverMapper {
	int deleteByPrimaryKey(String id);

	int insert(List<DocumentReceiver> receiver);

	int insertSelective(DocumentReceiver record);

	DocumentReceiver selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(DocumentReceiver record);

	int updateByPrimaryKey(DocumentReceiver record);

	int updateState(String id);

	int delete(DocumentReceiver record);

	int deleteByDocumentID(String id);

	int deleteState(String id);
}
