package com.benqzl.dao.dispatch;

import java.util.List;

import com.benqzl.pojo.dispatch.SelfDispatchExecutePeople;

public interface SelfDispatchExecutePeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<SelfDispatchExecutePeople> record);

    int insertSelective(SelfDispatchExecutePeople record);

    SelfDispatchExecutePeople selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDispatchExecutePeople record);

    int updateByPrimaryKey(SelfDispatchExecutePeople record);

	List<SelfDispatchExecutePeople> selectExecutePeopleByExecuteId(String executeid);
}