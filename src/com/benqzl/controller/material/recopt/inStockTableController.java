package com.benqzl.controller.material.recopt;

import java.lang.reflect.Type;
import java.text.ParseException;
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
import com.benqzl.pojo.material.MaterialManage;
import com.benqzl.service.material.TableService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: inStockTableController
 * @Description: TODO(入库报表)
 * @author pxj
 * @date 2016年1月27日 下午12:39:08
 * 
 */
@Controller
@RequestMapping("inStockTable")
public class inStockTableController extends BasicController {

	@Autowired
	private TableService service;

	public inStockTableController() {
		super(inStockTableController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(入库查页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/material/inStockTable");
		return mv;
	}

	/**
	 * @Title: stockTable
	 * @Description: TODO(入库报表)
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
	@RequestMapping(value = "stockTable", method = RequestMethod.POST)
	@ResponseBody
	public String stockTable(int page, int rows, String starttime,
			String endtime, String type, String typeDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", 0);
		if (typeDate != null && typeDate.equals("9")||typeDate=="") {
			map.put("typedate", null);
		} else {
			map.put("typedate", typeDate);
		}
		if (starttime == null || starttime.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (endtime == null || endtime.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		List<MaterialManage> material = null;
		int pageCount = 0;
		try {
			material = service.findByPage(map);
			pageCount = service.pageCount(map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", material);
		gson = new GsonBuilder().registerTypeAdapter(MaterialManage.class,
				new JsonSerializer<MaterialManage>() {
					@Override
					public JsonElement serialize(MaterialManage arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("sname", arg0.getMaterial().getSize()
								.getName());
						json.addProperty("pname", arg0.getMaterial()
								.getPrickle().getName());
						json.addProperty("mname", arg0.getMaterial().getName());
						json.addProperty("newstorage", arg0.getNewstorage());
						json.addProperty("oldstorage", arg0.getOldstorage());
						json.addProperty("creatertime",
								datetimeFormat.format(arg0.getCreatetime()));
						json.addProperty("count", arg0.getCount());
						if (arg0.getMemo() != null) {
							json.addProperty("memo",
									arg0.getMemo().length() > 20 ? arg0
											.getMemo().substring(0, 10) + "..."
											: arg0.getMemo());
						}
						if (arg0.getSource() != null) {
							json.addProperty("source",
									arg0.getSource().length() > 20 ? arg0
											.getSource().substring(0, 10)
											+ "..." : arg0.getSource());
						}
						String typeDate="";
						if(arg0.getTypedate()==1){
							typeDate="手动入库";
						}else if(arg0.getTypedate()==2){
							typeDate="归还入库";
						}else{
							typeDate="溢出入库";
						}
						json.addProperty("type",typeDate);
						return json;
					}
				}).create();
		return gson.toJson(jsonMap);
	}
}
