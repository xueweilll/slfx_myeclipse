package com.benqzl.controller.oa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

/** 
* @ClassName: IndexController 
* @Description: TODO(oa子系统 ) 
* @author lyf 
* @date 2015年12月15日 下午3:18:16 
*  
*/
@Controller
@RequestMapping("oa")
public class OAController extends BasicController{

	public OAController() {
		super(BasicController.class);
	}

	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","oa");
		mv.setViewName("main/main");
		return mv;
	}
}
