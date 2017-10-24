package com.benqzl.controller.system.authority;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.core.LeeTree;
import com.benqzl.pojo.system.Actor;
import com.benqzl.pojo.system.Authority;
import com.benqzl.pojo.system.Menu;
import com.benqzl.service.system.ActorService;
import com.benqzl.service.system.AuthorityService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: ActorController
 * @Description: TODO(角色维护)
 * @author pxj
 * @date 2015年12月10日 上午8:54:14
 * 
 */
@Controller
@RequestMapping("power")
public class ActorController extends BasicController {

	public ActorController() {
		super(ActorController.class);
	}

	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/authority");
		return mv;
	}

	/**
	 * @Title: authoriytInfo
	 * @Description: TODO(显示编辑页面)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "authorityInfo", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView authorityInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Actor actor = actorService.selectByPrimaryKey(id);
			mv.addObject("actor", actor);
		}
		mv.setViewName("system/authorityInfo");
		return mv;
	}

	/**
	 * @Title: bind
	 * @Description: TODO(角色分页显示)
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page, int rows, String jsonStr) {
		String strJson = "";
		gson = new Gson();
		Actor actor = new Actor();
		if (jsonStr != null) {
			actor = gson.fromJson(jsonStr, Actor.class);
		}
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if (actor.getName() != null) {
			map.put("name", actor.getName().trim());
		} else {
			map.put("name", null);
		}
		if (actor.getRemark() != null) {
			map.put("remark", actor.getRemark().trim());
		} else {
			map.put("remark", null);
		}
		List<Actor> list = actorService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = actorService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Actor.class,
				new JsonSerializer<Actor>() {
					@Override
					public JsonElement serialize(Actor src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("name", src.getName());
						o.addProperty("remark", src.getRemark());
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("edittime",
								datetimeFormat.format(src.getEdittime()));
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this actor list strJson is " + strJson);
		return strJson;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: TODO(编辑，添加保存)
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr) {
		String result = "";
		gson = new Gson();
		Actor actor = gson.fromJson(jsonStr, Actor.class);
		// 新增
		if (actor.getId().equals("0")) {
			UUID uuid = UUID.randomUUID();
			actor.setId(uuid.toString());
			actor.setCreatetime(new Date());
			actor.setEdittime(new Date());
			actor.setIsdel(new Long(0));

			try {
				actorService.insert(actor);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}

		}
		// 更新
		else {
			actor.setEdittime(new Date());
			try {
				actorService.updateByPrimaryKeySelective(actor);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'編輯失敗！'}";
			}
		}
		return result;
	}

	/**
	 * @Title: delete根据id删除
	 * @Description: TODO(根据ID更新状态)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id) {
		String result = "";
		if (id != null && id != "") {
			try {
				actorService.deleteByPrimaryKey(id);
				result = " {'result':true,'msg':'删除成功'}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'删除失敗！'}";
			}
		}
		return result;
	}

	/**
	 * @Title: exsitName
	 * @Description: TODO(判断角色用户是否存在)
	 * @param @param name
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "exsitName", method = RequestMethod.POST)
	@ResponseBody
	public String exsitName(String name) {
		String result = "";
		int count = actorService.exsitName(name);
		if (count > 0) {
			result = "{'result':false}";
			return result;
		} else {
			result = "{'result':true}";
			return result;
		}
	}
	
	@RequestMapping(value = "authorityAllot", method = RequestMethod.GET)
	public ModelAndView authorityAllot(String id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/authorityAllot");//authorityAllot
		mv.addObject("aid", id);
		return mv;
	}
	
	@Autowired
	private AuthorityService service;
	/** 
	* @Title: bind 
	* @Description: TODO(获得菜单列表) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/bindAllot",method=RequestMethod.POST)
	@ResponseBody
	public String bindAllot(String id){
		LeeTree<Menu> tree = null;
		try {
			tree = service.selectAll(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'数据获取失败！'}";
		}
		gson = new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd hh-mm-ss")  
		  .create();  ;
		return "["+gson.toJson(tree).replace("\"children\":[],",	"")+"]";
	}
	
	/** 
	* @Title: save 
	* @Description: TODO(保存权限) 
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/saveAllot",method=RequestMethod.POST)
	@ResponseBody
	public String saveAllot(String jsonStr){
		String[] strings = jsonStr.split(";");
		List<Authority> authoritys = new ArrayList<Authority>();
		for (String string : strings) {
			if(!string.equals("")){
				String[] strs = string.split("/");
				Authority authority = new Authority();
				authority.setMenuid(strs[0]);
				authority.setId(strs[2]);
				authority.setActorid(strs[3]);
				BigInteger src = new BigInteger(strs[1],2);
				authority.setVal(new Long(src.toString()));
				authoritys.add(authority);
			}
		}
		try {
			service.save(authoritys);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'数据添加失败！'}";
		}
		return "result:true";
	}

}
