package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.text.ParseException;
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
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchExecute;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecutePeople;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteUnits;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.system.User;
import com.benqzl.service.dispatch.DispatchInstructionsService;
import com.benqzl.service.dispatch.SelfDispatchExecuteService;
//import com.benqzl.service.dispatch.DispatchIssuedListService;
import com.benqzl.service.dispatch.SelfDispatchStationsService;
import com.benqzl.unit.ActivitiUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: ExecuteController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shimh
 * @date 2016年1月4日 上午10:10:19
 * 
 */
@Controller
@RequestMapping("sdexecute")
public class SDExecuteController extends BasicController {
	@Autowired
	private SelfDispatchStationsService service;

	public SDExecuteController() {
		super(SDExecuteController.class);
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
		mv.setViewName("dispatch/sdexecute");
		return mv;
	}

	@RequestMapping(value = "/executeList", method = RequestMethod.POST)
	@ResponseBody
	public String executeList(int page, int rows, String typeDate,
			String starttime, String endtime, HttpSession session, String code) {
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
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
		if (code == "" || code == null) {
			map.put("code", null);
		} else {
			map.put("code", code.replaceAll(" ", ""));
		}
		if (typeDate.equals("1")) {
			map.put("rpid", null);
		} else {
			try {
				User user = (User) session.getAttribute("loginUser");
				List<Map<String, Object>> l = activitiUnitService.findStations(
						user.getUserid(), "A");
				if (l.size() == 0) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("total", 0);
					l = new ArrayList<Map<String, Object>>();
					jsonMap.put("rows", l);
					json = gson.toJson(jsonMap);
					return json;
				} else {
					String strwhere = "";
					int k = 0;
					for (Map<String, Object> m : l) {
						String s = "";
						s += "(b.id = '" + m.get("objId").toString()
								+ "' and a.sid ='" + m.get("stationId") + "') ";
						/*
						 * @SuppressWarnings("unchecked") List<String> stas =
						 * (List<String>) m.get("stationId"); for (int j = 0; j
						 * < stas.size(); j++) { if (j == 0) { if (stas.size()
						 * == 1) { s += " a.sid in ('" + stas.get(j).toString()
						 * + "'))"; } else { s += " a.sid in ('" +
						 * stas.get(j).toString() + "',"; } } else if (j ==
						 * (stas.size() - 1)) { s += " '" +
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
						strwhere = " a.state != 3";
					} else {
						strwhere += " and a.state != 3";
					}
					map.put("rpid", strwhere);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		List<SelfDispatchStations> list = service.findDispatch2(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = service.pageCount2(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);

		gson = new GsonBuilder().registerTypeAdapter(
				SelfDispatchStations.class,
				new JsonSerializer<SelfDispatchStations>() {
					@Override
					public JsonElement serialize(SelfDispatchStations station,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", station.getSelfDispatch().getId());
						//System.out.println(station.getId());
						o.addProperty("stationid", station.getId());
						o.addProperty("excuteid", UUID.randomUUID()
								.toString());// 手机端execute的id
						o.addProperty("code", station.getSelfDispatch()
								.getCode());
						o.addProperty("sid", station.getStation().getName());
						o.addProperty("stationsid", station.getStation().getId());
						o.addProperty("promoter", station.getSelfDispatch()
								.getUser().getUsername());
						o.addProperty("promotetime", datetimeFormat
								.format(station.getSelfDispatch()
										.getPromotetime()));

						o.addProperty("creater", station.getSelfDispatch()
								.getCreateUser().getUsername());
						o.addProperty("createtime", datetimeFormat
								.format(station.getSelfDispatch()
										.getCreatetime()));
						String state = "";
						Long s = station.getState();
						if (s.intValue() == 0) {
							state = "未查看";
						} else if (s.intValue() == 1) {
							state = "已查看未实施";
						} else if (s.intValue() == 2) {
							state = "已实施未完成";
						} else if (s.intValue() == 3) {
							state = "完成";
						}
						o.addProperty("state", state);
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);

		return json;
	}

	/**
	 * 
	 * @Title: findInstructionBySdid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sdid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findInstructionBySdid", method = RequestMethod.POST)
	@ResponseBody
	public String findInstructionBySdid(String sdid) {
		String json = "";
		List<SelfDispatchInstructions> list = service
				.findInstructionsByid(sdid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(
				SelfDispatchInstructions.class,
				new JsonSerializer<SelfDispatchInstructions>() {
					@Override
					public JsonElement serialize(
							SelfDispatchInstructions instruction,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", instruction.getId());
						if (instruction.getGateoperatetime() != null) {
							o.addProperty("gateoperatetime", datetimeFormat
									.format(instruction.getGateoperatetime()));
						}
						if (instruction.getUnitoperatetime() != null) {
							o.addProperty("unitoperatetime", datetimeFormat
									.format(instruction.getUnitoperatetime()));
						}
						if (instruction.getInstruction().intValue() == 0) {
							o.addProperty("instruction1", "关闸");
							o.addProperty("instruction2", "不操作");
						} else if (instruction.getInstruction().intValue() == 1) {
							o.addProperty("instruction1", "开闸");
							o.addProperty("instruction2", "不操作");
						} else if (instruction.getInstruction().intValue() == 2) {
							o.addProperty("instruction1", "不操作");
							o.addProperty("instruction2", "关泵");
						} else if (instruction.getInstruction().intValue() == 3) {
							o.addProperty("instruction1", "不操作");
							o.addProperty("instruction2", "开泵");
						} else if (instruction.getInstruction().intValue() == 4) {
							o.addProperty("instruction1", "关闸");
							o.addProperty("instruction2", "开泵");
						} else if (instruction.getInstruction().intValue() == 5) {
							o.addProperty("instruction1", "关闸");
							o.addProperty("instruction2", "关泵");
						} else if (instruction.getInstruction().intValue() == 6) {
							o.addProperty("instruction1", "开闸");
							o.addProperty("instruction2", "关泵");
						} else if (instruction.getInstruction().intValue() == 7) {
							o.addProperty("instruction1", "开闸");
							o.addProperty("instruction2", "开泵");
						}
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}

	@Autowired
	private ActivitiUnitService activitiUnitService;

	/**
	 * 
	 * @Title: exelist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @param rows
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/exelist", method = RequestMethod.POST)
	@ResponseBody
	public String exelist(int page, int rows) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);

		List<SelfDispatch> sd = null;
		try {
			sd = service.findAll(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", sd);

		gson = new GsonBuilder().registerTypeAdapter(SelfDispatch.class,
				new JsonSerializer<SelfDispatch>() {

					@Override
					public JsonElement serialize(SelfDispatch arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						List<SelfDispatchStations> s = arg0.getStations();
						for (SelfDispatchStations sdStations : s) {
							json.addProperty("id", arg0.getId());

							// 调度单编号
							json.addProperty("code", arg0.getCode());
							// 枢纽ID
							json.addProperty("sid", sdStations.getSid());
							// 枢纽信息表主键
							json.addProperty("stationid", sdStations.getId());
							// 发起人
							String userid = arg0.getPromoter();
							User user = new User();
							if (userid != null || userid != "") {
								user = service.findUserById(userid);
								json.addProperty("promoter", user.getUsername());
							}
							// 发起时间
							json.addProperty("promotetime", datetimeFormat
									.format(arg0.getPromotetime()));

							// 指令内容
							// 根据指令信息SDID查list再查Instruction
							List<SelfDispatchInstructions> sdilist = null;
							try {
								sdilist = service.selectBySDID(arg0.getId());

							} catch (Exception e) {
								e.printStackTrace();
							}

							StringBuilder sb = new StringBuilder();
							String separator = ",";
							for (int i = 0; i < sdilist.size(); i++) {

								Long instruction = sdilist.get(i)
										.getInstruction();
								String insString = "";
								if (instruction == 0) {
									insString = "关闸";
								} else if (instruction == 1) {
									insString = "开闸";
								} else if (instruction == 2) {
									insString = "关泵";
								} else if (instruction == 3) {
									insString = "开泵";
								} else if (instruction == 4) {
									insString = "关闸开泵";
								} else if (instruction == 5) {
									insString = "关闸关泵";
								} else if (instruction == 6) {
									insString = "开闸关泵";
								} else if (instruction == 7) {
									insString = "开闸开泵";
								}

								sb.append(insString).append(separator);
								insString = sb.toString().substring(0,
										sb.toString().length() - 1);
								json.addProperty("instruction", insString);
							}

							// 制单人
							String uid = arg0.getCreater();
							if (uid != null || uid != "") {
								user = service.findUserById(userid);
								json.addProperty("creater", user.getUsername());
							}
							// 制单时间
							json.addProperty("createtime",
									datetimeFormat.format(arg0.getCreatetime()));
							// 处理状态（枢纽的执行状态）
							Long state = sdStations.getState();
							if (state == 0) {
								json.addProperty("state", "未查看");
							} else if (state == 1) {
								json.addProperty("state", "已查看未执行");
							} else if (state == 2) {
								json.addProperty("state", "执行中");
							} else if (state == 3) {
								json.addProperty("state", "完成");
							}
						}
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	@RequestMapping(value = "doexecute", method = RequestMethod.GET)
	public ModelAndView doexecute(String stationid, String edit) {
		ModelAndView mv = new ModelAndView();
		if (edit == null) {
			SelfDispatchStations sds = service.selectstationById2(stationid);
			mv.addObject("sds", sds);
			mv.addObject("id", sds.getSdid());
			mv.setViewName("dispatch/addsdexecute");
		} else {
			SelfDispatchStations sds = service.selectstationById2(stationid);
			SelfDispatchExecute sde = service
					.selectExecuteByStationId2(stationid);
			List<SelfDispatchExecutePeople> list = sde.getSdep();
			String userids = "[";
			for (int i = 0; i < list.size(); i++) {
				userids += "'" + list.get(i).getUserid() + "',";
			}
			userids = userids.substring(0, userids.length() - 1);
			userids += "]";
			// System.out.println(userids);
			mv.addObject("userids", userids);
			mv.addObject("sde", sde);
			mv.addObject("id", sds.getSdid());
			mv.setViewName("dispatch/addsdexecute2");
		}
		return mv;
	}

	/**
	 * @Title: saveExecuteUnitMobile
	 * @Description: TODO(保存手机端unit)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @param startouterlevel
	 * @param @param rdsid
	 * @param @param unitid
	 * @param @param btime
	 * @param @param etime
	 * @param @param startinlandlevel
	 * @param @param stopouterlevel
	 * @param @param stopinlandlevel
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveExecuteUnitMobile", method = RequestMethod.POST)
	@ResponseBody
	public String saveExecuteUnitMobile(String jsonStr,
			HttpServletRequest request, String startouterlevel, String rdsid,
			String type,String unitid, String btime, String etime, String startinlandlevel,
			String stopouterlevel, String stopinlandlevel)
			throws ParseException {
		String result = "";
		gson = new Gson();
		SelfDispatchExecute sde = gson.fromJson(jsonStr,
				SelfDispatchExecute.class);
		if (startouterlevel != null && startouterlevel != "") {
			sde.setStartouterlevel(startouterlevel);
		}
		if (startinlandlevel != null && startinlandlevel != "") {
			sde.setStartinlandlevel(startinlandlevel);
		}
		if (stopouterlevel != null && stopouterlevel != "") {
			sde.setStopouterlevel(stopouterlevel);
		}
		if (stopinlandlevel != null && stopinlandlevel != "") {
			sde.setStopinlandlevel(stopinlandlevel);
		}
		if (type.equals("1")) {
			int isOnly = service
					.countExecuteByStationId2(sde.getDispatchstationid());
			if(isOnly==0){
			try {
				service.insertExecute(sde);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'操作失敗！'}";
			}
			}
			} else {
	
			try {
				SelfDispatchExecute sdes = service
						.selectExecuteByStationId2(sde.getDispatchstationid());
				sde.setId(sdes.getId());
				service.updateExecute(sde);
				result = "{'result':true}";
			} catch (Exception e1) {
				e1.printStackTrace();
				result = "{'result':false,'msg':'操作失敗！'}";
			}

		}
		try {
			SelfDispatchExecuteUnits sdeu = new SelfDispatchExecuteUnits();
			sdeu.setId(UUID.randomUUID().toString());
			sdeu.setUnitid(unitid);
			sdeu.setExecuteid(sde.getId());
			try {
				if (btime != null && btime != "" && btime.length() > 0) {
					sdeu.setBegintime(datetimeFormat.parse(btime));
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			try {
				if (etime != null && etime != "" && etime.length() > 0) {
					sdeu.setEndtime(datetimeFormat.parse(etime));
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			service.insertExecuteUnit(sdeu);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}
		return result;
	}

	/**
	 * @Title: updateExecuteMobile
	 * @Description: TODO(手机端更新三个参数)
	 * @param @param jsonStr
	 * @param @param request
	 * @param @param startouterlevel
	 * @param @param rdsid
	 * @param @param unitid
	 * @param @param btime
	 * @param @param etime
	 * @param @param type
	 * @param @param startinlandlevel
	 * @param @param stopouterlevel
	 * @param @param stopinlandlevel
	 * @param @return
	 * @param @throws ParseException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/updateExecuteMobile", method = RequestMethod.POST)
	@ResponseBody
	public String updateExecuteMobile(String jsonStr,
			HttpServletRequest request, String startouterlevel, String rdsid,
			String unitid, String btime, String etime, String type,
			String startinlandlevel, String stopouterlevel,
			String stopinlandlevel) throws ParseException {
		String result = "";
		gson = new Gson();
		SelfDispatchExecute sde = gson.fromJson(jsonStr,
				SelfDispatchExecute.class);
		if (startouterlevel != null && startouterlevel != "") {
			sde.setStartouterlevel(startouterlevel);
		}
		if (startinlandlevel != null && startinlandlevel != "") {
			sde.setStartinlandlevel(startinlandlevel);
		}
		if (stopouterlevel != null && stopouterlevel != "") {
			sde.setStopouterlevel(stopouterlevel);
		}
		if (stopinlandlevel != null && stopinlandlevel != "") {
			sde.setStopinlandlevel(stopinlandlevel);
		}
/*		if (type.equals("1")) {
			try {
				service.insertExecute(sde);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'操作失敗！'}";
			}
		} else {*/
			try {
				SelfDispatchExecute sdes = service
						.selectExecuteByStationId2(sde.getDispatchstationid());
				sde.setId(sdes.getId());
				service.updateExecute(sde);
				result = "{'result':true}";
			} catch (Exception e1) {
				e1.printStackTrace();
				result = "{'result':false,'msg':'操作失敗！'}";
			}
			SelfDispatchStations station = service.selectstationById2(sde.getDispatchstationid());
				User user = (User) request.getSession().getAttribute(
						"loginUser");
				station.setState(new Long(2));
				station.setReceipter(user.getUserid());
				station.setReceipttime(new Date());
				try {
					service.updateByPrimaryKeySelective(station);
					result = "{'result':true}";
				} catch (Exception e) {
					e.printStackTrace();
					result = "{'result':false,'msg':'操作失敗！'}";
				}
			
		/*}*/
	/*	try {
			SelfDispatchExecuteUnits sdeu = new SelfDispatchExecuteUnits();
			sdeu.setId(UUID.randomUUID().toString());
			try {
				if (btime != null && btime != "" && btime.length() > 0) {
					sdeu.setBegintime(datetimeFormat.parse(btime));
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			try {
				if (etime != null && etime != "" && etime.length() > 0) {
					sdeu.setEndtime(datetimeFormat.parse(etime));
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			service.insertExecuteUnit(sdeu);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'操作失敗！'}";
		}*/
		return result;
	}

	/**
	 * @Title: viewsdexecutemobile
	 * @Description: TODO(手机端查看)
	 * @param @param stationid
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "viewsdexecutemobile", method = RequestMethod.POST)
	@ResponseBody
	public String viewsdexecutemobile(String stationid) {
		String strJson = "";
		// SelfDispatchStations sds = service.selectstationById(stationid);
		SelfDispatchExecute sde = service.selectExecuteByStationId2(stationid);
		List<SelfDispatchExecutePeople> list = sde.getSdep();
		String userids = "[";
		for (int i = 0; i < list.size(); i++) {
			userids += "'" + list.get(i).getUserid() + "',";
		}
		userids = userids.substring(0, userids.length() - 1);
		userids += "]";
		gson = new GsonBuilder().registerTypeAdapter(SelfDispatchExecute.class,
				new JsonSerializer<SelfDispatchExecute>() {

					@Override
					public JsonElement serialize(SelfDispatchExecute arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						// json.addProperty("rdid",
						// arg0.getRds().getReceiptDispatch().getId());//调度单id
						// json.addProperty("sid",
						// arg0.getRds().getSid());//执行枢纽id
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
		strJson = gson.toJson(sde);
		return strJson;
	}

	@RequestMapping(value = "viewexecute", method = RequestMethod.GET)
	public ModelAndView viewexecute(String stationid, String edit) {
		ModelAndView mv = new ModelAndView();
		if (edit == null) {
			SelfDispatchStations sds = service.selectstationById(stationid);
			mv.addObject("sds", sds);
			mv.addObject("id", sds.getSdid());
			mv.setViewName("dispatch/viewaddsdexecute");
		} else {
			SelfDispatchStations sds = service.selectstationById(stationid);
			SelfDispatchExecute sde = service
					.selectExecuteByStationId2(stationid);
			List<SelfDispatchExecutePeople> list = sde.getSdep();
			String userids = "[";
			for (int i = 0; i < list.size(); i++) {
				userids += "'" + list.get(i).getUserid() + "',";
			}
			userids = userids.substring(0, userids.length() - 1);
			userids += "]";
			mv.addObject("userids", userids);
			mv.addObject("sde", sde);
			mv.addObject("id", sds.getSdid());
			mv.setViewName("dispatch/vieweditsdexecute");
		}
		return mv;
	}

	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	@ResponseBody
	public String findUser() {
		String json = "";
		List<User> users = service.findUser();
		json = gson.toJson(users);
		return json;
	}

	@RequestMapping(value = "/findUnit", method = RequestMethod.POST)
	@ResponseBody
	public String findUnit(String sid) {
		String json = "";
		gson = new Gson();
		List<Unit> units = service.findUnit2(sid);
		json = gson.toJson(units);
		return json;
	}

	@RequestMapping(value = "/findGate", method = RequestMethod.POST)
	@ResponseBody
	public String findGate(String sid) {
		String json = "";
		List<Gate> getes = service.findGate(sid);
		json = gson.toJson(getes);
		return json;
	}

	/**
	 * 
	 * @Title: addsdexecute
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sid
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "addsdexecute", method = RequestMethod.GET)
	public ModelAndView addsdexecute(String sdid, String sid) {
		ModelAndView mv = new ModelAndView();
		if (!sdid.equals("0") && !sid.equals("0")) {
			HashMap<String, String> map = new HashMap<>();
			map.put("sdid", sdid);
			map.put("sid", sid);
			SelfDispatchStations selfDispatchStations = service
					.selectBySDIDAndSId(map);
			String dispatchstationid = selfDispatchStations.getId();
			SelfDispatchExecute sde = null;

			sde = service.selectExecuteByDispatchstationId(dispatchstationid);
			if (sde != null) {
				mv.addObject("sde", sde);
				String executeid = sde.getId();
				List<SelfDispatchExecutePeople> sdeplist = null;
				sdeplist = service.selectExecutePeopleByExecuteId(executeid);
				if (sdeplist != null) {
					StringBuilder sb = new StringBuilder();
					String separator = ",";
					String executer = "";
					for (int i = 0; i < sdeplist.size(); i++) {
						User user = null;
						String uid = sdeplist.get(i).getUserid();
						user = service.selectUserByID(uid);
						executer = user.getUsername();
						sb.append(executer).append(separator);
						executer = sb.toString().substring(0,
								sb.toString().length() - 1);
					}
					mv.addObject("executer", executer);
				}

				List<SelfDispatchExecuteUnits> units = null;
				units = service.selectUnitByExecuteId(executeid);

				if (units != null) {
					mv.addObject("units", units);
				}
			}

			mv.addObject("selfDispatchStations", selfDispatchStations);
		}
		mv.setViewName("dispatch/addsdexecute");
		return mv;
	}

	/**
	 * 
	 * @Title: sdInstructionInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param stationid
	 * @param @param request
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "sdInstructionInfo", method = RequestMethod.GET)
	public ModelAndView sdInstructionInfo(String stationid,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (!stationid.equals("0")) {
			SelfDispatchStations station = service
					.selectstationById2(stationid);
			if (station.getState().intValue() == 0) {
				User user = (User) request.getSession().getAttribute(
						"loginUser");
				station.setState((long) 1);
				station.setReceipter(user.getUserid());
				station.setReceipttime(new Date());
				try {
					service.updateByPrimaryKeySelective(station);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mv.addObject("promotetime", datetimeFormat.format(station
					.getSelfDispatch().getPromotetime()));
			mv.addObject("createtime", datetimeFormat.format(station
					.getSelfDispatch().getCreatetime()));
			mv.addObject("station", station);
		}
		mv.setViewName("dispatch/sdInstructionInfo");
		return mv;
	}

	@Autowired
	private SelfDispatchExecuteService selfDispatchExecuteService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String json, String state, String sdid, HttpServletRequest request){
		System.out.println(json);
		SelfDispatchExecute execute = gson.fromJson(json, SelfDispatchExecute.class);
		// 新增
		if (execute.getId().equals("0")) {
			execute.setId(UUID.randomUUID().toString());
			for(SelfDispatchExecuteUnits u : execute.getExecuteunits()){
				u.setId(UUID.randomUUID().toString());
				u.setExecuteid(execute.getId());
			}
			for(SelfDispatchExecuteGate g : execute.getExecutegates()){
				g.setId(UUID.randomUUID().toString());
				g.setSdexecuteid(execute.getId());
			}
			for(SelfDispatchExecutePeople p : execute.getSdep()){
				p.setId(UUID.randomUUID().toString());
				p.setExecteid(execute.getId());
			}
			try {
				service.insertExecute(execute);
				if(execute.getExecutegates().size() > 0){
					selfDispatchExecuteService.insertGates(execute.getExecutegates());
				}
				selfDispatchExecuteService.insertPeople(execute.getSdep());
				if(execute.getExecuteunits().size() > 0){
					selfDispatchExecuteService.insertUnits(execute.getExecuteunits());
				}
				if (state.equals("0")) {
					service.updateDispatchStations(execute.getDispatchstationid());
				} else {
					try {
						List<SelfDispatchStations> list = dispatchInstructionsService
								.findStationsByid(sdid);
						boolean isComplete = true;
						for (SelfDispatchStations s : list) {
							if (s.getId().equals(execute.getDispatchstationid())) {
								continue;
							}
							if (s.getState() != 3) {
								isComplete = false;
							}
						}
						User user = (User) request.getSession().getAttribute(
								"loginUser");
						activitiUnitService.messagePushByDorSid("station",
								user.getUserid(), sdid, execute.getSid());
						// activitiUnitService.completeTask("station", ,id);
						activitiUnitService.completeTaskByDorS("station",
								user.getUserid(), sdid, execute.getSid(), null, null);
						service.updateDispatchStations2(execute.getDispatchstationid());
						SelfDispatch sd = service
								.findSelfDispatchBySdstationid(execute.getDispatchstationid());
						if (isComplete) {
							sd.setCompletetime(new Date());
							service.updateDispatchCompleteTime(sd);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'保存失败！'}";
			}
		}else{
			//编辑
			for(SelfDispatchExecuteUnits u : execute.getExecuteunits()){
				u.setId(UUID.randomUUID().toString());
				u.setExecuteid(execute.getId());
			}
			for(SelfDispatchExecuteGate g : execute.getExecutegates()){
				g.setId(UUID.randomUUID().toString());
				g.setSdexecuteid(execute.getId());
			}
			for(SelfDispatchExecutePeople p : execute.getSdep()){
				p.setId(UUID.randomUUID().toString());
				p.setExecteid(execute.getId());
			}
			try {
				service.updateExecute(execute);
				service.deleteExecutePeople(execute.getId());
				service.deleteExecuteUnit(execute.getId());
				service.deleteExecuteGate(execute.getId());
				if(execute.getExecutegates().size() > 0){
					selfDispatchExecuteService.insertGates(execute.getExecutegates());
				}
				selfDispatchExecuteService.insertPeople(execute.getSdep());
				if(execute.getExecuteunits().size() > 0){
					selfDispatchExecuteService.insertUnits(execute.getExecuteunits());
				}
				if (state.equals("0")) {
					service.updateDispatchStations(execute.getDispatchstationid());
				} else {
					List<SelfDispatchStations> list = dispatchInstructionsService
							.findStationsByid(sdid);
					boolean isComplete = true;
					for (SelfDispatchStations s : list) {
						if (s.getId().equals(execute.getDispatchstationid())) {
							continue;
						}
						if (s.getState() != 3) {
							isComplete = false;
						}
					}
					User user = (User) request.getSession().getAttribute(
							"loginUser");
					activitiUnitService.messagePushByDorSid("station",
							user.getUserid(), sdid, execute.getSid());
					activitiUnitService.completeTaskByDorS("station",
							user.getUserid(), sdid, execute.getSid(), null, null);
					service.updateDispatchStations2(execute.getDispatchstationid());
					SelfDispatch sd = service
							.findSelfDispatchBySdstationid(execute.getDispatchstationid());
					if (isComplete) {
						sd.setCompletetime(new Date());
						service.updateDispatchCompleteTime(sd);
					}				
				}
				return "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				return "{'result':false,'msg':'保存失败！'}";
			}
		}
	}

	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	@ResponseBody
	public String finish(String stationid) {

		SelfDispatchStations sds = service.selectByPrimaryKey(stationid);
		sds.setState((long) 3);
		try {
			service.updateByPrimaryKeySelective(sds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{'result':true}";
	}

	@Autowired
	private DispatchInstructionsService dispatchInstructionsService;

	@RequestMapping(value = "/finish2", method = RequestMethod.POST)
	@ResponseBody
	public String finish2(String stationid, String id, HttpSession session) {
		System.out.println(id);
		String result = "";
		try {
			List<SelfDispatchStations> list = dispatchInstructionsService
					.findStationsByid(id);
			boolean isComplete = true;
			for (SelfDispatchStations s : list) {
				if (s.getId().equals(stationid)) {
					continue;
				}
				if (s.getState() != 3) {
					isComplete = false;
				}
			}
			if (isComplete) {
				User user = (User) session.getAttribute("loginUser");
				activitiUnitService.completeTask("station", user.getUserid(),
						id);
			}
			service.updateDispatchStations2(stationid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
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
	@RequestMapping(value = "unit", method = RequestMethod.POST)
	@ResponseBody
	public String unit(String sid) {
		String json = "";
		String test_sid = "61ad6d3d-4fb2-486b-b747-122e0333d0ae";
		// List<Unit> list=service.findUnit(sid);
		List<Unit> list = service.findUnit(test_sid);
		json = gson.toJson(list);
		return json;
	}

	@RequestMapping(value = "searchInfo", method = RequestMethod.GET)
	public ModelAndView searchInfo(String id) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("dispatch/addsdexecute");
		return mv;
	}

	@RequestMapping(value = "findUnitByExecuteid", method = RequestMethod.POST)
	@ResponseBody
	public String findUnitByExecuteid(String executeid) {
		String json = "";
		List<SelfDispatchExecuteUnits> list = service
				.findUnitByExecuteid(executeid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(
				SelfDispatchExecuteUnits.class,
				new JsonSerializer<SelfDispatchExecuteUnits>() {
					@Override
					public JsonElement serialize(SelfDispatchExecuteUnits sdu,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("unit", sdu.getUnit().getName());
						o.addProperty("uid", sdu.getUnitid());
						o.addProperty("begintime",
								datetimeFormat.format((sdu.getBegintime())));
						if (sdu.getEndtime() != null) {
							o.addProperty("endtime",
									datetimeFormat.format(sdu.getEndtime()));
						}
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}

	@RequestMapping(value = "findGateByExecuteid", method = RequestMethod.POST)
	@ResponseBody
	public String findGateByExecuteid(String executeid) {
		String json = "";
		List<SelfDispatchExecuteGate> list = service
				.findGateByExecuteid(executeid);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(
				SelfDispatchExecuteGate.class,
				new JsonSerializer<SelfDispatchExecuteGate>() {
					@Override
					public JsonElement serialize(SelfDispatchExecuteGate sde,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("sid", sde.getGate().getName());
						o.addProperty("gid", sde.getGid());
						if(sde.getOperatetime() != null){
							o.addProperty("operatetime",
								datetimeFormat.format((sde.getOperatetime())));
						}
						if (sde.getOperate().intValue() == 0) {
							o.addProperty("operate", "关闸");
						} else if (sde.getOperate().intValue() == 1) {
							o.addProperty("operate", "开闸");
						} else if(sde.getOperate().intValue() == 2){
							o.addProperty("operate", "常关");
						}
						o.addProperty("operatetype", sde.getOperate());
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		return json;
	}
}
