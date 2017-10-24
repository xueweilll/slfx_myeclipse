package com.benqzl.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
@RequestMapping("/error")
public class ErrorController extends BasicController {

	public ErrorController() {
		super(ErrorController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/error");
		return mv;
	}

}
