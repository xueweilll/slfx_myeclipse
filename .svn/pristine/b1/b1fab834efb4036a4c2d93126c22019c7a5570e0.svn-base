package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Actor;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.service.dispatch.DispatchInstructionsService;
import com.benqzl.service.system.ActorService;
import com.benqzl.service.system.EmployeeService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("dispatchinstructions")
public class SelfDispatchController extends BasicController {

	public SelfDispatchController() {
		super(SelfDispatchController.class);
	}
	@Resource
	private DispatchInstructionsService dis;
	
	@Autowired
	private ActorService actorService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("loginUser");
		String actorid=user.getActor().getId();
		Actor actor = actorService.selectByPrimaryKey(actorid);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/dispatchInstructions");
		if(actor!=null&&actor.getName().equals("超级管理员")){
			mv.addObject("actor", 0);
		}else{
			mv.addObject("actor", 1);
		}
		return mv;
	}
	
	
	@RequestMapping(value="dispatchInfo",method=RequestMethod.GET)
	public ModelAndView dispatchInfo(String id,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){			
			Map<String,String> map=new HashMap<String,String>();
			map.put("dispatchid", id);
			SelfDispatch sd=dis.findDispatchByid(map);
			mv.addObject("dispatch",sd);
			mv.addObject("promotertime",datetimeFormat.format(sd.getPromotetime()));
		}
		User user = (User) session.getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		mv.addObject("departmentid", e.getDepartment().getId());
		mv.setViewName("dispatch/patchInfo");
		return mv;
	}
	@RequestMapping(value="dispatchInfo2",method=RequestMethod.GET)
	public ModelAndView dispatchInfo2(String id){
		ModelAndView mv = new ModelAndView();			
		Map<String,String> map=new HashMap<String,String>();
		map.put("dispatchid", id);
		SelfDispatch sd=dis.findDispatchByid(map);
		mv.addObject("dispatch",sd);
		if(sd!=null){
			mv.addObject("promotertime",datetimeFormat.format(sd.getPromotetime()));
		}	
		mv.setViewName("dispatch/patchInfo2");
		return mv;
	}
	@RequestMapping(value = "/findInstructionsByid", method = RequestMethod.POST)
	@ResponseBody
	public String findInstructionsByid(String id) {
		String json="";
		List<SelfDispatchInstructions> list=dis.findInstructionsByid(id);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(SelfDispatchInstructions.class,
				new JsonSerializer<SelfDispatchInstructions>() {
					@Override
					public JsonElement serialize(SelfDispatchInstructions instruction, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", instruction.getId());
						if(instruction.getGateoperatetime()!=null){
							o.addProperty("gateoperatetime", datetimeFormat.format(instruction.getGateoperatetime()));
						}
						if(instruction.getUnitoperatetime()!=null){
							o.addProperty("unitoperatetime", datetimeFormat.format(instruction.getUnitoperatetime()));
						}							
						if(instruction.getInstruction().intValue()==0){
							o.addProperty("instruction1", "关闸");								
							o.addProperty("instruction2", "不操作");								
						}else if(instruction.getInstruction().intValue()==1){
							o.addProperty("instruction1", "开闸");								
							o.addProperty("instruction2", "不操作");
						}else if(instruction.getInstruction().intValue()==2){
							o.addProperty("instruction1", "不操作");								
							o.addProperty("instruction2", "关泵");
						}else if(instruction.getInstruction().intValue()==3){
							o.addProperty("instruction1", "不操作");								
							o.addProperty("instruction2", "开泵");
						}else if(instruction.getInstruction().intValue()==4){
							o.addProperty("instruction1", "关闸");								
							o.addProperty("instruction2", "开泵");
						}else if(instruction.getInstruction().intValue()==5){
							o.addProperty("instruction1", "关闸");								
							o.addProperty("instruction2", "关泵");
						}else if(instruction.getInstruction().intValue()==6){
							o.addProperty("instruction1", "开闸");								
							o.addProperty("instruction2", "关泵");
						}else if(instruction.getInstruction().intValue()==7){
							o.addProperty("instruction1", "开闸");								
							o.addProperty("instruction2", "开泵");
						}
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	@RequestMapping(value = "/findStationsByid", method = RequestMethod.POST)
	@ResponseBody
	public String findStationsByid(String id,String departmentid,
			String pd) {
		String json="";
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		map.put("departmentid", departmentid);
		List<SelfDispatchStations> list=new ArrayList<SelfDispatchStations>();
		if(pd==null){
			 list=dis.findStationsByid(id);
		}
		else if(pd.equals("self")){
			 list=dis.findDepartmentStation(map);	
		}else{
			 list=dis.findStationsByid(id);
		}	
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(SelfDispatchStations.class,
				new JsonSerializer<SelfDispatchStations>() {
					@Override
					public JsonElement serialize(SelfDispatchStations instruction, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", instruction.getId());
						o.addProperty("sid", findSnameByid(instruction.getSid()));
						o.addProperty("sids", instruction.getSid());
						System.out.println(instruction.getControlwaterlevel());
						System.out.println(instruction.getTruewaterlevel());
						o.addProperty("controlwater", instruction.getControlwaterlevel().toString());
						o.addProperty("truewater", instruction.getTruewaterlevel().toString());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	@RequestMapping(value = "/findSnameByid", method = RequestMethod.POST)
	@ResponseBody
	public String findSnameByid(String id) {
		String json="";
		json=dis.findSnameByid(id);
		return json;
	}
	@RequestMapping(value = "/dispatchList", method = RequestMethod.POST)
	@ResponseBody
	public String dispatchList(int page,int rows,String code,String begintime,String endtime) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(code!=null && !code.trim().equals("")){
			map.put("code", code);
		}
		if(begintime!=null && !begintime.trim().equals("")){
			map.put("begintime", begintime);
		}
		if(endtime!=null && !endtime.trim().equals("")){
			map.put("endtime", endtime);
		}
		List<SelfDispatch> list=dis.findDispatch(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = dis.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(SelfDispatch.class,
				new JsonSerializer<SelfDispatch>() {
					@Override
					public JsonElement serialize(SelfDispatch dispatch, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", dispatch.getId());
						o.addProperty("code", dispatch.getCode());
						o.addProperty("promoter", dispatch.getPromoter());						
						o.addProperty("promotetime", datetimeFormat.format(dispatch.getPromotetime()));
						o.addProperty("sender", dispatch.getSender());
						if(dispatch.getSendtime()!=null && !dispatch.getSendtime().equals("")){
							o.addProperty("sendtime",  datetimeFormat.format(dispatch.getSendtime()));
						}else{
							o.addProperty("sendtime",  "");
						}
						o.addProperty("memo", dispatch.getMemo());
						o.addProperty("creatername", dispatch.getCreateUser().getUsername());
						o.addProperty("createtime", datetimeFormat.format(dispatch.getCreatetime()));
						Long st=dispatch.getState();
//						List<SelfDispatchStations> sds=dispatch.getStations();
//						boolean xxx=true;					
//						for(int i=0;i<sds.size();i++){
//							if(sds.get(i).getState().intValue()!=3){
//								xxx=false;
//								break;
//							}
//						}
						switch(st.intValue()){
							case 0:
								o.addProperty("state","新建");
								break;
							case 1:
								o.addProperty("state","已提交");
								break;
							case 2:
								o.addProperty("state","审核通过");
								break;
							case 3:
								o.addProperty("state","审核未通过");
								break;
							case 4:
								o.addProperty("state","审批通过");
								break;
							case 5:
								o.addProperty("state","审批未通过");
								break;
							case 6:
								o.addProperty("state","执行部门调度中");
								break;
							case 7:
								o.addProperty("state","完成");
								break;
							default:
								o.addProperty("state","完成");
								break;
						}
						/*if(st.intValue()==0){
							o.addProperty("state","保存");
						}else if(st.intValue()==1){
							o.addProperty("state","已提交");
						}else if(st.intValue()==2){
							o.addProperty("state","已下发未完成");
						}else if(st.intValue()==3){
							o.addProperty("state","已完成");
						}*/
						if(dispatch.getCompletetime()!=null && !dispatch.getCompletetime().equals("")){
							o.addProperty("completetime", datetimeFormat.format(dispatch.getCompletetime()));
						}else{
							o.addProperty("completetime", "");
						}						
						o.addProperty("username", dispatch.getUser().getUsername());												
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		
		return json;
	}
	@RequestMapping(value="user",method=RequestMethod.POST)
	@ResponseBody
	public String user(){
		String json="";
		List<User> list=dis.findUser();
		json=gson.toJson(list);
		return json;
	}
	@RequestMapping(value="station",method=RequestMethod.POST)
	@ResponseBody
	public String station(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("loginUser");
		Map<String,Object> map=new HashMap<>();
		map.put("userid",user.getUserid() );
		String json="";
		List<Station> list=dis.findSelfStation(map);
		json=gson.toJson(list);
		return json;
	}
	
	@Autowired
	ActivitiUnitService activitiUnitService;
	
	@Autowired
	com.benqzl.service.activiti.ActivitiUnitService activitiUnit;
	@Resource
	EmployeeService employeeService;
	
	
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public String save(String json,HttpServletRequest request) throws ParseException {
		String result = "";				
		SelfDispatch selfdispatch=gson.fromJson(json,SelfDispatch.class);
		String dispatchid=selfdispatch.getId();
		User user2 = (User) request.getSession().getAttribute("loginUser");
		//新增
		if(dispatchid.equals("")){
			dispatchid=UUID.randomUUID().toString();
			selfdispatch.setId(dispatchid);
			selfdispatch.setCreater(user2.getUserid());
			selfdispatch.setCreatetime(new Date());
			if(selfdispatch.getState().intValue()==1){
				try {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("type", "A");
					map.put("objId", dispatchid);
					Employee e = employeeService.selectByUser(user2.getUserid());
					map.put("departmentId", e.getDepartment().getId());
					activitiUnitService.statrtByKey("selfDispatch", map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String code=activitiUnit.selectCode("SD"); 
				selfdispatch.setCode(code);
			}
			try {
				dis.insertDispatch(selfdispatch);
				List<SelfDispatchStations> selfdispatchstations=selfdispatch.getStations();
				for(int i=0;i<selfdispatchstations.size();i++){
					SelfDispatchStations selfdispatchstation=selfdispatchstations.get(i);
					UUID uuid=UUID.randomUUID();
					selfdispatchstation.setId(uuid.toString());
					selfdispatchstation.setSdid(dispatchid);
					dis.insertStation(selfdispatchstation);					
				}
				result="true";
			} catch (Exception e) {
				result="false";
				e.printStackTrace();
			}
		//编辑	
		}else{
			selfdispatch.setCreater(user2.getUserid());
			selfdispatch.setCreatetime(new Date());
			if(selfdispatch.getState().intValue()==1){
				try {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("type", "A");
					map.put("objId", dispatchid);
					Employee e = employeeService.selectByUser(user2.getUserid());
					map.put("departmentId", e.getDepartment().getId());
					activitiUnitService.statrtByKey("selfDispatch", map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String code=activitiUnit.selectCode("SD"); 
				selfdispatch.setCode(code);
			}
			try {
				dis.updateDispatch(selfdispatch);
				dis.deleteStation(dispatchid);
				List<SelfDispatchStations> selfdispatchstations=selfdispatch.getStations();
				for(int i=0;i<selfdispatchstations.size();i++){
					SelfDispatchStations selfdispatchstation=selfdispatchstations.get(i);
					UUID uuid=UUID.randomUUID();
					selfdispatchstation.setId(uuid.toString());
					selfdispatchstation.setSdid(dispatchid);
					dis.insertStation(selfdispatchstation);					
				}
				result="true";			
			} catch (Exception e) {
				result="false";
				e.printStackTrace();
			}
		}														
		return result;		
	}
	@RequestMapping(value="/dispatchDelete" , method=RequestMethod.POST)
	@ResponseBody
	public String dispatchDelete(String id) {
		String result="";
		try {
			dis.deleteInstruction(id);
			dis.deleteStation(id);
			dis.deleteDispatch(id);
			result="true";
		} catch (Exception e) {
			result="false";
			e.printStackTrace();
		}
		return result;
	}
		
	@RequestMapping(value = "/receptdispatch", method = RequestMethod.POST)
	@ResponseBody
	public String receptdispatch(int page,int rows,String code,String begintime,String endtime) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
//		if(code!=null && !code.trim().equals("")){
//			map.put("code", code);
//		}	
//		if(begintime!=null && !begintime.trim().equals("00:00:00")){
//			map.put("begintime", begintime);
//		}
//		if(endtime!=null && !endtime.trim().equals("23:59:59")){
//			map.put("endtime", endtime);
//		}
		List<ReceiptDispatch> list=dis.receptdispatch(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = dis.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatch.class,
				new JsonSerializer<ReceiptDispatch>() {
					@Override
					public JsonElement serialize(ReceiptDispatch rd, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", rd.getId());
						o.addProperty("launchtime",datetimeFormat.format(rd.getReceipt().getLaunchtime()));
						o.addProperty("launcher",rd.getReceipt().getLauncher());
						o.addProperty("receipter",rd.getReceipt().getReceipteUser().getUsername());
						o.addProperty("memo",rd.getReceipt().getMemo());
						Long memo=rd.getState();
						if(memo.intValue()==0){
							o.addProperty("state","保存");
						}else if(memo.intValue()==1){
							o.addProperty("state","已提交");
						}else if(memo.intValue()==2){
							o.addProperty("state","已下发未转发");
						}else if(memo.intValue()==3){
							o.addProperty("state","已转发未下发");
						}else if(memo.intValue()==4){
							o.addProperty("state","下发并且已转发");
						}else if(memo.intValue()==5){
							o.addProperty("state","回访中");
						}else if(memo.intValue()==6){
							o.addProperty("state","督查中");
						}else if(memo.intValue()==7){
							o.addProperty("state","转发完成");
						}else if(memo.intValue()==8){
							o.addProperty("state","完成");
						}
						o.addProperty("dispatchmemo",rd.getReceipt().getMemo());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);		
		return json;
	}
	@RequestMapping(value = "/findCode", method = RequestMethod.POST)
	@ResponseBody
	public String findCode() {
		String pref="SD";
		String code=activitiUnitService.selectMaxCode(pref);
		return code;
	}
	@RequestMapping(value = "findCodeByPref", method = RequestMethod.POST)
	@ResponseBody
	public String findCodeByPref(String pref) {
		return activitiUnitService.selectMaxCode(pref);
	}
}
