package com.benqzl.dao.dispatch;

import java.util.List;

import com.benqzl.pojo.dispatch.SelfDispatchExecuteUnits;

public interface SelfDispatchExecuteUnitsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<SelfDispatchExecuteUnits> record);

    int insertSelective(SelfDispatchExecuteUnits record);

    SelfDispatchExecuteUnits selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SelfDispatchExecuteUnits record);

    int updateByPrimaryKey(SelfDispatchExecuteUnits record);

	List<SelfDispatchExecuteUnits> selectUnitByExecuteId(String executeid);
}