package com.benqzl.controller.material.manage;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.material.Stock;
import com.benqzl.pojo.material.StockItems;
import com.benqzl.pojo.system.Material;
import com.benqzl.pojo.system.User;
import com.benqzl.service.material.StockService;
//import com.benqzl.service.material.StorageService;
import com.benqzl.service.oa.MessageService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: CheckStorageController 
* @Description: TODO(物资盘点操作) 
* @author 冯庆国
* @date 2016年1月25日 下午4:14:12 
*  
*/
@Controller
@RequestMapping("checkStorage")
public class CheckStorageController extends BasicController{
	
	
	@Autowired
	private StockService service;
	
	
	public CheckStorageController() {
		super(CheckStorageController.class);
		// TODO Auto-generated constructor stub
	}
	/** 
	* @Title: index 
	* @Description: TODO(物资盘点首页) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/material/checkStorage");
		return mv;
	}
	
	/** 
	* @Title: checkStorageInfo 
	* @Description: TODO(物资盘点新增、查询、提交、编辑) 
	* @param @param id
	* @param @param type
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@Autowired
	private ActivitiUnitService activitiUnitService;
	@RequestMapping(value = "checkStorageInfo", method = RequestMethod.GET)
	public ModelAndView checkStorageInfo(String id,String type) {
		ModelAndView mv = new ModelAndView();
		Stock stock = new Stock();
		if(!id.equals("0")){
			try {
				Map<String, Object> map = new HashMap<>();
				map.put("id", id);
				if(type!=null){
					map.put("type", 0);
				}else{
					map.put("type", null);
				}
				stock = service.selectById(map);
				mv.addObject("datetime", datetimeFormat.format(stock.getProposertime()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			stock.setCode(activitiUnitService.selectMaxCode("PK"));
		}
		mv.addObject("stock", stock);
		mv.addObject("id", id);
		if(type!=null){
			mv.addObject("storageInfo", type);
		}
		mv.setViewName("/material/checkStorageInfo");
		return mv;
	}
	
	/** 
	* @Title: bind 
	* @Description: TODO(物资盘点首页数据绑定) 
	* @param @param page
	* @param @param rows
	* @param @param code
	* @param @param starttime
	* @param @param endtime
	* @param @param typeDate
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String code,String starttime,String endtime,String typeDate){
		Subject subject = SecurityUtils.getSubject();
		User user  =(User) subject.getSession().getAttribute("loginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", 3);
		if(typeDate.equals("0")){
			map.put("handler", user.getUserid());
		}else{
			map.put("handler", null);
		}
		if(code==null||code.equals("")){
			map.put("code", null);
		}else{
			map.put("code", code);
		}
		if(starttime==null||starttime.trim().equals("")){
			map.put("starttime", null);
		}else{
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		List<Stock> stocks = null;
		int pageCount=0;
		try {
			stocks = service.findByPage(map);
			pageCount = service.pageCount(map);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", stocks);
		gson = new GsonBuilder().registerTypeAdapter(Stock.class, new JsonSerializer<Stock>() {
			@Override
			public JsonElement serialize(Stock arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("code", arg0.getCode());
				json.addProperty("creater", arg0.getCreater());
				json.addProperty("handler", arg0.getHandler());
				json.addProperty("stateValue", arg0.getState());
				json.addProperty("proposer", arg0.getProposer());
				json.addProperty("creatertime", datetimeFormat.format(arg0.getCreatetime()));
				if(arg0.getProposertime()!=null){
					json.addProperty("proposerTime", datetimeFormat.format(arg0.getProposertime()));
				}else{
					json.addProperty("proposerTime", "未处理");
				}
				if(arg0.getHandletime()!=null){
					json.addProperty("handlertime", datetimeFormat.format(arg0.getHandletime()));
				}else{
					json.addProperty("handlertime", "未处理");
				}
				if(arg0.getMemo()!=null){
					json.addProperty("memo", arg0.getMemo().length()>20 ? arg0.getMemo().substring(0, 10)+"...":arg0.getMemo());
				}
				if(arg0.getCasuse()!=null){
					json.addProperty("casuse", arg0.getCasuse().length()>20 ? arg0.getCasuse().substring(0, 10)+"...":arg0.getCasuse());
				}
				json.addProperty("state", arg0.getState());
				return json;
			}
		}).create();
		return gson.toJson(jsonMap);
	}
	/** 
	* @Title: selectMaterials 
	* @Description: TODO(物资种类查询) 
	* @param @param str
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "selectMaterials", method = RequestMethod.POST)
	@ResponseBody
	public String selectMaterials(String str,String type){
		List<String> ids = new ArrayList<>();
		String[] strs=str.split(";");
		for (String string : strs) {
			if(string.trim()!=null&&!string.trim().equals("")){
				ids.add(string);
			}
		}
		List<Material> materials = null;
		Map<String, Object> map = new HashMap<>();
		map.put("type", null);
		map.put("types", new Long(type));
		if(ids.size()!=0&&ids!=null){
			map.put("ids", ids);
		}else{
			map.put("ids", null);
		}
		try {
			materials = service.findMaterials(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strJson="";
		gson = new GsonBuilder().registerTypeAdapter(Material.class, new JsonSerializer<Material>() {
			@Override
			public JsonElement serialize(Material arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				json.addProperty("size", arg0.getSizeid());
				json.addProperty("prickle", arg0.getPrickleid());
				json.addProperty("type", arg0.getType());
				return json;
			}
		}).create();
		strJson=gson.toJson(materials);
		return strJson;
	}
	
	/** 
	* @Title: selectStockItems 
	* @Description: TODO(物资盘点物资种类明细查询) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "selectStockItems", method = RequestMethod.POST)
	@ResponseBody
	public String selectStockItems(String id){
		List<StockItems> items = null;
		try {
			items = service.selectItemsByStockId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(StockItems.class, new JsonSerializer<StockItems>() {
			@Override
			public JsonElement serialize(StockItems arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getMaterialid());
				json.addProperty("name", arg0.getMaterial().getName());
				json.addProperty("size", arg0.getMaterial().getSizeid());
				json.addProperty("prickle", arg0.getMaterial().getPrickleid());
				json.addProperty("count", arg0.getCount());
				json.addProperty("type", arg0.getMaterial().getType());
				return json;
			}
		}).create();
		return gson.toJson(items);
	}

	/** 
	* @Title: save 
	* @Description: TODO(物资盘点数据保存) 
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr){
		Subject subject = SecurityUtils.getSubject();
		User user  =(User) subject.getSession().getAttribute("loginUser");
		try {
			Stock stock = gson.fromJson(jsonStr, Stock.class);
			stock.setType(new Long(3));
			stock.setCreater(user.getUserid());
			stock.setCreatetime(new Date());
			service.insert(stock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{'result':false,'msg':'保存失败！'}";
		}
		return "{'result':true}";
		
	}
	/*@Autowired
	private StorageService storageService;*/
	
