package com.benqzl.dao.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.system.User;

public interface ReceiptMapper {
	int deleteByPrimaryKey(String id);

    int insert(Receipt record);

    int insertSelective(Receipt record);

    Receipt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Receipt record);

    int updateByPrimaryKey(Receipt record);
    
    List<Receipt> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    List<User> userList();
    
    Receipt findByID(String id);
    
    List<Receipt> findByIds(List<String> list);

	List<Receipt> findDocket(List<String> list);
   
    int deleteState(String id);
    
    int aduitState(Map<String, Object> map);
    
    int commitState(String id);
    
    int updateState(Map<String, Object> map);

	Receipt findByTransmitID(String id);

	Receipt findReceiptDocket(HashMap<String, String> map);

	List<Receipt> findEDEndList(Map<String, Object> map);

	void updateState(HashMap<String, Object> map);

	int findEDEndCount(Map<String, Object> map);

	String selectCode(HashMap<String, Object> map);

}