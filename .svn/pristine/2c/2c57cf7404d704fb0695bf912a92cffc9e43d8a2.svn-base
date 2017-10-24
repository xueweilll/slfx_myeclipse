package com.benqzl.controller.patrol;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReportDetails;
import com.benqzl.pojo.patrol.PatrolSpecialImplement;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolSpecialDepartmentReportService;
import com.benqzl.service.patrol.PatrolSpecialFolwService;
import com.benqzl.service.system.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: EgPatrolEngineerController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author pxj
 * @date 2016年4月28日 下午2:55:54
 * 
 */
@Controller
@RequestMapping("psdepartmentreport")
public class PatrolSpecialDepartmentReportController extends BasicController {

	public PatrolSpecialDepartmentReportController() {
		super(PatrolSpecialDepartmentReportController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(水政工程科)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/patrolspecialdepartmentReport");
		return mv;
	}

	/**
	 * @Title: info
	 * @Description: TODO(info页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "patrolnormaldepartmentInfo", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView info(@RequestParam(required = true) String patrolplandetailid,String isid,String look) {
		List<Map<String, Object>> allResult = new ArrayList<Map<String, Object>>();
		List<String> list = new ArrayList<String>();
		String a[] = patrolplandetailid.split(",");
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		//list获取PL_SPECIAL_IMPLEMENT主键ID
		//List<Map<String, Object>> listResult = patrolSpecialDepartmentReportService.selectVal(list);
		List<Map<String, Object>> listResult = patrolSpecialDepartmentReportService.selectVals(isid);//同步
		List<Integer> listTable = new ArrayList<Integer>();
		for (Map<String, Object> map : listResult) {
			
			String[] vals = map.get("VALS").toString().split(",");
			for (int i = 0; i < vals.length; i++) {
				Map<String, Object> obj = new HashMap<String,Object>();
				obj.put("ID", Integer.parseInt(vals[i].split("@")[0]));
				obj.put("SID", map.get("SID"));
				obj.put("NAME", map.get("NAME"));
				obj.put("ISID", map.get("ISID"));
				obj.put("IMPLEMENTID", map.get("IMPLEMENTID"));
				//System.out.print(vals[i].split("@").length);
				if(vals[i].split("@").length==1){
					obj.put("REMARK","");
				}else{
					obj.put("REMARK",vals[i].split("@")[1]);
				}
				allResult.add(obj);
				listTable.add(Integer.parseInt(vals[i].split("@")[0]));
			}
		}
		List<Map<String, Object>> listdetials=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listTableClass = patrolSpecialDepartmentReportService.selectTable(listTable);
		for (Map<String, Object> map : listTableClass) {
			for (Map<String, Object> mapResult : allResult) {
				Map<String,Object> map1=new HashMap<String,Object>();
				if(map.get("ID").toString().equals(mapResult.get("ID").toString())){
					map1.put("ID", map.get("ID"));
					map1.put("CLASSES", map.get("CLASSES"));
					map1.put("TNAME", map.get("TNAME"));
					map1.put("SID", mapResult.get("SID"));
					map1.put("ISID",mapResult.get("ISID"));
					map1.put("IMPLEMENTID", mapResult.get("IMPLEMENTID"));
					map1.put("REMARK", mapResult.get("REMARK"));
					map1.put("NAME", mapResult.get("NAME"));
					listdetials.add(map1);
				}
			}
			
		}
		Map<String, Object> jsonMap= new HashMap<>();
		jsonMap.put("total", listTableClass.size());
		jsonMap.put("rows", listdetials);
		String json = new GsonBuilder().create().toJson(jsonMap);
		ModelAndView mv = new ModelAndView();
		mv.addObject("jsonStr",json);
		mv.addObject("look",look);
		mv.setViewName("patrol/patrolSpecialDepartmentReportInfo");
		return mv;
	}

	@Autowired
	private PatrolSpecialDepartmentReportService patrolSpecialDepartmentReportService;
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * @Title: egpatrolengineerlist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param rows
	 * @param @param page
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String pageBind(String classes,String begin,String end,int rows, int page, HttpSession session, String all) {
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if(begin==null||begin.trim().equals("")){
			map.put("startTime", null);
		}else{
			try {
				map.put("startTime", datetimeFormat.parse(begin));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(end==null||end.trim().equals("")){
			map.put("endTime", null);
		}else{
			try {
				map.put("endTime", datetimeFormat.parse(end));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(all.equals("1")){
			map.put("state", 0);
		}
		map.put("mytask", all);
		map.put("classes", classes);
		User user = (User) session.getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		map.put("departmentid", e.getDepartmentid());
		List<PatrolSpecialImplement> list = patrolSpecialDepartmentReportService.findBypage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = patrolSpecialDepartmentReportService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialImplement.class,
				new JsonSerializer<PatrolSpecialImplement>() {

					@Override
					public JsonElement serialize(PatrolSpecialImplement src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("sid", src.getSid());
						o.addProperty("isid", src.getIsid());
						o.addProperty("code", src.getIssue().getCode());
						if(src.getStation() != null){
							o.addProperty("stationname", src.getStation().getName());
						}
						if(src.getFlow() != null){
							System.out.println(src.getState());
							if(src.getState().toString().equals("1")){
								o.addProperty("Levels", "已汇总");
							}else {
								o.addProperty("Levels", "未汇总");
							}
						}						
						o.addProperty("parttime", datetimeFormat.format(src.getParttime()));
						o.addProperty("createtime", datetimeFormat.format(src.getCreatetime()));
						if(src.getCreateUser() != null){
							o.addProperty("creater", src.getCreateUser().getUsername());
						}				
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		logger.info("this station list strJson is " + json);
		return json;
	}

	
	@Autowired 
	private PatrolSpecialFolwService patrolspecialflowservice;
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr,HttpServletRequest request) {
		gson = new Gson();
		PatrolSpecialDeaprtmentReport p = gson.fromJson(jsonStr, PatrolSpecialDeaprtmentReport.class);
		User user = (User) request.getSession().getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		p.setId(UUID.randomUUID().toString());
		p.setReporter(user.getUserid());
		p.setReporttime(new Date());
		p.setIsid(p.getIsids().get(0));
		p.setDepartmentid(e.getDepartmentid());
		List<PatrolSpecialDeaprtmentReportDetails>  list = new ArrayList<>();
		List<String> ls=new ArrayList<>();
		for(PatrolSpecialDeaprtmentReportDetails pr : p.getDetails()){
			PatrolSpecialDeaprtmentReportDetails pp = new PatrolSpecialDeaprtmentReportDetails();
			pp.setId(UUID.randomUUID().toString());
			pp.setImplementid(pr.getImplementid());
			pp.setReportid(p.getId());
			list.add(pp);
			ls.add(pr.getImplementid());
		}
		p.setDetails(list);
		patrolSpecialDepartmentReportService.insert(p,ls);
		patrolspecialflowservice.collect(p.getIsids().get(0));
		return "{'result':true}";
	}
}
