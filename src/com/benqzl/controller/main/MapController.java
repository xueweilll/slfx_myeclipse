package com.benqzl.controller.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.service.system.StationService;

@Controller
@RequestMapping("map")
public class MapController extends BasicController{

	public MapController() {
		super(MapController.class);
	}

	@Resource
	StationService stationService;

	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/map");
		return mv;
	}
	
	@RequestMapping(value="stationBind",method=RequestMethod.POST)
	@ResponseBody
	public String stationBind(){
		String json = "";
		
		json = stationService.selectByAll();
		System.out.println(json);
		
		return json;
	}
	
}
