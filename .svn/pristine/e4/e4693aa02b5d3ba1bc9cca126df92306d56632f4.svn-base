package com.benqzl.controller.patrol.flow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
public class EgPatrolFlowController extends BasicController {

	public EgPatrolFlowController() {
		super(EgPatrolFlowController.class);
	}

	@RequestMapping(value = "egpatrolflow1", method = RequestMethod.GET)
	public ModelAndView index1() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/flow/egpatrolFlowyj");
		String index = "应急巡检(特别巡检)";
		mv.addObject("title", index);
		return mv;
	}
	
	@RequestMapping(value = "egpatrolflow2", method = RequestMethod.GET)
	public ModelAndView index2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/flow/egpatrolFlow");
		String index="定期巡检";
		mv.addObject("title", index);
		return mv;
	}

}
