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
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.OperateLogger;
import com.benqzl.service.system.EmployeeService;
import com.benqzl.service.system.OperaterLoggerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: OperateLoggerController
 * @Description: TODO(操作日志维护)
 * @author pxj
 * @date 2015年12月10日 上午8:48:03
 * 
 */
@Controller
@RequestMapping("operateLogger")
public class OperateLoggerController extends BasicController {

	public OperateLoggerController() {
		super(OperateLoggerController.class);
	}

	@Autowired
	private OperaterLoggerService operateLoggerService;
	@Autowired
	private EmployeeService employee;

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
		mv.setViewName("system/operatelogger");
		return mv;
	}

	/**
	 * @Title: operateLoggerBind
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @param rows
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/operateLoggerBind", method = RequestMethod.POST)
	@ResponseBody
	public String operateLoggerBind(int page, int rows, String jsonStr) {
		String strJson = "";
		gson = new Gson();
		OperateLogger operateLogger = new OperateLogger();
		if(jsonStr!=null){
			operateLogger= gson.fromJson(jsonStr,OperateLogger.class);
		}
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", String.valueOf(rows));
		if (operateLogger.getContents() != null) {
			map.put("contents", operateLogger.getContents().trim());
		} else {
			map.put("contents", null);
		}
		if (operateLogger.getUser() != null) {
			if (operateLogger.getUser().getUserid() == null
					|| "".equals(operateLogger.getUser().getUserid())) {
				map.put("userid", null);
			} else {
				map.put("userid", operateLogger.getUser().getUserid());
			}
		}
		if (operateLogger.getTypes() == null) {
			map.put("types", null);
		} else {
			map.put("types", String.valueOf(operateLogger.getTypes()));
		}

		if (operateLogger.getCreatetime() == null) {
			map.put("createtime", null);
		} else {
			map.put("createtime",
					datetimeFormat.format(operateLogger.getCreatetime()));

		}
		if (operateLogger.getEndtime() == null) {
			map.put("endtime", null);
		} else {
			map.put("endtime",
					datetimeFormat.format(operateLogger.getEndtime()));

		}
		List<OperateLogger> list = operateLoggerService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = operateLoggerService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(OperateLogger.class,
				new JsonSerializer<OperateLogger>() {

					@Override
					public JsonElement serialize(OperateLogger src,
							Type typeOfSrc, JsonSerializationContext context) {
						String types = "";
						if (src.getTypes() == 0) {
							types = "登录";
						} else if (src.getTypes() == 1) {
							types = "退出";
						} else if (src.getTypes() == 2) {
							types = "增加";
						} else if (src.getTypes() == 3) {
							types = " 删除";
						} else if (src.getTypes() == 4) {
							types = "修改";
						} else if (src.getTypes() == 5) {
							types = "查询";
						} else if (src.getTypes() == 6) {
							types = "审批";
						} else if (src.getTypes() == 7) {
							types = "打印";
						}

						JsonObject o = new JsonObject();
						o.addProperty("userid", src.getEmployee().getName());
						o.addProperty("types", types);
						o.addProperty("contents", src.getContents());
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this OperateLogger list strJson is " + strJson);
		return strJson;
	}

	/**
	 * @Title: getUsername
	 * @Description: TODO(获取用户名称,用户ID)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/getUsername", method = RequestMethod.POST)
	@ResponseBody
	public String getUsername() {
		//List<User> username = operateLoggerService.selectUserbyParentID();
		List<Employee> employeename=employee.findEmployeeName();
		gson = new GsonBuilder().registerTypeAdapter(Employee.class,
				new JsonSerializer<Employee>() {
					@Override
					public JsonElement serialize(Employee arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("name", arg0.getName());
						return json;
					}
				}).create();
		return gson.toJson(employeename);
	}
}
