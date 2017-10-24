package com.benqzl.dao.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.ReceiptDispatchDepartment;

public interface ReceiptDispatchDepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReceiptDispatchDepartment record);

    int insertSelective(ReceiptDispatchDepartment record);

    ReceiptDispatchDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiptDispatchDepartment record);

    int updateByPrimaryKey(ReceiptDispatchDepartment record);

	void insertDepartment(List<ReceiptDispatchDepartment> rddepartment);

	void deleteDepartment(String reid);

	void updateById(Map<String, Object> map);

	int selectByPk(Map<String, Object> map);

	void update(HashMap<String, Object> map);
	
	

    List<ReceiptDispatchDepartment> findRdSendList(Map<String, Object> map);
	
	int findRdSendCount(Map<String, Object> map);
}