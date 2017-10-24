package com.benqzl.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.core.LeeTree;
import com.benqzl.dao.system.DepartmentMapper;
import com.benqzl.pojo.system.Department;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper mapper;
	
	
	@Override
	public int insert(Department record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Department record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public Department selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}
	
	/* (非 Javadoc) 
	* <p>Title: selectAll</p> 
	* <p>Description:获取全部的部门Tree信息</p> 
	* @return 
	* @see com.benqzl.service.DepartmentService#selectAll() 
	*/
	@Override
	public LeeTree<Department> selectAll() {
		Department department1 = new Department();
		department1.setId("0");
		department1.setName("常州城市防洪处");
		LeeTree<Department> leeTree =  new LeeTree<>("0","常州城市防洪处","",department1,true);
		List<Department> list = mapper.selectAll();
		LeeTree<Department> node;
		for(Department department : list){
			node = new LeeTree<>(department.getId(), department.getName(), department.getPareid(), department, true);
			if(node.getPareId().equals(leeTree.getId())){
				leeTree.addNode(recursionLeeTree(node,list));
			}
		}
		return leeTree;
	}
	
	private LeeTree<Department> recursionLeeTree(LeeTree<Department> parent,List<Department> list){
		LeeTree<Department> node = null;
		for(Department department : list){
			if(department.getPareid().equals(parent.getId())){
				node = new LeeTree<>(department.getId(), department.getName(), department.getPareid(), department, true);
				parent.addNode(recursionLeeTree(node,list));
			}
		}
		return parent;
	}

	/* (非 Javadoc) 
	* <p>Title: updateDepartment</p> 
	* <p>Description:更新部门状态信息(伪删除) </p> 
	* @param id
	* @return 
	* @see com.benqzl.service.DepartmentService#updateDepartment(java.lang.String) 
	*/
	@Override
	public int updateDepartment(String id) {
		List<Department> list = mapper.selectAll();
		List<String> departments = new ArrayList<String>();
		for (Department department : list) {
			if(department.getId().equals(id)){
				departments.add(department.getId());
				departments.addAll(recursionDepartment(department.getId(),list));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isdel", 1);
		map.put("edittime", new Date());
		map.put("list", departments);
		mapper.deleteByPrimaryKey(map);
		return 0;
	}
	
	
	/** 
	* @Title: updateChildren 
	* @Description: TODO(根据传入的ID值，循环改变Department的状态) 
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public List<String> recursionDepartment(String id,List<Department> list){
		List<String> departments = new ArrayList<String>();
		for (Department department : list) {
			if(department.getPareid().equals(id)){
				departments.add(department.getId());
				departments.addAll(recursionDepartment(department.getId(),list));
			}
		}
		return departments;
	}

	@Override
	public boolean selectByName(String id, String name) throws Exception {
		// TODO Auto-generated method stub
		Department department = mapper.selectByName( name);
		if (department != null ) {
			if(department.getId().equals(id)&&department.getName().equals(name)){
				return true;
			}else{
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public List<Department> selectByArea() {
		return mapper.selectByArea();
	}
}
