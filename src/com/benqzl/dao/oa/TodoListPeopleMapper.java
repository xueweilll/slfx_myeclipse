package com.benqzl.dao.oa;

import com.benqzl.pojo.oa.TodoListPeople;

public interface TodoListPeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(TodoListPeople record);

    int insertSelective(TodoListPeople record);

    TodoListPeople selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TodoListPeople record);

    int updateByPrimaryKey(TodoListPeople record);
}