package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Map<String, Object> map);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    /** 
    * @Title: selectByParentId 
    * @Description: TODO(根据parentId查询所有子类) 
    * @param @param id
    * @param @return    设定文件 
    * @return List<Department>    返回类型 
    * @throws 
    */
    List<Department> selectByParentId(String id);

	List<Department> selectAll();
	
	Department selectByName(String name);
    
    List<Department> selectByArea();
}