package com.benqzl.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.service.system.CameraService;
import com.benqzl.service.system.GateService;
import com.benqzl.service.system.MaterialService;
import com.benqzl.service.system.StationService;
import com.benqzl.service.system.UnitService;

/**
 * @ClassName: IndexController
 * @Description: TODO(基础信息首页)
 * @author lyf
 * @date 2015年11月23日 下午2:48:01
 * 
 */
/** 
* @ClassName: IndexController 
* @Description: TODO(基础数据 ) 
* @author lyf 
* @date 2015年12月15日 下午3:18:31 
*  
*/
@Controller
@RequestMapping("system")
public class SystemController extends BasicController {

	public SystemController() {
		super(SystemController.class);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","sys");
		mv.setViewName("main/main");
		return mv;
	}
	@Autowired
	private GateService gateservice;
	@Autowired
	private UnitService unitService;
	@Autowired
	private CameraService cameraService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private StationService stationService;
	@RequestMapping(value = "systemInfo", method = RequestMethod.GET)
	public ModelAndView systemInfo() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","sys");
		int gatecount=gateservice.selectcount();
		int unitcount=unitService.selectcount();
		int cameracount=cameraService.selectcount();
		int materialcount=materialService.selectcount();
		int stationcount=stationService.selectcount();
		mv.addObject("gatecount", "闸门数量"+gatecount);
		mv.addObject("unitcount","机组数量"+unitcount);
		mv.addObject("cameracount","摄像头个数"+cameracount);
		mv.addObject("materialcount","物资种类"+materialcount);
		mv.addObject("stationcount","枢纽个数"+stationcount);
		mv.setViewName("system/systemInfo");
		return mv;
	}
	
	@RequestMapping(value = "defaultPage", method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys","sys");
		mv.setViewName("system/default");
		return mv;
	}
}
