package com.benqzl.dao.dispatch;

import java.util.List;

import com.benqzl.pojo.dispatch.ReceiptDispatchExecutePeople;

public interface ReceiptDispatchExecutePeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<ReceiptDispatchExecutePeople> record);

    int insertSelective(ReceiptDispatchExecutePeople record);

    ReceiptDispatchExecutePeople selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatchExecutePeople record);

    int updateByPrimaryKey(ReceiptDispatchExecutePeople record);

	List<ReceiptDispatchExecutePeople> selectExecutePeopleByExecuteId(
			String executeid);
}