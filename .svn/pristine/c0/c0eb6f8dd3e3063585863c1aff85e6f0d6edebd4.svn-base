package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Gate;

public interface GateService {
	
	List<Gate> findByPage(Map<String, Object> map);
	List<Gate> selectAll();
    
    int pageCount(Map<String, Object> map);
    
    void insert(Gate gate);
	
    Gate selectByPrimaryKey(String id);
    
    void updateByPrimaryKeySelective(Gate gate);
    
    void deleteIsdel(String id);
	
    int exsitCode(String code);
	String nameOnlyOne(Map<String, String> map);
	String codeOnlyOne(Map<String, String> map);
	int selectcount();
    
}
