package com.benqzl.controller.oa;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.benqzl.controller.BasicController;
import com.benqzl.service.oa.TodoService;


/** 
* @ClassName: todolistController 
* @Description: TODO(查看代办事项页面) 
* @author gyl 
* @date 2015年12月16日 上午9:55:40 
*  
*/
@Controller
@RequestMapping("/todosearch")
public class TodoSearchController extends BasicController{

	public TodoSearchController() {
		super(TodoSearchController.class);
	}
	
	@Resource
	private TodoService todoservice;

	/** 
	* @Title: index 
	* @Description: TODO(新增待办事项页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("oa/todolistpeople");
		return mv;
	}	
}

