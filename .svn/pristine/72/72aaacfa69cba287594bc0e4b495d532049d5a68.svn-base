package com.benqzl.controller.system.logger;

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
import com.benqzl.pojo.system.SystemLogger;
import com.benqzl.service.system.SystemLoggerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: SystemLoggerController
 * @Description: TODO(系统日志查询)
 * @author pxj
 * @date 2015年12月10日 上午8:25:27
 * 
 */
@Controller
@RequestMapping("systemLogger")
public class SystemLoggerController extends BasicController {

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public SystemLoggerController() {
		super(SystemLoggerController.class);
	}

	@Autowired
	private SystemLoggerService systemLoggerService;

	/**
	 * @Title: index
	 * @Description: TODO(显示日志列表)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/systemlogger");
		return mv;
	}

	/**
	 * @Title: systemLoggerBind
	 * @Description: TODO(系统日志查询)
	 * @param @param page
	 * @param @param rows
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/systemLoggerBind", method = RequestMethod.POST)
	@ResponseBody
	public String systemLoggerBind(int page, int rows, String jsonStr) {
		String strJson = "";
		gson = new Gson();
		SystemLogger systemLogger = new SystemLogger();
		if(jsonStr!=null){
			systemLogger = gson.fromJson(jsonStr, SystemLogger.class);
		}
			page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", String.valueOf(rows));
		if (systemLogger.getContents() != null) {
			map.put("contents", systemLogger.getContents().trim());
		} else {
			map.put("contents", null);
		}
		if (systemLogger.getLevels() == null) {
			map.put("levels", null);
		} else {
			map.put("levels", String.valueOf(systemLogger.getLevels()));
		}
		if (systemLogger.getEndtime() == null) {
			map.put("endtime", null);
		} else {
			map.put("endtime", datetimeFormat.format(systemLogger.getEndtime()));

		}
		if (systemLogger.getCreatetime() == null) {
			map.put("createtime", null);
		} else {
			map.put("createtime",
					datetimeFormat.format(systemLogger.getCreatetime()));
		}
		List<SystemLogger> list = systemLoggerService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = systemLoggerService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(SystemLogger.class,
				new JsonSerializer<SystemLogger>() {

					@Override
					public JsonElement serialize(SystemLogger src,
							Type typeOfSrc, JsonSerializationContext context) {
						String lever = "";
						if (src.getLevels() == 0) {
							lever = "轻";
						} else if (src.getLevels() == 1) {
							lever = "一般";
						} else {
							lever = "严重";
						}
						JsonObject o = new JsonObject();
						o.addProperty("levels", lever);
						o.addProperty("contents", src.getContents());
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this systemLogger list strJson is " + strJson);
		return strJson;
	}

}
