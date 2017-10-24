package com.benqzl.controller.patrol;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.SzPatrol;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.SZPatrolService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("/szpatrolApproval")
public class SZPatrolApprovalController extends BasicController{
	public SZPatrolApprovalController() {
		super(SZPatrolApprovalController.class);
	}
	@Resource
	private SZPatrolService sz;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/szpatrolApproval");
		return mv;
	}
	@RequestMapping(value = "szpatrolApprovalInfo", method = RequestMethod.GET)
	public ModelAndView szpatrolApprovalInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
			SzPatrol szpatrol=sz.findSzPatrolById(id);
			String patroltime=datetimeFormat.format(szpatrol.getPatroltime());
			mv.addObject("szpatrol",szpatrol);
			mv.addObject("patroltime",patroltime);
		}
		mv.setViewName("patrol/szpatrolApprovalInfo");
		return mv;
	}
//	@RequestMapping(value = "szpatrolReport", method = RequestMethod.GET)
//	public ModelAndView szpatrolReport(String id) {
//		ModelAndView mv = new ModelAndView();
//		if(!id.equals("0")){
//			SzPatrol szpatrol=sz.findSzPatrolById(id);
//			String patroltime=datetimeFormat.format(szpatrol.getPatroltime());
//			mv.addObject("szpatrol",szpatrol);
//			mv.addObject("patroltime",patroltime);
//		}
//		mv.setViewName("patrol/szpatrolReport");
//		return mv;
//	}
////	@RequestMapping(value = "reportdbl", method = RequestMethod.GET)
////	public ModelAndView reportdbl(String patrolid) {
////		ModelAndView mv = new ModelAndView();
////		Patrol patrol=pas.findPatrolById(patrolid);
////		mv.addObject("patrol",patrol);		
////		mv.addObject("patroltime",datetimeFormat.format(patrol.getPatroltime()));
////		mv.setViewName("patrol/reportdbl");
////		return mv;
////	}
	@RequestMapping(value = "/szpatrolList", method = RequestMethod.POST)
	@ResponseBody
	public String szpatrolList(int page,int rows,String all,String begintime,String endtime,HttpServletRequest request) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(begintime!=null && !begintime.equals("")){
			map.put("begintime", begintime);
		}
		if(endtime!=null && !endtime.equals("")){
			map.put("endtime", endtime);
		}
		User user=(User)request.getSession().getAttribute("loginUser");
		String sid=sz.findSidByUserId(user.getUserid());
		map.put("sid", sid);
		List<SzPatrol> list=null;
		int total=0;
		if(all.equals("1")){			
			list=sz.findSZPatrolApproval(map);
			total = sz.pageApprovalCount(map);
		}else if(all.equals("0")){
			list=sz.findSZPatrolApprovalNotAll(map);
			total = sz.pageApprovalCountNotAll(map);
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(SzPatrol.class,
				new JsonSerializer<SzPatrol>() {
					@Override
					public JsonElement serialize(SzPatrol sz, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", sz.getId());
						o.addProperty("sid", sz.getStation().getName());
						o.addProperty("patroltime",datetimeFormat.format(sz.getPatroltime()));
						o.addProperty("patroladdress", sz.getPatroladdress());
						o.addProperty("patrolcase",sz.getPatrolcase());
						o.addProperty("patrolmemo",sz.getPatrolmemo());
						o.addProperty("creater",sz.getUser().getEmployee().getName());
						o.addProperty("createtime",datetimeFormat.format(sz.getCreatetime()));
						Date edittime=sz.getEdittime();
						if(edittime!=null){
							o.addProperty("edittime",datetimeFormat.format(edittime));
						}
						Date creatertime=sz.getCreatertime();
						if(creatertime !=null){
							o.addProperty("creatertime",datetimeFormat.format(creatertime));
						}
						Long state=sz.getState();
						if(state.intValue()==2){
							o.addProperty("state", "已审批");
						}else if(state.intValue()==1){
							o.addProperty("state", "已提交");
						}
//						o.addProperty("handler", sz.getUserapproval().getEmployee().getName());
//						String handlertime=datetimeFormat.format(sz.getHandlertime());
//						if(handlertime !=null){
//							o.addProperty("handlertime", handlertime);
//						}						
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);		
		return json;
	}
//	@RequestMapping(value = "/sid", method = RequestMethod.POST)
//	@ResponseBody
//	public String sid() {
//		String json="";
//		List<Station> list=sz.findStation();
//		json=gson.toJson(list);
//		return json;
//	}
//	@RequestMapping(value = "/user", method = RequestMethod.POST)
//	@ResponseBody
//	public String user(HttpServletRequest request) {
//		String json="";
//		User user=(User) request.getSession().getAttribute("loginUser");
//		String sid=sz.findSidByUserId(user.getUserid());
//		List<User> list=null;
//		//本枢纽的人
//		if(sid !=null){
//			list=sz.findUser(sid);
//		//全部枢纽的人
//		}else{
//			list=sz.findAllUser();
//		}		
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("rows",list);
//		gson = new GsonBuilder().registerTypeAdapter(User.class,
//				new JsonSerializer<User>() {
//					@Override
//					public JsonElement serialize(User user, Type typeOfSrc,JsonSerializationContext context) {
//						JsonObject o = new JsonObject();
//						o.addProperty("userid",user.getUserid());
//						o.addProperty("name",user.getEmployee().getName());
//						return o;
//					}
//				}).create();
//		json=gson.toJson(jsonMap);
//		json=json.replace("{\"rows\":", "");
//		json=json.substring(0,json.length()-1);		
//		return json;
//	}
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	@ResponseBody
//	public String save(String json,HttpServletRequest request) throws ParseException {
//		String result="";
//		String params[]=json.split("/");
//		String id=params[0];
//		String patroltime=params[1];
//		String patroladdress=params[2];
//		String patrolcase=params[3];
//		String patrolmemo=params[4];
//		String users[]=params[5].split(",");
//		//新增
//		if(id.equals("0")){			
//			SzPatrol szpatrol=new SzPatrol();
//			id=UUID.randomUUID().toString();
//			szpatrol.setId(id);
//			szpatrol.setCreatetime(new Date());
//			szpatrol.setPatroltime(datetimeFormat.parse(patroltime));
//			User user=(User)request.getSession().getAttribute("loginUser");
//			String sid=sz.findSidByUserId(user.getUserid());
//			szpatrol.setSid(sid);
//			szpatrol.setPatroladdress(patroladdress);
//			szpatrol.setPatrolcase(patrolcase);
//			szpatrol.setPatrolmemo(patrolmemo);
//			szpatrol.setCreater(user.getUserid());
//			szpatrol.setState(new Long(0));
//			try {
//				sz.insertSzPztrol(szpatrol);
//				for(int i=0;i<users.length;i++){
//					SzPatrolUser szuser=new SzPatrolUser();
//					UUID uuid=UUID.randomUUID();
//					szuser.setId(uuid.toString());
//					szuser.setSzpatrolid(id);
//					szuser.setUserid(users[i]);
//					sz.insertSzPatrolUser(szuser);
//				}
//				result="true";
//			} catch (Exception e) {
//				result="false";
//				e.printStackTrace();
//			}
//		//修改
//		}else{
//			SzPatrol szpatrol=new SzPatrol();
//			szpatrol.setId(id);
//			szpatrol.setEdittime(new Date());
//			szpatrol.setPatroltime(datetimeFormat.parse(patroltime));
//			szpatrol.setPatroladdress(patroladdress);
//			szpatrol.setPatrolcase(patrolcase);
//			szpatrol.setPatrolmemo(patrolmemo);
//			try {
//				sz.updateSzPztrol(szpatrol);
//				sz.deleteSzUser(id);
//				for(int i=0;i<users.length;i++){
//					SzPatrolUser szuser=new SzPatrolUser();
//					UUID uuid=UUID.randomUUID();
//					szuser.setId(uuid.toString());
//					szuser.setSzpatrolid(id);
//					szuser.setUserid(users[i]);
//					sz.insertSzPatrolUser(szuser);
//				}
//				result="true";
//			} catch (Exception e) {
//				result="false";
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//	@RequestMapping(value = "/findPatrolUser", method = RequestMethod.POST)
//	@ResponseBody
//	public String findPatrolUser(String szid) {
//		String result="";
//		List<SzPatrol> list= sz.findSzUserBySzId(szid);
//		if(list !=null && list.size()>0){
//			result="[";
//			for(int i=0;i<list.size();i++){
//				result+="\'"+list.get(i)+"\',";
//			}
//			result=result.substring(0,result.length()-1);
//			result+="]";
//		}
//		return result;
//	}
//	@RequestMapping(value = "/szDelete", method = RequestMethod.POST)
//	@ResponseBody
//	public String szDelete(String szid) {
//		String result="";
//		try {
//			sz.deleteSzUser(szid);
//			sz.deleteSz(szid);
//			result="true";
//		} catch (Exception e) {
//			result="false";
//			e.printStackTrace();
//		}
//		return result;
//	}
	@RequestMapping(value = "/approval", method = RequestMethod.POST)
	@ResponseBody
	public String approval(String szid,HttpServletRequest request) {
		String result="";
		try {
			SzPatrol szpatrol=new SzPatrol();
			szpatrol.setId(szid);
			User user=(User)request.getSession().getAttribute("loginUser");
			szpatrol.setHandler(user.getUserid());
			szpatrol.setHandlertime(new Date());
			szpatrol.setState(new Long(2));
			sz.report(szpatrol);
			result="true";
		} catch (Exception e) {
			result="false";
			e.printStackTrace();
		}
		return result;
	}
}
