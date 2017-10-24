package com.benqzl.controller.material;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
@RequestMapping("material")
public class MaterialConteroller extends BasicController{

	public MaterialConteroller() {
		super(MaterialConteroller.class);
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","material");
		mv.setViewName("main/main");
		return mv;
	}
	@RequestMapping(value="materialInfo",method=RequestMethod.GET)
	public ModelAndView materialInfo(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","material");
		mv.setViewName("main/material");
		return mv;
	}
}
