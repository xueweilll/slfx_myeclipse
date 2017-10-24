package com.benqzl.controller.dispatch.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
@RequestMapping("dispatchImage")
public class DispatchImageContoroller extends BasicController{

	public DispatchImageContoroller() {
		super(DispatchImageContoroller.class);
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("src", "dispatchImage.jpg");
		mv.setViewName("/dispatch/dispatchIntroduce");
		return mv;
	}

}
