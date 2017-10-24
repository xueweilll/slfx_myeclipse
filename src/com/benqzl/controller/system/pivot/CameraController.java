package com.benqzl.controller.system.pivot;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Camera;
import com.benqzl.pojo.system.Station;
import com.benqzl.service.system.CameraService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;



/** 
* @ClassName: CameraController 
* @Description: TODO(摄像头信息维护) 
* @author gyl 
* @date 2015年12月9日 下午4:52:30 
*  
*/

@Controller
@RequestMapping("/camera")
public class CameraController extends BasicController {

	public CameraController() {
		super(CameraController.class);
	}
	@Resource
	private CameraService cameraservice;
	/** 
	* @Title: index 
	* @Description: TODO(摄像头页面)
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("system/camera");
		return mv;
	}

	/** 
	* @Title: cameraList 
	* @Description: TODO(获得所有摄像头信息)
	* @param @param page
	* @param @param rows
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/cameraList", method = RequestMethod.POST)
	@ResponseBody
	public String cameraList(int page,int rows,String sn) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", start+"");
		map.put("p2", rows+"");
		if(sn !=null){
			Camera camera=new Gson().fromJson(sn, Camera.class);
			if(!camera.getSid().trim().equals("0") && !camera.getSid().trim().equals("")){
				map.put("sid",camera.getSid().trim());				
			}
			if(!camera.getName().trim().equals("")){
				map.put("name", camera.getName().trim());				
			}
		}
		List<Camera> list=cameraservice.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = cameraservice.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(Camera.class,
				new JsonSerializer<Camera>() {
					@Override
					public JsonElement serialize(Camera Camera, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", Camera.getId());
						o.addProperty("sid", Camera.getSid());
						o.addProperty("code", Camera.getCode());
						o.addProperty("name", Camera.getName());
						o.addProperty("url", Camera.getUrl());
						o.addProperty("channel", Camera.getChannel()==null?"":Camera.getChannel().toString());
						o.addProperty("createtime", datetimeFormat.format(Camera.getCreatetime()));
						o.addProperty("edittime", datetimeFormat.format(Camera.getEdittime()));
						o.addProperty("isdel", Camera.getIsdel());
						o.addProperty("station_name", Camera.getStation().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}
	
	/** 
	* @Title: cameraInfo 
	* @Description: TODO(跳转至新增摄像头或编辑摄像头页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "cameraInfo", method = RequestMethod.GET)
	public ModelAndView cameraInfo(String id) {
		ModelAndView mv = new ModelAndView();	
		if(!id.equals("0")){
			Camera camera=cameraservice.selectByPrimaryKey(id);
			mv.addObject("camera",camera);
		}
		mv.setViewName("system/cameraInfo");
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
		List<Station> list=cameraservice.selectStation();
		json = JSON.toJSONString(list);
		return json;
	}
	
	/** 
	* @Title: codeOnlyOne 
	* @Description: TODO(code唯一验证) 
	* @param @param code
	* @param @param id
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
		result=cameraservice.selectCode(map);
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
	
	/** 
	* @Title: cameraOnlyOne 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param camera
	* @param @param id
	* @param @param sid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/cameraOnlyOne", method = RequestMethod.POST)
	@ResponseBody
	public String cameraOnlyOne(String camera,String id,String sid){
		String result="";
		Map<String,String> map=new HashMap<String,String>();
		map.put("camera", camera);
		if(id!=null){
			map.put("id", id);
		}
		map.put("sid", sid);
		result=cameraservice.cameraOnlyOne(map);
		if(result.equals("0")){
			return "false";
		}else{
			return "true";
		}
	}
	/** 
	* @Title: save 
	* @Description: TODO(执行新增摄像头或编辑摄像头功能) 
	* @param @param json
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	@ResponseBody
	public String save(String json) {
		String result="";
		System.out.println(json);
		gson=new Gson();
		Camera camera=gson.fromJson(json, Camera.class);
		if(camera.getId().equals("0")){
			UUID uuid=UUID.randomUUID();
			camera.setId(uuid.toString());
			camera.setCreatetime(new Date());
			camera.setEdittime(new Date());
		    try {
		    	cameraservice.insertSelective(camera);
		    	result="{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result= "{'result':false,'msg':'添加失敗！'}";
			}		
		}else{
			camera.setEdittime(new Date());
			try {
				cameraservice.updateByPrimaryKeySelective(camera);
				result="{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result= "{'result':false,'msg':'編輯失敗！'}";
			}
		}
		return result;
	}
	/** 
	* @Title: cameraDelete 
	* @Description: TODO(执行逻辑删除摄像头) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/cameraDelete" , method=RequestMethod.POST)
	@ResponseBody
	public String cameraDelete(String id) {
		String result="";
		try {
			cameraservice.deleteByPrimaryKey(id);
			result="{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result="{'result':false,'msg':'删除失败！'}";
		}
		return result;
	}
}
