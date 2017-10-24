package com.benqzl.controller.patrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

/** 
* @ClassName: InspectionController 
* @Description: 安防巡检子系统(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2016年1月22日 上午8:53:53 
*  
*/
@Controller
@RequestMapping("inspection")
public class InspectionController extends BasicController{

	public InspectionController() {
		super(InspectionController.class);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","inspection");
		mv.setViewName("main/main");
		return mv;
	}
	
	@RequestMapping(value="inspectionInfo",method=RequestMethod.GET)
	public ModelAndView inspectionInfo(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/inspection");
		return mv;
	}

}
