package com.benqzl.controller.oa;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.WorkPlan;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.WorkPlanService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: WorkplanController 
* @Description: 工作计划(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2015年12月15日 上午10:06:39 
*  
*/
@Controller
@RequestMapping("/workplan")
public class WorkplanController extends BasicController{
	
	public WorkplanController() {
		super(WorkplanController.class);
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(用户增删改查页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/workplan");
		return mv;
	}
	
	@Autowired
	private WorkPlanService workplanService;
	
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr,HttpSession httpSession) {
		String result = "";
		gson = new Gson();
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
*/		WorkPlan workplan = gson.fromJson(jsonStr,WorkPlan.class);
		if(workplan.getId().equals("0"))
		{
			UUID uuid = UUID.randomUUID();
			workplan.setId(uuid.toString());
			workplan.setCreatetime(new Date());
			workplan.setEdittime(new Date());
			workplan.setIspc(new Long(0));
			workplan.setIsphonemess(new Long(0));
			workplan.setState(new Long(0));
			User user= (User)httpSession.getAttribute("loginUser"); 
			workplan.setCreaterid(user.getUserid());
			try {
				workplanService.insert(workplan);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}
		}
		else {
			workplan.setEdittime(new Date());
			try {
				workplanService.updateByPrimaryKeySelective(workplan);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}
		}
		return result;
	}
	
	/** 
	* @Title: workplanInfo 
	* @Description: TODO(用户添加和编辑页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "workplanInfo", method = RequestMethod.GET)
	public ModelAndView workplanInfo(String id,String date) {
		ModelAndView mv = new ModelAndView();
		
		if(id.equals("0"))
		{
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			
			try {
				Date test = sdf.parse(date);
				mv.addObject("beginTime", datetimeFormat.format(test));
				mv.addObject("endTime", datetimeFormat.format(test));
				mv.addObject("remindTime", datetimeFormat.format(test));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mv.setViewName("oa/workplanInfo");
		}else{
			WorkPlan workplan = workplanService.selectByPrimaryKey(id);
			mv.addObject("beginTime", datetimeFormat.format(workplan.getBeigintime()));
			mv.addObject("endTime", datetimeFormat.format(workplan.getEndtime()));
			mv.addObject("remindTime", datetimeFormat.format(workplan.getRemindtime()));
			mv.addObject("workplan", workplan);
			mv.setViewName("oa/workplanInfo");
		}
		return mv;
	}	
	

	@RequestMapping(value="searchBydata",method = RequestMethod.POST)
	@ResponseBody
	public String searchBydata(String conditions,HttpSession httpSession){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String lastDate=conditions+"-01";
			
			Date date=df.parse(conditions+"-01");
			Calendar calendar = Calendar.getInstance();
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			String nextDate=df.format(calendar.getTime());
			
			Map<String, Object> map = new HashMap<String, Object>();
			User user= (User)httpSession.getAttribute("loginUser"); 
			if(user != null)
			{
				map.put("createID", user.getUserid());
			}			
			map.put("lastdate",lastDate);
			map.put("nextdate", nextDate);
			List<WorkPlan> list= workplanService.selectByMonth(map);
			
			gson = new Gson();
			gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd")  
			  .create();  ;
			String josn= gson.toJson(list);
			
			return josn;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{'msg':'查询失敗！'}";
		}  
	}
	
	@RequestMapping(value="distory",method = RequestMethod.POST)
	@ResponseBody
	public String distory(String id){
		String result="";
		try
		{
			workplanService.deleteState(id);
			result= "{'result':true}";
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			result= "{'result':false,'msg':'删除失敗！'}";
		}
		return result;
	}
	
	@RequestMapping(value = "workplansearch", method = RequestMethod.GET)
	public ModelAndView workplansearch(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("oa/workplanSearch");
		return mv;
	}
	
	@RequestMapping(value = "/pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String PageBind(int page, int rows,String conditions){
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if(conditions != null && conditions.length()>0){
			gson = new Gson();
			WorkPlan workplan = gson.fromJson(conditions, WorkPlan.class);
			if(workplan.getTitle() != null && workplan.getTitle().length()>0)
			{
				map.put("title", workplan.getTitle());
			}
			if(workplan.getRemark() != null && workplan.getRemark().length()>0)
			{
				map.put("remark", workplan.getRemark());
			}
			if(workplan.getBeigintime() != null )
			{
				map.put("beigintime",  datetimeFormat.format(workplan.getBeigintime()));
			}
			if(workplan.getEndtime() != null )
			{
				map.put("endtime",  datetimeFormat.format(workplan.getEndtime()));
			}
		}
		List<WorkPlan> list = workplanService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = workplanService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(WorkPlan.class,
				new JsonSerializer<WorkPlan>() {
					@Override
					public JsonElement serialize(WorkPlan src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("title", src.getTitle());
						o.addProperty("remark", src.getRemark());
						o.addProperty("beigintime",
								datetimeFormat.format(src.getBeigintime()));
						o.addProperty("endtime",
								datetimeFormat.format(src.getEndtime()));
						o.addProperty("remindtime",
								datetimeFormat.format(src.getRemindtime()));
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		logger.info("this station list strJson is " + json);
		return json;
	}
	
}
