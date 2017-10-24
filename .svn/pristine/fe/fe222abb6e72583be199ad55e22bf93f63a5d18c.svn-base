package com.benqzl.controller.oa;


import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.MessageReceiver;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.MessageReceiverService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: MessageController 
* @Description: 消息(这里用一句话描述这个类的作用) 
* @author fqg 
* @date 2015年12月15日 上午10:06:25 
*  
*/
@Controller
@RequestMapping("/messageReceiver")
public class MessageReceiverController extends BasicController{
	
	@Autowired
	private MessageReceiverService service;
	
	public MessageReceiverController() {
		super(MessageReceiverController.class);
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(消息增删改查页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/messageReceiver");
		return mv;
	}
	
	/** 
	* @Title: messageInfo 
	* @Description: TODO(消息中心添加和编辑页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "messageReceiverInfo", method = RequestMethod.GET)
	public ModelAndView messageInfo(int id) {
		ModelAndView mv = new ModelAndView();
	/*	MessageReceiver messageReceiver = service.findOne(id);
		mv.addObject("message", messageReceiver);*/
		mv.setViewName("oa/messageInfo");
		return mv;
	}
	
	/** 
	* @Title: bind 
	* @Description: TODO(消息中心数据获取) 
	* @param @param page
	* @param @param rows
	* @param @param type
	* @param @param messager
	* @param @param starttime
	* @param @param endtime
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String type,String starttime,String endtime,HttpServletRequest request){
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		User user=(User) request.getSession().getAttribute("loginUser");
		map.put("receiver", user.getUserid());
		if(starttime==null||starttime.trim().equals("")){
			map.put("starttime", null);
		}else{
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(type==null||type.trim().equals("")){
			map.put("ispc", null);
			map.put("isphone", null);
		}else{
			if(type.equals("0")){
				map.put("ispc", 0);
			}else{
				map.put("isphone", 0);
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
		List<MessageReceiver> messages = null;
		try {
			messages = service.findByPage(map);
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
		jsonMap.put("rows", messages);
		gson = new GsonBuilder().registerTypeAdapter(MessageReceiver.class, new JsonSerializer<MessageReceiver>() {

			@Override
			public JsonElement serialize(MessageReceiver arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				String str = "";
				if(arg0.getState().equals(new Long(0))){
					str="<span style='color:red;'>新消息，请查收</span>";
				}else{
					if(arg0.getMessage().getContents().length()>20){
						str=arg0.getMessage().getContents().substring(0, 20)+"...";
					}else{
						str=arg0.getMessage().getContents();
					}
				}
				json.addProperty("contents", str);
				json.addProperty("state", arg0.getState());
				json.addProperty("stime", datetimeFormat.format(arg0.getMessage().getSendtime()));
				json.addProperty("messager", arg0.getMessage().getUser().getUsername());
				if(arg0.getReceiptdate()!=null){
					json.addProperty("rtime", datetimeFormat.format(arg0.getReceiptdate()));
				}else{
					json.addProperty("rtime","");
				}
				return json;
			}
		}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	
	/** 
	* @Title: delete 
	* @Description: TODO(删除) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		try {
			service.deleteMessage(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'添加失敗！'}";
		}
		return "{'result':true}";
	}
	
	/** 
	* @Title: saveState 
	* @Description: TODO(更改消息状态) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/saveState",method=RequestMethod.POST)
	@ResponseBody
	public String saveState(String id,HttpServletRequest request){
		try {
			User user=(User) request.getSession().getAttribute("loginUser");
			service.updateState(id,user.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'添加失敗！'}";
		}
		return "{'result':true}";
	}
	@RequestMapping(value="/selectContents",method=RequestMethod.POST)
	@ResponseBody
	public String selectContents(String id){
		String contents=null;
		try {
			contents=service.selectContents(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "数据读取失敗";
		}
		return contents;
	}
}
