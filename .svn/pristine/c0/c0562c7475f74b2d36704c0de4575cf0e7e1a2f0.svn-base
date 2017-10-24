package com.benqzl.controller.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Assets;
import com.benqzl.service.system.AssetsService;
@Controller
@RequestMapping("qrcode")
public class QRCodeController extends BasicController{

	public QRCodeController() {
		super(QRCodeController.class);
	}
	@Autowired
	private AssetsService assetsService;
	/** 
	* @Title: String 
	* @Description: TODO(二维码页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView String(String id) {
		ModelAndView mv = new ModelAndView();
		if (id !=null) {
			Assets assets = assetsService.selectQrByPrimaryKey(id);
			if(assets!=null){
			mv.addObject("assets", assets);
			mv.addObject("sname",assets.getStation().getName());
			mv.addObject("createtime", datetimeFormat.format(assets.getCreatetime()));
		    mv.addObject("edittime",datetimeFormat.format(assets.getEdittime()));
		}
			}
		mv.setViewName("main/qrcode");
		return mv;
	}

}
