package com.benqzl.controller.system.authority;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Actor;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.ActorService;
import com.benqzl.service.system.EmployeeService;
import com.benqzl.service.system.UserService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
* @ClassName: UserController 
* @Description: TODO(用户增删改查) 
* @author 冯庆国 
* @date 2015年12月10日 上午8:24:03 
*
 */

@Controller
@RequestMapping("/user")
public class UserController extends BasicController{
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	
	@Autowired
	private UserService service;
	@Autowired
	private ActorService actorService;
	@Autowired
	private EmployeeService employeeService;

	public UserController() {
		super(UserController.class);
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(用户增删改查页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/user");
		return mv;
	}
	
	@RequestMapping(value = "editPwd", method = RequestMethod.GET)
	public ModelAndView editpwd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/editpwd");
		return mv;
	}
	
	/** 
	* @Title: userInfo 
	* @Description: TODO(用户添加和编辑页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo(String id) {
		ModelAndView mv = new ModelAndView();
		User user = null;
		try {
			user = service.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// User user = null;
		if(!id.equals("0")){
			Employee employee = employeeService.selectByPrimaryKey(user.getEmployee().getId());
			user.setEmployee(employee);
		}
		mv.addObject("user", user);
		mv.setViewName("system/userInfo");
		return mv;
	}
	@RequestMapping(value = "/changePWD", method = RequestMethod.POST)
	@ResponseBody
	public String changePWD(String newpwd,String oldpwd){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("loginUser");
		if(user.getUserpwd().equals(oldpwd)){
			User user2 = new User();
			user2.setUserid(user.getUserid());
			user2.setUserpwd(newpwd);
			try {
				service.updateByPrimaryKeySelective(user2);
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'密码修改失败！'}";
			}
		}else{
			return "{'result':false,'msg':'原密码错误！'}";
		}
		return "{'result':true}";
	}
	
	/** 
	* @Title: usersBind 
	* @Description: TODO(获取分页数据) 
	* @param @param page
	* @param @param rows
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/usersBind", method = RequestMethod.POST)
	@ResponseBody
	public String usersBind(int page,int rows,String actorid,String name){
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if(actorid==null||actorid.trim().equals("")){
			map.put("actorid", null);
		}else{
			map.put("actorid", actorid);
		}
		if(name==null||name.trim().equals("")){
			map.put("name", null);
		}else{
			map.put("name", name);
		}
		List<User> users = null;
		try {
			users = service.findByPage(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", users);
		gson = new GsonBuilder().registerTypeAdapter(User.class, new JsonSerializer<User>() {

			@Override
			public JsonElement serialize(User arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getUserid());
				json.addProperty("name", arg0.getUsername());
				json.addProperty("ename", arg0.getEmployee().getName());
				json.addProperty("aname", arg0.getActor().getName());
				json.addProperty("email", arg0.getEmail());
				json.addProperty("age", arg0.getAge());
				json.addProperty("sex", arg0.getSex().equals(new Long(0))?"男":"女");
				json.addProperty("createtime", datetimeFormat.format(arg0.getCreatetime()));
				json.addProperty("edittime", datetimeFormat.format(arg0.getEdittime()));
				return json;
			}
		}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	/** 
	* @Title: actorsBind 
	* @Description: TODO(得到角色列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/actorsBind",method=RequestMethod.POST)
	@ResponseBody
	public String actorsBind(){
		List<Actor> actors = actorService.selectAll();
		Actor actor = new Actor();
		actor.setId("0");
		actor.setName("全部");
		actors.add(0, actor);
		gson = new GsonBuilder().registerTypeAdapter(Actor.class, new JsonSerializer<Actor>() {

			@Override
			public JsonElement serialize(Actor arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				return json;
			}
		}).create();
		return gson.toJson(actors);
	}
	
	/** 
	* @Title: employeesBind 
	* @Description: TODO(根据部门ID得到员工列表) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/employeesBind",method=RequestMethod.POST)
	@ResponseBody
	public String employeesBind(String id,String empid){
		List<Employee> employees = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			if(empid.trim().equals("")||empid.trim()==null){
				map.put("empid", null);
			}else{
				map.put("empid", empid);
			}
			employees = service.selectEmployeebyParentID(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(Employee.class, new JsonSerializer<Employee>() {
			@Override
			public JsonElement serialize(Employee arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				return json;
			}
		}).create();
		return gson.toJson(employees);
	}
	
	/** 
	* @Title: save 
	* @Description: TODO(保存用户信息) 
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr){
		User user = gson.fromJson(jsonStr, User.class);
		if (user.getUserid().equals("0")) {
			try {
				service.insertSelective(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				service.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "{'result':true}";
	}
	/** 
	* @Title: delete 
	* @Description: TODO(伪删除用户信息) 
	* @param @param userid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(String userid){
		try {
			service.deleteByPrimaryKey(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{'result':true}";
	}
	
	/** 
	* @Title: reloadpwd 
	* @Description: TODO(重置用户密码‘12345’) 
	* @param @param userid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/saveReloadpwd",method=RequestMethod.POST)
	@ResponseBody
	public String reloadpwd(String userid){
		try {
			service.update_reloadpwdbyid(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{'result':true}";
	}
	
	@RequestMapping(value="/selectUser",method=RequestMethod.POST)
	@ResponseBody
	public String selectUser(String id,String name){
		boolean result = false;
		try {
			result = service.selectByName(id,name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result){
			return "true";
		}else{
			return "false";
		}
	}
	
	
}
