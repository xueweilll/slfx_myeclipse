package com.benqzl.service.oa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.MessageCenterMapper;
import com.benqzl.dao.oa.TodoListMapper;
import com.benqzl.pojo.oa.TodoList;
import com.benqzl.pojo.oa.TodoListPeople;
import com.benqzl.pojo.system.User;
import com.benqzl.unit.SendMessageAutoUtil;
@Service("TodoService")
public class TodoServiceImpl implements TodoService{
	@Autowired
	private TodoListMapper mapper;
	@Autowired
	private MessageCenterMapper mc;
	@Override
	public List<TodoListPeople> findByPage(Map<String, String> map) {		
		return mapper.findByPage(map);
	}
	@Override
	public int pageCount(Map<String, String> map) {		
		return mapper.pageCount(map);
	}
	@Override
	public String findUserNameById(String userid) {		
		return mapper.findUserNameById(userid);
	}
	@Override
	public TodoListPeople selectByPrimaryKey(String id) {		
		return mapper.selectByPrimaryKey(id);
	}
	@Override
	public TodoListPeople selectByPrimaryKey2(String id) {		
		return mapper.selectByPrimaryKey2(id);
	}
	@Override
	public List<User> selectUsers() {		
		return mapper.selectUsers();
	}
	@Override
	public List<User> selectusertree() {		
		return mapper.selectusertree();
	}
	@Override
	public int insertTodolist(TodoList todolist) {		
		return mapper.insertTodolist(todolist);
	}
	@Override
	public int insertTodolistpeople(TodoListPeople todolistpeople) {		
		return mapper.insertTodolistpeople(todolistpeople);
	}
	@Override
	public int deletetodolistpeople(String todolistpeopleid) {		
		return mapper.deletetodolistpeople(todolistpeopleid);
	}
	@Override
	public int deletetodolistpeopleById(String todolistpeopleid) {		
		return mapper.deletetodolistpeopleById(todolistpeopleid);
	}
	@Override
	public int deletetodolist(String todolistid) {		
		return mapper.deletetodolist(todolistid);
	}
	@Override
	public int updateAll(Date date) {		
		return mapper.updateAll(date);
	}
	@Override
	public int updateone(Map<String,Object> map) {		
		return mapper.updateone(map);
	}
	@Override
	public int updatetwo(Map<String,Object> map,String userid,String todoid) {
		SendMessageAutoUtil.sendMessageAuto(userid);		
		mc.deleteByTip(todoid);
		return mapper.updatetwo(map);
	}
	@Override
	public List<TodoList> findByPage3(Map<String, String> map) {		
		return mapper.findByPage3(map);
	}
	@Override
	public TodoList findtodolistByid(String id) {		
		return mapper.findtodolistByid(id);
	}
	@Override
	public int updateByPrimaryKeySelective(TodoList todolist) {		
		return mapper.updateByPrimaryKeySelective(todolist);
	}
	@Override
	public List<User> findUser() {		
		return mapper.findUser();
	}
	@Override
	public int pageCount2(Map<String, String> map) {
		return mapper.pageCount2(map);
	}
	@Override
	public List<TodoListPeople> findOtherPeopleByTodolistid(Map<String, Object> map) {
		return mapper.findOtherPeopleByTodolistid(map);
	}
	@Override
	public void updateTodolist(String todolistid) {
		mapper.updateTodolist(todolistid);
	}
}
