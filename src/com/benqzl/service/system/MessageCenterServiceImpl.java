package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.MessageCenterMapper;
import com.benqzl.pojo.system.MessageCenter;
@Service("messageCenterService")
public class MessageCenterServiceImpl implements MessageCenterService {
	@Autowired
	private MessageCenterMapper mapper;

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(List<MessageCenter> centers) throws Exception {
		return mapper.insert(centers);
	}

	@Override
	public List<MessageCenter> findByPage(Map<String, Object> map) throws Exception {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(String id) throws Exception {
		return mapper.pageCount(id);
	}

	@Override
	public void deleteAll(String string) {
		// TODO Auto-generated method stub
		mapper.deleteAll(string);
	}

	

}
