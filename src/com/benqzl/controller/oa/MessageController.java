package com.benqzl.controller.oa;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.Message;
import com.benqzl.pojo.oa.MessageReceiver;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.MessageService;
import com.benqzl.unit.SendMessageAutoUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: MessageController 
* @Description: TODO(消息发送与保存) 
* @author 冯庆国
* @date 2015年12月21日 上午8:49:06 
*  
*/
@Controller
@RequestMapping("/message")
public class MessageController extends BasicController{
	
	@Autowired
	private MessageService service;
	
	public MessageController() {
		super(MessageController.class);
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
		mv.setViewName("/oa/message");
		return mv;
	}
	
	/** 
	* @Title: messageInfo 
	* @Description: TODO(消息添加页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "messageInfo", method = RequestMethod.GET)
	public ModelAndView messageInfo(int id) {
		ModelAndView mv = new ModelAndView();
/*		Message message = messageService.findOne(id);
		// Message message = null;
		mv.addObject("message", message);*/
		mv.setViewName("oa/messageInfo");
		return mv;
	}
	
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
			users = service.selectUsers();
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
				json.addProperty("ephone", arg0.getEmployee().getPhone());
				json.addProperty("dname", arg0.getEmployee().getDepartment().getName());
				
				return json;
			}
		}).create();
		strJson = gson.toJson(users);
		return strJson;
	}
	
	/** 
	* @Title: bind 
	* @Description: TODO(消息数据获取) 
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
	public String bind(int page,int rows,String starttime,String endtime,HttpServletRequest request){
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
		map.put("messager", user.getUserid());
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
		List<Message> messages = null;
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
		gson = new GsonBuilder().registerTypeAdapter(Message.class, new JsonSerializer<Message>() {

			@Override
			public JsonElement serialize(Message arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				String str = "";
				if(arg0.getContents().length()>20){
					str=arg0.getContents().substring(0, 20)+"...";
				}else{
					str=arg0.getContents();
				}
				json.addProperty("contents", str);
				json.addProperty("contentsAll", arg0.getContents());
				json.addProperty("createtime", datetimeFormat.format(arg0.getCreatetime()));
				json.addProperty("edittime", datetimeFormat.format(arg0.getEdittime()));
				json.addProperty("sendtime", datetimeFormat.format(arg0.getSendtime()));
				json.addProperty("isphonemess", arg0.getIsphonemess().equals(new Long(0))?"是":"否");
				json.addProperty("ispc", arg0.getIspc().equals(new Long(0))?"是":"否");
				
				return json;
			}
		}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	
	/** 
	* @Title: save 
	* @Description: TODO(消息数据保存) 
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr,HttpServletRequest request){
		String[] strs = jsonStr.split("/");
		String id = strs[0];
		String[] usersid = strs[1].split(",");
		String contents = strs[2];
		String types = strs[3];
		if(id.equals("0")){
			Message message = new Message();
			message.setId(UUID.randomUUID().toString());
			message.setContents(contents);
			message.setCreatetime(new Date());
			message.setEdittime(new Date());
			message.setSendtime(new Date());
			User user=(User) request.getSession().getAttribute("loginUser");
			message.setUser(user);
			message.setIsDel(new Long(0));
			List<MessageReceiver> receivers =new ArrayList<MessageReceiver>();
			for (String userid : usersid) {
				MessageReceiver receiver = new MessageReceiver();
				receiver.setId(UUID.randomUUID().toString());
				receiver.setMessage(message);
				receiver.setReceiverid(userid);
				receiver.setState(new Long(0));
				receivers.add(receiver);
			}
			List<String> list = new ArrayList<String>();
			for (String userid : usersid) {
				if(userid!=null&&userid.trim()!=""){
					list.add(userid);
				}
			}
			if(types.equals("0")){//发送手机
				message.setIsphonemess(new Long(0));
				message.setIspc(new Long(1));
			}else if(types.equals("1")){//发送PC
				SendMessageAutoUtil.sendMessageListAuto(list);
				message.setIsphonemess(new Long(1));
				message.setIspc(new Long(0));
			}else{//同时发送
				SendMessageAutoUtil.sendMessageListAuto(list);
				message.setIsphonemess(new Long(0));
				message.setIspc(new Long(0));
			}
			try {
				service.insert(message,receivers);
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'添加失敗！'}";
			}
		}
		return "{'result':true}";
	}
	/** 
	* @Title: delete 
	* @Description: TODO(消息删除) 
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
	* @Title: receiverBind 
	* @Description: TODO(获取消息接收人) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/receiverBind",method=RequestMethod.POST)
	@ResponseBody
	public String receiverBind(String id){
		String strJson = "";
		List<User> users = null;
		try {
			users = service.selectUsersById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(User.class, new JsonSerializer<User>() {
			@Override
			public JsonElement serialize(User arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				//json.addProperty("id", arg0.getUserid());
				//json.addProperty("name", arg0.getUsername());
				json.addProperty("name", arg0.getEmployee().getName());
				json.addProperty("phone", arg0.getEmployee().getPhone());
				//json.addProperty("dname", arg0.getEmployee().getDepartment().getName());
				
				return json;
			}
		}).create();
		strJson = gson.toJson(users);
		return strJson;
	}
}
