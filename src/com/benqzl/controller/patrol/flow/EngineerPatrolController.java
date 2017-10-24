package com.benqzl.controller.patrol.flow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
@Controller
@RequestMapping("engineerPatrol")
public class EngineerPatrolController extends BasicController{

	public EngineerPatrolController() {
		super(EngineerPatrolController.class);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/flow/engineerPatrol");
		return mv;
	}

}
