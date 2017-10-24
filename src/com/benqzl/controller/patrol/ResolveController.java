package com.benqzl.controller.patrol;


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.controller.patrol.pojo.DepartmentJson;
import com.benqzl.pojo.patrol.Patrol;
import com.benqzl.pojo.patrol.PatrolDetails;
import com.benqzl.pojo.patrol.PatrolNormal;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalPeople;
import com.benqzl.pojo.patrol.PatrolNormalReport;
import com.benqzl.pojo.patrol.PatrolNormalReportDetails;
import com.benqzl.pojo.patrol.PatrolPeople;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolApprovalService;
import com.benqzl.service.patrol.PatrolNormalReportService;
import com.benqzl.service.system.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("/resolve")
public class ResolveController extends BasicController{
	public ResolveController() {
		super(ResolveController.class);
	}
	@Resource
	private PatrolApprovalService pas;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/patrolresolve");
		return mv;
	}
	@RequestMapping(value = "/patrolDetailProblemList1", method = RequestMethod.POST)
	@ResponseBody
	public String patrolDetailProblemList1(int page,int rows,String begin,String end,HttpServletRequest request) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		User user=(User) request.getSession().getAttribute("loginUser");
		String sid=pas.findSidByUserId(user.getUserid());
		if(sid!=null){
			map.put("sid", sid);
		}else{
//			map.put("sid", "");
		}
		if(begin!=null && !begin.equals("")){
			map.put("begin", begin);
		}
		if(end!=null && !end.equals("")){
			map.put("end", end);
		}
		List<Patrol> list=pas.findPatrolDetailProblemByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = pas.pageCount4(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(Patrol.class,
				new JsonSerializer<Patrol>() {
					@Override
					public JsonElement serialize(Patrol p, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("patrolid",p.getId());
						o.addProperty("code", p.getPpd().getPatrolplan().getCode());
						o.addProperty("patroltime",datetimeFormat.format(p.getPatroltime()));
						o.addProperty("creater", p.getCreate().getEmployee().getName());
						o.addProperty("createtime",datetimeFormat.format(p.getCreatetime()));
						String state=p.getState();
						if(state.equals("3")){
							o.addProperty("state", "未处理");
						}else if(state.equals("4")){
							o.addProperty("state", "已处理");
						}
						o.addProperty("stationname",p.getCreate().getEmployee().getStation().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	@RequestMapping(value = "resolveEdit", method = RequestMethod.GET)
	public ModelAndView resolveEdit(String patrolid) {
		ModelAndView mv = new ModelAndView();
		Patrol patrol=pas.findPatrolById(patrolid);
		mv.addObject("patrol",patrol);		
		mv.addObject("patroltime",datetimeFormat.format(patrol.getPatroltime()));
		mv.setViewName("patrol/resolve");
		return mv;
	}
	@RequestMapping(value = "resolveEditdbl", method = RequestMethod.GET)
	public ModelAndView resolveEditdbl(String patrolid) {
		ModelAndView mv = new ModelAndView();
		Patrol patrol=pas.findPatrolById(patrolid);
		mv.addObject("patrol",patrol);		
		mv.addObject("patroltime",datetimeFormat.format(patrol.getPatroltime()));
		mv.setViewName("patrol/resolvedbl");
		return mv;
	}
	@RequestMapping(value = "/operater", method = RequestMethod.POST)
	@ResponseBody
	public String operater(HttpServletRequest request){
		String json="";
		User user=(User) request.getSession().getAttribute("loginUser");
		String sid=pas.findSidByUserId(user.getUserid());		
		List<User> list=pas.findPatrolPeople(sid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(User.class,
				new JsonSerializer<User>() {
					@Override
					public JsonElement serialize(User u, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("userid",u.getUserid());
						o.addProperty("name",u.getEmployee().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		json=json.replace("{\"rows\":", "");
		json=json.substring(0,json.length()-1);
		json=json.substring(0,1)+json.substring(1,json.length());
		return json;
	}
	@RequestMapping(value = "/patroldetailProblemList3", method = RequestMethod.POST)
	@ResponseBody
	public String patroldetailProblemList3(String patrolid) {
		String json="";
//		page = (page == 0 ? 1 : page);
//		rows = (rows == 0 ? 15 : rows);
//		int start = (page - 1) * rows;
//		rows = start+rows;
//		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
//		map.put("p1", String.valueOf(start));
//		map.put("p2", Integer.toString(rows));
		map.put("patrolid", patrolid);
		List<PatrolDetails> list=pas.findPatrolDetailsProblemByPage3(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = pas.pageCount6(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolDetails.class,
				new JsonSerializer<PatrolDetails>() {
					@Override
					public JsonElement serialize(PatrolDetails pd, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						String enuim=pd.getEnumid();
						if(enuim.equals("01")){
							o.addProperty("enumid","01" );
							o.addProperty("enumid1","内外河引河" );
							o.addProperty("enumid2", "水流平顺，无杂物，无水草，无威胁工程的漂浮物");
						}else if(enuim.equals("02")){
							o.addProperty("enumid","02" );
							o.addProperty("enumid1","内外河护坡" );
							o.addProperty("enumid2", "护坡完好，排水畅通，无塌陷，表面无开裂破损");
						}else if(enuim.equals("03")){
							o.addProperty("enumid","03" );
							o.addProperty("enumid1","内外河翼墙" );
							o.addProperty("enumid2", "墙体完好，排水通畅，无塌陷，表面无开裂破损");
						}else if(enuim.equals("04")){
							o.addProperty("enumid","04" );
							o.addProperty("enumid1","翼墙后填土区" );
							o.addProperty("enumid2", "无雨淋沟，无裂缝，无塌陷，无渗漏，无滑坡");
						}else if(enuim.equals("05")){
							o.addProperty("enumid","05" );
							o.addProperty("enumid1","泵站内外进水池" );
							o.addProperty("enumid2", "水流平顺，无杂物，无水草");
						}else if(enuim.equals("06")){
							o.addProperty("enumid","06" );
							o.addProperty("enumid1","导流墩" );
							o.addProperty("enumid2", "墩体完好，无裂缝，无渗漏，观测标志完好");
						}else if(enuim.equals("07")){
							o.addProperty("enumid","07" );
							o.addProperty("enumid1","泵站主厂房" );
							o.addProperty("enumid2", "墙面完好，门窗完好，伸缩缝完好，排水通畅，无破损，无渗漏");
						}else if(enuim.equals("08")){
							o.addProperty("enumid","08" );
							o.addProperty("enumid1","泵站站身" );
							o.addProperty("enumid2", "工程设施完好，无裂缝，无渗漏，伸缩缝完好");
						}else if(enuim.equals("09")){
							o.addProperty("enumid","09" );
							o.addProperty("enumid1","变压器" );
							o.addProperty("enumid2", "室温正常，风扇运行正常");
						}else if(enuim.equals("10")){
							o.addProperty("enumid","10" );
							o.addProperty("enumid1","节制闸闸室" );
							o.addProperty("enumid2", "闸门无振动、倾斜，闸下流态正常，无漂浮物");
						}else if(enuim.equals("11")){
							o.addProperty("enumid","11" );
							o.addProperty("enumid1","节制闸启闭机" );
							o.addProperty("enumid2", "钢丝绳无断丝无明显变形，油缸无泄漏");
						}else if(enuim.equals("12")){
							o.addProperty("enumid","12" );
							o.addProperty("enumid1","交通桥" );
							o.addProperty("enumid2", "桥面完好，栏杆完好，观测标志完好，无裂缝，无渗漏");
						}else if(enuim.equals("13")){
							o.addProperty("enumid","13" );
							o.addProperty("enumid1","消防器材" );
							o.addProperty("enumid2", "灭火器压力正常，消防栓内水带，水栓完好");
						}else if(enuim.equals("14")){
							o.addProperty("enumid","14" );
							o.addProperty("enumid1","高压室" );
							o.addProperty("enumid2", "柜体、开关完好，仪表显示正常，柜内照明正常，柜前后绝缘垫完好清洁");
						}else if(enuim.equals("15")){
							o.addProperty("enumid","15" );
							o.addProperty("enumid1","高压电容室" );
							o.addProperty("enumid2", "柜体、开关完好，仪表显示正常，柜内照明正常，柜前后绝缘垫完好清洁");
						}else if(enuim.equals("16")){
							o.addProperty("enumid","16" );
							o.addProperty("enumid1","低压室" );
							o.addProperty("enumid2", "柜体、开关完好，仪表显示正常，电容补偿正常，指示灯显示正常，柜前后绝缘垫完好清洁");
						}else if(enuim.equals("17")){
							o.addProperty("enumid","17" );
							o.addProperty("enumid1","中控室" );
							o.addProperty("enumid2", "监控设备运行正常，数据显示正确，视频监控画面清晰");
						}else if(enuim.equals("18")){
							o.addProperty("enumid","18" );
							o.addProperty("enumid1","其他" );
							o.addProperty("enumid2", "");
						}
						o.addProperty("istype", pd.getIstype().intValue()==0?"正常":"有问题");
						o.addProperty("contents", pd.getContents());
						o.addProperty("handletype", pd.getHandletype().intValue()==0?"自行解决":"上报工程科");
						o.addProperty("handlecontents", pd.getHandlecontents());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);		
		return json;
	}
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public String save(String json,HttpServletRequest request) throws ParseException {
		String result = "";
		String [] jsons=json.split("/");
		String patrolid=jsons[0];
		String[] enumid=jsons[1].split(",");
		String[] handlecontents=jsons[2].split(",");
		String[] operater=jsons[3].split(",");
		String operatetime=jsons[4];
		String update="";
		if(jsons.length>5){
			update=jsons[5];
		}
		try {
			pas.deletePatrolPeople(patrolid);
			for(int i=0;i<enumid.length;i++){
				Map<String,String> map=new HashMap<String,String>();
				map.put("enumid", enumid[i]);
				map.put("handlecontents", handlecontents[i]);
				map.put("patrolid", patrolid);
				pas.updatePatrolDetails(map);
			}
			for(int i=0;i<operater.length;i++){
				PatrolPeople pp=new PatrolPeople();
				UUID uuid= UUID.randomUUID();
				pp.setId(uuid.toString());
				pp.setPatrolid(patrolid);
				pp.setOperater(operater[i]);
				pp.setOperatetime(datetimeFormat.parse(operatetime));
				pp.setType(new Long(1));
				pas.insertPatrolPeople(pp);
			}
			if(!update.equals("")){
				pas.updatePatrolDone(patrolid);
			}
			result="true";
		} catch (Exception e) {
			result="false";
			e.printStackTrace();
		}	
		return result;
	}
	@RequestMapping(value="findOperaterByPatrolid",method = RequestMethod.POST)
	@ResponseBody
	public String findOperaterByPatrolid(String patrolid){
		String result="";
		List<String> list=pas.findOperaterByPatrolid(patrolid);
		if(list.size()>0){
			result="[";
			for(int i=0;i<list.size();i++){
				result+="'"+list.get(i)+"',";
			}
			result=result.substring(0,result.length()-1)+"]";
		}
		return result;
	}
	@RequestMapping(value="findOperatetimeByPatrolid",method = RequestMethod.POST)
	@ResponseBody
	public String findOperatetimeByPatrolid(String patrolid){
		String result="";
		List<String> list=pas.findOperatetimeByPatrolid(patrolid);
		if(list.size()>0){
			result=list.get(0);
		}
		return result;
	}
	@RequestMapping(value = "/patrolDetailProblemList2", method = RequestMethod.POST)
	@ResponseBody
	public String patrolDetailProblemList2(int page,int rows,String begin,String end,HttpServletRequest request) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		User user=(User) request.getSession().getAttribute("loginUser");
		String sid=pas.findSidByUserId(user.getUserid());
		if(sid!=null){
			map.put("sid", sid);
		}else{
//			map.put("sid", "");
		}
		if(begin!=null && !begin.equals("")){
			map.put("begin", begin);
		}
		if(end!=null && !end.equals("")){
			map.put("end", end);
		}
		List<PatrolNormal> list=pas.findPatrolNormal(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = pas.findPatrolNormalcount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolNormal.class,
				new JsonSerializer<PatrolNormal>() {
					@Override
					public JsonElement serialize(PatrolNormal patrolnormal, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id",patrolnormal.getId());
						o.addProperty("stationname",patrolnormal.getStation().getName());
						o.addProperty("degree",patrolnormal.getDegree()==0?"第一次":"第二次");
						o.addProperty("parttime",datetimeFormat.format(patrolnormal.getParttime()));
						List<PatrolNormalPeople> peoples=patrolnormal.getPeoples();
						String handlername="";
						for(int i=0;i<peoples.size();i++){
							handlername+=peoples.get(i).getUser().getEmployee().getName()+",";
						}
						if(!handlername.equals("")){
							handlername=handlername.substring(0,handlername.length()-1);
						}
						o.addProperty("handlername",handlername);
						o.addProperty("creatername",patrolnormal.getCreateuser().getEmployee().getName());
						o.addProperty("creatertime",datetimeFormat.format(patrolnormal.getCreatetime()));						
//						o.addProperty("code", p.getPpd().getPatrolplan().getCode());
//						o.addProperty("patroltime",datetimeFormat.format(p.getPatroltime()));
//						o.addProperty("creater", p.getCreate().getEmployee().getName());
//						o.addProperty("createtime",datetimeFormat.format(p.getCreatetime()));
//						String state=p.getState();
//						if(state.equals("3")){
//							o.addProperty("state", "未处理");
//						}else if(state.equals("4")){
//							o.addProperty("state", "已处理");
//						}
//						o.addProperty("stationname",p.getCreate().getEmployee().getStation().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	@Resource
	private PatrolNormalReportService normalReportService;
	@RequestMapping(value = "resolveinfo", method = RequestMethod.GET)
	public ModelAndView resolveinfo(String data) {
		ModelAndView mv = new ModelAndView();
		gson = new Gson();
		DepartmentJson departmentJson = gson.fromJson(data, DepartmentJson.class);//获取前台传递的数据参数，解析成java类
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("handletype", 1);
		map.put("list", departmentJson.getIds());
		List<PatrolNormalDetails> list = normalReportService.selectByDepartment(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows",list);
	
		gson = new GsonBuilder().registerTypeAdapter(PatrolNormalDetails.class,
				new JsonSerializer<PatrolNormalDetails>() {
			@Override
			public JsonElement serialize(PatrolNormalDetails src, Type typeOfSrc,JsonSerializationContext context) {
				JsonObject o = new JsonObject();
				o.addProperty("id", src.getId());
				o.addProperty("patrolid", src.getPatrolid());
				o.addProperty("sname", src.getPatrolnormal().getStation().getName());
				o.addProperty("text", src.getPatrolenumid().getText());
				o.addProperty("contents", src.getContents());
				if(src.getPatrolnormal() != null && src.getPatrolnormal().getDegree() != null){
				if(src.getPatrolnormal().getDegree() == 0){
					o.addProperty("degreename", "第一次");
				}else if(src.getPatrolnormal().getDegree() == 1){
					o.addProperty("degreename", "第二次");
				}
				else if(src.getPatrolnormal().getDegree() == 2){
					o.addProperty("degreename", "第三次");
				}	
				}
				return o;
			}
		}).create();
//		String json=gson.toJson(list);
		String json=gson.toJson(jsonMap);
		mv.addObject("jsonStr", json);
		mv.setViewName("patrol/resolveinfo");
		return mv;
	}
	@Autowired
	private EmployeeService employeeService;
	@RequestMapping(value = "save2", method = RequestMethod.POST)
	@ResponseBody
	public String save2(String jsonStr, String departmentjson,HttpServletRequest request){
		gson = new Gson();
		PatrolNormalReport report = gson.fromJson(jsonStr, PatrolNormalReport.class);
		report.setId(UUID.randomUUID().toString());
		report.setReporttime(new Date());
		User user = (User) request.getSession().getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		report.setReporter(user.getUserid());
		report.setDepartmentid(e.getDepartmentid());
		List<PatrolNormalReportDetails> list = new ArrayList<PatrolNormalReportDetails>();
		for(PatrolNormalReportDetails d : report.getPatrolNormalReportDetails() ){
			PatrolNormalReportDetails pd = new PatrolNormalReportDetails();
			pd.setId(UUID.randomUUID().toString());
			pd.setReportid(report.getId());
			pd.setPatrolid(d.getPatrolid());
			pd.setState(new Long(0));
			list.add(pd);
		}
		DepartmentJson d= gson.fromJson(departmentjson,DepartmentJson.class);
		try {
			normalReportService.insert2(report, list, d.getIds(), 0);
			return "{'result':true}";
		} catch (Exception e1) {
			e1.printStackTrace();
			return "{'result':false,'msg':'保存失败！'}";
		}
		
	}
}
