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

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Camera;
import com.benqzl.pojo.system.Station;
import com.benqzl.service.system.CameraService;
import com.benqzl.service.system.StationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: CurrentMonitorsController 
* @Description: 实时监控
* @author xw 
* @date 2016年2月2日 上午9:16:05 
*  
*/
@Controller
@RequestMapping("/currentMonitorsController")
public class CurrentMonitorsController extends BasicController{

	public CurrentMonitorsController() {
		super(CurrentMonitorsController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(String code) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("code",code);
		mv.setViewName("water/currentMonitors");
		return mv;
	}
	
	@RequestMapping(value = "current", method = RequestMethod.GET)
	public ModelAndView current(String code) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("code",code);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", code);
	    Station station=stationService.findStationInformation(map);
	    mv.addObject("station",station);	    
		mv.setViewName("water/current");
		return mv;
	}
	
	@Resource
	private StationService stationService;
	
	@Resource
	private CameraService cameraService;
	
	@RequestMapping(value="stationList", method = RequestMethod.POST)
	@ResponseBody
	public String stationList(String code){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(code != null && !code.equals(""))
		map.put("code", code);
		List<Station> list = stationService.selectBYCode(map);
		Gson gson = new GsonBuilder().registerTypeAdapter(Station.class,
				new JsonSerializer<Station>() {
					@Override
					public JsonElement serialize(Station src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("text", src.getName());
						String attributes="{\"NVRUrl\":\""+ src.getNvrurl()+"\",\"NVRUsername\":\""+src.getNvrusername()+
								"\",\"NVRPassword\":\""+src.getNvrpassword()+"\",\"NVRProt\":\""+src.getNvrprot()+"\"}";
						o.addProperty("attributes", attributes);
						return o;
					}
				}).create();		
		return gson.toJson(list);
	}
	
	@RequestMapping(value="cameraList", method = RequestMethod.POST)
	@ResponseBody
	public String cameraList(String sid){
		List<Camera> list = cameraService.selectByFk(sid);
		Gson gson = new GsonBuilder().registerTypeAdapter(Camera.class,
				new JsonSerializer<Camera>() {
					@Override
					public JsonElement serialize(Camera src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("text", src.getName());		
						String attributes="{\"channel\":\""+ src.getChannel()+"\"}";
						o.addProperty("attributes", attributes);
						return o;
					}
				}).create();		
		return gson.toJson(list);
	}
	
	@RequestMapping(value="cameraListSecond", method = RequestMethod.POST)
	@ResponseBody
	public String cameraListSecond(String scode){
		List<Camera> list = cameraService.selectByFkCode(scode);
		Gson gson = new GsonBuilder().registerTypeAdapter(Camera.class,
				new JsonSerializer<Camera>() {
					@Override
					public JsonElement serialize(Camera src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("text", src.getName());
						o.addProperty("channel", src.getChannel());
						return o;
					}
				}).create();		
		return gson.toJson(list);
	}
	
	@RequestMapping(value="cameraListByCode", method = RequestMethod.POST)
	@ResponseBody
	public String cameraListByCode(String scode){
		List<Camera> list = cameraService.selectByFkCode(scode);
		Gson gson = new GsonBuilder().registerTypeAdapter(Camera.class,
				new JsonSerializer<Camera>() {
					@Override
					public JsonElement serialize(Camera src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("text", src.getName());		
						String attributes="{\"channel\":\""+ src.getChannel()+"\"}";
						o.addProperty("attributes", attributes);
						return o;
					}
				}).create();		
		return gson.toJson(list);
	}
	
	
	
	@RequestMapping(value="stationContents", method = RequestMethod.POST)
	@ResponseBody
	public String stationContents(String code){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("code", code);
		List<Station> list = stationService.selectBYCode(map);
		if(list == null || list.size() == 0){
			return "";
		}else {
			Station station = (Station)list.get(0);
			if(station != null){
				String result="{\"NVRUrl\":\""+ station.getNvrurl()+"\",\"NVRUsername\":\""+station.getNvrusername()+
						"\",\"NVRPassword\":\""+station.getNvrpassword()+"\",\"NVRProt\":\""+station.getNvrprot()+"\"}";
				return result;
			}else {
				return "";
			}
		}
	}

}
