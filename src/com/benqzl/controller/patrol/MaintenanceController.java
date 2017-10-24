package com.benqzl.controller.patrol;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceDetails;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.MaintenanceService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;



/** 
* @ClassName: MaintenanceController 
* @Description: MaintenanceController(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2016年2月20日 上午10:14:31 
*  
*/
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController extends BasicController {

	public MaintenanceController() {
		super(MaintenanceController.class);
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
		mv.setViewName("patrol/maintenance");
		return mv;
	}
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	/**
	 * 
	 * @Title: pageBind
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String pageBind(int page, int rows, String code,
			String projectname, String units,String begintime, String endtime,HttpSession session) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (code == null || code.trim().equals("")) {
			map.put("code", null);
		} else {
			map.put("code", code);
		}
		if (projectname == null || projectname.trim().equals("")) {
			map.put("projectname", null);
		} else {
			map.put("projectname", projectname);
		}
		if (units == null || units.trim().equals("")) {
			map.put("units", null);
		} else {
			map.put("units", units);
		}
		try {
			if(begintime != null && endtime.length() > 0){				
				map.put("begintime", sdf.parse(begintime));				
			}
			if(endtime != null && endtime.length() > 0){
				map.put("endtime", sdf.parse(endtime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Maintenance> list =maintenanceService.findByPage(map);
		int total = maintenanceService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Maintenance.class,
				new JsonSerializer<Maintenance>() {

					@Override
					public JsonElement serialize(Maintenance src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("code", src.getCode());
						if(src.getDepartment() != null){
							o.addProperty("departmentname", src.getDepartment().getName());
						}
						if(src.getApply() != null){
							o.addProperty("applyname", src.getApply().getUsername());
						}
						if(src.getApplydate() != null){
							o.addProperty("applydate", datetimeFormat.format(src.getApplydate()));
						}
						o.addProperty("projectname", src.getProjectname());
						o.addProperty("constructionunits", src.getConstructionunits());
						o.addProperty("state", src.getState());
						if(src.getState() == 0){
							o.addProperty("statename", "保存");
						}else if(src.getState() == 1){
							o.addProperty("statename", "申请未通过");
						}else if(src.getState() == 2){
							o.addProperty("statename", "已提交");
						}else if(src.getState() == 3){
							o.addProperty("statename", "已复核");
						}else if(src.getState() == 4){
							o.addProperty("statename", "已审核");
						}else if(src.getState() == 5){
							o.addProperty("statename", "已审批");
						}						
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this maintenance list strJson is " + strJson);
		return strJson;
	}
	
	@Autowired
	private ActivitiUnitService activitiUnitService;
	
	/** 
	* @Title: maintenanceInfo 
	* @Description: info(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "maintenanceInfo", method = RequestMethod.GET)
	public ModelAndView maintenanceInfo(String id,String type){
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
			Maintenance mt = maintenanceService.selectByPK(id);
			mv.addObject("mt",mt);
			mv.addObject("applydate",datetimeFormat.format(mt.getApplydate()));
			if(mt.getStep() != null){
				if(mt.getStep() == 3){
					mv.addObject("step","复核");
				}else if(mt.getStep() == 4){
					mv.addObject("step","审核");
				}else if(mt.getStep() == 5){
					mv.addObject("step","审批");
				}
			}
		}else {
			String code = activitiUnitService.selectMaxCode("MT");
			Maintenance mt = new Maintenance();
			mt.setCode(code);
			mv.addObject("mt",mt);
		}
		mv.addObject("type",type);
		mv.setViewName("patrol/maintenanceInfo");
		return mv;
	}
	
	/** 
	* @Title: detailsList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="detailsList",method = RequestMethod.POST)
	@ResponseBody
	public String detailsList(String id){
		List<MaintenanceDetails> list = maintenanceService.selectByMid(id);
		gson = new GsonBuilder().registerTypeAdapter(MaintenanceDetails.class,
				new JsonSerializer<MaintenanceDetails>() {

					@Override
					public JsonElement serialize(MaintenanceDetails src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("maintenanceid", src.getMaintenanceid());					
						o.addProperty("itemname", src.getItemname());
						o.addProperty("materials", src.getMaterials());
						o.addProperty("unit", src.getUnit());
						o.addProperty("memo", src.getMemo());						
						o.addProperty("quantity", src.getQuantity());
						o.addProperty("price", src.getPrice());
						o.addProperty("totalamount", src.getTotalamount());						
						return o;
					}
				}).create();
		String strJson = gson.toJson(list);
		return strJson;
	}
	
	/** 
	* @Title: save 
	* @Description: save(这里用一句话描述这个方法的作用) 
	* @param @param jsonStr
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr, HttpServletRequest request) {
		gson = new Gson();
		Maintenance model = gson.fromJson(jsonStr, Maintenance.class);
		String id = model.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		// 新增
		if (id.equals("0")) {
			model.setId(UUID.randomUUID().toString());
			model.setCreaterid(user.getUserid());
			model.setCreatetime(new Date());
			try {
				maintenanceService.insertSelective(model);
				List<MaintenanceDetails> list = new ArrayList<MaintenanceDetails>();
				for(MaintenanceDetails md : model.getDetails()){
					md.setId(UUID.randomUUID().toString());
					md.setMaintenanceid(model.getId());
					list.add(md);
				}
				maintenanceService.insertMaintenanceDtails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				maintenanceService.updateMaintenance(model);
				maintenanceService.deleteByMid(model.getId());
				List<MaintenanceDetails> list = new ArrayList<MaintenanceDetails>();
				for(MaintenanceDetails md : model.getDetails()){
					md.setId(UUID.randomUUID().toString());
					md.setMaintenanceid(model.getId());
					list.add(md);
				}
				maintenanceService.insertMaintenanceDtails(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "{'result':true}";
	}
	
	/** 
	* @Title: commit 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param jsonStr
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="commit",method = RequestMethod.POST)
	@ResponseBody
	public String commit(String jsonStr, String type,HttpServletRequest request) {
		System.out.println(jsonStr);
		gson = new Gson();
		Maintenance model = gson.fromJson(jsonStr, Maintenance.class);
		String id = model.getId();
		User user = (User) request.getSession().getAttribute("loginUser");
		// 新增
		if (id.equals("0")) {
			id=UUID.randomUUID().toString();
			model.setId(id);
			model.setCreaterid(user.getUserid());
			model.setCreatetime(new Date());
			model.setState(new Long(2));
			try {
				maintenanceService.insertSelective(model);
				List<MaintenanceDetails> list = new ArrayList<MaintenanceDetails>();
				for(MaintenanceDetails md : model.getDetails()){
					md.setId(UUID.randomUUID().toString());
					md.setMaintenanceid(model.getId());
					list.add(md);
				}
				maintenanceService.insertMaintenanceDtails(list);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("maintainId", id);
				map.put("inoutUser", user.getUserid());
				
				if(type.equals("0")){
					activitiUnitService.statrtByKey("maintain", map);						
				}				
				activitiUnitService.completeTaskByPerson("petitioner",user.getUserid(), id);
				activitiUnitService.messagePush("petitioner", user.getUserid(),id);				
				} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			try {
				model.setState(new Long(2));
				maintenanceService.updateMaintenance(model);
				maintenanceService.deleteByMid(model.getId());
				List<MaintenanceDetails> list = new ArrayList<MaintenanceDetails>();
				for(MaintenanceDetails md : model.getDetails()){
					md.setId(UUID.randomUUID().toString());
					md.setMaintenanceid(model.getId());
					list.add(md);
				}
				maintenanceService.insertMaintenanceDtails(list);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("maintainId", id);
				map.put("inoutUser", user.getUserid());
				try {
					if(type.equals("0")){
						activitiUnitService.statrtByKey("maintain", map);						
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
				activitiUnitService.completeTaskByPerson("petitioner",user.getUserid(), id);
				activitiUnitService.messagePush("petitioner", user.getUserid(),id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "{'result':true}";
	}

}
