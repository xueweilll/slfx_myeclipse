package com.benqzl.controller.system.authority;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.TaskService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: TaskCintroller 
* @Description: TODO(任务中心操作页面) 
* @author 冯庆国
* @date 2015年12月31日 下午1:25:57 
*  
*/
@Controller
@RequestMapping("task")
public class TaskCintroller extends BasicController{
	
	@Autowired
	private TaskService service;
	
	public TaskCintroller() {
		super(TaskCintroller.class);
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(任务中心操作页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/task");
		return mv;
	}
	
	@RequestMapping(value = "/imageInfo", method = RequestMethod.GET)
	public ModelAndView imageInfo(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("taskid", id);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map=service.findCoordingByTask(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("map", map);
		mv.setViewName("/system/image");
		return mv;
	}
	
	@RequestMapping(value = "viewBind", method = RequestMethod.GET)
	@ResponseBody
	public String viewBind(String id,HttpServletResponse response) {
		OutputStream os = null;
		InputStream is = null;
		try {
			is=service.findViewById(id);
			os=response.getOutputStream();
			for(int b=-1;(b=is.read())!=-1;){
				os.write(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				os.flush();
				os.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(HttpServletRequest request,int page,int rows,String starttime,String endtime,String type){
		User user = (User) request.getSession().getAttribute("loginUser");
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		map.put("userid", user.getUserid());
		if(starttime==null||starttime.trim().equals("")){
			map.put("starttime", null);
		}else{
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Task> activitiDeployments = null;
		List<HistoricTaskInstance> activitiDeploymentsHI = null;
		try {
			if(type.equals("0")){
				activitiDeployments = service.findByPageTask(map);
			}else{
				activitiDeploymentsHI = service.findByPageTaskHI(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			if(type.equals("0")){
				total = service.pageCount(map);
			}else{
				total = service.pageCountHI(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(type.equals("1")){
			jsonMap.put("total", total);
			jsonMap.put("rows", activitiDeploymentsHI);
			gson = new GsonBuilder().registerTypeAdapter(HistoricTaskInstanceEntity.class, new JsonSerializer<HistoricTaskInstanceEntity>() {
				@Override
				public JsonElement serialize(HistoricTaskInstanceEntity arg0, Type arg1,
						JsonSerializationContext arg2) {
					// TODO Auto-generated method stub
					JsonObject json = new JsonObject();
					json.addProperty("id", arg0.getId());
					json.addProperty("time", datetimeFormat.format(arg0.getCreateTime()));
					json.addProperty("name", arg0.getName());
					json.addProperty("uname", "个人任务");
					json.addProperty("formkey", arg0.getFormKey());
					json.addProperty("state", "1");
					
					//json.addProperty("pid", arg0.getProcessVariables().get("objid").toString());
					return json;
				}
			}).create();
		}else{
			jsonMap.put("total", total);
			jsonMap.put("rows", activitiDeployments);
			gson = new GsonBuilder().registerTypeAdapter(TaskEntity.class, new JsonSerializer<TaskEntity>() {
				@Override
				public JsonElement serialize(TaskEntity arg0, Type arg1,
						JsonSerializationContext arg2) {
					// TODO Auto-generated method stub
					JsonObject json = new JsonObject();
					json.addProperty("id", arg0.getId());
					json.addProperty("time", datetimeFormat.format(arg0.getCreateTime()));
					json.addProperty("name", arg0.getName());
					if(arg0.getAssignee()==null){
						json.addProperty("uname", "组任务");
					}else{
						json.addProperty("uname", "个人任务");
					}
					json.addProperty("formkey", arg0.getFormKey());
					json.addProperty("state", "0");
					//json.addProperty("pid", arg0.getProcessVariables().get("objid").toString());
					return json;
				}
			}).create();
		}
		
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	@ResponseBody
	public String start(String key){
		try {
			service.startP(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/claim", method = RequestMethod.POST)
	@ResponseBody
	public String claim(String id,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			service.claim(id,user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
