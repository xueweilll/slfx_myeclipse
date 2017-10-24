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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.ActivitiDeployment;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.MessageService;
import com.benqzl.service.system.WorkFlowService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: WorkFlowController 
* @Description: TODO(流程配置中心) 
* @author 冯庆国
* @date 2016年1月12日 下午2:01:52 
*  
*/
@Controller
@RequestMapping("workFlow")
public class WorkFlowController extends BasicController{
	@Autowired
	private WorkFlowService service;
	public WorkFlowController() {
		super(WorkFlowController.class);
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(流程配置页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/workFlow");
		return mv;
	}
	
	/** 
	* @Title: workFlowInfo 
	* @Description: TODO(流程增加页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "workFlowInfo", method = RequestMethod.GET)
	public ModelAndView workFlowInfo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/workFlowInfo");
		return mv;
	}
	
	/** 
	* @Title: workFlowTaskUser 
	* @Description: TODO(配置任务办理人页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "workFlowTaskUserInfo", method = RequestMethod.GET)
	public ModelAndView workFlowTaskUser(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pid", id);
		mv.setViewName("/system/workFlowTaskUser");
		return mv;
	}
	
	/** 
	* @Title: imageInfo 
	* @Description: TODO(查看流程图) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/imageInfo", method = RequestMethod.GET)
	public ModelAndView imageInfo(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pid", id);
		mv.setViewName("/system/image");
		return mv;
	}
	/** 
	* @Title: viewBind 
	* @Description: TODO(输出流程图片信息) 
	* @param @param id
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				os.flush();
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/** 
	* @Title: taskBind 
	* @Description: TODO(绑定任务办理人信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/taskBind", method = RequestMethod.POST)
	@ResponseBody
	public String taskBind(String id){
		String strJson="";
		List<Map<String, Object>> taskDefinitions = null;
		try {
			taskDefinitions=service.findTaskAll(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		strJson = gson.toJson(taskDefinitions);
		return strJson;
	}
	
	/** 
	* @Title: bind 
	* @Description: TODO(流程列表信息绑定) 
	* @param @param page
	* @param @param rows
	* @param @param starttime
	* @param @param endtime
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String starttime,String endtime){
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
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
				e.printStackTrace();
			}
		}
		List<ActivitiDeployment> activitiDeployments = null;
		try {
			activitiDeployments = service.findByPageDeployment(map);
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
		jsonMap.put("rows", activitiDeployments);
		gson = new GsonBuilder().registerTypeAdapter(ActivitiDeployment.class, new JsonSerializer<ActivitiDeployment>() {
			@Override
			public JsonElement serialize(ActivitiDeployment arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("time", datetimeFormat.format(arg0.getCreatetime()));
				json.addProperty("name", arg0.getDeploymentname());
				json.addProperty("pid", arg0.getProcessdefinitionid());
				json.addProperty("version", arg0.getVersion());
				if(arg0.getType().equals(new Long(0))){
					json.addProperty("type", "防洪调度");
				}else{
					json.addProperty("type", "安防巡检");
				}
				return json;
			}
		}).create();
		strJson = gson.toJson(jsonMap);
		System.out.println(strJson);
		return strJson;
	}
	/** 
	* @Title: save 
	* @Description: TODO(添加流程) 
	* @param @param zipfile
	* @param @param name
	* @param @param type
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(MultipartFile zipfile,String name,String type,HttpServletRequest request){
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			service.insertDeployment(zipfile,name,type,user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "result:false";
		}
		return "result:true";
	}
	
	/** 
	* @Title: delete 
	* @Description: TODO(删除流程) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		try {
			service.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "result:false";
		}
		return "result:true";
	}
	
	/** 
	* @Title: saveTaskUser 
	* @Description: TODO(保存任务办理人信息) 
	* @param @param jsonStr
	* @param @param pid
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/saveTaskUser",method=RequestMethod.POST)
	@ResponseBody
	public String saveTaskUser(String jsonStr,String pid,HttpServletRequest request){
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			service.saveTaskUser(jsonStr,pid,user.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'添加失敗！'}";
		}
		return "{'result':true}";
	}
	/** 
	* @Title: deleteDeployment 
	* @Description: TODO(真实删除流程) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/deleteDeployment",method=RequestMethod.POST)
	@ResponseBody
	public String deleteDeployment(String id){
		try {
			service.deleteDeployment(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'添加失敗！'}";
		}
		return "{'result':true}";
	}
	
	@Autowired
	private MessageService messageService;
	/** 
	* @Title: userBind 
	* @Description: TODO(用户数据获取) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/userBind", method = RequestMethod.POST)
	@ResponseBody
	public String userBind(){
		String strJson = "";
		List<User> users = null;
		try {
			users = messageService.selectUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(User.class, new JsonSerializer<User>() {
			@Override
			public JsonElement serialize(User arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getUserid());
				json.addProperty("name", arg0.getUsername());
				json.addProperty("ename", arg0.getEmployee().getName());
				json.addProperty("ephone", arg0.getEmployee().getSid());
				json.addProperty("dname", arg0.getEmployee().getDepartment().getName());
				
				return json;
			}
		}).create();
		strJson = gson.toJson(users);
		return strJson;
	}
}
