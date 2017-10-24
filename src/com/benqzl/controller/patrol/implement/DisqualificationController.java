package com.benqzl.controller.patrol.implement;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.google.gson.Gson;

@Controller
public class DisqualificationController extends BasicController{

	public DisqualificationController() {
		super(DisqualificationController.class);
	}

	public ModelAndView index(String implementStrs){
		gson = new Gson();
		//PatrolSpecialImplement patrolSpecialImplement = gson.fromJson(implementStrs, PatrolSpecialImplement.class);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/implement/disqualification");
		return mv;
	}
	
}
