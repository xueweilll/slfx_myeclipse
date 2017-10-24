package com.benqzl.controller.material.recopt;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.service.material.StockService;
import com.google.gson.Gson;
@Controller
@RequestMapping("statistics")
public class StatisticsController extends BasicController{
	public StatisticsController() {
		super(StatisticsController.class);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView materialInfo(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("material/material");
		return mv;
	}
	@Autowired
	private StockService service;
	@RequestMapping(value="monthJSON",method=RequestMethod.GET)
	@ResponseBody
	public String monthJSON(){
		Map<String, Object> map;
		try {
			map =service.findByYear();
			gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'数据读取失败！'}";
		}
	}

}
