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

import com.alibaba.fastjson.JSON;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.service.system.StationService;
import com.benqzl.service.system.UnitService;
import com.benqzl.socket.MessageQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: UnitController 
* @Description: TODO(机组维护) 
* @author gyl 
* @date 2015年12月9日 下午4:58:44 
*  
*/
@Controller
@RequestMapping("/unit")
public class UnitController extends BasicController {

	public UnitController() {
		super(UnitController.class);
	}
	@Resource
	private UnitService unitservice;
	/** 
	* @Title: index 
	* @Description: TODO(机组信息页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/unit");
		return mv;
	}
	/** 
	* @Title: unitList 
	* @Description: TODO(获得所有机组信息) 
	* @param @param page
	* @param @param rows
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/unitList", method = RequestMethod.POST)
	@ResponseBody
	public String unitList(int page,int rows,String sn) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(sn!=null && sn.length()>0){	
			gson=new Gson();
			Unit unit=gson.fromJson(sn, Unit.class);
			if(!unit.getSid().trim().equals("") && !unit.getSid().trim().equals("0")){
				map.put("sid", unit.getSid().trim());
			}
			if(!unit.getName().trim().equals("")){
				map.put("name", unit.getName().trim());
			}
		}
		List<Unit> list=unitservice.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = unitservice.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);		
		gson = new GsonBuilder().registerTypeAdapter(Unit.class,
				new JsonSerializer<Unit>() {
					@Override
					public JsonElement serialize(Unit unit, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", unit.getId());
						o.addProperty("sid", unit.getSid());
						o.addProperty("code", unit.getCode());
						o.addProperty("name", unit.getName());
						o.addProperty("types", unit.getTypes()==0?"贯流":"轴流");
						o.addProperty("power", unit.getPower());
						o.addProperty("motertype", unit.getMotertype());
						o.addProperty("feature", unit.getFeature()==0?"引水":"排涝");
						o.addProperty("designdischarge", unit.getDesigndischarge());
						o.addProperty("createtime", datetimeFormat.format(unit.getCreatetime()));
						o.addProperty("edittime", datetimeFormat.format(unit.getEdittime()));
						o.addProperty("isdel", unit.getIsdel());
						o.addProperty("station_name", unit.getStation().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	/** 
	* @Title: unitInfo 
	* @Description: TODO(跳转至新增机组或编辑机组页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "unitInfo", method = RequestMethod.GET)
	public ModelAndView unitInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
			Unit unit=unitservice.selectByPrimaryKey(id);
			mv.addObject("unit",unit);
		}
		mv.setViewName("system/unitInfo");
		return mv;
	}
	/** 
	* @Title: sid 
	* @Description: TODO(获得站点信息) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/sid", method = RequestMethod.POST)
	@ResponseBody
	public String sid() {
		String json="";
		List<Station> list=unitservice.selectStation();
		json = JSON.toJSONString(list);
		json=json.substring(0,1)+"{\"id\":\"0\",\"name\":\"--全部--\"},"+json.substring(1,json.length());
		return json;
	}
	@RequestMapping(value = "/sid2", method = RequestMethod.POST)
	@ResponseBody
	public String sid2() {
		String json="";
		List<Station> list=unitservice.selectStation();
		json = JSON.toJSONString(list);
		return json;
	}
	/** 
	* @Title: codeOnlyOne 
	* @Description: TODO(code唯一验证)
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/codeOnlyOne", method = RequestMethod.POST)
	@ResponseBody
	public String codeOnlyOne(String code,String id,String sid){
		
		String result="";
		Map<String ,String> map=new HashMap<String,String>();
		map.put("code", code);
		if(id!=null){
			map.put("id", id);
		}
		map.put("sid", sid);
		result=unitservice.selectCode(map);
		if(result.equals("0")){
			return "{\"msg\":false}";
		}else{
			return "{\"msg\":true}";
		}
//		if(result.equals("0")){
//			return "false";
//		}else{
//			return "true";
//		}		
	}
	
	@RequestMapping(value = "/nameOnlyOne", method = RequestMethod.POST)
	@ResponseBody
	public String nameOnlyOne(String name,String id,String sid){
		String result="";
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", name);
		if(id!=null){
			map.put("id", id);
		}
		map.put("sid", sid);
		result=unitservice.nameOnlyOne(map);
		if(result.equals("0")){
			return "false";
		}else{
			return "true";
		}		
	}
	
	@Resource
	MessageQueue messageQueue;
	
	@Autowired
	private StationService stationService;
	
	/** 
	* @Title: save 
	* @Description: TODO(执行新增机组或编辑机组功能) 
	* @param @param json
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	@ResponseBody
	public String save(String json) {
		String result="";
		
		gson=new Gson();
		Unit unit=gson.fromJson(json, Unit.class);
		if(unit.getId().equals("0")){
			UUID uuid=UUID.randomUUID();
			unit.setId(uuid.toString());
			unit.setCreatetime(new Date());
			unit.setEdittime(new Date());
		    try {
		    	Station s= stationService.selectByPrimaryKey(unit.getSid());
		    	unit.setStation(s);
		    	messageQueue.unitMap.put(unit.getId(), unit);
		    	unitservice.insert(unit);
		    	result="{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result= "{'result':false,'msg':'添加失敗！'}";
			}		
		}else{
			unit.setEdittime(new Date());
			
			try {
				Station s= stationService.selectByPrimaryKey(unit.getSid());
		    	unit.setStation(s);
				messageQueue.unitMap.remove(unit.getId());
				messageQueue.unitMap.put(unit.getId(), unit);
				unitservice.updateByPrimaryKeySelective(unit);
				result="{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result= "{'result':false,'msg':'編輯失敗！'}";
			}
		}
		return result;
	}
	/** 
	* @Title: unitDelete 
	* @Description: TODO(执行逻辑删除机组) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/unitDelete" , method=RequestMethod.POST)
	@ResponseBody
	public String unitDelete(String id) {
		String result="";
		try {
			messageQueue.unitMap.remove(id);
			unitservice.deleteByPrimaryKey(id);
			result="{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result="{'result':false,'msg':'删除失败！'}";
		}
		return result;
	}
}
