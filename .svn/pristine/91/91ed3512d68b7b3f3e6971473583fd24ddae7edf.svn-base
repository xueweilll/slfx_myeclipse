package com.benqzl.controller.patrol.implement;

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
import com.benqzl.dao.patrol.PatrolImplementMapper;
import com.benqzl.dao.patrol.PatrolSpecialImplementMapper;
import com.benqzl.dao.patrol.PatrolSpecialIssueMapper;
import com.benqzl.pojo.patrol.PatrolSpecialImplement;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolSpecialFolwService;
import com.benqzl.service.system.EmployeeService;
import com.benqzl.service.system.StationService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("patrolImplement")
public class PatrolImplementController extends BasicController {

	@Autowired
	private PatrolImplementMapper patrolImplementMapper;
	
	public PatrolImplementController() {
		super(PatrolImplementController.class);
	}
	
	@RequestMapping(value="")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/implement/patrolImplement");
		return mv;
	}
	
	@Autowired
	private ActivitiUnitService activitiUnitService;
	
	@RequestMapping(value="patrolImplementInfo",method=RequestMethod.GET)
	public ModelAndView patrolImplementInfo(String classes){
		ModelAndView mv = new ModelAndView();
		String code = activitiUnitService.selectMaxCode("CUFC");
		mv.addObject("code",code);
		if(classes.equals("3")){
			mv.setViewName("patrol/implement/patrolImplementInfo");
		}else{
			mv.setViewName("patrol/implement/regularpatrolImplementInfo");
		}
		return mv;
	}
/*	@RequestMapping(value="queryData")
	@ResponseBody
	public String queryData(String startTime,String endTime,int page,int rows){
		List<Map<String,Object>> lists = patrolImplementMapper.query(nullToString(startTime), nullToString(endTime));
		gson = new Gson();
		return gson.toJson(lists);
	}*/
	@RequestMapping(value="queryData")
	@ResponseBody
	public String queryData(String classes,String startTime,String endTime,int page,int rows){
		Map<String,Object> map=new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if(startTime==null||startTime.trim().equals("")){
			map.put("startTime", null);
		}else{
			try {
				map.put("startTime", datetimeFormat.parse(startTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endTime==null||endTime.trim().equals("")){
			map.put("endTime", null);
		}else{
			try {
				map.put("endTime", datetimeFormat.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.put("classes", classes);
		List<PatrolSpecialImplement> list = new ArrayList<>();
		int count =0;
		try {
			list= patrolImplementMapper.findByPage(map);
			count=patrolImplementMapper.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", list);
		jsonMap.put("total", count);
		gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialImplement.class, new JsonSerializer<PatrolSpecialImplement>() {
			@Override
			public JsonElement serialize(PatrolSpecialImplement arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				//json.addProperty("id", arg0.getId());
				if(arg0.getIssue()==null){
					json.addProperty("CODE", "");//签发单编号
				}else{
					json.addProperty("CODE", arg0.getIssue().getCode());//签发单编号
				}
				json.addProperty("NAME", arg0.getStation().getName());//枢纽名称
				json.addProperty("USERNAME", arg0.getEmployee().getName());//制单人
				if(arg0.getParttime()==null){
					json.addProperty("PARTTIME1", "");//巡检时间
				}else{
					json.addProperty("PARTTIME1", datetimeFormat.format(arg0.getParttime()));//巡检时间
				}
				if(arg0.getCreatetime()==null){
					json.addProperty("CREATETIME1", "");//制单时间
				}else{
					json.addProperty("CREATETIME1", datetimeFormat.format(arg0.getCreatetime()));//制单时间
				}
				json.addProperty("STATE", arg0.getState());
				return json;
			}
		}).create();
		return gson.toJson(jsonMap);
	}
	
	@RequestMapping(value="getDemo")
	@ResponseBody
	public ModelAndView patrolImplementDemo(String classes){
		List<Map<String,Object>> lists = patrolImplementMapper.getDemo();
		gson = new Gson();
		ModelAndView mav = new ModelAndView();
		String code = activitiUnitService.selectMaxCode("YJ");
		mav.addObject("code",code);
		mav.addObject("firstDemo", gson.toJson(lists));
		if(classes.equals("3")){
			mav.setViewName("patrol/implement/patrolImplementInfo");
		}else{                                
			mav.setViewName("patrol/implement/regularpatrolImplementInfo");			
		}
		return mav;
	}
	
	@Autowired
	private PatrolSpecialImplementMapper pimmapper;
	@Autowired
	private PatrolSpecialFolwService patrolspecialflowservice;
	@RequestMapping(value="save")
	@ResponseBody
	public String patrolSaveDemo(String implementid,String examintime,String station,String issueid,String info,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("loginUser");
		PatrolSpecialImplement psi = new PatrolSpecialImplement();
		psi.setId(UUID.randomUUID().toString());
		psi.setIsid(issueid);		
		try {
			if(examintime == null){
				psi.setParttime(new Date());
			}else{
				psi.setParttime(datetimeFormat.parse(examintime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		psi.setCreater(user.getUserid());
		psi.setCreatetime(new Date());
		psi.setState(new Long(0));
		psi.setSid(station);
		pimmapper.insert(psi);
		String id = UUID.randomUUID().toString();
		//int flag = patrolImplementMapper.save(id,psi.getId(), examintime, station, info, user.getUserid());
		//System.out.println(flag);
		patrolImplementMapper.save(id,psi.getId(), examintime, station, info, user.getUserid());
	   int count=patrolImplementMapper.boobleisid(issueid);
		if(count==0){
		patrolspecialflowservice.implement(issueid);
		}
		return "1";
	}
	
/*	private String nullToString(String obj){
		return obj != null ? obj : "";
	}*/
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private StationService stationService;
	
	@RequestMapping(value="stationList")
	@ResponseBody
	public String stationList(HttpServletRequest request,String isid){
		User user=(User) request.getSession().getAttribute("loginUser");
		Employee e = employeeService.selectByUser(user.getUserid());
		//List<Station> list = stationService.selectByDepartment(e.getDepartmentid());
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("did", e.getDepartmentid());
		map.put("isid", isid);
		List<Station> list = stationService.selectSpecialByDepartment(map);
		return gson.toJson(list);
	}
	
	@Autowired
	private PatrolSpecialIssueMapper psimapper;
	
	@RequestMapping(value="issueList")
	@ResponseBody
	public  String issueList(String classes){
		//List<PatrolSpecialIssue> list = psimapper.selectByCombobox();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("classes", classes);
		List<PatrolSpecialIssue> list = psimapper.selectByIssue(map);
		return gson.toJson(list);
	}
}
