package com.benqzl.dao.oa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.TodoList;
import com.benqzl.pojo.oa.TodoListPeople;
import com.benqzl.pojo.system.User;

public interface TodoListMapper {
	List<TodoListPeople> findByPage(Map<String,String> map);
	int pageCount(Map<String,String> map);
	String findUserNameById(String userid);
	TodoListPeople selectByPrimaryKey(String id);
	TodoListPeople selectByPrimaryKey2(String id);
	List<User> selectUsers();
	int insertTodolist(TodoList todolist);
	int insertTodolistpeople(TodoListPeople todolistpeople);
	int deletetodolistpeople(String todolistpeopleid);
	int deletetodolist(String todolistid);
	List<User> selectusertree();
	int updateAll(Date date);
	int updateone(Map<String,Object> map);
	int updatetwo(Map<String,Object> map);
	List<TodoList> findByPage3(Map<String,String> map);	
	TodoList findtodolistByid(String id);
	int deletetodolistpeopleById(String todolistpeopleid);
	int updateByPrimaryKeySelective(TodoList todolist);
	List<User> findUser();
	int pageCount2(Map<String, String> map);
	List<TodoListPeople> findOtherPeopleByTodolistid(Map<String, Object> map);
	void updateTodolist(String todolistid);
}
