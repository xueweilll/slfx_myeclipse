package com.benqzl.controller.patrol;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.benqzl.controller.patrol.pojo.DepartmentJson;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalReport;
import com.benqzl.pojo.patrol.PatrolNormalReportDetails;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.DepartmentPatrolService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: DepartmentPatrolController 
* @Description: TODO(工程科汇总) 
* @author 冯庆国 
* @date 2016年4月27日 下午3:49:42 
*  
*/
@Controller
@RequestMapping("departmentPatrol")
public class DepartmentPatrolController extends BasicController{
	
	@Autowired
	private DepartmentPatrolService service;
	
	public DepartmentPatrolController() {
		super(DepartmentPatrolController.class);
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
		mv.setViewName("patrol/departmentPatrol");
		return mv;
	}
	
	/** 
	* @Title: departmentPatrolInfo 
	* @Description: TODO(跳转工程科详细信息页) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value="departmentPatrolInfo",method=RequestMethod.GET)
	public ModelAndView departmentPatrolInfo(String jsonStr){
		gson = new Gson();
		DepartmentJson departmentJson = gson.fromJson(jsonStr, DepartmentJson.class);//获取前台传递的数据参数，解析成java类
		List<PatrolNormalReport> list = new ArrayList<>();
		Map<String, Object> selectMap = new HashMap<>();
		selectMap.put("list", departmentJson.getIds());
		selectMap.put("pndtype", 2);
		selectMap.put("pndIsType", 1);
		try {
			list=service.selectReportDetailsList(selectMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		for (PatrolNormalReport report : list) {
			Map<String, Object> map =  new HashMap<>();
			map.put("id", report.getId());
			map.put("name", report.getDepartmentid());
			for (PatrolNormalReportDetails details : report.getPatrolNormalReportDetails()) {
				Map<String, Object> cmap =  new HashMap<>();
				cmap.put("id", details.getId());
				cmap.put("name", details.getPatrolNormal().getSid());
				cmap.put("_parentId", report.getId());
				for (PatrolNormalDetails normalDetails : details.getPatrolNormal().getPds()) {
					Map<String, Object> ccmap =  new HashMap<>();
					ccmap.put("id", normalDetails.getId());
					ccmap.put("name", normalDetails.getPatrolenumid().getText());
					ccmap.put("content", normalDetails.getContents());
					ccmap.put("_parentId", details.getId());
					ccmap.put("patrolid", details.getPatrolid());
					maps.add(ccmap);
				}
				maps.add(cmap);
			}
			maps.add(map);
		}
		ModelAndView mv = new ModelAndView();
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("total", maps.size());
		jsonMap.put("rows", maps);
		mv.addObject("jsonStr", gson.toJson(jsonMap));
		mv.addObject("type", departmentJson.getType());
		mv.setViewName("patrol/departmentPatrolInfo");
		return mv;
	}
	/** 
	* @Title: bind 
	* @Description: TODO(绑定数据) 
	* @param @param page
	* @param @param rows
	* @param @param starttime
	* @param @param endtime
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	
	@RequestMapping(value="bind",method=RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("pnrtype", 0);
		map.put("pnrtypes", 0);
		if(starttime==null||starttime.trim().equals("")){
			map.put("startTime", null);
		}else{
			try {
				map.put("startTime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endTime", null);
		}else{
			try {
				map.put("endTime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<PatrolNormalReport> list = new ArrayList<>();
		int count =0;
		try {
			list= service.findByPage(map);
			count=service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", list);
		jsonMap.put("total", count);
		gson = new GsonBuilder().registerTypeAdapter(PatrolNormalReport.class, new JsonSerializer<PatrolNormalReport>() {
			@Override
			public JsonElement serialize(PatrolNormalReport arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("departmentid", arg0.getDepartmentid());
				json.addProperty("reporter", arg0.getReporter());
				json.addProperty("reporttime", datetimeFormat.format(arg0.getReporttime()));
				return json;
			}
		}).create();
		return gson.toJson(jsonMap);
	}
	/** 
	* @Title: save 
	* @Description: TODO(info页面数据绑定) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr){
		Subject subject = SecurityUtils.getSubject();
		User user  =(User) subject.getSession().getAttribute("loginUser");
		PatrolNormal normal = gson.fromJson(jsonStr, PatrolNormal.class);
		normal.setCreater(user.getUserid());
		try {
			service.insert(normal);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'保存失败！'}";
		}
		return "{'result':true}";
	}
	
	

}
