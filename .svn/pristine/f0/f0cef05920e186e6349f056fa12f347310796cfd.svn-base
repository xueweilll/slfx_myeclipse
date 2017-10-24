package com.benqzl.controller.patrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.patrol.PatrolSpecialExcute;
import com.benqzl.pojo.patrol.PatrolSpecialExcuteDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.EgpatorlDepartemntSendService;
import com.benqzl.service.patrol.PatrolSpecialFolwService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: EgPatrolDepartmentSendController
 * @Description:
 * @author xw
 * @date 2016年4月28日 下午2:59:07
 * 
 */
@Controller
@RequestMapping("egpatroldepartmentsend")
public class EgPatrolDepartmentSendController extends BasicController {

	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public EgPatrolDepartmentSendController() {
		super(EgPatrolDepartmentSendController.class);
	}

	@Autowired
	private EgpatorlDepartemntSendService egpatorlDepartemntSendService;
	@Autowired
	private PatrolSpecialFolwService patrolspecialflowservice;
	/**
	 * @Title: index
	 * @Description: TODO(跳转执行部门执行)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/egpatorlDepartemntSend");
		return mv;
	}

	/** 
	* @Title: findstation 
	* @Description: TODO(查找对应用户部门下枢纽) 
	* @param @param did
	* @param @param isid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/findstation", method = RequestMethod.POST)
	@ResponseBody
	public String findstation(String did, String isid,String look) {
		String result = "";
		Map<String, Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("did", did);
		List<PatrolSpecialExcuteDetails> excute = egpatorlDepartemntSendService
				.findExcuteDetails(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(look.equals("1")){
			jsonMap.put("rows", excute);
			gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialExcuteDetails.class,
					new JsonSerializer<PatrolSpecialExcuteDetails>() {
						@Override
						public JsonElement serialize(PatrolSpecialExcuteDetails station,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							o.addProperty("id", station.getSid());
							o.addProperty("name", station.getSpecialExcutestation().getName());
							o.addProperty("controlwaterlevel",
									station.getSpecialExcutestation().getControlwaterlevel());
							o.addProperty("checked", station.getSpecialExcutestation().getIsdel() != null ? true
									: false);
							return o;
						}
					}).create();
		}else{
		List<Station> list = egpatorlDepartemntSendService.findstation(did);
		for (int i = 0; i < list.size(); i++) {
			for (int a = 0; a < excute.size(); a++) {
				if (list.get(i)
						.getId()
						.equals(excute.get(a).getSpecialExcutestation().getId())) {
					list.get(i).setIsdel(new Long(2));
				}
			}
		}
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Station.class,
				new JsonSerializer<Station>() {
					@Override
					public JsonElement serialize(Station station,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", station.getId());
						o.addProperty("name", station.getName());
						o.addProperty("controlwaterlevel",
								station.getControlwaterlevel());
						o.addProperty("checked", station.getIsdel() == 2 ? true
								: false);
						return o;
					}
				}).create();
	}
		result = gson.toJson(jsonMap);
		return result;
	}

	/**
	 * @Title: egpatroldepartmentsendInfo
	 * @Description: TODO(跳转到执行info页面)
	 * @param @param isid
	 * @param @param detailsid
	 * @param @param request
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "egpatroldepartmentsendInfo", method = RequestMethod.GET)
	public ModelAndView egpatroldepartmentsendInfo(String isid, String did,
			String detailsid, String regular,String look,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dids", did);
		mv.addObject("look",look);
		PatrolSpecialIssue issue=egpatorlDepartemntSendService.findIssue(isid);
		mv.addObject("code",issue.getCode());
		if (isid.equals("") || isid == null) {
			mv.addObject("isid", null);
		} else {
			mv.addObject("isid", isid);
			Map<String, Object> map = new HashMap<>();
			map.put("isid", isid);
			map.put("did", did);
			PatrolSpecialExcute excute = egpatorlDepartemntSendService
					.findexcute(map);
			mv.addObject("excute", excute);
		}
		if(regular.equals("regular")){
			mv.setViewName("patrol/regularpatorlDepartemntSendInfo");   			
		}else{
			mv.setViewName("patrol/egpatorlDepartemntSendInfo");
		}
		return mv;
	}

	/**
	 * @Title: egpatorlDepartemntSendList
	 * @Description: TODO(执行部门list)
	 * @param @param page
	 * @param @param rows
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/egpatorlDepartemntSendList", method = RequestMethod.POST)
	@ResponseBody
	public String patrolreceiptlist(int page, int rows, String type,
			String typeDate, String sender, String begintime, String endtime,
			String stationid, HttpSession session, HttpServletRequest request,
			String classes) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		User user = (User) request.getSession().getAttribute("loginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		map.put("user", user.getUserid());
		/***********************************************************/
		if (typeDate.equals("1")) {
			map.put("state", "1");
		} else {
			map.put("state", "0");
		}
		// 判断应急巡检与日常巡检
		// classes=3 为应急巡检其他为定期巡检
		if (classes.equals("3")) {
			map.put("classes", "3");
		} else {
			map.put("classes", "2");
		}
		/***********************************************************/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (sender == null || sender.trim().equals("")) {
			map.put("sender", null);
		} else {
			map.put("sender", sender);
		}
		try {
			if (begintime != null && begintime.length() > 0) {
				map.put("begintime", sdf.parse(begintime));
			}
			if (endtime != null && endtime.length() > 0) {
				map.put("endtime", sdf.parse(endtime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (stationid == null || stationid.trim().equals("")) {
			map.put("did", null);
		} else {
			map.put("did", stationid);
		}
		List<PatrolSpecialResolveDetails> normal = egpatorlDepartemntSendService
				.findegpatroldepartment(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = egpatorlDepartemntSendService
					.findegpatroldepartmentcount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", normal);
		gson = new GsonBuilder().registerTypeAdapter(
				PatrolSpecialResolveDetails.class,
				new JsonSerializer<PatrolSpecialResolveDetails>() {
					@Override
					public JsonElement serialize(
							PatrolSpecialResolveDetails arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						// 分解单主键id
						json.addProperty("id", arg0.getId());
						// 巡检单号
						json.addProperty("isid", arg0.getSpecialResolve()
								.getIsid());
						// json.addProperty("isid",
						// arg0.getPatrolplan().getIsid());
						// 分解人姓名
						json.addProperty("creater", arg0.getSpecialResolve()
								.getCreateuser().getEmployee().getName());
						// 分解时间
						json.addProperty("createtime", datetimeFormat
								.format(arg0.getSpecialResolve()
										.getCreatetime()));
						// 备注
						json.addProperty("remark", arg0.getSpecialResolve()
								.getRemark());
						// 分解内容
						json.addProperty("content", arg0.getSpecialResolve()
								.getContent());
						// json.addProperty("detailsid", arg0.getId());
						// 部门id
						json.addProperty("did", arg0.getDid());
						// 部门名称
						json.addProperty("dname", arg0.getSpecialResolve()
								.getCreateuser().getEmployee().getDepartment()
								.getName());
						// 状态
						json.addProperty("state", arg0.getFolw().getZx()==1 ? "已执行"
								: "未执行");
						/*
						 * json.addProperty("stateValue", arg0.getState());
						 */
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	/**
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr, HttpServletRequest request) {
		gson = new Gson();
		PatrolSpecialExcute patrol = gson.fromJson(jsonStr,
				PatrolSpecialExcute.class);
		String id = patrol.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		if (id.equals("0")) {
			egpatorlDepartemntSendService
					.deleteByEgPatrolIsId(patrol.getIsid());
			patrol.setId(UUID.randomUUID().toString());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			try {
				egpatorlDepartemntSendService
						.insertegpatorlSpecialExcute(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolSpecialExcuteDetails> list = new ArrayList<PatrolSpecialExcuteDetails>();
			for (PatrolSpecialExcuteDetails details : patrol
					.getPatrolSpecialExcuteDetails()) {
				details.setId(UUID.randomUUID().toString());
				details.setSid(details.getSid());
				details.setEid(patrol.getId());
				list.add(details);
			}
			try {
				egpatorlDepartemntSendService.insertegpatorlDepartemntlDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 编辑
		else {
			egpatorlDepartemntSendService
					.deleteByEgPatrolIsId(patrol.getIsid());
			patrol.setId(UUID.randomUUID().toString());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			try {
				egpatorlDepartemntSendService.insertegpatorlDepartemnt(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolSpecialExcuteDetails> list = new ArrayList<PatrolSpecialExcuteDetails>();
			for (PatrolSpecialExcuteDetails details : patrol
					.getPatrolSpecialExcuteDetails()) {
				details.setId(UUID.randomUUID().toString());
				details.setSid(details.getSid());
				details.setEid(patrol.getId());
				list.add(details);
			}
			try {
				egpatorlDepartemntSendService
						.insertegpatorlDepartemntlDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "{'result':true}";
	}

	/**
	 * 
	 * @Title: commit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/commit", method = RequestMethod.POST)
	@ResponseBody
	public String commit(String jsonStr, HttpServletRequest request) {
		gson = new Gson();
		PatrolSpecialExcute patrol = gson.fromJson(jsonStr,
				PatrolSpecialExcute.class);
		String id = patrol.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		if (id.equals("0")) {
			egpatorlDepartemntSendService
					.deleteByEgPatrolIsId(patrol.getIsid());
			patrol.setId(UUID.randomUUID().toString());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			try {
				egpatorlDepartemntSendService
						.insertegpatorlSpecialExcute(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolSpecialExcuteDetails> list = new ArrayList<PatrolSpecialExcuteDetails>();
			for (PatrolSpecialExcuteDetails details : patrol
					.getPatrolSpecialExcuteDetails()) {
				details.setId(UUID.randomUUID().toString());
				details.setSid(details.getSid());
				details.setEid(patrol.getId());
				list.add(details);
			}
			try {
				egpatorlDepartemntSendService
						.insertegpatorlDepartemntlDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		else {
			egpatorlDepartemntSendService
					.deleteByEgPatrolIsId(patrol.getIsid());
			patrol.setId(UUID.randomUUID().toString());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			try {
				egpatorlDepartemntSendService.insertegpatorlSpecialExcute(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolSpecialExcuteDetails> list = new ArrayList<PatrolSpecialExcuteDetails>();
			for (PatrolSpecialExcuteDetails details : patrol
					.getPatrolSpecialExcuteDetails()) {
				details.setId(UUID.randomUUID().toString());
				details.setSid(details.getSid());
				details.setEid(patrol.getId());
				list.add(details);
			}
			try {
				egpatorlDepartemntSendService
						.insertegpatorlDepartemntlDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		patrolspecialflowservice.execute(patrol.getIsid());
		return "{'result':true}";
	}

}
