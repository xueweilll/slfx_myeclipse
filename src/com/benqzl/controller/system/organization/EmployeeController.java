package com.benqzl.controller.system.organization;

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
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.Station;
/*import com.benqzl.service.dispatch.DispatchInstructionsService;*/
import com.benqzl.service.system.EmployeeService;
import com.benqzl.service.system.StationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import com.google.gson.JsonSerializationContext;

/**
 * @ClassName: EmployeeController
 * @Description: TODO(人员信息维护)
 * @author pxj
 * @date 2015年12月10日 上午8:35:53
 * 
 */
@Controller
@RequestMapping("employee")
public class EmployeeController extends BasicController {

	/**
	 * <p>
	 * Title: 构造函数
	 * </p>
	 * <p>
	 * Description: 传入参数该类字节码
	 * </p>
	 */
	public EmployeeController() {
		super(EmployeeController.class);
	}

	@Autowired
	private EmployeeService employeeService;
	/*@Autowired
	private DispatchInstructionsService dis;*/
	@Autowired
	private StationService station;
	/**
	 * @Title: index
	 * @Description: TODO(显示列表页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/employee");
		return mv;
	}

	/**
	 * @Title: employeeInfo
	 * @Description: TODO(人员信息显示编辑页面)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "employeeInfo", method = RequestMethod.GET)
	public ModelAndView employeeInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Employee employee = employeeService.selectByPrimaryKey(id);
			if(employee.getBirthday()==null){
			mv.addObject("date", "");
			}else{
			mv.addObject("date", dateFormat.format(employee.getBirthday()));
			}
			if(employee.getStation()==null){
				mv.addObject("sname","");
				mv.addObject("snameid","");
			}else{
				mv.addObject("sname",employee.getStation().getName());
				mv.addObject("snameid",employee.getStation().getId());
			}
			
			mv.addObject("employee", employee);
		}
		mv.setViewName("system/employeeInfo");
		return mv;
	}

	/**
	 * @Title:employeeList
	 * @Description: TODO(人员信息分页显示,查询)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/employeeListBind", method = RequestMethod.POST)
	@ResponseBody
	public String employeeListBind(int page, int rows, String jsonStr) {
		String strJson = "";
		Map<String, Object> map = new HashMap<String, Object>();
		gson = new Gson();
		Employee employee = new Employee();
		if (jsonStr != null) {
			employee = gson.fromJson(jsonStr, Employee.class);
			if (employee.getDepartment().getId() != null) {
				map.put("id", employee.getDepartment().getId());
			} else {
				map.put("id", null);
			}
		}
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if (employee.getName() != null) {
			map.put("name", employee.getName().trim());
		} else {
			map.put("name", null);
		}

		List<Employee> list = employeeService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = employeeService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Employee.class,
				new JsonSerializer<Employee>() {

					@Override
					public JsonElement serialize(Employee src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						if(src.getStation()==null){
							o.addProperty("sid","");
						}else{
							o.addProperty("sid",src.getStation().getName());
						}
						o.addProperty("name", src.getName());
						o.addProperty("birthday",src.getBirthday()==null?"":
								dateFormat.format(src.getBirthday()));
						o.addProperty("position", src.getPosition());
						o.addProperty("phone", src.getPhone());
						o.addProperty("telephone", src.getTelephone());
						o.addProperty("sex", src.getSex() == 0 ? "男" : "女");
						o.addProperty("age", src.getAge());
						o.addProperty("dname", src.getDepartment().getName());
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("edittime",
								datetimeFormat.format(src.getEdittime()));
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this employee list strJson is " + strJson);
		return strJson;
	}

	/**
	 * @Title: save
	 * @Description: 保存事件，分为新增与更新
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
		Employee employee = gson.fromJson(jsonStr, Employee.class);
		if (employee.getId().equals("0")) {
			UUID uuid = UUID.randomUUID();
			employee.setId(uuid.toString());
			employee.setCreatetime(new Date());
			employee.setEdittime(new Date());
			employee.setIsdel(new Long(0));

			try {
				employeeService.insert(employee);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}

		}
		// 更新
		else {
			employee.setEdittime(new Date());
			try {
				employeeService.updateByPrimaryKeySelective(employee);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'編輯失敗！'}";
			}
		}
		return result;
	}

	
	/** 
	* @Title: station 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/station", method = RequestMethod.POST)
	@ResponseBody
	public String station(String id) {
		String json = "";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		List<Station> list = station.findStations(map);
		json = gson.toJson(list);
		return json;
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
				employeeService.deleteByPrimaryKey(id);
				return "result:true";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'删除失敗！'}";
			}
		}
		return result;
	}
}
