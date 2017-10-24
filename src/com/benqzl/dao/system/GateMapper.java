package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Gate;

public interface GateMapper {
    int deleteByPrimaryKey(String id);

    int insert(Gate record);

    int insertSelective(Gate record);

    Gate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Gate record);

    int updateByPrimaryKey(Gate record);
    
    List<Gate> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    void deleteIsdel(String id);
    
    int exsitCode(String code);

	List<Gate> selectAll();

	String nameOnlyOne(Map<String, String> map);

	String codeOnlyOne(Map<String, String> map);

	int selectcount();

	void deleteBySid(String id);
}