package com.benqzl.controller.system.organization;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.core.LeeTree;
import com.benqzl.pojo.system.Department;
import com.benqzl.service.system.DepartmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/** 
* @ClassName: DepartmentController 
* @Description: TODO(部门正删改查) 
* @author 冯庆国
* @date 2015年12月10日 上午8:24:03 
*  
*/
@Controller
@RequestMapping("/department")
public class DepartmentController extends BasicController{
	@Autowired
	private DepartmentService service;
	/** 
	* <p>Title:构造函数 </p> 
	* <p>Description: 构造函数传入类的字节码</p>  
	*/
	public DepartmentController() {
		super(DepartmentController.class);
	}
	
	
	/** 
	* @Title: index 
	* @Description: TODO(显示页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/department");
		return mv;
	}
	
	@RequestMapping(value="/departmentsBind",method=RequestMethod.POST)
	@ResponseBody
	public String departmentsBind(String id){
		/*List<Tree> trees = service.selectByParentId(id);
		Gson gson = new Gson();
		return gson.toJson(trees);*/
		LeeTree<Department> leeTree = null;
		try {
			leeTree = service.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//gson日期格式化
		gson = new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd hh-mm-ss")  
		  .create();  ;
		return "["+gson.toJson(leeTree).replace("\"children\":[],",	"")+"]";
	}
	
	/** 
	* @Title: insertDepartment 
	* @Description: TODO(解析jsonStr,插入或更新department) 
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr){
		gson = new Gson();
		Department department = gson.fromJson(jsonStr, Department.class);
		if(department.getId().equals(department.getPareid())){
			department.setId(UUID.randomUUID().toString());
			department.setCreatetime(new Date());
			department.setEdittime(new Date());
			department.setIsdel(new Long(0));
			try {
				service.insertSelective(department);
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false}";
			}
		}else{
			department.setEdittime(new Date());
			try {
				service.updateByPrimaryKeySelective(department);
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false}";
			}
		}
		return "{'result':true}";
	}
	
	
	/** 
	* @Title: updateDepartment 
	* @Description: TODO(根据ID更新状态) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		try {
			service.updateDepartment(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "result:true";
	}
	@RequestMapping(value="/selectDepartmentName",method=RequestMethod.POST)
	@ResponseBody
	public String selectDepartmentName(String id,String name){
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
