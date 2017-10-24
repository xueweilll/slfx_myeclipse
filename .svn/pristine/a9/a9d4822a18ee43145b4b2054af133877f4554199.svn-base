package com.benqzl.controller.system;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.MessageCenter;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.MessageCenterService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: MessageCenterController 
* @Description: TODO(消息中心) 
* @author 冯庆国
* @date 2015年12月25日 上午10:18:28 
*  
*/
@Controller()
@RequestMapping("messageCenter")
public class MessageCenterController extends BasicController{
	
	
	@Autowired
	private MessageCenterService service;
	
	
	public MessageCenterController() {
		super(MessageCenterController.class);
		// TODO Auto-generated constructor stub
	}
/*	
	*//** 
	* @Title: index 
	* @Description: TODO(消息中心增删改查页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*//*
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/messageCenter");
		return mv;
	}
	*/
	
	/** 
	* @Title: bind 
	* @Description: TODO(获取分页数据) 
	* @param @param page
	* @param @param rows
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,HttpServletRequest request){
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		User user=(User)request.getSession().getAttribute("loginUser");
		map.put("id", user.getUserid());
		List<MessageCenter> centers = null;
		try {
			centers = service.findByPage(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.pageCount(user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", centers);
		gson = new GsonBuilder().registerTypeAdapter(MessageCenter.class, new JsonSerializer<MessageCenter>() {

			@Override
			public JsonElement serialize(MessageCenter arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("sname", arg0.getSendUser().getUsername());
				json.addProperty("mname", arg0.getMenu().getMenuname());
				json.addProperty("murl", arg0.getMenu().getMenuurl());
				json.addProperty("micon", arg0.getMenu().getMenuicon());
				json.addProperty("tip", arg0.getTid());
				json.addProperty("sendtime", datetimeFormat.format(arg0.getSendtime()));
				return json;
			}
		}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		try {
			service.deleteByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	@RequestMapping(value="/deleteAll",method=RequestMethod.POST)
	@ResponseBody
	public String deleteAll(){
		try {
			Subject subject = SecurityUtils.getSubject();
			User user = (User) subject.getSession().getAttribute("loginUser");
			service.deleteAll(user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{'result':false,'msg':'提交失败！'}";
		}
		return "{'result':true}";
	}
	
	
	@RequestMapping(value="/selectCount",method=RequestMethod.POST)
	@ResponseBody
	public String selectCount(HttpServletRequest request){
		int count=0;
		try {
			User user=(User)request.getSession().getAttribute("loginUser");
			count=service.pageCount(user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return count+"";
	}
}
