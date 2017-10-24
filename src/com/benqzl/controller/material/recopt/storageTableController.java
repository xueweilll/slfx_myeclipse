package com.benqzl.controller.material.recopt;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.material.Storage;
import com.benqzl.service.material.TableService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: storageTableController
 * @Description: TODO(库存报表)
 * @author pxj
 * @date 2016年1月27日 下午12:36:59
 * 
 */
@Controller
@RequestMapping("storageTable")
public class storageTableController extends BasicController {

	@Autowired
	private TableService storage;

	public storageTableController() {
		super(storageTableController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(库存查页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/material/storageTable");
		return mv;
	}

	/**
	 * @Title: storageTable
	 * @Description: TODO(库存报表)
	 * @param @param page
	 * @param @param rows
	 * @param @param starttime
	 * @param @param endtime
	 * @param @param type
	 * @param @param typeDate
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "storageTable", method = RequestMethod.POST)
	@ResponseBody
	public String storageTable(int page, int rows, String type, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if(type != null && !type.trim().equals("")){
			map.put("type", type);
		}	
		if(name != null && !name.trim().equals("")){
			map.put("name", name);
		}

		List<Storage> storage1 = null;
		int pageCount = 0;
		try {
			storage1 = storage.findStorageByPage(map);
			pageCount = storage.pageStorageCount(map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", storage1);
		gson = new GsonBuilder().registerTypeAdapter(Storage.class,
				new JsonSerializer<Storage>() {
					@Override
					public JsonElement serialize(Storage arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("sname", arg0.getMaterial().getSize()
								.getName());
						json.addProperty("pname", arg0.getMaterial()
								.getPrickle().getName());
						json.addProperty("mname", arg0.getMaterial().getName());
						json.addProperty("creatertime",
								datetimeFormat.format(arg0.getCreatetime()));
						json.addProperty("edittime",
								datetimeFormat.format(arg0.getEdittime()));
						json.addProperty("storage", arg0.getStorage());
						json.addProperty("checkoutstorage",
								arg0.getCheckoutstorage());
						String type="";
						switch (arg0.getMaterial().getType().intValue()) {
						case 0:
							type="灯具";
							break;
						case 1:
							type="电器开关";
							break;
						case 2:
							type="工具";
							break;
						case 3:
							type="劳保用品";
							break;
						case 4:
							type="电线电缆";
							break;
						case 5:
							type=" 油类";
							break;
						case 6:
							type="办公用品";
							break;
						case 7:
							type="生活用具";
							break;
						case 8:
							type="备用备件";
							break;
						default:
							type="";
							break;
						}
						json.addProperty("mtype", type);
						if(arg0.getInstorage()==null){
							json.addProperty("instorage", "0");
						}else{
							json.addProperty("instorage", arg0.getInstorage());
						}
						json.addProperty("outstorage", arg0.getOutstorage());
						if(arg0.getBreturn()==null){
							json.addProperty("breturn", "0");
						}else{
						json.addProperty("breturn", arg0.getBreturn());
						}
						json.addProperty("scrapstorage", arg0.getScrapstorage());
						
						return json;
					}
				}).create();
		return gson.toJson(jsonMap);
	}
}
