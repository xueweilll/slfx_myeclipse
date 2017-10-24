package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchDepartment;
import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.pojo.util.ReceiptAndDispatch;
import com.benqzl.service.activiti.ActivitiUnitService;
import com.benqzl.service.dispatch.BigSurroundService;
//import com.benqzl.service.dispatch.DispatchInstructionsService;
import com.benqzl.service.dispatch.ReceiptService;
import com.benqzl.service.system.StationService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: AreaDispatchController
 * @Description: TODO(片区调度)
 * @author pxj
 * @date 2016年1月14日 上午9:13:09
 * 
 */
@Controller
@RequestMapping("dispatchAreaList")
public class AreaDispatchController extends BasicController {

	public AreaDispatchController() {
		super(AreaDispatchController.class);
	}

	//@Autowired
	//private DispatchInstructionsService dis;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private BigSurroundService area;
	@Autowired
	ActivitiUnitService activitiUnitService;
	@Autowired
	private StationService stationService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/dispatchAreaList");
		return mv;
	}

	/**
	 * @Title: dispatchAreaList
	 * @Description: TODO(片区列表)
	 * @param @param request
	 * @param @param page
	 * @param @param rows
	 * @param @param code
	 * @param @param type
	 * @param @param starttime
	 * @param @param endtime
	 * @param @param typeDate
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/dispatchAreaList", method = RequestMethod.POST)
	@ResponseBody
	public String dispatchAreaList(HttpServletRequest request, int page,
			int rows, String code, String type, String starttime,
			String endtimes, String typeDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", "0");
		map.put("rpid", null);
		if (typeDate.equals("1")) {
			map.put("s", 1);
		} else {
			map.put("s", 0);
		}
		if (code == null || code.equals("")) {
			map.put("code", null);
		} else {
			map.put("code", code);
		}
		if (type == null || type.equals("")) {
			map.put("way", null);
		} else {
			map.put("way", type);
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
		if (endtimes == null || endtimes.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		List<ReceiptAndDispatch> list = null;
		int count = 0;
		try {
			list = area.findByPage(map);
			count = area.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", count);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(ReceiptAndDispatch.class,
				new JsonSerializer<ReceiptAndDispatch>() {
					@Override
					public JsonElement serialize(ReceiptAndDispatch arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("reid", arg0.getDispatch().getId());
						json.addProperty("receiptid1", arg0.getReceipt()
								.getId());
						json.addProperty("id", arg0.getDispatch().getId());
						json.addProperty("code", arg0.getDispatch().getCode());
						json.addProperty("receiptid", arg0.getReceipt()
								.getCode());
						json.addProperty("username", arg0.getLname());
						json.addProperty("receipter", arg0.getRname());
						json.addProperty("creater", arg0.getDcname());
						json.addProperty("handler", arg0.getDcname());
						json.addProperty("memo", arg0.getDispatch().getMemo());
						json.addProperty("launchtime", datetimeFormat
								.format(arg0.getReceipt().getLaunchtime()));
						json.addProperty("rtime", datetimeFormat.format(arg0
								.getReceipt().getReceiptetime()));
						json.addProperty("createtime", datetimeFormat
								.format(arg0.getReceipt().getCreatetime()));
						json.addProperty("handletime", datetimeFormat
								.format(arg0.getDispatch().getCreatetime()));
						if (arg0.getDispatch().getCompletetime() == null) {
							json.addProperty("completetime", "");
						} else {
							json.addProperty("completetime", datetimeFormat
									.format(arg0.getDispatch()
											.getCompletetime()));
						}
						json.addProperty("endtime", datetimeFormat.format(arg0
								.getReceipt().getEndtime()));
						String str = "";
						switch (arg0.getDispatch().getState().toString()) {
						case "0":
							str = "保存";
							break;
						case "1":
							str = "已提交";
							break;
						case "2":
							str = "已执行";
							break;
						case "3"://片区完成
							str = "完成";
							break;
						}
						json.addProperty("state", str);
						json.addProperty("states", arg0.getDispatch()
								.getState());
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	/**
	 * @throws Exception 
	 * @Title: documentInfo
	 * @Description: TODO(片区新增)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/dispatchAreaInfo", method = RequestMethod.GET)
	public ModelAndView dispatchAreaInfo(String id, String reid, String states) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {

			try {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", id);
				map.put("reid", reid);
				Receipt receipt = receiptService.findReceiptDocket(map);
				mv.addObject("dispatch", receipt);
				mv.addObject("dcode", receipt.getRedispatch()==null?"":receipt.getRedispatch().getCode());
				mv.addObject("way", receipt.getWay() == 0 ? "电话" : "传真");
				mv.addObject("launcher", receipt.getLauncher());
				mv.addObject("launchertime",
						datetimeFormat.format(receipt.getLaunchtime()));
				mv.addObject("endtime",
						datetimeFormat.format(receipt.getEndtime()));
				mv.addObject("receipteter", receipt.getEmployee().getName());
				mv.addObject("receiptetertime",
						datetimeFormat.format(receipt.getReceiptetime()));
				mv.addObject("code", receipt.getCode());
				mv.addObject("memo", receipt.getMemo());
				mv.addObject("state", states);
				mv.addObject("reid", reid);
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			//mv.addObject("dcode", activitiUnitService.selectMaxCode("CUFC-FJ"));
		     mv.addObject("dcode","");
		}
		mv.addObject("id", id);

		mv.setViewName("dispatch/dispatchAreaInfo");
		return mv;
	}

	/**
	 * @Title: dispatchAreaWatch
	 * @Description: TODO(查看片区单)
	 * @param @param id
	 * @param @param reid
	 * @param @param states
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/dispatchAreaWatch", method = RequestMethod.GET)
	public ModelAndView dispatchAreaWatch(String id, String reid, String states) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			try {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", id);
				map.put("reid", reid);
				Receipt receipt = receiptService.findReceiptDocket(map);
				mv.addObject("dispatch", receipt);
				mv.addObject("dcode", receipt.getRedispatch()==null?"":receipt.getRedispatch().getCode());
				mv.addObject("way", receipt.getWay() == 0 ? "电话" : "传真");
				mv.addObject("launcher", receipt.getLauncher());
				mv.addObject("launchertime",
						datetimeFormat.format(receipt.getLaunchtime()));
				mv.addObject("endtime",
						datetimeFormat.format(receipt.getEndtime()));
				mv.addObject("receipteter", receipt.getEmployee().getName());
				mv.addObject("receiptetertime",
						datetimeFormat.format(receipt.getReceiptetime()));
				mv.addObject("code", receipt.getCode());
				mv.addObject("memo", receipt.getMemo());
				mv.addObject("state", states);
				mv.addObject("reid", reid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mv.addObject("id", id);
		/* mv.addObject("dcode",activitiUnitService.selectMaxCode("PQ")); */
		mv.setViewName("dispatch/dispatchAreaWatch");
		return mv;
	}

	/**
	 * @Title: dispatchAreaDocket
	 * @Description: TODO(片区查询接收单)
	 * @param @param state
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/dispatchAreaDocket", method = RequestMethod.POST)
	@ResponseBody
	public String dispatchAreaDocket(String state, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		// List<String> receipts = null;
		List<String> receipts = new ArrayList<>();
		try {
			receipts = activitiUnitService.findObjIds("autoOrder",user.getUserid(),
					 "B");
			/*
			 * receipts = activitiUnitService.findeReceiptIds(user.getUserid(),
			 * "B", );
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Receipt> docket=new ArrayList<>();
		if(receipts.size()!=0&&receipts!=null){
			docket = receiptService.findDocket(receipts);
		}
		gson = new GsonBuilder().registerTypeAdapter(Receipt.class,
				new JsonSerializer<Receipt>() {
					@Override
					public JsonElement serialize(Receipt arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("way", arg0.getWay() == 0 ? "电话"
								: "传真");
						json.addProperty("launcher", arg0.getLauncher());
						json.addProperty("launchertime",
								datetimeFormat.format(arg0.getLaunchtime()));
						json.addProperty("endtime",
								datetimeFormat.format(arg0.getEndtime()));
						json.addProperty("receipteter", arg0.getEmployee()
								.getName());
						json.addProperty("receiptetertime",
								datetimeFormat.format(arg0.getReceiptetime()));
						json.addProperty("code", arg0.getCode());
						json.addProperty("memo", arg0.getMemo());
						return json;
					}
				}).create();
		return gson.toJson(docket);
	}

	/**
	 * @Title: station
	 * @Description: TODO(枢纽id)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/station", method = RequestMethod.POST)
	@ResponseBody
	public String station() {
		String json = "";
		List<Station> list = stationService.findStation();
		json = gson.toJson(list);
		return json;
	}

	/**
	 * @Title: instructions
	 * @Description: TODO(指令信息回显)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/instructions", method = RequestMethod.POST)
	@ResponseBody
	public String instructions(String id) {
		String json = "";
		List<ReceiptDispatchInstructions> reinstruction = area
				.findReceiptInstructions(id);
		gson = new GsonBuilder().registerTypeAdapter(
				ReceiptDispatchInstructions.class,
				new JsonSerializer<ReceiptDispatchInstructions>() {
					@Override
					public JsonElement serialize(
							ReceiptDispatchInstructions arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						if (arg0.getGateoperatetime() != null) {
							json.addProperty("gateoperatetime", datetimeFormat
									.format(arg0.getGateoperatetime()));
						}
						if (arg0.getUnitoperatetime() != null) {
							json.addProperty("unitoperatetime", datetimeFormat
									.format(arg0.getUnitoperatetime()));
						}
						if (arg0.getInstruction().intValue() == 0) {
							json.addProperty("instruction1", "关闸");
							json.addProperty("instruction2", "不操作");
						} else if (arg0.getInstruction().intValue() == 1) {
							json.addProperty("instruction1", "开闸");
							json.addProperty("instruction2", "不操作");
						} /*
						 * else if (arg0.getInstruction().intValue() == 2) {
						 * json.addProperty("instruction1", "不操作");
						 * json.addProperty("instruction2", "关泵"); }
						 */else if (arg0.getInstruction().intValue() == 3) {
							json.addProperty("instruction1", "不操作");
							json.addProperty("instruction2", "关泵");
						} else if (arg0.getInstruction().intValue() == 4) {
							json.addProperty("instruction1", "不操作");
							json.addProperty("instruction2", "开泵");
						} else if (arg0.getInstruction().intValue() == 5) {
							json.addProperty("instruction1", "关闸");
							json.addProperty("instruction2", "开泵");
						} else if (arg0.getInstruction().intValue() == 6) {
							json.addProperty("instruction1", "关闸");
							json.addProperty("instruction2", "关泵");
						} else if (arg0.getInstruction().intValue() == 7) {
							json.addProperty("instruction1", "开闸");
							json.addProperty("instruction2", "关泵");
						}
						return json;
					}
				}).create();
		json = gson.toJson(reinstruction);
		return json;
	}

	/**
	 * @Title: stations
	 * @Description: TODO(枢纽回显)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/stations", method = RequestMethod.POST)
	@ResponseBody
	public String stations(String id) {
		String json = "";
		List<ReceiptDispatchStations> stations = area.findrestations(id);
		gson = new GsonBuilder().registerTypeAdapter(
				ReceiptDispatchStations.class,
				new JsonSerializer<ReceiptDispatchStations>() {
					@Override
					public JsonElement serialize(ReceiptDispatchStations arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getSid());
						json.addProperty("runcount", arg0.getRuncount());
						json.addProperty("keepcount", arg0.getKeepcount());
						json.addProperty("gatetype", arg0.getGatetype());
						json.addProperty("sid", arg0.getStation().getName());
						json.addProperty("departmentid", arg0.getStation()
								.getDepartmentid());
						return json;
					}
				}).create();
		json = gson.toJson(stations);
		return json;
	}

	/**
	 * @Title: commit
	 * @Description: TODO(提交)
	 * @param @param json
	 * @param @param submit
	 * @param @param id
	 * @param @param reid
	 * @param @param request
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/commit", method = RequestMethod.POST)
	@ResponseBody
	public String commit(String json, String submit, String id, String reid,
			String state, HttpServletRequest request) throws ParseException {
		String stations = "1";
		User user = (User) request.getSession().getAttribute("loginUser");
		ReceiptDispatch receiptDispatch = gson.fromJson(json,ReceiptDispatch.class);
		receiptDispatch.setCreater(user.getUserid());
		String code=activitiUnitService.selectCode("CUFC-FJ");
		if (state.equals("0")) {
			Map<String,Object> maps=new HashMap<String,Object>();
			maps.put("reid", reid);
			maps.put("code", code);
			try {
				List<String> list = new ArrayList<>();
				for (ReceiptDispatchStations receiptDispatchStations : receiptDispatch.getDispatchStations()) {
					list.add(receiptDispatchStations.getSid());
				}
				List<ReceiptDispatchDepartment> rddepartment=new ArrayList<>();
				List<String> departmentList = stationService.findDepartmentIdsBySids(list);
				Set<String> strings = new HashSet<>();
				for (String string : departmentList) {
					strings.add(string);
					ReceiptDispatchDepartment list2= new ReceiptDispatchDepartment();	
					list2.setId(UUID.randomUUID().toString());
					list2.setRdid(reid);
					list2.setDepartmentid(string);
					list2.setState((short)(0));
					rddepartment.add(list2);
				}
				receiptDispatch.setRdDepartments(rddepartment);
				area.deleteDepartment(reid);
				//area.updateAreas(reid);
				area.updateAreasMap(maps);
				area.deleteAreaStation(reid);
				area.insertDepartment(rddepartment);
				area.insertAreaStations(receiptDispatch.getDispatchStations(),
						reid);
				if (receiptDispatch.getReceiptDispatchInstructions() == null) {// 调度指令隐藏
					area.deleteAreaInstruction(reid);
					area.insertAreaInstructions(receiptDispatch.getReceiptDispatchInstructions(),reid);
				}				
				//activitiUnitService.messagePush("autoOrder", user.getUserid()
						//.toString(), receiptDispatch.getReceiptid().toString());
				/*activitiUnitService.completeTask("autoOrder", user.getUserid(),
						receiptDispatch.getReceiptid(), "departmentList",
						new ArrayList<String>(strings));*/
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("objId", reid);
				map.put("departmentList", new ArrayList<String>(strings));
				map.put("type", "B");
				activitiUnitService.statrtByKey("implement", map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			try {
				receiptDispatch.setId(UUID.randomUUID().toString());
				receiptDispatch.setCode(code);
				List<String> list = new ArrayList<>();
				for (ReceiptDispatchStations receiptDispatchStations : receiptDispatch.getDispatchStations()) {
					list.add(receiptDispatchStations.getSid());
				}
				List<ReceiptDispatchDepartment> rddepartment=new ArrayList<>();
				List<String> departmentList = stationService.findDepartmentIdsBySids(list);
				Set<String> strings = new HashSet<>();
				for (String string : departmentList) {
					strings.add(string);
					ReceiptDispatchDepartment list2= new ReceiptDispatchDepartment();	
					list2.setId(UUID.randomUUID().toString());
					list2.setRdid(receiptDispatch.getId());
					list2.setDepartmentid(string);
					list2.setState((short)(0));
					rddepartment.add(list2);
				}
				receiptDispatch.setRdDepartments(rddepartment);
				area.insertAreaDispacth(receiptDispatch, stations);
				/*activitiUnitService.messagePush("autoOrder", user.getUserid()
						.toString(), receiptDispatch.getReceiptid().toString());				
				activitiUnitService.completeTask("autoOrder", user.getUserid(),
						receiptDispatch.getReceiptid(), "departmentList",
						new ArrayList<String>(strings));*/
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("objId", receiptDispatch.getId());
				map.put("departmentList", new ArrayList<String>(strings));
				map.put("type", "B");
				activitiUnitService.statrtByKey("implement", map);
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'提交失败'}";
			}

			return "{'result':true,'code':'"+ code +"'}";
		}
		return "{'result':true,'code':'"+ code +"'}";
	}

	/**
	 * @Title: save
	 * @Description: TODO(保存编辑)
	 * @param @param json
	 * @param @param submit
	 * @param @param id
	 * @param @param reid
	 * @param @param request
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String json, String submit, String id, String reid,
			HttpServletRequest request) throws ParseException {
		String stations = "0";
		User user = (User) request.getSession().getAttribute("loginUser");
		ReceiptDispatch receiptDispatch = gson.fromJson(json,
				ReceiptDispatch.class);
		receiptDispatch.setCreater(user.getUserid());
		if (id.equals("0")) {
			try {
				receiptDispatch.setId(UUID.randomUUID().toString());
				area.insertAreaDispacth(receiptDispatch, stations);
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'保存失败！'}";
			}
		}// 编辑
		else {
			if (submit.equals("0")) {
				stations = "0";
			} else {
				stations = "1";
				try {
					/*	activitiUnitService.messagePush("autoOrder", user
							.getUserid().toString(), receiptDispatch
							.getReceiptid().toString());
					
					 * activitiUnitService.completeTask("autoOrder", user
					 * .getUserid().toString(), receiptDispatch
					 * .getReceiptid().toString());
					 */
					Set<String> strings = new HashSet<>();
					for (ReceiptDispatchStations department : receiptDispatch
							.getDispatchStations()) {
						strings.add(department.getDepartmentid());
					}
				/*	activitiUnitService.completeTask("autoOrder",
							user.getUserid(), receiptDispatch.getReceiptid(),
							"departmentList", new ArrayList<String>(strings));*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try {
				area.updateArea(receiptDispatch, stations);
				area.deleteAreaStation(reid);
				area.insertAreaStations(receiptDispatch.getDispatchStations(),
						reid);
				if (receiptDispatch.getReceiptDispatchInstructions() == null) {
					// 隐藏调度指令
					area.deleteAreaInstruction(reid);
					area.insertAreaInstructions(
							receiptDispatch.getReceiptDispatchInstructions(),
							reid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'保存失败！'}";
			}
		}

		return "{'result':true}";
	}

	/**
	 * @Title: dispatchDelete
	 * @Description: TODO(删除)
	 * @param @param reid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String reid) {
		try {
			area.delete(reid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{'result':true}";
	}

}