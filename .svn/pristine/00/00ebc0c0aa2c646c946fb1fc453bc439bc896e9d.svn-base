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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.patrol.PatrolSpecialResolve;
import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;
import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolSpecialFolwService;
import com.benqzl.service.patrol.PatrolSpecialIssueService;
import com.benqzl.service.patrol.PatrolSpecialResolveService;
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
@RequestMapping("egpatrolengineer")
public class EgPatrolEngineerController extends BasicController {

	public EgPatrolEngineerController() {
		super(EgPatrolEngineerController.class);
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
		mv.setViewName("patrol/egpatrolengineer");
		return mv;
	}

	/**
	 * @Title: info
	 * @Description: TODO(info页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */

	/**
	 * @Title: egpatrolengineerlist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param rows
	 * @param @param page
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@Autowired
	private PatrolSpecialIssueService patrolSpecialIssueService;
	@Autowired
	private PatrolSpecialResolveService patrolSpecialResolveService;
	//调用flow
	@Autowired
	private PatrolSpecialFolwService patrolspecialflowservice;
	@RequestMapping(value = "egpatrolengineerInfo", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView info(String id, Model model) throws Exception {
		PatrolSpecialIssue patrolSpecialIssue = patrolSpecialIssueService
				.findIssueById(id);
		ModelAndView mv = new ModelAndView();
		model.addAttribute("patrolIssue", patrolSpecialIssue);
		PatrolSpecialResolve patrolSpecialResolve=patrolSpecialResolveService.findegpatrolById(id);
		model.addAttribute("resolveInfo", patrolSpecialResolve);
		mv.setViewName("patrol/egpatrolengineerInfo");
		return mv;
	}
	@RequestMapping(value = "egpatrolengineerInfoHide", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView infoHide(String id, Model model) throws Exception {
		PatrolSpecialIssue patrolSpecialIssue = patrolSpecialIssueService
				.findIssueById(id);
		ModelAndView mv = new ModelAndView();
		model.addAttribute("patrolIssue", patrolSpecialIssue);
		PatrolSpecialResolve patrolSpecialResolve=patrolSpecialResolveService.findegpatrolById(id);
		model.addAttribute("resolveInfo", patrolSpecialResolve);
		mv.setViewName("patrol/egpatrolengineerInfoHide");
		return mv;
	}
	@RequestMapping(value = "/egpatrolengineerlist", method = RequestMethod.POST)
	@ResponseBody
	public String egpatrolengineerlist(int rows, int page,String starttime, String endtime,String typeDate,String type) throws Exception {
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		//时间查询
		

		if (typeDate.equals("1")) {
		
			map.put("type", "1");
		} else {
			map.put("type", "0");
		}
		
		
		if(starttime==null||starttime.trim().equals("")){
			map.put("startTime", null);
		}else{
			try {
				map.put("startTime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endTime", null);
		}else{
			try {
				map.put("endTime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		
		List<PatrolSpecialIssue> list = patrolSpecialIssueService
				.selectEgpatrolByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = patrolSpecialIssueService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialIssue.class,
				new JsonSerializer<PatrolSpecialIssue>() {

					@Override
					public JsonElement serialize(PatrolSpecialIssue src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("creater", src.getUser().getEmployee()
								.getName());
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("classes", src.getClasses());
						o.addProperty("content", src.getContent());
						o.addProperty("remark", src.getRemark());
						o.addProperty("code", src.getCode());
						if(src.getPatrolSpecialFolw().getFj() != null){
							o.addProperty("fj",src.getPatrolSpecialFolw().getFj()==0?"未分解":src.getPatrolSpecialFolw().getFj()==1?"已分解":"空" );
						}
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}

	@RequestMapping(value = "/findDepartment", method = RequestMethod.POST)
	@ResponseBody
	public String findDepartment(String id,
			PatrolSpecialResolve patrolSpecialResolve,String did, String isid,String rid) throws Exception {
		String json = "";
		Map<String, Object> map = new HashMap<>();
		map.put("isid", isid);
		map.put("did", did);
		System.out.println(isid);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("rid", rid);
		List<PatrolSpecialResolveDetails> resolve = patrolSpecialResolveService.findResolveDetails(map);
		List<Department> list = patrolSpecialResolveService
				.selectDepartmentResultMap(map2);
		for (int i = 0; i < list.size(); i++) {
			for (int a = 0; a < resolve.size(); a++) {
				if (list.get(i)
						.getId()
						.equals(resolve.get(a).getDid())) {
					list.get(i).setIsdel(new Long(2));
				}
			}
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		System.out.println("+++++++++++++++++++++");
		System.out.println(jsonMap);
		gson = new GsonBuilder().registerTypeAdapter(Department.class,
				new JsonSerializer<Department>() {
					@Override
					public JsonElement serialize(Department src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("name", src.getName());
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr, HttpServletRequest request) throws Exception {
		gson = new Gson();
		PatrolSpecialResolve patrolSpecialResolve = gson.fromJson(jsonStr,
				PatrolSpecialResolve.class);
		//String id = patrolSpecialResolve.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		String result = "";
		
			patrolSpecialResolveService.deleteByIsid(patrolSpecialResolve.getIsid());
			
		
			UUID uuid = UUID.randomUUID();
			patrolSpecialResolve.setId(uuid.toString());
			patrolSpecialResolve.setCreater(user.getUserid());
			patrolSpecialResolve.setCreatetime(new Date());
			try {
				patrolSpecialResolveService
						.insertSelective(patrolSpecialResolve);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolSpecialResolveDetails> list = new ArrayList<PatrolSpecialResolveDetails>();
			for (PatrolSpecialResolveDetails details : patrolSpecialResolve
					.getListPatrolSpecialResolveDetails()) {
				details.setId(UUID.randomUUID().toString());
				details.setRid(patrolSpecialResolve.getId());
				list.add(details);
			}
			try {
				patrolSpecialResolveService.insertResolveDetail(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return result;
	}
	@RequestMapping(value = "/commit", method = RequestMethod.POST)
	@ResponseBody
	public String submit(String jsonStr, HttpServletRequest request) throws Exception {
		gson = new Gson();
		PatrolSpecialResolve patrolSpecialResolve = gson.fromJson(jsonStr,
				PatrolSpecialResolve.class);
		//String id = patrolSpecialResolve.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		String result = "";
		
		patrolSpecialResolveService.deleteByIsid(patrolSpecialResolve.getIsid());
		
			UUID uuid = UUID.randomUUID();
			patrolSpecialResolve.setId(uuid.toString());
			patrolSpecialResolve.setCreater(user.getUserid());
			patrolSpecialResolve.setCreatetime(new Date());
			try {
				patrolSpecialResolveService
						.insertSelective(patrolSpecialResolve);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<PatrolSpecialResolveDetails> list = new ArrayList<PatrolSpecialResolveDetails>();
			for (PatrolSpecialResolveDetails details : patrolSpecialResolve
					.getListPatrolSpecialResolveDetails()) {
				details.setId(UUID.randomUUID().toString());
				details.setRid(patrolSpecialResolve.getId());
				list.add(details);
			}
			try {
				patrolSpecialResolveService.insertResolveDetail(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		patrolspecialflowservice.resolve(patrolSpecialResolve.getIsid());
		return result;
	}
	@RequestMapping(value = "/findDetailsByRid", method = RequestMethod.POST)
	@ResponseBody
	public String findDetailsByRid(String rid) throws Exception {
		String result="";
		List<String> list=patrolspecialflowservice.findDetailsByRid(rid);
		result=JSON.toJSONString(list);
		return result;
	}
	/*@RequestMapping(value = "/findDetailsByRid2", method = RequestMethod.POST)
	@ResponseBody
	public String findDetailsByRid2(String rid) throws Exception {
		String json="";
		List<String> list=patrolspecialflowservice.findDetailsByRid(rid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Department.class,
				new JsonSerializer<Department>() {
					@Override
					public JsonElement serialize(Department src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("name", src.getName());
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}*/
}
