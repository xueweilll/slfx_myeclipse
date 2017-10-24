package com.benqzl.controller.patrol;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.patrol.Patrol;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.PatrolApprovalService;
/*import com.benqzl.service.patrol.PatrolConfirmService;
import com.benqzl.unit.ActivitiUnitService;*/
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
* @ClassName: PatrolConfirmController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2016年1月20日 下午4:33:24  
*
 */
@Controller
@RequestMapping("/patrolconfirm")
public class PatrolConfirmController extends BasicController {
	/*@Autowired
	private PatrolConfirmService service;*/
	@Resource
	private PatrolApprovalService pas;
	public PatrolConfirmController() {
		super(PatrolConfirmController.class);
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
		mv.setViewName("patrol/patrolconfirm");
		return mv;
	}

	/*@Autowired
	private ActivitiUnitService activitiUnitService;*/

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
	@RequestMapping(value = "/patrolconfirmlist", method = RequestMethod.POST)
	@ResponseBody
	public String patrolconfirmlist(int page,int rows,String all,String begin,String end,HttpServletRequest request) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(begin!=null && !begin.equals("")){
			map.put("begin", begin);
		}
		if(end!=null && !end.equals("")){
			map.put("end", end);
		}
		User user=(User) request.getSession().getAttribute("loginUser");
		map.put("operater", user.getUserid());
		List<Patrol> list=null;
		if(all.equals("1")){
			list=pas.findPatrolDetailProblemByPage3(map);
		}else{
			list=pas.findPatrolDetailProblemByPage3NotAll(map);
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total=0;
		if(all.equals("1")){
			total= pas.pageCount3(map);
		}else{
			total= pas.pageCount3NotAll(map);
		}				
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(Patrol.class,
				new JsonSerializer<Patrol>() {
					@Override
					public JsonElement serialize(Patrol p, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("patrolid",p.getId());
						o.addProperty("code", p.getPpd().getPatrolplan().getCode());
						o.addProperty("patroltime",datetimeFormat.format(p.getPatroltime()));
						o.addProperty("creater", p.getCreate().getEmployee().getName());
						o.addProperty("createtime",datetimeFormat.format(p.getCreatetime()));
						o.addProperty("stationname",p.getCreate().getEmployee().getStation().getName());
						Long state=p.getPatrolpeople().getState();
						if(state.intValue()==0){
							o.addProperty("state", "未确认");
						}else if(state.intValue()==1){
							o.addProperty("state", "已确认");
						}
						System.out.println("");
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		return json;
	}

	
	
	/**
	 * 
	* @Title: patrolplaninfo  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param id
	* @param @return    设定文件  
	* @return ModelAndView    返回类型  
	* @throws
	 */
	@RequestMapping(value = "patrolconfirminfo", method = RequestMethod.GET)
	public ModelAndView patrolconfirminfo(String id) {
		ModelAndView mv = new ModelAndView();
//		if (!id.equals("0")) {
//			Foler foler = service.selectByPrimaryKey(id);
//			mv.addObject("foler", foler);
//		}
		mv.setViewName("patrol/patrolconfirminfo");
		return mv;
	}
	@RequestMapping(value = "patrolok", method = RequestMethod.GET)
	public ModelAndView patrolok(String patrolid) {
		ModelAndView mv = new ModelAndView();
		Patrol patrol=pas.findPatrolById2(patrolid);
		mv.addObject("patroltime", datetimeFormat.format(patrol.getPatroltime()));	
		mv.addObject("patrol",patrol);
		mv.setViewName("patrol/patrolok");
		return mv;
	}
	@RequestMapping(value = "patrolokdbl", method = RequestMethod.GET)
	public ModelAndView patrolokdbl(String patrolid) {
		ModelAndView mv = new ModelAndView();
		Patrol patrol=pas.findPatrolById2(patrolid);
		mv.addObject("patroltime", datetimeFormat.format(patrol.getPatroltime()));	
		mv.addObject("patrol",patrol);
		mv.setViewName("patrol/patrolokdbl");
		return mv;
	}
//
//	/**
//	 * 
//	 * @Title: findUser
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
//	@ResponseBody
//	public String findUser() {
//		String json = "";
//		List<User> users = service.findUser();
//		json = gson.toJson(users);
//		return json;
//	}

//	/**
//	 * 
//	 * @Title: findUnitByExecuteid
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @param executeid
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping(value = "findUnitByExecuteid", method = RequestMethod.POST)
//	@ResponseBody
//	public String findUnitByExecuteid(String executeid) {
//		String json = "";
//		List<ReceiptDispatchExecuteUnits> list = service
//				.findUnitByExecuteid(executeid);
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("rows", list);
//		gson = new GsonBuilder().registerTypeAdapter(
//				ReceiptDispatchExecuteUnits.class,
//				new JsonSerializer<ReceiptDispatchExecuteUnits>() {
//					@Override
//					public JsonElement serialize(
//							ReceiptDispatchExecuteUnits rdu, Type typeOfSrc,
//							JsonSerializationContext context) {
//						JsonObject o = new JsonObject();
//						o.addProperty("unit", rdu.getUnit().getName());
//						o.addProperty("unitid", rdu.getUnitid());
//						o.addProperty("begintime",
//								datetimeFormat.format((rdu.getBegintime())));
//						o.addProperty("endtime",
//								datetimeFormat.format((rdu.getEndtime())));
//						return o;
//					}
//				}).create();
//		json = gson.toJson(jsonMap);
//		return json;
//	}
//
//	/**
//	 * 
//	 * @Title: findGateByExecuteid
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @param executeid
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping(value = "findGateByExecuteid", method = RequestMethod.POST)
//	@ResponseBody
//	public String findGateByExecuteid(String executeid) {
//		String json = "";
//		List<ReceiptDispatchExecuteGate> list = service
//				.findGateByExecuteid(executeid);
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("rows", list);
//		gson = new GsonBuilder().registerTypeAdapter(
//				ReceiptDispatchExecuteGate.class,
//				new JsonSerializer<ReceiptDispatchExecuteGate>() {
//					@Override
//					public JsonElement serialize(
//							ReceiptDispatchExecuteGate sde, Type typeOfSrc,
//							JsonSerializationContext context) {
//						JsonObject o = new JsonObject();
//						o.addProperty("sid", sde.getGate().getName());
//						o.addProperty("sidid", sde.getGid());
//						o.addProperty("operatetime",
//								datetimeFormat.format((sde.getOperatetime())));
//						if (sde.getOperate().intValue() == 0) {
//							o.addProperty("operate", "关闸");
//						} else if (sde.getOperate().intValue() == 1) {
//							o.addProperty("operate", "开闸");
//						}
//						return o;
//					}
//				}).create();
//		json = gson.toJson(jsonMap);
//		return json;
//	}

//	@RequestMapping(value = "findUnits", method = RequestMethod.POST)
//	@ResponseBody
//	public String findUnits(String sid) {
//		String json = "";
//		List<Unit> list = service.findUnits(sid);
//		json = gson.toJson(list);
//		return json;
//	}
//
//	@RequestMapping(value = "/findGates", method = RequestMethod.POST)
//	@ResponseBody
//	public String findGates(String sid) {
//		String json = "";
//		List<Gate> getes = service.findGates(sid);
//		json = gson.toJson(getes);
//		return json;
//	}

//	/**
//	 * 
//	 * @Title: look
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @param rdsid
//	 * @param @param session
//	 * @param @return 设定文件
//	 * @return String 返回类型
//	 * @throws
//	 */
//	@RequestMapping(value = "look", method = RequestMethod.POST)
//	@ResponseBody
//	public String look(String rdsid, HttpSession session) {
//		String result = "";
//		try {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			User user = (User) session.getAttribute("loginUser");
//			map.put("id", rdsid);
//			map.put("state", 1);
//			map.put("receipter", user.getUserid());
//			map.put("receipttime", new Date());
//			service.updateRDStationState_look(map);
//			result = "{'result':true}";
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			result = "{'result':false,'msg':'操作失敗！'}";
//		}
//		return result;
//	}

//	@Autowired
//	private ReceiptDispatchService receiptDispatchService;
//
//	@RequestMapping(value = "complete", method = RequestMethod.POST)
//	@ResponseBody
//	public String complete(String rdsid, String rdid, String rid,
//			HttpSession session) {
//		String result = "";
//		try {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("id", rdsid);
//			map.put("state", 3);
//			service.updateRDStationState(map);
//			List<ReceiptDispatchStations> list = receiptDispatchService
//					.selectStationsByRDID(rdid);
//			boolean isComplete = true;
//			for (ReceiptDispatchStations s : list) {
//				if (s.getId().equals(rdsid)) {
//					continue;
//				}
//				if (s.getState() != 3) {
//					isComplete = false;
//				}
//			}
//			if (isComplete) {
//				User user = (User) session.getAttribute("loginUser");
//				activitiUnitService.completeTask("station", user.getUserid(),
//						rid);
//			}
//
//			result = "{'result':true}";
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			result = "{'result':false,'msg':'操作失敗！'}";
//		}
//		return result;
//	}

//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	@ResponseBody
//	public String save(String jsonStr, HttpServletRequest request) {
//		String[] strs = jsonStr.split("/");
//		String id = strs[0];
//		String sid = strs[1];
//		String rdid = strs[2];
//		String executers[] = strs[3].split(",");
//		String memo = strs[4];
//
//		String[] unitnames = strs[5].split(",");
//		String[] begintime = strs[6].split(",");
//		String[] endtime = strs[7].split(",");
//
//		String[] gatenames = strs[8].split(",");
//		String[] operatetime = strs[9].split(",");
//		String[] operate = strs[10].split(",");
//		String executeid = strs[11];
//		
//		String aaa = "";
//		if (strs.length > 12) {
//			aaa = strs[12];
//		}
//
//		ReceiptDispatchExecute rde = new ReceiptDispatchExecute();
//		// 新增
//		if (executeid.equals("0")) {
//			rde.setId(UUID.randomUUID().toString());
//			rde.setSid(sid);
//			rde.setRdstationid(id); // 接收调令枢纽信息主键
//			rde.setMemo(memo);
//			try {
//				service.insertSelective(rde);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			for (int i = 0; i < unitnames.length; i++) {
//				String unitname = unitnames[i];
//				String btime = begintime[i];
//				String etime = endtime[i];
//
//				HashMap<String, String> map = new HashMap<>();
//				map.put("unitName", unitname);
//				map.put("sid", sid);
//				Unit unit = service.selectUnitByName(map);
//				ReceiptDispatchExecuteUnits rdeu = new ReceiptDispatchExecuteUnits();
//				rdeu.setId(UUID.randomUUID().toString());
//				rdeu.setUnitid(unit.getId());
//				rdeu.setExecuteid(rde.getId());
//				try {
//					rdeu.setBegintime(datetimeFormat.parse(btime));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					rdeu.setEndtime(datetimeFormat.parse(etime));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					service.insertSelective(rdeu);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			for (int i = 0; i < gatenames.length; i++) {
//				String gatename = gatenames[i];
//				String opertime = operatetime[i];
//				String oper = operate[i];
//
//				HashMap<String, String> map = new HashMap<>();
//				map.put("gateName", gatename);
//				map.put("sid", sid);
//				Gate gate = service.selectGateByName(map);
//				ReceiptDispatchExecuteGate rdeg = new ReceiptDispatchExecuteGate();
//				rdeg.setId(UUID.randomUUID().toString());
//				rdeg.setGid(gate.getId());
//				rdeg.setRdexecuteid(rde.getId());
//				if (oper.equals("关闸")) {
//					rdeg.setOperate(new Long(0));
//				} else if (oper.equals("开闸")) {
//					rdeg.setOperate(new Long(1));
//				}
//
//				try {
//					rdeg.setOperatetime(datetimeFormat.parse(opertime));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					service.insertSelective(rdeg);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			for (String executer : executers) {
//				ReceiptDispatchExecutePeople rdep = new ReceiptDispatchExecutePeople();
//				rdep.setId(UUID.randomUUID().toString());
//				rdep.setExecteid(rde.getId());
//				rdep.setPeopletype((long) 0);
//				rdep.setUserid(executer);
//
//				try {
//					service.insertSelective(rdep);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			ReceiptDispatchStations rds = service.selectByPrimaryKey(id);
//			if (aaa.equals("")) {
//				rds.setState((long) 2);
//				service.updateByPrimaryKeySelective(rds);
//			} else {
//				try {
//					List<ReceiptDispatchStations> list = receiptDispatchService
//							.selectStationsByRDID(rdid);
//					//根据rdid(dis_rd_stations rdispatchid)查询dis_receipt_dispatch的receptid
//					ReceiptDispatch rd = service.selectReceiptDispatchByRdid(rdid);
//					String receiptid =rd.getReceiptid();
//					boolean isComplete = true;
//					for (ReceiptDispatchStations s : list) {
//						if (s.getId().equals(id)) {
//							continue;
//						}
//						if (s.getState() != 3) {
//							isComplete = false;
//						}
//					}
//					if (isComplete) {
//						User user = (User) request.getSession().getAttribute("loginUser");
//						activitiUnitService.completeTask("station", user.getUserid(),
//								receiptid);
//					}
//					rds.setState((long) 3);
//					service.updateByPrimaryKeySelective(rds);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//			// 编辑
//		} else {
//			rde.setId(executeid);
//			rde.setSid(sid);
//			rde.setRdstationid(id); // 接收调令枢纽信息主键
//			rde.setMemo(memo);
//
//			try {
//				service.updateExecute(rde);
//				// service.deleteExecutePeople(executeid);
//				service.deleteExecuteUnit(executeid);
//				service.deleteExecuteGate(executeid);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			for (int i = 0; i < unitnames.length; i++) {
//				String unitname = unitnames[i];
//				String btime = begintime[i];
//				String etime = endtime[i];
//
//				HashMap<String, String> map = new HashMap<>();
//				map.put("unitName", unitname);
//				map.put("sid", sid);
//				Unit unit = service.selectUnitByName(map);
//				ReceiptDispatchExecuteUnits rdeu = new ReceiptDispatchExecuteUnits();
//				rdeu.setId(UUID.randomUUID().toString());
//				rdeu.setUnitid(unit.getId());
//				rdeu.setExecuteid(rde.getId());
//				try {
//					rdeu.setBegintime(datetimeFormat.parse(btime));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					rdeu.setEndtime(datetimeFormat.parse(etime));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					service.insertSelective(rdeu);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			for (int i = 0; i < gatenames.length; i++) {
//				String gatename = gatenames[i];
//				String opertime = operatetime[i];
//				String oper = operate[i];
//
//				HashMap<String, String> map = new HashMap<>();
//				map.put("gateName", gatename);
//				map.put("sid", sid);
//				Gate gate = service.selectGateByName(map);
//				ReceiptDispatchExecuteGate rdeg = new ReceiptDispatchExecuteGate();
//				rdeg.setId(UUID.randomUUID().toString());
//				rdeg.setGid(gate.getId());
//				rdeg.setRdexecuteid(rde.getId());
//				if (oper.equals("关闸")) {
//					rdeg.setOperate(new Long(0));
//				} else if (oper.equals("开闸")) {
//					rdeg.setOperate(new Long(1));
//				}
//
//				try {
//					rdeg.setOperatetime(datetimeFormat.parse(opertime));
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					service.insertSelective(rdeg);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			// for (String executer : executers) {
//			// ReceiptDispatchExecutePeople rdep = new
//			// ReceiptDispatchExecutePeople();
//			// rdep.setId(UUID.randomUUID().toString());
//			// rdep.setExecteid(rde.getId());
//			// rdep.setPeopletype((long) 0);
//			// rdep.setUserid(executer);
//			//
//			// try {
//			// service.insertSelective(rdep);
//			// } catch (Exception e) {
//			// e.printStackTrace();
//			// }
//			// }
//
//			ReceiptDispatchStations rds = service.selectByPrimaryKey(id);
//			if (aaa.equals("")) {
//				rds.setState((long) 2);
//				service.updateByPrimaryKeySelective(rds);
//			} else {
//				try {
//					List<ReceiptDispatchStations> list = receiptDispatchService
//							.selectStationsByRDID(rdid);
//					//根据rdid(dis_rd_stations rdispatchid)查询dis_receipt_dispatch的receptid
//					ReceiptDispatch rd = service.selectReceiptDispatchByRdid(rdid);
//					String receiptid =rd.getReceiptid();
//					boolean isComplete = true;
//					for (ReceiptDispatchStations s : list) {
//						if (s.getId().equals(id)) {
//							continue;
//						}
//						if (s.getState() != 3) {
//							isComplete = false;
//						}
//					}
//					if (isComplete) {
//						User user = (User) request.getSession().getAttribute("loginUser");
//						activitiUnitService.completeTask("station", user.getUserid(),
//								receiptid);
//					}
//					rds.setState((long) 3);
//					service.updateByPrimaryKeySelective(rds);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return "{'result':true}";
//	}
}
