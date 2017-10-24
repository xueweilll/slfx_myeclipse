package com.benqzl.controller.dispatch.water;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.water.TrWarnlog;
import com.benqzl.service.water.WarningService;
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
@RequestMapping("/warning")
public class WarningController extends BasicController {

	public WarningController() {
		super(WarningController.class);
	}
	@Resource
	private WarningService warningservice;
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
		mv.setViewName("water/warning");
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
	@RequestMapping(value = "/warningList", method = RequestMethod.POST)
	@ResponseBody
	public String unitList(int page,int rows,String stationname,String begin,String end) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(stationname!=null && !stationname.equals("0")){
			map.put("stationname", stationname);
		}
		if(begin!=null && !begin.trim().equals("")){
			map.put("begin", begin);
		}
		if(end!=null && !end.trim().equals("")){
			map.put("end", end);
		}
		List<TrWarnlog> list=warningservice.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = warningservice.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);		
		gson = new GsonBuilder().registerTypeAdapter(TrWarnlog.class,
				new JsonSerializer<TrWarnlog>() {
					@Override
					public JsonElement serialize(TrWarnlog tw, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", tw.getfId());
						o.addProperty("stationname", tw.getStation().getName());
						o.addProperty("fWarncode", tw.getfCaption());
						o.addProperty("fWarntime",datetimeFormat.format(tw.getfWarntime()));
						o.addProperty("fFreetime", datetimeFormat.format(tw.getfFreetime()));
						String state=tw.getfWarnstate();
						if(state.equals("0")){
							o.addProperty("fWarnstate","解除");
						}else if(state.equals("1")){
							o.addProperty("fWarnstate","报警");
						}
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
	@RequestMapping(value = "warningInfo", method = RequestMethod.GET)
	public ModelAndView warningInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
//			Unit unit=warningservice.selectByPrimaryKey(id);
//			mv.addObject("unit",unit);
		}
		mv.setViewName("water/warningInfo");
		return mv;
	}
	/** 
	* @Title: sid 
	* @Description: TODO(获得站点信息) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/station", method = RequestMethod.POST)
	@ResponseBody
	public String station() {
		String json="";
		List<Station> list=warningservice.findStation();
		json = JSON.toJSONString(list);
		json=json.substring(0,1)+"{\"code\":\"0\",\"name\":\"--全部--\"},"+json.substring(1,json.length());
		return json;
	}
//	@RequestMapping(value = "/sid2", method = RequestMethod.POST)
//	@ResponseBody
//	public String sid2() {
//		String json="";
//		List<Station> list=warningservice.selectStation();
//		json = JSON.toJSONString(list);
//		return json;
//	}
	/** 
	* @Title: codeOnlyOne 
	* @Description: TODO(code唯一验证)
	* @param @param code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
//	@RequestMapping(value = "/codeOnlyOne", method = RequestMethod.POST)
//	@ResponseBody
//	public String codeOnlyOne(String code,String id){
//		
//		String result="";
//		Map<String ,String> map=new HashMap<String,String>();
//		map.put("code", code);
//		if(id!=null){
//			map.put("id", id);
//		}
//		result=warningservice.selectCode(map);
//		if(result.equals("0")){
//			return "{\"msg\":false}";
//		}else{
//			return "{\"msg\":true}";
//		}
////		if(result.equals("0")){
////			return "false";
////		}else{
////			return "true";
////		}		
//	}
	
//	@RequestMapping(value = "/nameOnlyOne", method = RequestMethod.POST)
//	@ResponseBody
//	public String nameOnlyOne(String name,String id){
//		String result="";
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name", name);
//		if(id!=null){
//			map.put("id", id);
//		}
//		result=warningservice.nameOnlyOne(map);
//		if(result.equals("0")){
//			return "false";
//		}else{
//			return "true";
//		}		
//	}
	/** 
	* @Title: save 
	* @Description: TODO(执行新增机组或编辑机组功能) 
	* @param @param json
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
//	@RequestMapping(value="/save" , method=RequestMethod.POST)
//	@ResponseBody
//	public String save(String json) {
//		String result="";
//		
//		gson=new Gson();
//		Unit unit=gson.fromJson(json, Unit.class);
//		if(unit.getId().equals("0")){
//			UUID uuid=UUID.randomUUID();
//			unit.setId(uuid.toString());
//			unit.setCreatetime(new Date());
//			unit.setEdittime(new Date());
//		    try {
//		    	warningservice.insert(unit);
//		    	result="{'result':true}";
//			} catch (Exception e) {
//				e.printStackTrace();
//				result= "{'result':false,'msg':'添加失敗！'}";
//			}		
//		}else{
//			unit.setEdittime(new Date());
//			
//			try {
//				warningservice.updateByPrimaryKeySelective(unit);
//				result="{'result':true}";
//			} catch (Exception e) {
//				e.printStackTrace();
//				result= "{'result':false,'msg':'編輯失敗！'}";
//			}
//		}
//		return result;
//	}
	/** 
	* @Title: unitDelete 
	* @Description: TODO(执行逻辑删除机组) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
//	@RequestMapping(value="/unitDelete" , method=RequestMethod.POST)
//	@ResponseBody
//	public String unitDelete(String id) {
//		String result="";
//		try {
//			warningservice.deleteByPrimaryKey(id);
//			result="{'result':true}";
//		} catch (Exception e) {
//			e.printStackTrace();
//			result="{'result':false,'msg':'删除失败！'}";
//		}
//		return result;
//	}
}
