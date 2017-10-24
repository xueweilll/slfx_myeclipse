package com.benqzl.controller.patrol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.PatrolSpecialProjectReport;
import com.benqzl.service.patrol.engineering.PatrolSpecialProjectReportService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: EgPatrolManagementexamineController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pxj 
* @date 2016年4月29日 下午4:50:00 
*  
*/
@Controller
@RequestMapping("psmanageapproval")
public class PatrolSpecialManageApproveController extends BasicController{

	public PatrolSpecialManageApproveController() {
		super(PatrolSpecialManageApproveController.class);
	}
	
	/** 
	* @Title: index 
	* @Description: index页面
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/patrolmanagementexamine");
		return mv;
	}
	
	@Autowired
	private PatrolSpecialProjectReportService projectReportService;
	
	@RequestMapping(value = "pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String pageBind(int rows, int page, String all) {
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if(all.equals("1")){
			map.put("state", 0);
		}
		List<PatrolSpecialProjectReport> list = projectReportService.findManageByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = projectReportService.pageManageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialProjectReport.class,
				new JsonSerializer<PatrolSpecialProjectReport>() {
					@Override
					public JsonElement serialize(PatrolSpecialProjectReport src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("isid", src.getIsid());
						if(src.getFlow() != null){
							if(src.getFlow().getSb() == 1){
								o.addProperty("state", "已上报");
							}else {
								o.addProperty("state", "未上报");
							}
						}						
						o.addProperty("reporttime", datetimeFormat.format(src.getReporttime()));
						if(src.getReportUser() != null){
							o.addProperty("reporter", src.getReportUser().getUsername());
						}				
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}
	
	/** 
	* @Title: patrolManagementInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "patrolManagementInfo", method = RequestMethod.GET)
	public ModelAndView patrolManagementInfo(){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("patrol/patrolmanagementexamineInfo");
		return mv;
	}


}
