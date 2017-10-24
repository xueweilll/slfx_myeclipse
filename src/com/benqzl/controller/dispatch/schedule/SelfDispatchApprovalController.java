package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.User;
import com.benqzl.service.dispatch.DispatchIssuedListService;
import com.benqzl.service.dispatch.ReceiptService;
import com.benqzl.service.dispatch.SelfDispatchStationsService;
import com.benqzl.service.system.StationService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: SelfDispatchApprovalController 
* @Description: 领导审批
* @author xw 
* @date 2016年3月18日 上午10:25:01 
*  
*/
@Controller
@RequestMapping("sdApproval")
public class SelfDispatchApprovalController extends BasicController{

	public SelfDispatchApprovalController() {
		super(SelfDispatchApprovalController.class);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/selfDispatchApproval");
		return mv;
	}
	

	@Autowired
	private DispatchIssuedListService dispatchIssuedListService;
	
	@Autowired
	private ActivitiUnitService activitiUnitService;
	
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
	@RequestMapping(value = "/sdApprovalList", method = RequestMethod.POST)
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
				List<String> ids=activitiUnitService.findObjIds(user.getUserid(),"leadertask","A");
				if(ids.size()==0){
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					List<String> l = new ArrayList<String>();
					jsonMap.put("total", 0);
					jsonMap.put("rows", l);
					strJson=gson.toJson(jsonMap);
					return strJson;
				}else{
					map.put("rpid", ids);
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
		map.put("bm","tg");
		List<SelfDispatch> list = dispatchIssuedListService.findByPage1(map);
		//List<SelfDispatchDepartment> list=dispatchIssuedListService.findByPage1(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = dispatchIssuedListService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(SelfDispatch.class,
				new JsonSerializer<SelfDispatch>() {	
					@Override
					public JsonElement serialize(SelfDispatch src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						
						o.addProperty("id", src.getId());
						o.addProperty("promotetime",datetimeFormat.format(src.getPromotetime()));
						o.addProperty("username", src.getUser().getUsername());
						o.addProperty("memo", src.getMemo());
						o.addProperty("creater", src.getCreateUser().getUsername());
						o.addProperty("createtime",datetimeFormat.format(src.getCreatetime()));
						o.addProperty("code", src.getCode());
						if(src.getLdApproval() != null){
							o.addProperty("approval", src.getLdApproval().getUsername());
						}
						if(src.getLdapprovaltime() != null){
							o.addProperty("approvaltime",	datetimeFormat.format(src.getLdapprovaltime()));
						}else {
							o.addProperty("approvaltime","");
						}
						o.addProperty("state", src.getState());	
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);		
		
		logger.info("this document list strJson is " + strJson);
		return strJson;
	}
	
	/**
	 * @Title: documentInfo
	 * @Description: info页面
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "sdApprovalInfo", method = RequestMethod.GET)
	public ModelAndView sdApprovalInfo(String id,String state) {

		ModelAndView mv = new ModelAndView();
		SelfDispatch dispatch = dispatchIssuedListService.selectDispatchIssuedInfo(id);
		mv.addObject("promotetime",
				datetimeFormat.format(dispatch.getPromotetime()));
		mv.addObject("promoter", dispatch.getEmployee().getName());
		mv.addObject("memo", dispatch.getMemo());
		mv.addObject("dispatch", dispatch);	
		mv.addObject("state",state);
		mv.setViewName("dispatch/selfDispatchApprovalInfo");
		return mv;
	}
	
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
						o.addProperty("id", src.getSid());
						o.addProperty("name", src.getStation().getName());
						o.addProperty("controlwaterlevel", src.getControlwaterlevel());
						o.addProperty("truewaterlevel", src.getTruewaterlevel());
						return o;
					}
				}).create();
		return gson.toJson(stations);
	}
	
	/** 
	* @Title: send 
	* @Description: 下发(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@Autowired
	private StationService stationService;
	@Autowired 
	private ReceiptService receiptService;
	@Autowired
	private SelfDispatchStationsService selfStationServeic;
	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	@ResponseBody
	public String pass(String id,HttpSession session,String json,
			HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("loginUser");
			SelfDispatch self=gson.fromJson(json, SelfDispatch.class);	
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("state", 4);
			map.put("sender", user.getUserid());
			List<SelfDispatchStations> selfstations=new ArrayList<>();
			List<String> list = new ArrayList<>();
			for (SelfDispatchStations selfDispatchStations : self.getStations()) {
				SelfDispatchStations station=new SelfDispatchStations();
				station.setId(UUID.randomUUID().toString());
				station.setSid(selfDispatchStations.getSid());
				station.setSdid(id);
				station.setState(new Long(0));
				station.setTruewaterlevel(selfDispatchStations.getTruewaterlevel());
				station.setControlwaterlevel(selfDispatchStations.getControlwaterlevel());
				selfstations.add(station);
				list.add(selfDispatchStations.getSid());
			}
			List<SelfDispatchDepartment> selfdepartment=new ArrayList<>();
			
			List<String> departmentList = stationService.findDepartmentIdsBySids(list);
			Set<String> strings = new HashSet<>();
			for (String string : departmentList) {
				strings.add(string);
				SelfDispatchDepartment list2= new SelfDispatchDepartment();	
				list2.setId(UUID.randomUUID().toString());
				list2.setSdid(id);
				list2.setDepartmentid(string);
				list2.setState((short)(0));
				selfdepartment.add(list2);
			}
			receiptService.insertDepartment(selfdepartment);
			selfStationServeic.delete(id);
			selfStationServeic.insertStations(selfstations);
			Map<String,Object> map1=new HashMap<>();
			map1.put("order", true);
			map1.put("departmentList", new ArrayList<String>(strings));
			activitiUnitService.messagePush("leadertask", user.getUserid()
					.toString(), id);
			activitiUnitService.completeTasks("leadertask", user.getUserid(),
					id,map1);			
			if(user != null){
				map.put("ldapproval", user.getUserid());
			}
			
			dispatchIssuedListService.updateByPrimaryKeyIssued(map, null);
/*			activitiUnitService.messagePush("leadertask", user.getUserid(),id);
			activitiUnitService.completeTask("leadertask", user.getUserid(), id,"order",true);*/
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'审核通过失敗！'}";
		}
		return "{'result':true,'msg':'审核通过成功！'}";
	}
	
	@RequestMapping(value = "/nopass", method = RequestMethod.POST)
	@ResponseBody
	public String nopass(String id,HttpSession session) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("state", 5);
			User user =(User)session.getAttribute("loginUser");
			if(user != null){
				map.put("ldapproval", user.getUserid());
			}
			dispatchIssuedListService.updateByPrimaryKeyIssued(map, null);
			activitiUnitService.messagePush("leadertask", user.getUserid(),id);
			activitiUnitService.completeTask("leadertask", user.getUserid(), id,"order",false);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'审核不通过失敗！'}";
		}
		return "{'result':true,'msg':'审核不通过成功！'}";
	}
}
