package com.benqzl.service.oa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.oa.MessageMapper;
import com.benqzl.dao.oa.MessageReceiverMapper;
import com.benqzl.dao.system.MessageCenterMapper;
import com.benqzl.pojo.oa.Message;
import com.benqzl.pojo.oa.MessageReceiver;
import com.benqzl.pojo.system.Menu;
import com.benqzl.pojo.system.MessageCenter;
import com.benqzl.pojo.system.User;

@Service("messageServie")
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageMapper mapper;
	@Autowired
	private MessageReceiverMapper receiverMapper;
	@Autowired
	private MessageCenterMapper centerMapper;
	@Override
	public List<Message> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageCount(map);
	}

	@Override
	public List<User> selectUsers() {
		// TODO Auto-generated method stub
		List<User> users = mapper.selectUsers();
		return users;
	}

	@Override
	public int insert(Message message, List<MessageReceiver> receivers) {
		
		mapper.insertSelective(message);
		receiverMapper.insert(receivers);
		List<MessageCenter> centers = new ArrayList<MessageCenter>();
		for (MessageReceiver receiver : receivers) {
			MessageCenter center = new MessageCenter();
			center.setId(UUID.randomUUID().toString());
			center.setSendUser(message.getUser());
			User user = new User();
			user.setUserid(receiver.getReceiverid());
			center.setReceiverUser(user);
			center.setSendtime(new Date());
			center.setTid(receiver.getId());
			Menu menu = new Menu();
			menu.setMenuid("b0d859bb-560c-44d8-b057-70f14308daa5");
			center.setMenu(menu);
			centers.add(center);
		}
		centerMapper.insert(centers);
		return 0;
		// TODO Auto-generated method stub
	}

	@Override
	public int deleteMessage(String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> selectUsersById(String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectUsersById(id);
	}
}
