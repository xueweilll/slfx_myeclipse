package com.benqzl.controller.system.pivot;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.service.system.GateService;
import com.benqzl.service.system.StationService;
import com.benqzl.socket.MessageQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: GateController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2015年12月9日 下午4:48:32 
*  
*/
@Controller
@RequestMapping(value = "/gate")
public class GateController extends BasicController {

	public GateController() {
		super(GateController.class);
	}

	/**
	 * @Title: index
	 * @Description: index界面(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/gate");
		return mv;
	}
	

	@Autowired
	private GateService gateService;

	/** 
	* @Title: PageBind 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param page
	* @param @param rows
	* @param @param conditions
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/gateList", method = RequestMethod.POST)
	@ResponseBody
	public String PageBind(int page, int rows,String conditions) {
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
			Gate gate = gson.fromJson(conditions, Gate.class);
			if(gate.getCode() != null && gate.getCode().length()>0)
			{
				map.put("code", gate.getCode());
			}
			if(gate.getName() != null && gate.getName().length()>0)
			{
				map.put("name", gate.getName());
			}
		}
		List<Gate> list = gateService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = gateService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Gate.class,
				new JsonSerializer<Gate>() {

					@Override
					public JsonElement serialize(Gate src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("Code", src.getCode());
						o.addProperty("Name", src.getName());
						o.addProperty("OnOffWay",
								src.getOnoffway() ==0 ? "液压" : "卷扬");
						o.addProperty("Createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("Edittime",
								datetimeFormat.format(src.getEdittime()));
						o.addProperty("Sname", src.getStation().getName());
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		logger.info("this station list strJson is " + json);
		return json;
	}
	
	@RequestMapping(value="gateInfo",method = RequestMethod.GET)
	public ModelAndView gateInfo(String id)
	{
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
			Gate gate = gateService.selectByPrimaryKey(id);
			mv.addObject("gate", gate);
		}
		mv.setViewName("system/gateInfo");
		return mv;
	}

	@Resource
	MessageQueue messageQueue;
	
	@Autowired
	private StationService stationService;

	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr) {
		String result = "";
		gson = new Gson();
		Gate gate = gson.fromJson(jsonStr, Gate.class);
		// 判断id=0；
		if (gate.getId().equals("0")) {

			UUID uuid = UUID.randomUUID();
			gate.setId(uuid.toString());
			gate.setCreatetime(new Date());
			gate.setEdittime(new Date());
			gate.setIsdel(new Long(0));
			try {
				Station s= stationService.selectByPrimaryKey(gate.getSid());
				gate.setStation(s);
				messageQueue.gateMap.put(gate.getId(), gate);
				gateService.insert(gate);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}

		} else {
			try {
				gate.setEdittime(new Date());
				Station s= stationService.selectByPrimaryKey(gate.getSid());
				gate.setStation(s);
				messageQueue.gateMap.put(gate.getId(), gate);
				gateService.updateByPrimaryKeySelective(gate);
				result = "{'result':true}";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}
		}
		return result;
	}
	
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public String gateDelete(String id)
	{
		String result="";
		if (id != null && id !="")
		{
			try
			{
				messageQueue.gateMap.remove(id);
				gateService.deleteIsdel(id);
				result= "{'result':true}";
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				result= "{'result':false,'msg':'删除失敗！'}";
			}
		}
		return result;
	}
	
	/** 
	* @Title: exsitCode 
	* @Description: code是否存在(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	@RequestMapping(value="exsitCode",method = RequestMethod.GET)
	@ResponseBody
	public String exsitCode(String code)
	{
		String result="";
		int count = gateService.exsitCode(code);
		if(count >0)
		{
			result= "{'result':false}";
			System.out.println(result);
			return result;
		}
		else
		{
			result= "{'result':true}";
			System.out.println(result);
			return result;
		}
	}
	
	@RequestMapping(value = "/nameOnlyOne", method = RequestMethod.POST)
	@ResponseBody
	public String nameOnlyOne(String name,String id,String sid){
		String result="";
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", name);
		if(id !=null){
			map.put("id", id);
		}
		map.put("sid", sid);
		result=gateService.nameOnlyOne(map);
		if(result.equals("0")){
			return "false";
		}else{
			return "true";
		}		
	}
	
	@RequestMapping(value = "/codeOnlyOne", method = RequestMethod.POST)
	@ResponseBody
	public String codeOnlyOne(String code,String id,String sid){
		String result="";
		Map<String,String> map=new HashMap<String,String>();
		map.put("code", code);
		if(id !=null){
			map.put("id", id);
		}
		map.put("sid", sid);
		result=gateService.codeOnlyOne(map);
		if(result.equals("0")){
			return "false";
		}else{
			return "true";
		}		
	}
	
}
