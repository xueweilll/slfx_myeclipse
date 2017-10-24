package com.benqzl.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> findByPage(Map<String, Object> map);
    
    int pageCount(Map<String, Object> map);
    
    List<Employee> selectEmployeebyParentID(Map<String, String> map);
    
    int update_reloadpwdbyid(String id);
    
    List<User> selectUserByName(String username);
    
    List<User> selectUserList();

	void deleteByEmployeeid(String id);
	
	List<User> selectbyMuiltCombox(HashMap<String, String> map);
}