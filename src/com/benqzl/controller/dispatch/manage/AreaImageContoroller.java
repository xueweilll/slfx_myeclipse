package com.benqzl.controller.dispatch.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
@RequestMapping("areaImage")
public class AreaImageContoroller extends BasicController{

	public AreaImageContoroller() {
		super(AreaImageContoroller.class);
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("src", "areaImage.jpg");
		mv.setViewName("/dispatch/areaIntroduce");
		return mv;
	}
}
