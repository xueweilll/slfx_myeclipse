package com.benqzl.controller.patrol.implement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
@RequestMapping("regularpatrolImplement")
public class RegularPatrolImplementController extends BasicController {

	
	public RegularPatrolImplementController() {
		super(RegularPatrolImplementController.class);
	}
	
	@RequestMapping(value="")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/implement/regularpatrolImplement");
		return mv;
	}
}