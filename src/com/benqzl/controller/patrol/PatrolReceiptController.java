package com.benqzl.controller.patrol;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.PatrolEnumid;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalPeople;
import com.benqzl.pojo.patrol.PatrolPlan;
import com.benqzl.pojo.patrol.PatrolPlanDetails;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolPlanService;
import com.benqzl.service.patrol.PatrolReceiptService;
import com.benqzl.service.system.EmployeeService;
import com.benqzl.service.system.StationService;
import com.benqzl.service.system.UserService;
//import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: PatrolReceiptController 
* @Description: TODO(日常巡检) 
* @author pxj 
* @date 2016年5月12日 下午1:49:16 
*  
*/
@Controller
@RequestMapping("/patrolreceipt")
public class PatrolReceiptController extends BasicController {
	@Autowired
	private PatrolReceiptService service;

	@Autowired
	private PatrolPlanService ppservice;

	public PatrolReceiptController() {
		super(PatrolReceiptController.class);
	}

	/**
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/patrolreceipt");
		return mv;
	}

	/*@Autowired
	private ActivitiUnitService activitiUnitService;*/

	/**
	 * 
	 * @Title: rdexelist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/patrolreceiptlist", method = RequestMethod.POST)
	@ResponseBody
	public String patrolreceiptlist(int page, int rows, String type,
			String typeDate, String sender, String begintime, String endtime,
			String stationid, HttpSession session) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		// User user = (User) session.getAttribute("loginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);

		/***********************************************************/
		if (typeDate.equals("1")) {
			map.put("type", "1");
		} else {
			map.put("type", "0");
		}
		/***********************************************************/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
			map.put("stationid", null);
		} else {
			map.put("stationid", stationid);
		}
		List<PatrolNormal> normal = service.findpatrolnormal(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.findpatrolnormalcount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", normal);
		gson = new GsonBuilder().registerTypeAdapter(PatrolNormal.class,
				new JsonSerializer<PatrolNormal>() {

					@Override
					public JsonElement serialize(PatrolNormal arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						// 计划表主键id
						json.addProperty("id", arg0.getId());
						// 巡检次数
						if(arg0.getDegree()==null){
							json.addProperty("degree","");
						}else{
							 json.addProperty("degree",
										arg0.getDegree()==0?"第一次":"第二次");
						}
						// 巡检时间
						json.addProperty("excepttime",
								datetimeFormat.format(arg0.getParttime()));
						// 制单人
						// json.addProperty("creater",
						// arg0.getPatrolplan().getCreater());
						// 制单人姓名
						json.addProperty("createrusername", arg0
								.getCreateuser().getEmployee().getName());
						// 制单时间
						json.addProperty("createtime",
								datetimeFormat.format(arg0.getCreatetime()));
						// 备注
						json.addProperty("sid", arg0.getSid());
						json.addProperty("stationname", arg0.getStation()
								.getName());
						json.addProperty("detailsid", arg0.getId());
						// 状态
						String str = "";
						switch (arg0.getState().toString()) {
						case "0":
							str = "保存";
							break;
						case "1":
							str = "提交执行部门";
							break;
						case "2":
						     str="提交工程科";
						     break;
						case "3":
							 str="提交管理处";
			                 break;
						case "4":
							 str="管理处";
							 break;
						default:
							str = "";
							break;
						}
						json.addProperty("state", str);
						json.addProperty("stateValue", arg0.getState());

						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	/**
	 * 
	 * @Title: patrolplaninfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "patrolreceiptinfo", method = RequestMethod.GET)
	public ModelAndView patrolreceiptinfo(String id, String detailsid,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User user = (User) request.getSession().getAttribute("loginUser");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", detailsid);
		map.put("receipter", user.getUserid());
		map.put("receipttime", new Date());
		map.put("state", 1);
		try {
			service.updatePPDStateByPk(map);
			PatrolPlan pp = ppservice.selectByPrimaryKey(id);
			List<PatrolPlanDetails> ppdlist = service
					.selectPatrolPlanDetailsByPatrolPlanId2(id);
			if (ppdlist.size() == 0) {
				pp.setState((long) 3);
			} else {
				pp.setState((long) 2);
			}
			ppservice.updatePatrolPlan(pp);

			mv.addObject("patrolPlan", pp);
			mv.addObject("detailsid", detailsid);
			mv.addObject("excepttime",
					datetimeFormat.format(pp.getExcepttime()));
			mv.setViewName("patrol/patrolreceiptinfo");
		} catch (Exception ex) {

		}

		return mv;
	}

	/**
	 * 
	 * @Title: patrolrecordinfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "patrolrecordinfo", method = RequestMethod.GET)
	public ModelAndView patrolrecordinfo(String id, String ppid, String sid,String look,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		/*
		 * Patrol patrol = service.selectByPPDtailsID(id); if (patrol == null) {
		 * patrol = new Patrol(); patrol.setPatrolplandetailsid(id);
		 * mv.addObject("patrol", patrol); mv.addObject("ppid",ppid);
		 * mv.addObject("sid",sid); } else { mv.addObject("patrol", patrol);
		 * mv.addObject("ppid",ppid); mv.addObject("sid",sid);
		 * mv.addObject("excepttime",
		 * datetimeFormat.format(patrol.getPatroltime())); }
		 */
		mv.addObject("look", look);
		if (id.equals("0")) {
			mv.addObject("id", 0);
		} else {
			mv.addObject("id", id);
			PatrolNormal patrol = service.selectByPatrolNormalId(id);
			List<PatrolNormalPeople> list = service.getfindUser(id);
			String userIds = "";
			for (PatrolNormalPeople people : list) {
				if (userIds == "") {
					userIds += "[";
				}
				userIds += "'" + people.getHandler() + "',";
			}
			userIds = userIds.substring(0, userIds.length() - 1);
			userIds += "]";
			mv.addObject("patrol", patrol);
			mv.addObject("parttime",
					datetimeFormat.format(patrol.getParttime()));
			mv.addObject("user", userIds);
		}
		mv.setViewName("patrol/patrolrecordinfo");
		return mv;
	}

	/** 
	* @Title: patrolType 
	* @Description: TODO(查询18项) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "PatrolType", method = RequestMethod.POST)
	@ResponseBody
	public String patrolType(String id) {
		String result = "";
		gson = new Gson();
		String json = "[{\"enumid\":\"01\",\"text\":\"内外河引河\",\"contents\":\"水流平顺，无杂物，无水草，无威胁工程的漂浮物\"},"
				+ "{\"enumid\":\"02\",\"text\":\"内外河护坡\",\"contents\":\"护坡完好，排水畅通，无塌陷，表面无开裂破损\"},"
				+ "{\"enumid\":\"03\",\"text\":\"内外河翼墙\",\"contents\":\"墙体完好，排水通畅，无塌陷，表面无开裂破损\"},"
				+ "{\"enumid\":\"04\",\"text\":\"翼墙后填土区\",\"contents\":\"无雨淋沟，无裂缝，无塌陷，无渗漏，无滑坡\"},"
				+ "{\"enumid\":\"05\",\"text\":\"泵站内外进水池\",\"contents\":\"水流平顺，无杂物，无水草\"},"
				+ "{\"enumid\":\"06\",\"text\":\"导流墩\",\"contents\":\"墩体完好，无裂缝，无渗漏，观测标志完好\"},"
				+ "{\"enumid\":\"07\",\"text\":\"泵站主厂房\",\"contents\":\"墙面完好，门窗完好，伸缩缝完好，排水通畅，无破损，无渗漏\"},"
				+ "{\"enumid\":\"08\",\"text\":\"泵站站身\",\"contents\":\"工程设施完好，无裂缝，无渗漏，伸缩缝完好\"},"
				+ "{\"enumid\":\"09\",\"text\":\"变压器\",\"contents\":\"室温正常，风扇运行正常\"},"
				+ "{\"enumid\":\"10\",\"text\":\"节制闸闸室\",\"contents\":\"闸门无振动、倾斜，闸下流态正常，无漂浮物\"},"
				+ "{\"enumid\":\"11\",\"text\":\"节制闸启闭机\",\"contents\":\"钢丝绳无断丝无明显变形，油缸无泄漏\"},"
				+ "{\"enumid\":\"12\",\"text\":\"交通桥\",\"contents\":\"桥面完好，栏杆完好，观测标志完好，无裂缝，无渗漏\"},"
				+ "{\"enumid\":\"13\",\"text\":\"消防器材\",\"contents\":\"灭火器压力正常，消防栓内水带，水栓完好\"},"
				+ "{\"enumid\":\"14\",\"text\":\"高压室\",\"contents\":\"柜体、开关完好，仪表显示正常，柜内照明正常，柜前后绝缘垫完好清洁\"},"
				+ "{\"enumid\":\"15\",\"text\":\"高压电容室\",\"contents\":\"柜体、开关完好，仪表显示正常，柜内照明正常，柜前后绝缘垫完好清洁\"},"
				+ "{\"enumid\":\"16\",\"text\":\"低压室\",\"contents\":\"柜体、开关完好，仪表显示正常，电容补偿正常，指示灯显示正常，柜前后绝缘垫完好清洁\"},"
				+ "{\"enumid\":\"17\",\"text\":\"中控室\",\"contents\":\"监控设备运行正常，数据显示正确，视频监控画面清晰\"},"
				+ "{\"enumid\":\"18\",\"text\":\"其他\",\"contents\":\"\"}]";

		/*
		 * if (id == null || id.length() == 0) { return json; }
		 */
		if (id.equals("0")) {
			return json;
		} else {
			List<PatrolNormalDetails> list=new ArrayList<PatrolNormalDetails>();
			List<PatrolNormalDetails> details = service
					.selectByNormalDetails(id);
			List<PatrolEnumid> enums=service.selectEnum();//循环18项
			for(int i=0;i<enums.size();i++){
				PatrolNormalDetails pnds=new PatrolNormalDetails();
				pnds.setEnumid(enums.get(i).getEnumid());
				pnds.setAddress(enums.get(i).getText());//文本内容
				pnds.setHandlecontents(enums.get(i).getContents());//巡视内容
				for(int a=0;a<details.size();a++){
					if(enums.get(i).getEnumid().equals(details.get(a).getEnumid())){
						pnds.setContents(details.get(a).getContents());
						pnds.setIstype(details.get(a).getIstype());
						pnds.setHandletype(details.get(a).getHandletype());
					}
				}
				list.add(pnds);
			}
			gson = new GsonBuilder().registerTypeAdapter(
					PatrolNormalDetails.class,
					new JsonSerializer<PatrolNormalDetails>() {
						@Override
						public JsonElement serialize(PatrolNormalDetails arg0,
								Type arg1, JsonSerializationContext arg2) {
							JsonObject json = new JsonObject();
							// 计划表主键id
							json.addProperty("enumid", arg0.getEnumid());
							/*json.addProperty("text", arg0.getPatrolenumid()
									.getText());
							json.addProperty("contents", arg0.getPatrolenumid()
									.getContents());*/
							json.addProperty("text", arg0.getAddress());
							json.addProperty("contents", arg0.getHandlecontents());
							json.addProperty("problemtype", arg0.getIstype());
							json.addProperty("record", arg0.getContents());
							json.addProperty("handletype", arg0.getHandletype());
							return json;
						}
					}).create();
			result = gson.toJson(list);
			return result;
		}
	}

	
	/**
	 * 
	 * @Title: saveMobilezb
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveMobilezb", method = RequestMethod.POST)
	@ResponseBody
	public String saveMobilezb(String jsonStr, HttpServletRequest request) {
		gson = new Gson();
		String result="";
		PatrolNormal patrol = gson.fromJson(jsonStr, PatrolNormal.class);
		String id = patrol.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		// 新增
		if (id.equals("0")) {
			patrol.setId(UUID.randomUUID().toString());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			patrol.setSid(patrol.getSid());
			try {
				service.insertpatrolNormal(patrol);
				result="{'id'"+":"+patrol.getId()+"}";
			} catch (Exception e) {
				e.printStackTrace();
				result="{'result':false}";
			}
			List<PatrolNormalPeople> people = new ArrayList<PatrolNormalPeople>();
			for (int i = 0; i < patrol.getPp().length; i++) {
				PatrolNormalPeople lists = new PatrolNormalPeople();
				lists.setId(UUID.randomUUID().toString());
				lists.setPatrolid(patrol.getId());
				lists.setHandler(patrol.getPp()[i]);
				people.add(lists);
			}
			try {
				service.insertPatrolNormalPeople(people);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//result="{'id'"+":"+patrol.getId()+"}";
			//return "{'result':true}";
		//"[{\"id\":\"0\",\"text\":\"贯流\"},{\"id\":\"1\",\"text\":\"轴流\"}]"
		//return "{'id'"+":"+patrol.getId()+","+"url"+":"+pictureUrl+"}";
	}else {
			service.deleteByPatrolNormalID(patrol.getId());
			patrol.setId(UUID.randomUUID().toString());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			patrol.setSid(patrol.getSid());
			try {
				service.insertpatrolNormal(patrol);
				result="{'id'"+":"+patrol.getId()+"}";
			} catch (Exception e) {
				e.printStackTrace();
				result="{'result':false}";
			}
			List<PatrolNormalPeople> people = new ArrayList<PatrolNormalPeople>();
			for (int i = 0; i < patrol.getPp().length; i++) {
				PatrolNormalPeople lists = new PatrolNormalPeople();
				lists.setId(UUID.randomUUID().toString());
				lists.setPatrolid(patrol.getId());
				lists.setHandler(patrol.getPp()[i]);
				people.add(lists);
			}
			try {
				service.insertPatrolNormalPeople(people);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//result="{'id'"+":"+patrol.getId()+"}";
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: saveMobiledetials
	 * @Description: TODO(手机端保存18项)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveMobiledetials", method = RequestMethod.POST)
	@ResponseBody
	public String saveMobiledetials(String jsonStr, HttpServletRequest request) {
		gson = new Gson();
		PatrolNormal patrol = gson.fromJson(jsonStr, PatrolNormal.class);
		//User user = (User) request.getSession().getAttribute("loginUser");
		/*String pictureUrl = request.getSession().getServletContext()
				.getRealPath("upload/normarpicture");*/
			List<PatrolNormalDetails> list = new ArrayList<PatrolNormalDetails>();
			for (PatrolNormalDetails details : patrol.getPds()) {
				details.setId(UUID.randomUUID().toString());
				details.setPatrolid(patrol.getId());
				list.add(details);
			}
			try {
				service.insertPatrolNormalDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "{'result':true}";
			
		//"[{\"id\":\"0\",\"text\":\"贯流\"},{\"id\":\"1\",\"text\":\"轴流\"}]"
		/*return "{'id'"+":"+patrol.getId()+","+"url"+":"+pictureUrl+"}";*/
			//return "{'id'"+":"+patrol.getId()+"}";
	}
	/** 
	* @Title: savePicture 
	* @Description: TODO(图片保存) 
	* @param @param file
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/savePicture", method = RequestMethod.POST)
	@ResponseBody
	public String savePicture(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request){
        String result="";
		if(!file.getOriginalFilename().equals("")){
			String path = request.getSession().getServletContext().getRealPath("/upload/patrolnormal")+"\\";
			String str ="demo.jpg";
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,str));
			    result="true";
			} catch (IOException e) {
				e.printStackTrace();
                result="false";
			}
		}
		return result;
	}
	
	
	/**
	 * 
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
		PatrolNormal patrol = gson.fromJson(jsonStr, PatrolNormal.class);
		// Patrol patrol = gson.fromJson(jsonStr, Patrol.class);
		String id = patrol.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		// 新增
		if (id.equals("0")) {
			// patrol.setId(UUID.randomUUID().toString());
			patrol.setId(UUID.randomUUID().toString());
			// patrol.setPatrolplandetailsid(patrol.getPatrolplandetailsid());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			patrol.setSid(patrol.getSid());
			// patrol.getPds().setId(UUID.randomUUID().toString());
			try {
				service.insertpatrolNormal(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalDetails> list = new ArrayList<PatrolNormalDetails>();
			for (PatrolNormalDetails details : patrol.getPds()) {
				details.setId(UUID.randomUUID().toString());
				details.setPatrolid(patrol.getId());
				list.add(details);
			}
			try {
				service.insertPatrolNormalDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalPeople> people = new ArrayList<PatrolNormalPeople>();
			for (int i = 0; i < patrol.getPp().length; i++) {
				PatrolNormalPeople lists = new PatrolNormalPeople();
				lists.setId(UUID.randomUUID().toString());
				lists.setPatrolid(patrol.getId());
				lists.setHandler(patrol.getPp()[i]);
				people.add(lists);
			}
			try {
				service.insertPatrolNormalPeople(people);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 编辑
		else {
			service.deleteByPatrolNormalID(patrol.getId());
			patrol.setId(UUID.randomUUID().toString());
			// patrol.setPatrolplandetailsid(patrol.getPatrolplandetailsid());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			patrol.setSid(patrol.getSid());
			// patrol.getPds().setId(UUID.randomUUID().toString());
			try {
				service.insertpatrolNormal(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalDetails> list = new ArrayList<PatrolNormalDetails>();
			for (PatrolNormalDetails details : patrol.getPds()) {
				details.setId(UUID.randomUUID().toString());
				details.setPatrolid(patrol.getId());
				list.add(details);
			}
			try {
				service.insertPatrolNormalDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalPeople> people = new ArrayList<PatrolNormalPeople>();
			for (int i = 0; i < patrol.getPp().length; i++) {
				PatrolNormalPeople lists = new PatrolNormalPeople();
				lists.setId(UUID.randomUUID().toString());
				lists.setPatrolid(patrol.getId());
				lists.setHandler(patrol.getPp()[i]);
				people.add(lists);
			}
			try {
				service.insertPatrolNormalPeople(people);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "{'result':true}";
	}
	
	
	
	/** 
	* @Title: mobilecommit 
	* @Description: TODO(手机端更新巡检记录单变为提交) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/mobilecommit",method=RequestMethod.POST)
	@ResponseBody
	public String mobilecommit(String id){
        String result="";
		try {
			service.updateMobileCommit(id);
			 result="{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
		    result="{'result':false}";
		}
		
		return result;
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
		PatrolNormal patrol = gson.fromJson(jsonStr, PatrolNormal.class);
		String id = patrol.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		// 新增
		if (id.equals("0")) {
			// patrol.setId(UUID.randomUUID().toString());
			patrol.setId(UUID.randomUUID().toString());
			// patrol.setPatrolplandetailsid(patrol.getPatrolplandetailsid());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			patrol.setSid(patrol.getSid());
			// patrol.getPds().setId(UUID.randomUUID().toString());
			try {
				service.insertpatrolNormal(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalDetails> list = new ArrayList<PatrolNormalDetails>();
			for (PatrolNormalDetails details : patrol.getPds()) {
				details.setId(UUID.randomUUID().toString());
				details.setPatrolid(patrol.getId());
				details.setHandletype(new Long(0));
				list.add(details);
			}
			try {
				service.insertPatrolNormalDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalPeople> people = new ArrayList<PatrolNormalPeople>();
			for (int i = 0; i < patrol.getPp().length; i++) {
				PatrolNormalPeople lists = new PatrolNormalPeople();
				lists.setId(UUID.randomUUID().toString());
				lists.setPatrolid(patrol.getId());
				lists.setHandler(patrol.getPp()[i]);
				people.add(lists);
			}
			try {
				service.insertPatrolNormalPeople(people);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 编辑提交
		else {
			/*
			 * try { service.deleteByPatrolID(patrol.getId());
			 * List<PatrolDetails> list = new ArrayList<PatrolDetails>(); for
			 * (PatrolDetails details : patrol.getPds()) {
			 * details.setId(UUID.randomUUID().toString());
			 * details.setPatrolid(patrol.getId()); list.add(details); }
			 * service.insertPatrolDetails(list); List<PatrolPeople> plist = new
			 * ArrayList<PatrolPeople>(); List<String> userids =
			 * activitiUnitService
			 * .userids("execute",user.getUserid(),patrol.getPatrolplandetailsid
			 * ()); for (String s : userids) { PatrolPeople pp = new
			 * PatrolPeople(); pp.setId(UUID.randomUUID().toString());
			 * pp.setPatrolid(patrol.getId()); pp.setType(new Long(0));
			 * pp.setOperater(s); pp.setOperatetime(new Date());
			 * if(s.equals(user.getUserid())){ pp.setState(new Long(1)); } else
			 * { pp.setState(new Long(0)); } plist.add(pp); } if(userids.size()
			 * == 1){ patrol.setState("2"); }
			 * service.updateByPrimaryKeySelective(patrol);
			 * service.insertPatrolPeople(plist); int c =
			 * service.selectIScomplete(ppid); if(userids.size() == 1){ if(c ==
			 * 0){ activitiUnitService.messagePush("execute", user.getUserid(),
			 * patrol.getPatrolplandetailsid().toString());
			 * activitiUnitService.completeTask("execute", user.getUserid(),
			 * patrol.getPatrolplandetailsid());
			 * activitiUnitService.messagePush("confirm", user.getUserid(),
			 * patrol.getPatrolplandetailsid());
			 * activitiUnitService.completeTask("confirm", user.getUserid(),
			 * patrol.getPatrolplandetailsid()); } }else{ if(c == 0){
			 * activitiUnitService.messagePush("execute", user.getUserid(),
			 * patrol.getPatrolplandetailsid().toString());
			 * activitiUnitService.completeTask("execute", user.getUserid(),
			 * patrol.getPatrolplandetailsid()); } } } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
			service.deleteByPatrolNormalID(patrol.getId());
			patrol.setId(UUID.randomUUID().toString());
			// patrol.setPatrolplandetailsid(patrol.getPatrolplandetailsid());
			patrol.setCreater(user.getUserid());
			patrol.setCreatetime(new Date());
			patrol.setSid(patrol.getSid());

			// patrol.getPds().setId(UUID.randomUUID().toString());
			try {
				service.insertpatrolNormal(patrol);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalDetails> list = new ArrayList<PatrolNormalDetails>();
			for (PatrolNormalDetails details : patrol.getPds()) {
				details.setId(UUID.randomUUID().toString());
				details.setPatrolid(patrol.getId());
				details.setHandletype(new Long(0));
				list.add(details);
			}
			try {
				service.insertPatrolNormalDetails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolNormalPeople> people = new ArrayList<PatrolNormalPeople>();
			for (int i = 0; i < patrol.getPp().length; i++) {
				PatrolNormalPeople lists = new PatrolNormalPeople();
				lists.setId(UUID.randomUUID().toString());
				lists.setPatrolid(patrol.getId());
				lists.setHandler(patrol.getPp()[i]);
				people.add(lists);
			}
			try {
				service.insertPatrolNormalPeople(people);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "{'result':true}";
	}

	@Autowired
	private StationService stationService;

	@RequestMapping(value = "/stationList", method = RequestMethod.POST)
	@ResponseBody
	public String stationList() {
		List<Station> list = stationService.stationByFk();
		Station s = new Station();
		s.setId(null);
		s.setName("全部");
		list.add(0, s);
		gson = new GsonBuilder().registerTypeAdapter(Station.class,
				new JsonSerializer<Station>() {
					@Override
					public JsonElement serialize(Station arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("text", arg0.getName());
						return json;
					}
				}).create();
		// System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}

	/**
	 * @Title: validatenormal
	 * @Description: TODO(验证枢纽上报)
	 * @param @param sid
	 * @param @param degree
	 * @param @param parttime
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value="/validatenormal",method=RequestMethod.POST)
	@ResponseBody
	public String validatenormal(String jsonStr){
		String result= "";
		gson = new Gson();
	    PatrolNormal 	normal=gson.fromJson(jsonStr,PatrolNormal.class);
	    normal.setCreater(datetimeFormat.format(normal.getParttime()));
	    PatrolNormal validate=service.validatenormal(normal);
		if(normal.getDegree()==0){
			if(validate.getState()>2){
				result="false";
			}else if(validate.getState()==0){
				result="true";
			}
			else {
				result="first";
			}
		}else{
			if(validate.getState()>2){
				result="false";
			}else if(validate.getState()==0){
				result="true";
			}
			else {
				result="second";
			}
		}
		return result;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/userListByDepartment", method = RequestMethod.POST)
	@ResponseBody
	public String userListByDepartment(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("departmentid", e.getDepartment().getId());
		List<User> list = userService.selectbyMuiltCombox(map);
		gson = new GsonBuilder().registerTypeAdapter(User.class,
				new JsonSerializer<User>() {
					@Override
					public JsonElement serialize(User arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getUserid());
						json.addProperty("dname", arg0.getEmployee()
								.getDepartment().getName());
						json.addProperty("username", arg0.getUsername());
						json.addProperty("ename", arg0.getEmployee().getName());
						return json;
					}
				}).create();
		return gson.toJson(list);
	}
	

	/** 
	* @Title: deletepatrolrecord 
	* @Description: TODO(删除) 
	* @param @param id 主表PatrolNormalid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "deletepatrolrecord", method = RequestMethod.POST)
	@ResponseBody
	public String deletepatrolrecord(String id) {
        String result= "";
		 try {
			service.deleteByPatrolNormalById(id);
			result="{'result':'true'}";
		} catch (Exception e) {
			e.printStackTrace();
			result="{'result':false}";
		}
		return result;
	}
	
	
}
