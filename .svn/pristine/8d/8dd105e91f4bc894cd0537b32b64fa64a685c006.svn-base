package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchDepartment;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.User;
import com.benqzl.service.dispatch.DispatchIssuedListService;
import com.benqzl.service.dispatch.SelfDispatchStationsService;
import com.benqzl.service.system.EmployeeService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("dispatchIssuedList")
public class DispatchIssuedController extends BasicController {

	public DispatchIssuedController() {
		super(DispatchIssuedController.class);
	}

	@Autowired
	private DispatchIssuedListService dispatchIssuedListService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/dispatchIssuedList");
		return mv;
	}

	/**
	 * @Title: search
	 * @Description: TODO(查询文件)
	 * @param @param page
	 * @param @param rows
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/dispatchIssuedList", method = RequestMethod.POST)
	@ResponseBody
	public String search(HttpServletRequest request,int page, int rows, String code,String begintime,String endtime,String typeDate) {
		String strJson = "";
		Map<String, Object> map = new HashMap<String, Object>();
		gson = new Gson();
		User user = (User) request.getSession().getAttribute("loginUser");
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if(typeDate.equals("1")){
			map.put("rpid", null);
		}else{
			try {
				List<Map<String, Object>> ids=activitiUnitService.findDistricts(user.getUserid(),"A");
				if(ids.size()==0){
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					List<String> l = new ArrayList<String>();
					jsonMap.put("total", 0);
					jsonMap.put("rows", l);
					strJson=gson.toJson(jsonMap);
					return strJson;
				}else{
					int k = 0;
					String strwhere = "";
					for(Map<String,Object> string:ids){
						String s = "";
						s += "(d.id = '" + string.get("objId").toString() + "' and depatment.departmentid ='" +  string.get("departmentId") +"') ";
						if (k == 0) {
							if (ids.size() == 1) {
								strwhere = "( " + s + ")";
							} else {
								strwhere = "( " + s + "or ";
							}
						} else if (k == (ids.size() - 1)) {
							strwhere += " " + s + " ) ";
						} else {
							strwhere += s + "or ";
						}
						k++;
					}
					/*List<String> lObjs = new ArrayList<>();
					for(Map<String, Object> m : ids){
						lObjs.add(m.get("objId").toString());
					}*/
					map.put("rpid", strwhere);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(code == null || code.length() ==0){
			map.put("code", null);
		}else {
			map.put("code", code);
		}
		
		if(begintime == null || begintime.length() == 0){
			map.put("begintime",null);
		}else {
			map.put("begintime", begintime);
		}
		if(endtime == null || endtime.length() == 0){
			map.put("endtime", null);
		}else {
			map.put("endtime", endtime);
		}
		List<SelfDispatchDepartment> list = dispatchIssuedListService.findByPages(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = dispatchIssuedListService.pageCounts(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(SelfDispatchDepartment.class,
				new JsonSerializer<SelfDispatchDepartment>() {	
					@Override
					public JsonElement serialize(SelfDispatchDepartment arg0,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						SelfDispatch src = arg0.getSd();
						if(src.getState()==4){
						o.addProperty("state","领导审批通过");
						}else if(src.getState()==6){
							o.addProperty("state","片区调度中");
						}else if(src.getState()==7){
							o.addProperty("state","片区调度完成");
						}
						o.addProperty("departmentid", arg0.getDepartmentid());
						if(arg0.getDepartment()!=null){
						o.addProperty("name", arg0.getDepartment().getName());}
						o.addProperty("id", src.getId());
						o.addProperty("promotetime",datetimeFormat.format(src.getPromotetime()));
						if(src.getUser() !=null){
						o.addProperty("username", src.getUser().getUsername());
						}
						o.addProperty("memo", src.getMemo());
						o.addProperty("creater", src.getCreateUser().getUsername());
						o.addProperty("createtime",datetimeFormat.format(src.getCreatetime()));
						o.addProperty("code", src.getCode());
						if(arg0.getSendUser() != null){
							o.addProperty("sender", arg0.getSendUser().getUsername());
						}
						if(src.getSendtime() != null){
							o.addProperty("sendertime",	datetimeFormat.format(arg0.getSendtime()));
						}else {
							o.addProperty("sendertime","");
						}
						//o.addProperty("state", arg0.getState());	
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		
		
		logger.info("this document list strJson is " + strJson);
		return strJson;
	}

	/**
	 * @Title: documentInfo
	 * @Description: TODO(用户添加和编辑页面)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "dispatchIssuedInfo", method = RequestMethod.GET)
	public ModelAndView dispatchIssuedInfo(String id,String state,String departmentid) {

		ModelAndView mv = new ModelAndView();
		SelfDispatch dispatch = dispatchIssuedListService.selectDispatchIssuedInfo(id);
		if(dispatch.getPromotetime()!=null){
		mv.addObject("promotetime",
				datetimeFormat.format(dispatch.getPromotetime()));}
		mv.addObject("promoter", dispatch.getEmployee().getName());
		mv.addObject("memo", dispatch.getMemo());
		mv.addObject("dispatch", dispatch);	
		mv.addObject("state",state);
		mv.addObject("departmentid",departmentid);
		mv.setViewName("dispatch/dispatchIssuedInfo");
		return mv;
	}

	/**
	 * @Title: ddmx
	 * @Description: TODO(调度明细)
	 * @param @param id
	 * @param @param fileaddress
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/ddmx", method = RequestMethod.POST)
	@ResponseBody
	public String ddmx(String id,HttpServletRequest request) {
		List<SelfDispatchStations> stations = dispatchIssuedListService.selectSDStationBySDID(id);
		gson = new GsonBuilder().registerTypeAdapter(
				SelfDispatchStations.class,
				new JsonSerializer<SelfDispatchStations>() {
					@Override
					public JsonElement serialize(SelfDispatchStations src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("station", src.getStation().getName());
						return o;
					}
				}).create();
		return gson.toJson(stations);
	}

	/**
	 * @Title: ddzl
	 * @Description: TODO(调度指令)
	 * @param @param id
	 * @param @param fileaddress
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/ddzl", method = RequestMethod.POST)
	@ResponseBody
	public String ddzl(String id) {
		System.out.println(id);
		List<SelfDispatchInstructions> instructions = dispatchIssuedListService.selectSDInstructionsBySDID(id);
		gson = new GsonBuilder().registerTypeAdapter(
				SelfDispatchInstructions.class,
				new JsonSerializer<SelfDispatchInstructions>() {
					@Override
					public JsonElement serialize(SelfDispatchInstructions src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						String zl = "";
						String beng = "";
						if (src.getInstruction() == 0) {
							zl = "关闸";// 0:关闸,1:开闸 ,2:关泵3:开泵 4:关闸 开泵5:关闸关泵 6:开闸关泵 7:开闸开泵
							beng = "不操作";
						} else if (src.getInstruction() == 1) {
							zl = "开闸";
							beng = "不操作";
						} else if (src.getInstruction() == 2) {
							beng = "关泵";
							zl = "不操作";
						} else if (src.getInstruction() == 3) {
							beng = "开泵";
							zl = "不操作";
						} else if (src.getInstruction() == 4) {
							zl = "关闸";
							beng = "开泵";
						} else if (src.getInstruction() == 5) {
							zl = "关闸";
							beng = "关泵";
						} else if (src.getInstruction() == 6) {
							zl = "开闸";
							beng = "关泵";
						}else if (src.getInstruction() == 7) {
							zl = "开闸";
							beng = "开泵";
						}
						o.addProperty("instruction", zl);
						o.addProperty("uinstruction", beng);
						if(src.getGateoperatetime() != null){
						o.addProperty("gateOperateTime",
								datetimeFormat.format(src.getGateoperatetime()));
						}
						if(src.getUnitoperatetime() != null){
						o.addProperty("unitOperateTime",
								datetimeFormat.format(src.getUnitoperatetime()));
						}
						return o;
					}
				}).create();
		return gson.toJson(instructions);
	}

	@Autowired
	ActivitiUnitService activitiUnitService;
	@Resource
	EmployeeService employeeService;

	@Autowired
	SelfDispatchStationsService selfDispatchStationsService;
	
	/** 
	* @Title: send 
	* @Description: 下发(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public String send(String id,HttpSession session,String departmentid) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("state", 6);
			User user =(User)session.getAttribute("loginUser");
			if(user != null){
				map.put("sender", user.getUserid());
			}
			Map<String, Object> mu= new HashMap<String, Object>();
			mu.put("sdid", id);
			mu.put("departmentid", departmentid);
			mu.put("sender", user.getUserid());
			List<SelfDispatchStations> stations = selfDispatchStationsService.selectStationByUserid(mu);
			List<String> l = new ArrayList<>();
			for(SelfDispatchStations s : stations){
				l.add(s.getSid());
			}
			dispatchIssuedListService.updateByPrimaryKeyIssued(map , mu);
			activitiUnitService.messagePushByDorSid("district",user.getUserid(), id, departmentid);
			activitiUnitService.completeTaskByDorS("district", user.getUserid(), id,departmentid,"stationList",l);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'下发失敗！'}";
		}
		return "{'result':true,'msg':'下发成功！'}";
	}
	
	

}
