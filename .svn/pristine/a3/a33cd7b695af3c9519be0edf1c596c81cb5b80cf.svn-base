package com.benqzl.controller.main;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.benqzl.controller.BasicController;
import com.benqzl.core.LeeTree;
import com.benqzl.pojo.system.Menu;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.MenuService;

@Controller
@RequestMapping("main")
public class MainController extends BasicController {

	public MainController() {
		super(MainController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(主页框架)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		/*User user = new User();
		user.setUserid("59dd4b70-b802-4d66-ae5b-620811d297b6");
		user.setUsername("fengqingg");
		user.setAge(new Long(18));
		user.setEmail("295201976@qq.com");
		Actor actor =new Actor();
		actor.setId("e22873a6-6886-4448-9af7-0c845503485c");
		user.setActorid(actor);
		user.setCreatetime(new Date());
		user.setEdittime(new Date());
		Employee employee = new Employee();
		employee.setId("adeaa844-4218-4692-a5b8-e28a11497dc8");
		user.setEmployee(employee);
		user.setSex(new Long(1));
		user.setUserpwd("12345");
		request.getSession().setAttribute("loginUser", user);*/
		User user = (User) request.getSession().getAttribute("loginUser");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/index");
		mv.addObject("username", user.getUsername());
		mv.addObject("password", user.getUserpwd());
		return mv;
	}

	protected static String pareId = "56f86588-68e9-4060-9464-da481766079d";

	@Autowired
	private MenuService menuService;

	/**
	 * @Title: menuJson
	 * @Description: TODO(菜单数据)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/menuJson", method = RequestMethod.POST)
	@ResponseBody
	public String menuJson(String sys) {
		System.out.println(new Date());
		
		
		String json = "";
		List<Menu> menus = menuService.getMenus();
		LeeTree<Menu> root = new LeeTree<>();
		root.setId(system(sys).getSystemId());
		// root.setId("56f86588-68e9-4060-9464-da481766079d");
		root.setText("菜单");
		root.setIsLeaf(true);
		root.setPareId("0");
		LeeTree<Menu> node;
		for (Menu menu : menus) {
			node = new LeeTree<>();
			node.setId(menu.getMenuid());
			node.setText(menu.getMenuname());
			node.setObj(menu);
			node.setPareId(menu.getPareid());
			root.add(node);
		}
		json = JSON.toJSONString(root).replace("\"children\":[],", "");
		System.out.println(new Date());
		return "{\"basic\":" + json + "}";
	}

	private Sys system(String key) {
		Sys sys = new Sys();
		switch (key) {
		case "sys":
			sys.setSystemId("56f86588-68e9-4060-9464-da481766079d");
			sys.setLogUrl("");
			break;
		case "oa":
			sys.setSystemId("00e6da6b-6e3e-442a-b220-ab3d269deb93");
			sys.setLogUrl("");
			break;
		case "dispatch":
			sys.setSystemId("ebcc38ed-bc83-496a-9a17-1a20f86c134a");
			sys.setLogUrl("");
			break;
		case "material":
			sys.setSystemId("5a6129dd-9ab8-4a94-a867-030cac55d2f3");
			sys.setLogUrl("");
			break;
		case "inspection":
			sys.setSystemId("eb5c740c-5648-4f36-9f3c-f238ed7395ef");
			sys.setLogUrl("");
			break;
		default:
			break;
		}
		return sys;
	}
	
	class Sys{
		String systemId;
		String logUrl;
		public String getSystemId() {
			return systemId;
		}
		public void setSystemId(String systemId) {
			this.systemId = systemId;
		}
		public String getLogUrl() {
			return logUrl;
		}
		public void setLogUrl(String logUrl) {
			this.logUrl = logUrl;
		}
		
	}
}
