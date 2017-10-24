package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Employee;


public interface EmployeeService {
    int deleteByPrimaryKey(String id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    List<Employee> selectAll();

	List<Employee> findByPage(Map<String, Object> map);
	
	int pageCount(Map<String, Object> map);

	Employee selectFindLeve(String userid);
	
	Employee selectByUser(String userid);

	List<Employee> findEmployeeName();

	
}