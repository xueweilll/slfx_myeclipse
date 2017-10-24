package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.oa.MessageReceiverMapper;
import com.benqzl.dao.system.MessageCenterMapper;
import com.benqzl.pojo.oa.MessageReceiver;
import com.benqzl.unit.SendMessageAutoUtil;
@Service("messageReceiverService")
public class MessageReceiverServiceimpl implements MessageReceiverService{
	@Autowired
	private MessageReceiverMapper mapper;
	@Autowired
	private MessageCenterMapper centerMapper;
	
	@Override
	public List<MessageReceiver> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageCount(map);
	}

	@Override
	public int deleteMessage(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateState(String id,String userid) {
		// TODO Auto-generated method stub
		SendMessageAutoUtil.sendMessageAuto(userid);
		centerMapper.deleteByTip(id);
		return mapper.updateState(id);
	}

	@Override
	public String selectContents(String id) {
		// TODO Auto-generated method stub
		return mapper.selectContents(id);
	}

}
