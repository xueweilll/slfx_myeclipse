package com.benqzl.controller.patrol;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.controller.patrol.pojo.DepartmentJson;
import com.benqzl.pojo.system.User;
import com.google.gson.Gson;

/** 
* @ClassName: EgPatrolEngineerexamineController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pxj 
* @date 2016年4月29日 下午4:53:13 
*  
*/
@Controller
@RequestMapping("egpatrolengineerexamine")
public class EgPatrolEngineerexamineController extends BasicController{

	public EgPatrolEngineerexamineController() {
		super(EgPatrolEngineerexamineController.class);
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(跳转工程科首页) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/egpatrolengineerexamine");
		return mv;
	}
	
	/** 
	* @Title: departmentPatrolInfo 
	* @Description: TODO(跳转工程科详细信息页) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value="egpatrolengineerexamineInfo",method=RequestMethod.GET)
	public ModelAndView departmentPatrolInfo(String jsonStr){
		gson = new Gson();
		DepartmentJson departmentJson = gson.fromJson(jsonStr, DepartmentJson.class);//获取前台传递的数据参数，解析成java类
		//模拟数据
		List<Map<String, Object>> maps = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> map =  new HashMap<>();
			String ID = UUID.randomUUID().toString();
			map.put("id", ID);
			map.put("name", i+"所");
			for (int j = 0; j < 3; j++) {
				Map<String, Object> cmap =  new HashMap<>();
				String CID=UUID.randomUUID().toString();
				cmap.put("id", CID);
				cmap.put("name", j+"枢纽");
				cmap.put("_parentId", ID);
				maps.add(cmap);
				for (int k = 0; k < 3; k++) {
					Map<String, Object> ccmap =  new HashMap<>();
					ccmap.put("id", UUID.randomUUID().toString());
					ccmap.put("name", k+"问题");
					ccmap.put("_parentId", CID);
					maps.add(ccmap);
				}
			}
			maps.add(map);
		}
		ModelAndView mv = new ModelAndView();
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("total", maps.size());
		jsonMap.put("rows", maps);
		mv.addObject("jsonStr", gson.toJson(jsonMap));
		mv.addObject("type", departmentJson.getType());
		mv.setViewName("patrol/egpatrolengineerexamineInfo");
		return mv;
	}
	/** 
	* @Title: egpatrolengineerexaminelist 
	* @Description: TODO(绑定数据) 
	* @param @param page
	* @param @param rows
	* @param @param starttime
	* @param @param endtime
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	
	@RequestMapping(value="egpatrolengineerexaminelist",method=RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String starttime,String endtime,String typeDate){
		Subject subject = SecurityUtils.getSubject();
		User user  =(User) subject.getSession().getAttribute("loginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", 4);
		if(typeDate.equals("0")){
			map.put("handler", user.getUserid());
		}else{
			map.put("handler", null);
		}
		if(starttime==null||starttime.trim().equals("")){
			map.put("starttime", null);
		}else{
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<Map<String, Object>> maps = new ArrayList<>();
		for (int i = 0; i < 20; i++) {//数据模拟
			Map<String, Object> data = new HashMap<>();
			data.put("id", UUID.randomUUID().toString());
			data.put("department", "一所");
			data.put("creater", "冯庆国");
			data.put("creatertime", datetimeFormat.format(new Date()));
			data.put("handler", "薛伟");
			data.put("handlertime", datetimeFormat.format(new Date()));
			data.put("memo", "aaaaaaaaa");
			data.put("state", 0);
			maps.add(data);
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", maps);
		jsonMap.put("total", maps.size());
		gson = new Gson();
		return gson.toJson(jsonMap);
	}
	/** 
	* @Title: patrolDataBind 
	* @Description: TODO(info页面数据绑定) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="patrolDataBind",method=RequestMethod.POST)
	@ResponseBody
	public String patrolDataBind(){
		return null;
	}
	
	

}
