package com.benqzl.service.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*import com.benqzl.dao.system.DepartmentMapper;*/
import com.benqzl.dao.system.EmployeeMapper;
import com.benqzl.dao.system.UserMapper;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	/*@Autowired
	private DepartmentMapper departmentMapper;*/
	@Override
	public int deleteByPrimaryKey(String userid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(userid);
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	/* (非 Javadoc) 
	* <p>Title: insertSelective</p> 
	* <p>Description:保存用户信息 </p> 
	* @param record
	* @return 
	* @see com.benqzl.service.UserService#insertSelective(com.benqzl.pojo.User) 
	*/
	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(record.getEmployee().getId());
		record.setAge(employee.getAge());
		record.setIsdel(new Long(0));
		record.setSex(employee.getSex());
		record.setCreatetime(new Date());
		record.setEdittime(new Date());
		record.setUserpwd("12345");
		record.setUserid(UUID.randomUUID().toString());
		return mapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(String userid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(userid);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		record.setEdittime(new Date());
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<User> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageCount(map);
	}

	@Override
	public List<Employee> selectEmployeebyParentID(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.selectEmployeebyParentID(map);
	}

	@Override
	public int update_reloadpwdbyid(String id) {
		// TODO Auto-generated method stub
		return mapper.update_reloadpwdbyid(id);
	}

	@Override
	public boolean selectByName(String userid, String userName) {
		// TODO Auto-generated method stub
		List<User> users = mapper.selectUserByName(userName);
		if (users != null && users.size() > 0) {
			for (User user : users) {
				if(user.getUserid().equals(userid)&&user.getUsername().equals(userName)){
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public User selectByName(String userName) throws Exception {
		List<User> users = mapper.selectUserByName(userName);
		if(users.size()==0){
			return null;
		}else{
			return users.get(0);
		}
	}

	@Override
	public List<User> selectUserList() {
		return mapper.selectUserList();
	}

	@Override
	public List<User> selectbyMuiltCombox(HashMap<String, String> map) {
		return mapper.selectbyMuiltCombox(map);
	}
}
