package com.benqzl.controller.patrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

/** 
* @ClassName: EgPatrolRecordController 
* @Description: EgPatrolRecordController
* @author xw 
* @date 2016年4月28日 下午4:00:31 
*  
*/
@Controller
@RequestMapping("egpatrolrecord")
public class EgPatrolRecordController extends BasicController{

	public EgPatrolRecordController() {
		super(EgPatrolRecordController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/egpatrolRecord");
		return mv;
	}
	
	@RequestMapping(value = "egPatrolRecordInfo", method = RequestMethod.GET)
	public ModelAndView egPatrolRecordInfo(String id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/egpatrolRecordInfo");
		return mv;
	}

}
