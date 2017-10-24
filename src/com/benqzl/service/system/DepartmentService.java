package com.benqzl.service.system;

import java.util.List;

import com.benqzl.core.LeeTree;
import com.benqzl.pojo.system.Department;

public interface DepartmentService {

	int insert(Department record)throws Exception;

	int insertSelective(Department record)throws Exception;

	Department selectByPrimaryKey(String id)throws Exception;

	int updateByPrimaryKeySelective(Department record)throws Exception;

	int updateByPrimaryKey(Department record)throws Exception;

	/**
	 * @Title: selectByParentId
	 * @Description: TODO(根据parentId查询所有子类)
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 * @throws
	 */
	/*List<LeeTree<Department>> selectByParentId(String id);*/
	LeeTree<Department> selectAll()throws Exception;
	
	int updateDepartment(String id)throws Exception;

	boolean selectByName(String id, String name) throws Exception;
	
	List<Department> selectByArea();
}
