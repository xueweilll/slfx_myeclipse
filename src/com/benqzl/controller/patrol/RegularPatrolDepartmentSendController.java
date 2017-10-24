package com.benqzl.controller.patrol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.benqzl.controller.BasicController;
@Controller
@RequestMapping("regularpatroldepartmentsend")
public class RegularPatrolDepartmentSendController extends BasicController{

	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param classz 
	*/
	public RegularPatrolDepartmentSendController() {
		super(RegularPatrolDepartmentSendController.class);
	}	
	/** 
	* @Title: index 
	* @Description: TODO(跳转执行部门签发) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/regularpatorlDepartemntSend");
		return mv;
	}
}