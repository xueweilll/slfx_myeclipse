package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.EmployeeMapper;
import com.benqzl.dao.system.UserMapper;
import com.benqzl.pojo.system.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private UserMapper user;
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			user.deleteByEmployeeid(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Employee record) {
		return employeeMapper.insert(record);
	}

	@Override
	public int insertSelective(Employee record) {
		return employeeMapper.insertSelective(record);
	}

	@Override
	public Employee selectByPrimaryKey(String id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Employee record) {
		return employeeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		return employeeMapper.updateByPrimaryKey(record);
	}

	@Override
	/*
	 * () 
	* <p>Title: selectAll</p> 
	* <p>Description:查询 </p> 
	* @return 
	* @see com.benqzl.service.EmployeeService#selectAll()
	 */
	public List<Employee> selectAll() {
		List<Employee> employees = employeeMapper.selectAll();
		return employees;
	}
/*
 * (非 Javadoc) 
* <p>Title: findByPage</p> 
* <p>Description: </p> 
* @param map
* @return 
* @see com.benqzl.service.EmployeeService#findByPage(java.util.Map)
 */
	@Override
	public List<Employee> findByPage(Map<String, Object> map) {
		return employeeMapper.findByPage(map);
	}
/*
 * (非 Javadoc) 
* <p>Title: pageCount</p> 
* <p>Description: 获取记录数</p> 
* @return 
* @see com.benqzl.service.EmployeeService#pageCount()
 */
	@Override
	public int pageCount(Map<String, Object> map) {
		return employeeMapper.pageCount(map);
	}

	@Override
	public Employee selectFindLeve(String userid) {
		return employeeMapper.selectFindLeve(userid);
	}

	@Override
	public Employee selectByUser(String userid) {
		return employeeMapper.selectByUser(userid);
	}

	@Override
	public List<Employee> findEmployeeName() {
		Employee emplyees=new Employee();
		emplyees.setId("0");
		emplyees.setName("全部");
		List<Employee> employee =employeeMapper.findEmployeeName();
		employee.add(0, emplyees);
		return employee;
	}


}