	/** 
	* @Title: commit 
	* @Description: TODO(物资盘点提交) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "commit", method = RequestMethod.POST)
	@ResponseBody
	public String commit(String id){
		try {
			service.insertProfitAndLoss(id);
			service.updateState(id,new Long(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{'result':false,'msg':'提交失败！'}";
		}
		return "{'result':true}";
	}
	
	/** 
	* @Title: delete 
	* @Description: TODO(物资盘点删除) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id){
		try {
			service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'提交失败！'}";
		}
		return "{'result':true}";
	}
	
	@Autowired
	private MessageService messageService;
	/** 
	* @Title: userBind 
	* @Description: TODO(用户数据获取) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/userBind", method = RequestMethod.POST)
	@ResponseBody
	public String userBind(){
		String strJson = "";
		List<User> users = null;
		try {
			users = messageService.selectUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(User.class, new JsonSerializer<User>() {
			@Override
			public JsonElement serialize(User arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getUserid());
				json.addProperty("name", arg0.getUsername());
				json.addProperty("ename", arg0.getEmployee().getName());
				json.addProperty("ephone", arg0.getEmployee().getPhone());
				json.addProperty("dname", arg0.getEmployee().getDepartment().getName());
				
				return json;
			}
		}).create();
		strJson = gson.toJson(users);
		return strJson;
	}
}
