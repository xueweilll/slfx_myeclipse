package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecute;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecutePeople;
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.ReceiptDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;
import com.benqzl.service.activiti.ActivitiUnitService;
import com.benqzl.service.dispatch.ReceiptDispatchDepartmentService;
import com.benqzl.service.dispatch.ReceiptDispatchExecuteService;
import com.benqzl.service.dispatch.ReceiptDispatchService;
import com.benqzl.service.dispatch.ReceiptDispatchStationsService;
//import com.benqzl.service.system.EmployeeService;
import com.benqzl.service.system.StationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @ClassName: RDExecuteController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shimh
 * @date 2016年1月11日 上午9:29:10
 * 
 */
@Controller
@RequestMapping("rdexecute")
public class RDExecuteController extends BasicController {
	@Autowired
	private ReceiptDispatchStationsService service;

	public RDExecuteController() {
		super(RDExecuteController.class);
	}

	/**
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/rdexecute");
		return mv;
	}

	@Autowired
	private ActivitiUnitService activitiUnitService;

	/**
	 * 
	 * @Title: rdexelist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/rdexelist", method = RequestMethod.POST)
	@ResponseBody
	public String rdexelist(int page, int rows, String type, String typeDate,
			String r_code, String rd_code, String starttime, String endtime,
			HttpSession session) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		User user = (User) session.getAttribute("loginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", type);
		if (starttime == "" || starttime == null) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (endtime == "" || endtime == null) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (typeDate.equals("1")) {
			map.put("rpid", null);
		} else {
			try {
				// System.out.println(new SimpleDateFormat("SSS").format(new
				// Date()));
				List<Map<String, Object>> l = activitiUnitService
						.findByStation(user.getUserid(), "B");
				// System.out.println(new SimpleDateFormat("SSS").format(new
				// Date()));
				if (l.size() == 0) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("total", 0);
					l = new ArrayList<Map<String, Object>>();
					jsonMap.put("rows", l);
					gson = new Gson();
					strJson = gson.toJson(jsonMap);
					return strJson;
				} else {
					String strwhere = "";
					int k = 0;
					for (Map<String, Object> m : l) {
						String s = "";

						s += "(rd.id = '" + m.get("objId").toString()
								+ "' and rds.sid ='" + m.get("stationId")
								+ "') ";
						/*
						 * @SuppressWarnings("unchecked") List<String> stas =
						 * (List<String>) m.get("stationId"); for (int j = 0; j
						 * < stas.size(); j++) { if (j == 0) { if (stas.size()
						 * == 1) { s += " rds.sid in ('" +
						 * stas.get(j).toString() + "'))"; } else { s +=
						 * " rds.sid in ('" + stas.get(j).toString() + "',"; } }
						 * else if (j == (stas.size() - 1)) { s += " '" +
						 * stas.get(j).toString() + "'))"; } else { s += " '" +
						 * stas.get(j).toString() + "',"; } }
						 */
						if (k == 0) {
							if (l.size() == 1) {
								strwhere = "( " + s + ")";
							} else {
								strwhere = "( " + s + "or ";
							}

						} else if (k == (l.size() - 1)) {
							strwhere += " " + s + " ) ";
						} else {
							strwhere += s + "or ";
						}
						k++;
					}
					if (strwhere.length() == 0) {
						strwhere = " rds.state != 3";
					} else {
						strwhere += " and rds.state != 3";
					}
					map.put("rpid", strwhere);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (r_code == null || r_code.trim().equals("")) {
			map.put("r_code", null);
		} else {
			map.put("r_code", r_code);
		}

		if (rd_code == null || rd_code.trim().equals("")) {
			map.put("rd_code", null);
		} else {
			map.put("rd_code", rd_code);
		}

		List<ReceiptDispatchStations> rds = null;
		try {
			// System.out.println(new SimpleDateFormat("SSS").format(new
			// Date()));
			rds = service.findRdExecuteList(map);
			// System.out.println(new SimpleDateFormat("SSS").format(new
			// Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.findRdExecuteCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", rds);

		gson = new GsonBuilder().registerTypeAdapter(
				ReceiptDispatchStations.class,
				new JsonSerializer<ReceiptDispatchStations>() {

					@Override
					public JsonElement serialize(ReceiptDispatchStations arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();

						json.addProperty("id", arg0.getReceiptDispatch()
								.getId());
						json.addProperty("receiptid", arg0.getReceiptDispatch()
								.getReceiptid());
						// 接收单编号
						json.addProperty("receiptcode", arg0
								.getReceiptDispatch().getReceipt().getCode());
						// 调度单编号
						json.addProperty("rdcode", arg0.getReceiptDispatch()
								.getCode());
						json.addProperty("rcode", arg0.getReceiptDispatch()
								.getReceipt().getCode());
						json.addProperty("stationid", arg0.getSid());
						json.addProperty("rdsid", arg0.getId());
						if (arg0.getStation() != null) {
							json.addProperty("stationname", arg0.getStation()
									.getName());
						}
						if (arg0.getStation() != null) {
							json.addProperty("departmentid", arg0.getStation()
									.getDepartmentid());
						}
						// 发起人
						json.addProperty("launcher", arg0.getReceiptDispatch()
								.getReceipt().getLauncher());
						// 发起时间
						json.addProperty(
								"launchTime",
								datetimeFormat.format(arg0.getReceiptDispatch()
										.getReceipt().getLaunchtime()));
						json.addProperty(
								"endtime",
								datetimeFormat.format(arg0.getReceiptDispatch()
										.getReceipt().getEndtime()));
						json.addProperty("creater", arg0.getReceiptDispatch()
								.getNames().getCreateName());
						// 调度令接收时间
						json.addProperty(
								"receiptetime",
								datetimeFormat.format(arg0.getReceiptDispatch()
										.getReceipt().getReceiptetime()));
						// 制单时间
						json.addProperty("createtime", datetimeFormat
								.format(arg0.getReceiptDispatch()
										.getCreatetime()));
						json.addProperty("excuteid", UUID.randomUUID()
								.toString());// EXCUTEID执行单id
						// 处理状态（枢纽的执行状态）
						Long state = arg0.getState();
						if (state == 0) {
							json.addProperty("state", "未查看");
						} else if (state == 1) {
							json.addProperty("state", "已查看未实施");
						} else if (state == 2) {
							json.addProperty("state", "已实施未完成");
						} else if (state == 3) {
							json.addProperty("state", "完成");
						}

						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	/**
	 * @Title: viewrdexecutemobile
	 * @Description: TODO(mobile端查看)
	 * @param @param rdid
	 * @param @param sid
	 * @param @param rdstationid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "viewrdexecutemobile", method = RequestMethod.POST)
	@ResponseBody
	public String viewrdexecutemobile(String rdid, String sid,
			String rdstationid) {
		String strJson = "";
		HashMap<String, String> map = new HashMap<>();
		map.put("rdid", rdid);
		map.put("sid", sid);
		ReceiptDispatchExecute rde = service
				.selectExecuteByStationId(rdstationid);
		List<ReceiptDispatchExecutePeople> list = rde.getRdeplist();
		String userids = "[";
		for (int i = 0; i < list.size(); i++) {
			userids += "'" + list.get(i).getUserid() + "',";
		}
		userids = userids.substring(0, userids.length() - 1);
		userids += "]";
		// mv.addObject("userids", userids);
		gson = new GsonBuilder().registerTypeAdapter(
				ReceiptDispatchExecute.class,
				new JsonSerializer<ReceiptDispatchExecute>() {

					@Override
					public JsonElement serialize(ReceiptDispatchExecute arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("rdid", arg0.getRds()
								.getReceiptDispatch().getId());// 调度单id
						json.addProperty("sid", arg0.getRds().getSid());// 执行枢纽id
						// 执行枢纽名称
						json.addProperty("sname", arg0.getS().getName());
						// 调度单编号
						json.addProperty("startouterlevel",
								arg0.getStartouterlevel());// 开机时外河水位
						json.addProperty("startinlandlevel",
								arg0.getStartinlandlevel());// 开机时内河水位
						json.addProperty("stopouterlevel",
								arg0.getStopouterlevel());// 停机时外河水位
						json.addProperty("stopinlandlevel",
								arg0.getStopinlandlevel());// 停机时内河水位
						json.addProperty("memo", arg0.getMemo());// 备注
						return json;
					}
				}).create();
		strJson = gson.toJson(rde);
		return strJson;
	}

	/**
	 * 
	 * @Title: addrdexecute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sid
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "addrdexecute", method = RequestMethod.GET)
	public ModelAndView addrdexecute(String rdid, String sid,
			String rdstationid, String edit) {
		ModelAndView mv = new ModelAndView();
		HashMap<String, String> map = new HashMap<>();
		map.put("rdid", rdid);
		map.put("sid", sid);
		if (edit == null) {
			ReceiptDispatchStations receiptDispatchStations = service
					.selectByRDIDAndSId(map);
			mv.addObject("receiptDispatchStations", receiptDispatchStations);
			mv.setViewName("dispatch/addrdexecute");
		} else {
			ReceiptDispatchExecute rde = service
					.selectExecuteByStationId(rdstationid);
			List<ReceiptDispatchExecutePeople> list = rde.getRdeplist();
			String userids = "[";
			for (int i = 0; i < list.size(); i++) {
				userids += "'" + list.get(i).getUserid() + "',";
			}
			userids = userids.substring(0, userids.length() - 1);
			userids += "]";
			mv.addObject("userids", userids);
			mv.addObject("rde", rde);
			mv.setViewName("dispatch/editrdexecute");
		}
		return mv;
	}

	@RequestMapping(value = "viewrdexecute", method = RequestMethod.GET)
	public ModelAndView viewrdexecute(String rdid, String sid,
			String rdstationid, String edit) {
		ModelAndView mv = new ModelAndView();
		HashMap<String, String> map = new HashMap<>();
		map.put("rdid", rdid);
		map.put("sid", sid);
		// if (edit == null) {
		// ReceiptDispatchStations receiptDispatchStations = service
		// .selectByRDIDAndSId(map);
		// mv.addObject("receiptDispatchStations", receiptDispatchStations);
		// mv.setViewName("dispatch/addrdexecute");
		// } else {
		ReceiptDispatchExecute rde = service
				.selectExecuteByStationId(rdstationid);
		List<ReceiptDispatchExecutePeople> list = rde.getRdeplist();
		String userids = "[";
		for (int i = 0; i < list.size(); i++) {
			userids += "'" + list.get(i).getUserid() + "',";
		}
		userids = userids.substring(0, userids.length() - 1);
		userids += "]";
		mv.addObject("userids", userids);
		mv.addObject("rde", rde);
		mv.setViewName("dispatch/vieweditrdexecute");
		// }
		return mv;
	}

	/**
	 * 
	 * @Title: findUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	@ResponseBody
	public String findUser() {
		String json = "";
		List<User> users = service.findUser();
		json = gson.toJson(users);
		return json;
	}

	@Autowired
	StationService stationService;

	/**
	 * 
	 * @Title: findUnitByExecuteid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param executeid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "findUnitByExecuteid", method = RequestMethod.POST)
	@ResponseBody
	public String findUnitByExecuteid(String executeid) {
		String json = "";
		List<ReceiptDispatchExecuteUnits> list = service
				.findUnitByExecuteid(executeid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(
				ReceiptDispatchExecuteUnits.class,
				new JsonSerializer<ReceiptDispatchExecuteUnits>() {
					@Override
					public JsonElement serialize(
							ReceiptDispatchExecuteUnits rdu, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("unit", rdu.getUnit().getName());
						o.addProperty("uid", rdu.getUnitid());
						o.addProperty("begintime",
								datetimeFormat.format((rdu.getBegintime())));
						if (rdu.getEndtime() != null) {
							o.addProperty("endtime",
									datetimeFormat.format((rdu.getEndtime())));
						}
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}

	/**
	 * 
	 * @Title: findGateByExecuteid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param executeid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "findGateByExecuteid", method = RequestMethod.POST)
	@ResponseBody
	public String findGateByExecuteid(String executeid) {
		String json = "";
		List<ReceiptDispatchExecuteGate> list = service
				.findGateByExecuteid(executeid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(
				ReceiptDispatchExecuteGate.class,
				new JsonSerializer<ReceiptDispatchExecuteGate>() {
					@Override
					public JsonElement serialize(
							ReceiptDispatchExecuteGate sde, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("sid", sde.getGate().getName());
						o.addProperty("gid", sde.getGid());
						if (sde.getOperatetime() != null) {
							o.addProperty("operatetime", datetimeFormat
									.format((sde.getOperatetime())));
						}
						if (sde.getOperate().intValue() == 0) {
							o.addProperty("operate", "关闸");
						} else if (sde.getOperate().intValue() == 1) {
							o.addProperty("operate", "开闸");
						} else if (sde.getOperate().intValue() == 2) {
							o.addProperty("operate", "常关");
						}
						o.addProperty("operatetype", sde.getOperate());
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}

	/**
	 * 
	 * @Title: unit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "findUnits", method = RequestMethod.POST)
	@ResponseBody
	public String findUnits(String sid) {
		String json = "";
		gson = new Gson();
		List<Unit> list = service.findUnits(sid);
		json = gson.toJson(list);
		return json;
	}

	@RequestMapping(value = "/findGates", method = RequestMethod.POST)
	@ResponseBody
	public String findGates(String sid) {
		String json = "";
		List<Gate> getes = service.findGates(sid);
		json = gson.toJson(getes);
		return json;
	}

	/**
	 * 
	 * @Title: look
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param rdsid
	 * @param @param session
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "look", method = RequestMethod.POST)
	@ResponseBody
	public String look(String rdsid, HttpSession session) {
		String result = "";
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			User user = (User) session.getAttribute("loginUser");
			map.put("id", rdsid);
			map.put("state", 1);
			map.put("receipter", user.getUserid());
			map.put("receipttime", new Date());
			service.updateRDStationState_look(map);
			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}
		return result;
	}

	@Autowired
	private ReceiptDispatchService receiptDispatchService;

	@RequestMapping(value = "complete", method = RequestMethod.POST)
	@ResponseBody
	public String complete(String rdsid, String rdid, String rid,
			HttpSession session) {
		String result = "";
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", rdsid);
			map.put("state", 3);
			map.put("rdid", rdid);
			service.updateRDStationState(map);
			List<ReceiptDispatchStations> list = receiptDispatchService
					.selectStationsByRDID(map);
			boolean isComplete = true;
			for (ReceiptDispatchStations s : list) {
				if (s.getId().equals(rdsid)) {
					continue;
				}
				if (s.getState() != 3) {
					isComplete = false;
				}
			}
			if (isComplete) {
				// User user = (User) session.getAttribute("loginUser");
				// activitiUnitService.completeTask("station", user.getUserid(),
				// rid);
			}

			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}
		return result;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	/*
	 * @Autowired private EmployeeService employeeService;
	 */

	@Autowired
	private ReceiptDispatchDepartmentService receiptDispatchDepartmentService;

	@Autowired
	private ReceiptDispatchExecuteService receiptDispatchExecuteService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, String json, String state,
			String rdid) {
		// System.out.println(json);
		ReceiptDispatchExecute execute = gson.fromJson(json,
				ReceiptDispatchExecute.class);
		// System.out.println(execute.getExecuteunits().size());
		if (execute.getId().equals("0")) {
			execute.setId(UUID.randomUUID().toString());
			// 机组指令为空的情况
			if (execute.getExecuteunits().size() > 0) {
				for (ReceiptDispatchExecuteUnits u : execute.getExecuteunits()) {
					u.setId(UUID.randomUUID().toString());
					u.setExecuteid(execute.getId());// 主表id
				}
			}
			// 闸门指令为空
			if (execute.getExecutegate().size() > 0) {
				for (ReceiptDispatchExecuteGate g : execute.getExecutegate()) {
					g.setId(UUID.randomUUID().toString());
					g.setRdexecuteid(execute.getId());// 主表id
				}
			}
			// 人员
			for (ReceiptDispatchExecutePeople p : execute.getRdeplist()) {
				p.setId(UUID.randomUUID().toString());
				p.setExecteid(execute.getId());
			}
			try {
				service.insertSelective(execute);
				if (execute.getExecutegate().size() > 0) {
					receiptDispatchExecuteService.insertGates(execute
							.getExecutegate());
				}
				receiptDispatchExecuteService.insertPeoples(execute
						.getRdeplist());
				if (execute.getExecuteunits().size() > 0) {
					receiptDispatchExecuteService.insertUnits(execute
							.getExecuteunits());
				}
				ReceiptDispatchStations rds = service
						.selectByPrimaryKey(execute.getRdstationid());
				if (state.equals("0")) {
					rds.setState((long) 2);
					service.updateByPrimaryKeySelective(rds);// 更改station表中状态为实施中，
				} else if (state.equals("1")) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("id", execute.getRdstationid());
					rds.setState((long) 3);
					service.updateByPrimaryKeySelective(rds);// 更改station表中状态为完成，
					// 根据分解单id获得调度单id（流程控制）
					// ReceiptDispatch rd =
					// service.selectReceiptDispatchByRdid(rds.getRdispatchid());
					// String receiptid = rd.getReceiptid();
					// 判断啊相同部门中其他枢纽是否实施完毕，完毕更改department状态
					boolean isComplete = true;
					Station s = stationService.selectByPrimaryKey(execute
							.getSid());
					HashMap<String, Object> rddmap = new HashMap<>();
					rddmap.put("id", rds.getRdispatchid());
					rddmap.put("departmentid", s.getDepartmentid());
					int count = receiptDispatchDepartmentService
							.selectByPk(rddmap);
					if (count > 1) {
						isComplete = false;
					}
					User user = (User) request.getSession().getAttribute(
							"loginUser");
					// activitiUnitService.messagePushByDorSid("station",
					// user.getUserid(), receiptid, execute.getSid());
					// activitiUnitService.completeTaskByDorS("station",
					// user.getUserid(), receiptid, execute.getSid(), null,
					// null);

					activitiUnitService.completeTaskByStation(user.getUserid(),
							rds.getRdispatchid(), execute.getSid(), null);
					if (isComplete) {
						HashMap<String, Object> rsMap = new HashMap<>();
						rsMap.put("id", rds.getRdispatchid());
						rsMap.put("departmentid", s.getDepartmentid());
						receiptDispatchDepartmentService.update(rsMap);
					}
				}
				return "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'保存出错！'}";
			}
		} else {
			// 编辑
			// 机组指令为空的情况
			if (execute.getExecuteunits().size() > 0) {
				for (ReceiptDispatchExecuteUnits u : execute.getExecuteunits()) {
					u.setId(UUID.randomUUID().toString());
					u.setExecuteid(execute.getId());// 主表id
				}
			}
			// 闸门指令为空
			if (execute.getExecutegate().size() > 0) {
				for (ReceiptDispatchExecuteGate g : execute.getExecutegate()) {
					g.setId(UUID.randomUUID().toString());
					g.setRdexecuteid(execute.getId());// 主表id
				}
			}
			// 人员
			for (ReceiptDispatchExecutePeople p : execute.getRdeplist()) {
				p.setId(UUID.randomUUID().toString());
				p.setExecteid(execute.getId());
			}

			try {
				// 先删除子表中数据
				service.deleteExecutePeople(execute.getId());
				service.deleteExecuteUnit(execute.getId());
				service.deleteExecuteGate(execute.getId());
				service.updateExecute(execute);
				if (execute.getExecutegate().size() > 0) {
					receiptDispatchExecuteService.insertGates(execute
							.getExecutegate());
				}
				receiptDispatchExecuteService.insertPeoples(execute
						.getRdeplist());
				if (execute.getExecuteunits().size() > 0) {
					receiptDispatchExecuteService.insertUnits(execute
							.getExecuteunits());
				}

				ReceiptDispatchStations rds = service
						.selectByPrimaryKey(execute.getRdstationid());
				if (state.equals("0")) {

					rds.setState((long) 2);
					service.updateByPrimaryKeySelective(rds);// 更改station表中状态为实施中，
				} else if (state.equals("1")) {

					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("id", rds.getRdispatchid());
					// ReceiptDispatch rd =
					// service.selectReceiptDispatchByRdid(rds.getRdispatchid());
					rds.setState((long) 3);
					service.updateByPrimaryKeySelective(rds);
					// String receiptid = rd.getReceiptid();
					boolean isComplete = true;
					Station s = stationService.selectByPrimaryKey(execute
							.getSid());
					HashMap<String, Object> rddmap = new HashMap<>();
					rddmap.put("id", rds.getRdispatchid());
					rddmap.put("departmentid", s.getDepartmentid());
					int count = receiptDispatchDepartmentService
							.selectByPk(rddmap);
					if (count > 1) {
						isComplete = false;
					}
					User user = (User) request.getSession().getAttribute(
							"loginUser");
					/*
					 * Map<String, Object> mu= new HashMap<String, Object>();
					 * mu.put("sdid", rds.getRdispatchid()); mu.put("userid",
					 * user.getUserid()); List<ReceiptDispatchStations> stations
					 * = receiptDispatchService.selectStationByUserid(mu);
					 * List<String> l = new ArrayList<>();
					 * for(ReceiptDispatchStations ss : stations){
					 * l.add(ss.getSid()); }
					 */
					// activitiUnitService.messagePushByDorSid("station",
					// user.getUserid(), receiptid, execute.getSid());
					// activitiUnitService.completeTaskByDorS("station",
					// user.getUserid(), receiptid, execute.getSid(), null,
					// null);
					activitiUnitService.completeTaskByStation(user.getUserid(),
							rds.getRdispatchid(), execute.getSid(), null);
					if (isComplete) {
						HashMap<String, Object> rsMap = new HashMap<>();
						rsMap.put("id", rds.getRdispatchid());
						rsMap.put("departmentid", s.getDepartmentid());
						receiptDispatchDepartmentService.update(rsMap);
					}
				}
				return "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'保存出错！'}";
			}

		}

	}

	/**
	 * @Title: saveExecuteUnitMobile
	 * @Description: TODO(手机端保存内外河水位)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @param startouterlevel
	 * @param @param startinlandlevel
	 * @param @param stopouterlevel
	 * @param @param stopinlandlevel
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveExecuteUnitMobile", method = RequestMethod.POST)
	@ResponseBody
	public String saveExecuteUnitMobile(String jsonStr,
			HttpServletRequest request, String startouterlevel,
			String excuteid, String rdsid, String unitid, String btime,
			String etime, String type, String startinlandlevel,
			String stopouterlevel, String stopinlandlevel) {
		gson = new Gson();
		ReceiptDispatchExecute rde = gson.fromJson(jsonStr,
				ReceiptDispatchExecute.class);
		String result = "";
		if (startouterlevel != null && startouterlevel != "") {
			rde.setStartouterlevel(startouterlevel);
		}
		if (startinlandlevel != null && startinlandlevel != "") {
			rde.setStartinlandlevel(startinlandlevel);
		}
		if (stopouterlevel != null && stopouterlevel != "") {
			rde.setStopouterlevel(stopouterlevel);
		}
		if (stopinlandlevel != null && stopinlandlevel != "") {
			rde.setStopinlandlevel(stopinlandlevel);
		}
		if (type.equals("1")) {// 判断是否是已经实施
			int isOnly = service.countExecuteByStationId(rde.getRdstationid());
			if (isOnly == 0) {
				try {
					// rde.setId(UUID.randomUUID().toString());
					service.insertSelective(rde);
					result = "{'result':true}";
				} catch (Exception e) {
					e.printStackTrace();
					result = "{'result':false,'msg':'操作失敗！'}";
				}
			}
		} else {
			try {
				ReceiptDispatchExecute rdes = service
						.selectExecuteByStationId(rde.getRdstationid());
				rde.setId(rdes.getId());
				service.updateExecute(rde);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'操作失敗！'}";
			}
		}
		ReceiptDispatchExecuteUnits rdeu = new ReceiptDispatchExecuteUnits();
		rdeu.setId(UUID.randomUUID().toString());
		rdeu.setUnitid(unitid);
		rdeu.setExecuteid(rde.getId());
		try {
			rdeu.setBegintime(datetimeFormat.parse(btime));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			if (etime != null && etime != "" && etime.length() > 0) {
				rdeu.setEndtime(datetimeFormat.parse(etime));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			service.insertSelective(rdeu);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}
		return result;
	}

	/**
	 * @Title: updateExecuteMobile
	 * @Description: TODO(手机端更新内外河水位)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @param startouterlevel
	 * @param @param startinlandlevel
	 * @param @param stopouterlevel
	 * @param @param stopinlandlevel
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/updateExecuteMobile", method = RequestMethod.POST)
	@ResponseBody
	public String updateExecuteMobile(String excuteid, String startouterlevel,
			HttpSession session, String startinlandlevel, String jsonStr,
			String type, String stopouterlevel, String stopinlandlevel) {
		String result = "";
		User user = (User) session.getAttribute("loginUser");
		gson = new Gson();
		ReceiptDispatchExecute rde = gson.fromJson(jsonStr,
				ReceiptDispatchExecute.class);
		if (startouterlevel != null && startouterlevel != "") {
			rde.setStartouterlevel(startouterlevel);
		}
		if (startinlandlevel != null && startinlandlevel != "") {
			rde.setStartinlandlevel(startinlandlevel);
		}
		if (stopouterlevel != null && stopouterlevel != "") {
			rde.setStopouterlevel(stopouterlevel);
		}
		if (stopinlandlevel != null && stopinlandlevel != "") {
			rde.setStopinlandlevel(stopinlandlevel);
		}
		/*
		 * if(type.equals("1")){//判断是否是已经实施 try {
		 * rde.setId(UUID.randomUUID().toString());
		 * service.insertSelective(rde); result = "{'result':true}"; } catch
		 * (Exception e) { e.printStackTrace(); result =
		 * "{'result':false,'msg':'操作失敗！'}"; } }else{
		 */
		try {
			ReceiptDispatchExecute rdes = service.selectExecuteByStationId(rde
					.getRdstationid());
			rde.setId(rdes.getId());
			service.updateExecute(rde);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}
		/* } */
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", rde.getRdstationid());
			map.put("state", 2);
			map.put("receipter", user.getUserid());
			map.put("receipttime", new Date());
			service.updateRDStationState_look(map);
			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}
		return result;
	}
	
	@RequestMapping(value = "/checkUnitComplete", method = RequestMethod.POST)
	@ResponseBody
	public String checkUnitComplete(String uid, String executeid){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("unitid", uid);
		if(!executeid.equals("0")){
			map.put("executeid", executeid);
		}
		List<ReceiptDispatchExecuteUnits> list = receiptDispatchExecuteService.selectByCheck(map);
		if(list.size() > 0){
			return "{'result':false}";
		}else{
			return "{'result':true}";
		}
		
	}

}