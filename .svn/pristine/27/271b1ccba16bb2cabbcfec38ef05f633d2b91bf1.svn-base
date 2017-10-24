package com.benqzl.controller.patrol;

import java.lang.reflect.Type;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.PatrolSpecialIssue;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolPlanService;
import com.benqzl.service.patrol.PatrolSpecialFolwService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @ClassName: PatrolPlanController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zy
 * 
 * 
 */
@Controller
@RequestMapping("/regularpatrolplan")
public class RegularPatrolPlanController extends BasicController {
	@Autowired
	private PatrolPlanService service;
	@Autowired
	private PatrolSpecialFolwService patrolspecialflowservice;
	@Autowired
	private ActivitiUnitService activitiUnitService;
	
	
	public RegularPatrolPlanController() {
		super(RegularPatrolPlanController.class);
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
		mv.setViewName("patrol/regularpatrolplan");
		return mv;
	}

	//管理处制定计划
	  @RequestMapping(value = "/patrolplanlist", method = RequestMethod.POST)
		@ResponseBody
		public String patrolSpecialIssueList(int page, int rows, String username,
				String starttime, String endtime,String type, String typeDate, HttpSession session) {
			String strJson="";
			page = (page == 0 ? 1 : page);
			rows = (rows == 0 ? 15 : rows);
			int start = (page - 1) * rows;
			rows = start + rows;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("p1", start);
			map.put("p2", rows);
		    
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
			
			List<PatrolSpecialIssue> patrolSpecialIssue=null;
			    patrolSpecialIssue=service.findByPagere(map);
			    int total = service.pageCountre(map);
			
		    Map<String, Object> jsonMap = new HashMap<String, Object>();
			
			jsonMap.put("total", total);
			jsonMap.put("rows",patrolSpecialIssue);
		
			  
			gson = new GsonBuilder().registerTypeAdapter(PatrolSpecialIssue.class,
					new JsonSerializer<PatrolSpecialIssue>() {
						@Override
						public JsonElement serialize(PatrolSpecialIssue patrolSpecialIssue, Type typeOfSrc,
								JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							o.addProperty("id", patrolSpecialIssue.getId());
							o.addProperty("creater", patrolSpecialIssue.getCreater());
							o.addProperty("username", patrolSpecialIssue.getUser().getEmployee().getName());
							o.addProperty("createtime", datetimeFormat.format(patrolSpecialIssue.getCreatetime()));
							o.addProperty("classes", patrolSpecialIssue.getClasses()==0?"汛前":patrolSpecialIssue.getClasses()==1?"汛期":patrolSpecialIssue.getClasses()==2?"讯后":"特别检查");
							o.addProperty("content", patrolSpecialIssue.getContent());
							o.addProperty("remark", patrolSpecialIssue.getRemark());
							o.addProperty("code", patrolSpecialIssue.getCode());
							if(patrolSpecialIssue.getPatrolSpecialFolw() != null){
								o.addProperty("qf",patrolSpecialIssue.getPatrolSpecialFolw().getQf()==0?"未签发":patrolSpecialIssue.getPatrolSpecialFolw().getQf()==1?"已签发":"kong" );
							}
							return o;
						}
					}).create();
			
			strJson=gson.toJson(jsonMap);
			return strJson;
	  }
	  @RequestMapping(value = "regularpatrolplaninfo", method = RequestMethod.GET)
		public ModelAndView regularpatrolplanInfo(String id) throws Exception {
			ModelAndView mv = new ModelAndView();
			//修改页面
			if(!id.equals("0")){
				
				PatrolSpecialIssue patrolSpecialIssue=service.findPatrolSpecialIssueByid(id);
				mv.addObject("patrolSpecialIssue",patrolSpecialIssue);
				mv.addObject("code", patrolSpecialIssue.getCode());
			}else{
				String code = activitiUnitService.selectMaxCode("FS");
			    mv.addObject("code",code);
			}
			mv.setViewName("patrol/regularpatrolplaninfo");
			return mv;
		}
	  
