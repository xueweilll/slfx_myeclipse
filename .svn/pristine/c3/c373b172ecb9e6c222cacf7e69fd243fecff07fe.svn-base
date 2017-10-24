package com.benqzl.controller.patrol.engineering;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.PatrolImplementClass;
import com.benqzl.pojo.patrol.PatrolImplementDetails;
import com.benqzl.pojo.patrol.PatrolImplementTable;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReport;
import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReportDetails;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.service.patrol.PatrolSpecialIssueService;
import com.benqzl.service.patrol.engineering.PatrolSpecialProjectReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author MJ006
 *	工程科上报（定期）
 */
@Controller
@RequestMapping("psprojectreport1")
public class PatrolSpecialProjectReprot1Controller extends BasicController {
	
	@Autowired
	private PatrolSpecialProjectReportService projectReportService;
	@Autowired
	private PatrolSpecialIssueService issueService;
	
	public PatrolSpecialProjectReprot1Controller() {
		super(PatrolSpecialProjectReprot1Controller.class);
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
		mv.setViewName("patrol/engineering/patrolspecialprojectreport1");
		return mv;
	}

	/**
	 * @Title: info
	 * @Description: TODO(info页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "patrolspecialprojectreport1Info", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView patrolspecialprojectreport1Info(String type,String isId) {
		
		List<PatrolImplementDetails> details = new ArrayList<>();
		try {
			details=projectReportService.selectValsByIsid(isId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Map<String, String>> detailsMap = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		for (PatrolImplementDetails implementDetails : details) {
			Map<String, Object> map2 = new HashMap<>();
			String[] sarray= implementDetails.getVals().split(",");
			if(sarray.length!=0){
				map2.put("implementId", implementDetails.getImplementid());
				Map<String, String> map = new HashMap<>();
				List<String> list2 = new ArrayList<>();
				for (String string : sarray) {
					String[] sarraydetails=string.split("@");
					if(sarraydetails.length!=1){
						map.put(sarraydetails[0], sarraydetails[1]);
						list2.add(sarraydetails[0]);
					}else{
						map.put(sarraydetails[0], " ");
						list2.add(sarraydetails[0]);
					}
				}
				if(list2.size()!=0){
					map2.put("list", list2);
					detailsMap.put(implementDetails.getImplementid(),map);
				}
			}
			if(map2.get("list")!=null){
				list.add(map2);
			}
		}
		Map<String, Object> map =new HashMap<>();
		map.put("isId", isId);
		map.put("lists", list);
		List<PatrolSpecialDeaprtmentReport> deaprtmentReports = new ArrayList<>();
		try {
			if(list.size()!=0){
				deaprtmentReports=projectReportService.selectByIssueId(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> resultMap = new ArrayList<>();
		for (PatrolSpecialDeaprtmentReport deaprtmentReport : deaprtmentReports) {
			Map<String, Object> cmap = new HashMap<>();
			cmap.put("id", deaprtmentReport.getId());
			cmap.put("name", deaprtmentReport.getIsid());
			for (PatrolSpecialDeaprtmentReportDetails deaprtmentReportDetails : deaprtmentReport.getDetails()) {
				String uuid = UUID.randomUUID().toString();
				Map<String, Object> ccmap = new HashMap<>();
				ccmap.put("id", uuid);
				ccmap.put("name", deaprtmentReportDetails.getSpecialImplements().getIsid());
				ccmap.put("_parentId", deaprtmentReport.getId());
				Map<String, String> contentMap = detailsMap.get(deaprtmentReportDetails.getSpecialImplements().getId());
				for (PatrolImplementClass patrolImplementClass : deaprtmentReportDetails.getSpecialImplements().getPatrolImplementClasses()) {
					boolean result1=false;
					for (PatrolImplementClass implementClass : patrolImplementClass.getImplementClasses()) {
						boolean result2=false;
						for (PatrolImplementTable implementTable : implementClass.getImplementTables()) {
							boolean result=false;
							if(contentMap.get(implementTable.getId())!=null){
								result=true;
								result1=true;
								result2=true;
							}
							if(result){
								Map<String, Object> cccccmap = new HashMap<>();
								cccccmap.put("id", "table"+uuid+implementTable.getId());
								cccccmap.put("name", implementTable.getName());
								cccccmap.put("_parentId", uuid+implementClass.getId());
								cccccmap.put("content", contentMap.get(implementTable.getId()));
								resultMap.add(cccccmap);
							}
						}
						if(result2){
							Map<String, Object> ccccmap = new HashMap<>();
							ccccmap.put("id", uuid+implementClass.getId());
							ccccmap.put("name", implementClass.getClasses());
							ccccmap.put("_parentId", uuid+patrolImplementClass.getId());
							resultMap.add(ccccmap);
						}
					}
					if(result1){   
						Map<String, Object> cccmap = new HashMap<>();
						cccmap.put("id", uuid+patrolImplementClass.getId());
						cccmap.put("name", patrolImplementClass.getClasses());
						cccmap.put("_parentId", uuid);
						resultMap.add(cccmap);
					}
				}
				resultMap.add(ccmap);
			}
			resultMap.add(cmap);
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("total", resultMap.size());
		jsonMap.put("rows", resultMap);
		ModelAndView mv = new ModelAndView();
		PatrolSpecialIssue issue = new PatrolSpecialIssue();
		try {
			issue=issueService.findIssueById(isId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("type", type);
		mv.addObject("issue", issue);
		gson = new Gson();
		mv.addObject("jsonMap", gson.toJson(jsonMap));
		mv.setViewName("patrol/engineering/patrolspecialprojectreport1Info");
		return mv;
	}
	

	/**
	 * @Title: egpatrolengineerlist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param rows
	 * @param @param page
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int rows, int page, String starttime,String endtime,String typeDate) {
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", 1);
		map.put("classes", null);
		if(typeDate.equals("0")){
			map.put("typeDate",null);
		}else{
			map.put("typeDate",typeDate);
		}
		if(starttime==null||starttime.trim().equals("")){
			map.put("starttime", null);
		}else{
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<PatrolSpecialIssue> list = new ArrayList<>();
		int total = 0;
		try {
			list = projectReportService.findIssueByPage(map);
			total = projectReportService.pageIssueCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialIssue.class,
				new JsonSerializer<PatrolSpecialIssue>() {
					@Override
					public JsonElement serialize(PatrolSpecialIssue arg0, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", arg0.getId());
						o.addProperty("creater", arg0.getCreater());
						o.addProperty("code", arg0.getCode());
						o.addProperty("createtime", datetimeFormat.format(arg0.getCreatetime()));
						o.addProperty("content", arg0.getContent());
						o.addProperty("remark", arg0.getRemark());
						if(arg0.getClasses().equals(new Long(0))){
							o.addProperty("classes", "汛前");
						}else if(arg0.getClasses().equals(new Long(1))){
							o.addProperty("classes", "汛期");
						}else{
							o.addProperty("classes", "汛后");
						}
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String isid) {
		Map<String, Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("sb", 1);
		try {
			projectReportService.updateFlow(map);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'上报失败！'}";
		}
		return "{'result':true,'msg':'上报成功！'}";
	}
}
