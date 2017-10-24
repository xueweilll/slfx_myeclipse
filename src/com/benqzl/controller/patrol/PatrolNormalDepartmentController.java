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
import com.benqzl.pojo.patrol.PatrolNormalDepartmentSearch;
import com.benqzl.pojo.patrol.PatrolNormalDetails;
import com.benqzl.pojo.patrol.PatrolNormalReport;
import com.benqzl.pojo.patrol.PatrolNormalReportDetails;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolNormalReportService;
import com.benqzl.service.system.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


@Controller
@RequestMapping("patrolnormaldepartment")
public class PatrolNormalDepartmentController extends BasicController{
	public PatrolNormalDepartmentController() {
		super(PatrolNormalDepartmentController.class);
	}
	
	@Resource
	private PatrolNormalReportService normalReportService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/patrolnormaldepartment");
		return mv;
	}
	@RequestMapping(value = "patrolnormaldepartmentInfo", method = RequestMethod.GET)
	public ModelAndView patrolnormaldepartmentInfo(String ids,String type) {
		ModelAndView mv = new ModelAndView();
		String[] is = ids.split(",");
		List<String> strings = new ArrayList<>();
		for(int i = 0;i<is.length;i++){
			strings.add(is[i]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list",strings);
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
				if(src.getHandletype() == null){
					o.addProperty("handletype", "");
				}else if(src.getHandletype() == 0){
					o.addProperty("handletype", "上报执行部门");
				}else if(src.getHandletype() == 1){
					o.addProperty("handletype", "自行解决");
				}else if(src.getHandletype() == 2){
					o.addProperty("handletype", "上报工程科");
				}else if(src.getHandletype() == 3){
					o.addProperty("handletype", "安排解决");
				}else if(src.getHandletype() == 4){
					o.addProperty("handletype", "上报管理处");
				}
				return o;
			}
		}).create();
//		String json=gson.toJson(list);
		String json=gson.toJson(jsonMap);
		mv.addObject("jsonStr", json);
		mv.addObject("type",type);
		mv.setViewName("patrol/patrolnormaldepartmentInfo");
		return mv;
	}

	
	@RequestMapping(value = "pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String pageBind(int page,int rows,String all,HttpServletRequest request,String begin, String end) throws Exception {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		User user=(User) request.getSession().getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		map.put("departmentid", e.getDepartmentid());
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(begin==null||begin.trim().equals("")){
			map.put("begin", null);
		}else{
			try {
				map.put("begin", datetimeFormat.parse(begin));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		if(end==null||end.trim().equals("")){
			map.put("end", null);
		}else{
			try {
				map.put("end", datetimeFormat.parse(end));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		map.put("type", all);
		List<PatrolNormalDepartmentSearch> list = normalReportService.findByDepartmentPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total=normalReportService.countByDepartment(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolNormalDepartmentSearch.class,
				new JsonSerializer<PatrolNormalDepartmentSearch>() {
					@Override
					public JsonElement serialize(PatrolNormalDepartmentSearch src, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("stationname", src.getSnames());
						if(src.getDegree() == 0){
							o.addProperty("degreename", "第一次");
						}else if(src.getDegree() == 1){
							o.addProperty("degreename", "第二次");
						}
						else if(src.getDegree() == 2){
							o.addProperty("degreename", "第三次");
						}
						o.addProperty("degree", src.getDegree());
						o.addProperty("creatertime",src.getCreatetime());
						o.addProperty("ids", src.getIds());
						o.addProperty("dname", src.getDname());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr, String departmentjson,HttpServletRequest request){
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
			normalReportService.insert(report, list, d.getIds(), 1);
			return "{'result':true}";
		} catch (Exception e1) {
			e1.printStackTrace();
			return "{'result':false,'msg':'保存失败！'}";
		}
		
	}
	
}