	  @RequestMapping(value = "regularpatrolplaninfoheld", method = RequestMethod.GET)
		public ModelAndView patrolplanInfoheld(String id) throws Exception {
			ModelAndView mv = new ModelAndView();
			//修改页面
			if(!id.equals("0")){
				PatrolSpecialIssue patrolSpecialIssue=service.findPatrolSpecialIssueByid(id);
				mv.addObject("patrolSpecialIssue",patrolSpecialIssue);
				mv.addObject("code", patrolSpecialIssue.getCode());
			}
			mv.setViewName("patrol/regularpatrolplaninfoheld");
			return mv;
		}
	  @RequestMapping(value="/save" , method=RequestMethod.POST)
		@ResponseBody
		public String save(String json, HttpServletRequest request) {
			
			User user = (User) request.getSession().getAttribute("loginUser");
		    //String userid= user.getUserid();
			//System.out.println(user.getUserid());
		/*  System.out.println(user.getUsername());
			Map<String, Object> mapuser = new HashMap<String, Object>();
			mapuser.put("userid", userid);
			String username=service.selectUser(mapuser);
			System.out.println(username);
		*/
			String result="";		
			gson=new GsonBuilder().serializeNulls().create();  //转化成json，可为null
			PatrolSpecialIssue patrolSpecialIssue=gson.fromJson(json, PatrolSpecialIssue.class);
			//新增
			if(patrolSpecialIssue.getId().equals("0")){
				UUID uuid=UUID.randomUUID();
				patrolSpecialIssue.setId(uuid.toString());
				//patrolSpecialIssue.setCreater(user.getUserid());
//				patrolSpecialIssue.setCreatetime(new Date());
		
				
				try {
					patrolSpecialIssue.setCreater(user.getUserid());
					patrolSpecialIssue.setCreatetime(new Date());
					patrolSpecialIssue.setCode(patrolSpecialIssue.getCode());
					service.insert(patrolSpecialIssue);
					
					patrolspecialflowservice.issue(patrolSpecialIssue.getId());
					
				
					result="{'result':true}";
				} catch (Exception e) {
					e.printStackTrace();
					result= "{'result':false,'msg':'添加失败！'}";
				}
			//编辑
			}else{			
				try {
					patrolSpecialIssue.setCreater(user.getUserid());
					patrolSpecialIssue.setCreatetime(new Date());
					 service.update(patrolSpecialIssue);
					result="{'result':true}";
				} catch (Exception e) {
					e.printStackTrace();
					result= "{'result':false,'msg':'编辑失败！'}";
				}
			}
			return result;
		}
		@RequestMapping(value="/delete" , method=RequestMethod.POST)
		@ResponseBody
		public String delete(String id) {
			System.out.println("删除"+id);
			String result="";
			try {
				patrolspecialflowservice.deleteflow(id);
				service.distory(id);
				result="{'result':true}";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				result= "{'result':false,'msg':'編輯失敗！'}";
			}
			return result;
		}
		
		  @RequestMapping(value="/commit" , method=RequestMethod.POST)
			@ResponseBody
			public String commit(String json, HttpServletRequest request,String id) {
			  
			  System.out.println("COMMIT"+id);
				
				User user = (User) request.getSession().getAttribute("loginUser");
			
				String result="";		
				gson=new GsonBuilder().serializeNulls().create();  //转化成json，可为null
				PatrolSpecialIssue patrolSpecialIssue=gson.fromJson(json, PatrolSpecialIssue.class);
				//新增
				if(patrolSpecialIssue.getId().equals("0")){
					UUID uuid=UUID.randomUUID();
					patrolSpecialIssue.setId(uuid.toString());
					//patrolSpecialIssue.setCreater(user.getUserid());
                  //patrolSpecialIssue.setCreatetime(new Date());
                   System.out.println(uuid.toString());
					try {
						patrolSpecialIssue.setCreater(user.getUserid());
						patrolSpecialIssue.setCreatetime(new Date());
						patrolSpecialIssue.setCode(patrolSpecialIssue.getCode());
						service.insert(patrolSpecialIssue);
						System.out.println(patrolSpecialIssue.getId());
						//改变状态
						patrolspecialflowservice.issue(patrolSpecialIssue.getId());
						patrolspecialflowservice.update(patrolSpecialIssue.getId());
						 result="{'result':true}";
					} catch (Exception e) {
						e.printStackTrace();
						
						result= "{'result':false,'msg':'添加失败！'}";
					}
				}else{
					try {						
						 service.update(patrolSpecialIssue);
						 patrolspecialflowservice.update(patrolSpecialIssue.getId());
						 result="{'result':true}";
					} catch (Exception e) {						
						result= "{'result':false,'msg':'编辑失败！'}";
						e.printStackTrace();
					}
				}
					
				return result;
		
		  }
	  
}

