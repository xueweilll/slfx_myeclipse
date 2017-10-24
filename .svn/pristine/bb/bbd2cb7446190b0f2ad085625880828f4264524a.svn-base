package com.benqzl.controller.dispatch;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.AlarmControl;
import com.benqzl.pojo.system.AlarmInfo;
import com.benqzl.pojo.system.AlarmShip;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.StPptnR;
import com.benqzl.pojo.water.StPumpr;
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.pojo.water.TrWarnlog;
//import com.benqzl.service.system.StationService;
//import com.benqzl.service.system.StationService;
import com.benqzl.socket.MessageQueue;
import com.google.gson.Gson;

@Controller
@RequestMapping("floodctl")
public class DispatchController extends BasicController {

	@Resource
	MessageQueue messageQueue;

	/*
	 * @Autowired private StationService stationService;
	 */
	public DispatchController() {
		super(DispatchController.class);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("sys", "dispatch");
		mv.setViewName("main/dispatch");
		return mv;
	}

	private static DecimalFormat df = new DecimalFormat("#.00");
	/**
	 * @Title: waterCurrentList @Description: TODO(实时水情) @param @param
	 *         code @param @return 设定文件 @return String 返回类型 @throws
	 */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM-dd HH:mm");

	@RequestMapping(value = "waterCurrentList", method = RequestMethod.POST)
	@ResponseBody
	public String waterCurrentList(String code) {
		List<Station> stations = new ArrayList<>();
		Iterator<String> stationiter = messageQueue.stationMap.keySet().iterator();
		while (stationiter.hasNext()) {
			Object key = stationiter.next();
			Station station = messageQueue.stationMap.get(key);
			if (code == null || code.equals("")) {
				if (station != null) {
					if (station.getCode().equals("bthcz")) {
						continue;
					}
					if (station.getCode().equals("bth")) {
						Station s = new Station();
						s.setId(station.getId());
						s.setName(station.getName() + "(船闸)");
						s.setLat(station.getLat());
						s.setLon(station.getLon());
						s.setLevels(station.getLevels());
						s.setCode(station.getCode());
						s.setDepartmentid(station.getDepartmentid());
						s.setRemark(station.getRemark());
						s.setNvrpassword(station.getNvrpassword());
						s.setNvrprot(station.getNvrprot());
						s.setNvrurl(station.getNvrurl());
						s.setNvrusername(station.getNvrusername());
						s.setImgurl(station.getImgurl());
						stations.add(s);
						continue;
					}
					stations.add(station);
				}
			} else {
				if (station.getCode().equals(code)) {
					if (station != null) {
						stations.add(station);
					}
					break;
				}
			}
		}
		// Collections.sort(stations);

		/*
		 * List<Unit> units = new ArrayList<>(); Iterator<String> unititer =
		 * messageQueue.unitMap.keySet().iterator(); while (unititer.hasNext())
		 * { Object key = unititer.next(); Unit unit =
		 * messageQueue.unitMap.get(key); if(unit != null){ units.add(unit); } }
		 * List<Gate> gates = new ArrayList<>(); Iterator<String> gateiter =
		 * messageQueue.gateMap.keySet().iterator(); while (gateiter.hasNext())
		 * { Object key = gateiter.next(); Gate gate =
		 * messageQueue.gateMap.get(key); if(gate != null){ gates.add(gate); } }
		 */
		List<StPumpr> list = new ArrayList<>();
		if (code == null || code.equals("")) {
			list = new ArrayList<>();
			/*
			 * for (List<StPumpr> stPumprs : messageQueue.concurrentPumprs
			 * .values()) { list.addAll(stPumprs); }
			 */
			Iterator<String> iter = messageQueue.concurrentPumprs.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				List<StPumpr> stPumprs = messageQueue.concurrentPumprs.get(key);
				if (stPumprs != null) {
					list.addAll(stPumprs);
				}
			}
		} else {
			list = (List<StPumpr>) messageQueue.concurrentPumprs.get(code);
			if (list == null) {
				list = new ArrayList<>();
			}
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		if (code == null) {
			for (Station station : stations) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", station.getCode());
				map.put("name", station.getName());
				map.put("obj", station);
				map.put("levels", station.getLevels());
				for (StPumpr src : list) {
					if (station.getCode().equals(src.getStcd())) {
						if (station.getCode().equals("zg")) {
							map.put("d", String.format("%.2f", new BigDecimal(df.format(src.getPpupz()))));
							map.put("b", String.format("%.2f", new BigDecimal(df.format(src.getPpdwz()))));
						} else {
							map.put("b", String.format("%.2f", new BigDecimal(df.format(src.getPpupz()))));
							map.put("d", String.format("%.2f", new BigDecimal(df.format(src.getPpdwz()))));
						}
						map.put("time", simpleDateFormat1.format(new Date()));
					}
				}
				maps.add(map);
			}
		} else {
			for (StPumpr src : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("b", new BigDecimal(df.format(src.getPpupz())));
				map.put("d", new BigDecimal(df.format(src.getPpdwz())));
				map.put("time", simpleDateFormat.format(new Date()));
				maps.add(map);
			}
		}
		gson = new Gson();
		String json = gson.toJson(maps);
		System.out.println(json);
		logger.info("this cunrrentWater list strJson is " + json);
		return json;
	}

	/**
	 * @Title: gateRunCurentList @Description: TODO(实时闸门数据) @param @param
	 *         code @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "gateRunCurentList", method = RequestMethod.POST)
	@ResponseBody
	public String gateRunCurentList(String code) {

		List<Station> stations = new ArrayList<>();
		Iterator<String> stationiter = messageQueue.stationMap.keySet().iterator();
		while (stationiter.hasNext()) {
			Object key = stationiter.next();
			Station station = messageQueue.stationMap.get(key);
			if (code == null || code.equals("")) {
				if (station != null) {
					stations.add(station);
				}
			} else {
				if (station.getCode().equals(code)) {
					if (station != null) {
						stations.add(station);
					}
					break;
				}
			}
		}
		// Collections.sort(stations);
		List<Gate> gates = new ArrayList<>();
		for (Station s : stations) {
			Iterator<String> gateiter = messageQueue.gateMap.keySet().iterator();
			while (gateiter.hasNext()) {
				Object key = gateiter.next();
				Gate gate = messageQueue.gateMap.get(key);
				if (gate != null) {
					if (gate.getStation() != null) {
						if (gate.getStation().getCode().equals(s.getCode())) {
							gates.add(gate);
						}
					}
				}
			}
		}

		List<TrGaterun> list = new ArrayList<>();
		if (code == null || code.equals("")) {
			list = new ArrayList<>();
			/*
			 * for (List<TrGaterun> listGateruns :
			 * messageQueue.concurrentGateRuns .values()) {
			 * list.addAll(listGateruns); }
			 */
			Iterator<String> iter = messageQueue.concurrentGateRuns.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				List<TrGaterun> stPumprs = messageQueue.concurrentGateRuns.get(key);
				list.addAll(stPumprs);
			}
		} else {
			list = messageQueue.concurrentGateRuns.get(code);
			if (list == null) {
				list = new ArrayList<>();
			}
		}

		SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Gate g : gates) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (g.getStation() == null) {
				continue;
			}
			map.put("code", g.getStation().getCode());
			map.put("name", g.getStation().getName());
			map.put("levels", g.getStation().getLevels());
			map.put("obj", g.getStation());
			map.put("gatename", g.getName());
			for (TrGaterun src : list) {
				if (src.getfStationcode().equals(g.getStation().getCode()) && src.getfGatecode().equals(g.getCode())) {

					if (src.getfTime() != null) {
						map.put("c", sf.format(src.getfTime()));
					}
					if (src.getfRunstate() != null) {
						map.put("d",
								src.getfRunstate().equals("1") ? "上升"
										: (src.getfRunstate().equals("2") ? "停止"
												: src.getfRunstate().equals("3") ? "下降"
														: src.getfRunstate().equals("4") ? "报警" : ""));
					}
					if (src.getfPositionstate() != null) {
						map.put("e",
								src.getfPositionstate().equals("1") ? "全开"
										: (src.getfPositionstate().equals("2") ? "中间"
												: (src.getfPositionstate().equals("3") ? "全关" : "")));
					}

					break;
				}
			}
			maps.add(map);
		}

		// for (Station station : stations) {
		/*
		 * for (TrGaterun src : list) {
		 * //if(station.getCode().equals(src.getfStationcode())){ Map<String,
		 * Object> map = new HashMap<String, Object>(); map.put("code",
		 * src.getfStationcode()); map.put("name", ""); map.put("obj", ""); for
		 * (Station station : stations) {
		 * if(station.getCode().equals(src.getfStationcode())){ map.put("name",
		 * station.getName()); map.put("obj", station); break; } }
		 * SimpleDateFormat sf = new SimpleDateFormat("MM-dd hh:mm");
		 * map.put("b", src.getfGatecode()); if (src.getfTime() != null) {
		 * map.put("c", sf.format(src.getfTime())); } if (src.getfRunstate() !=
		 * null) { map.put( "d", src.getfRunstate().equals("1") ? "上升" :
		 * (src.getfRunstate().equals("2") ? "停止" : src.getfRunstate()
		 * .equals("3") ? "下降" : src.getfRunstate() .equals("4") ? "报警" : ""));
		 * } if (src.getfPositionstate() != null) { map.put( "e",
		 * src.getfPositionstate().equals("1") ? "全开" :
		 * (src.getfPositionstate().equals( "2") ? "中间" : (src
		 * .getfPositionstate() .equals("3") ? "全关" : ""))); } maps.add(map); }
		 */
		// }
		// }
		gson = new Gson();
		String json = gson.toJson(maps);
		logger.info("this TrGaterun list strJson is " + json);
		return json;
	}

	/*
	 * @Autowired private StationService stationService;
	 */

	/**
	 * @Title: pumpRunCurrentList @Description: TODO(实时机组数据) @param @param
	 *         code @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "pumpRunCurrentList", method = RequestMethod.POST)
	@ResponseBody
	public String pumpRunCurrentList(String code) {
		List<Station> stations = new ArrayList<>();
		Iterator<String> stationiter = messageQueue.stationMap.keySet().iterator();
		while (stationiter.hasNext()) {
			Object key = stationiter.next();
			Station station = messageQueue.stationMap.get(key);
			if (code == null || code.equals("")) {
				if (station != null) {
					stations.add(station);
				}
			} else {
				if (station.getCode().equals(code)) {
					if (station != null) {
						stations.add(station);
					}
					break;
				}
			}
		}
		// Collections.sort(stations);

		// 取得本系统所有泵站信息，并且按照枢纽顺序排好
		List<Unit> units = new ArrayList<>();
		for (Station s : stations) {
			List<Unit> l = new ArrayList<>();
			// System.out.println(messageQueue.unitMap.size());
			Iterator<String> unititer = messageQueue.unitMap.keySet().iterator();
			while (unititer.hasNext()) {
				Object key = unititer.next();
				Unit unit = messageQueue.unitMap.get(key);
				if (unit != null) {
					if (unit.getStation() != null) {
						if (unit.getStation().getCode().equals(s.getCode())) {
							l.add(unit);
						}
					}
				}
			}
			if (l.size() == 0 || l == null) {
				continue;
			}
			// Collections.sort(l);
			units.addAll(l);
		}

		// 获取实时数据存入List中
		List<TrPumprun> list = new ArrayList<>();
		if (code == null || code.equals("")) {
			list = new ArrayList<>();
			/*
			 * for (List<TrPumprun> listPumpruns :
			 * messageQueue.concurrentPumpruns .values()) {
			 * list.addAll(listPumpruns); }
			 */
			Iterator<String> iter = messageQueue.concurrentPumpruns.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				List<TrPumprun> stPumprs = messageQueue.concurrentPumpruns.get(key);
				list.addAll(stPumprs);
			}
		} else {
			list = messageQueue.concurrentPumpruns.get(code);
			if (list == null) {
				list = new ArrayList<>();
			}
		}

		SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Unit u : units) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (u.getStation() == null) {
				continue;
			}
			map.put("code", u.getStation().getCode());
			map.put("name", u.getStation().getName());
			map.put("levels", u.getStation().getLevels());
			map.put("obj", u.getStation());
			map.put("unitname", u.getName());
			for (TrPumprun src : list) {
				if (src.getfStationcode().equals(u.getStation().getCode()) && src.getfPumpcode().equals(u.getCode())) {
					if (src.getfTime() != null) {
						map.put("c", sf.format(src.getfTime()));
					}
					if (src.getfRunstate().equals("1")) {
						map.put("d", "运行");
					} else if (src.getfRunstate().equals("2")) {
						map.put("d", "停止");
					} else if (src.getfRunstate().equals("3")) {
						map.put("d", "报警");
					}
					map.put("e", src.getfFlow());
				}
			}
			maps.add(map);

		}
		gson = new Gson();
		String json = gson.toJson(maps);
		logger.info("this TrPumprun list strJson is " + json);
		return json;
	}

	@RequestMapping(value = "warnCurrentList", method = RequestMethod.POST)
	@ResponseBody
	public String warnCurrentList() {
		List<Station> stations = new ArrayList<>();
		Iterator<String> stationiter = messageQueue.stationMap.keySet().iterator();
		while (stationiter.hasNext()) {
			Object key = stationiter.next();
			Station station = messageQueue.stationMap.get(key);
			if (station != null) {
				stations.add(station);
			}
		}
		List<TrWarnlog> list = new ArrayList<>();
		list = new ArrayList<>();
		Iterator<String> iter = messageQueue.concurrentTrWarnlogs.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			List<TrWarnlog> stPumprs = messageQueue.concurrentTrWarnlogs.get(key);
			list.addAll(stPumprs);
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		for (TrWarnlog src : list) {
			for (Station station : stations) {
				if (station.getCode().equals(src.getfStationcode())) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", src.getfId());
					map.put("code", station.getCode());
					map.put("name", station.getName());
					map.put("obj", station);
					SimpleDateFormat sf = new SimpleDateFormat("MM-dd hh:mm");
					map.put("b", src.getfCaption());
					if (src.getfWarntime() != null) {
						map.put("c", sf.format(src.getfWarntime()));
					}
					if (src.getfFreetime() != null) {
						map.put("d", sf.format(src.getfFreetime()));
					}
					if (src.getfWarnstate().equals("1")) {
						map.put("e", "解除");
					} else if (src.getfWarnstate().equals("2")) {
						map.put("e", "报警");
					}
					maps.add(map);
				}
			}
		}
		gson = new Gson();
		String json = gson.toJson(maps);
		logger.info("this TrPumprun list strJson is " + json);
		return json;
	}

	@RequestMapping(value = "pptnCurrentList", method = RequestMethod.POST)
	@ResponseBody
	public String pptnCurrentList(String code) {

		List<StPptnR> list = new ArrayList<>();
		try {
			if (code == null || code.equals("")) {
				list = new ArrayList<>();
				System.out.println("雨情：" + messageQueue.concurrentStPptnR);
				Iterator<String> iter = messageQueue.concurrentStPptnR.keySet().iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					List<StPptnR> stPumprs = messageQueue.concurrentStPptnR.get(key);
					if (stPumprs != null) {
						list.addAll(stPumprs);
					}
				}

			} else {
				list = (List<StPptnR>) messageQueue.concurrentStPptnR.get(code);
				if (list == null) {
					list = new ArrayList<>();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Map<String, Object>> maps = new ArrayList<>();
		if (code == null || code.equals("")) {

			Map<String, Object> map = new HashMap<>();
			map.put("code", "lzg");
			map.put("name", "老澡港河枢纽");
			SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
			BigDecimal b = new BigDecimal(0);
			for (StPptnR s : list) {
				if ("lzg".equals(s.getStcd())) {
					if (s.getDyp().compareTo(b) != -1) {
						map.put("c", s.getDyp());
						if (s.getTm() != null) {
							map.put("b", sf.format(s.getTm()));
						}
						b = s.getDyp();
					}
				}
			}

			Map<String, Object> map2 = new HashMap<>();
			map2.put("code", "hthn");
			map2.put("name", "横塘河南枢纽");
			b = new BigDecimal(0);
			for (StPptnR s : list) {
				if ("hthn".equals(s.getStcd())) {
					if (s.getDyp().compareTo(b) != -1) {
						map2.put("c", s.getDyp());
						if (s.getTm() != null) {
							map2.put("b", sf.format(s.getTm()));
						}
						b = s.getDyp();
					}
				}
			}

			Map<String, Object> map3 = new HashMap<>();
			map3.put("code", "tzh");
			map3.put("name", "童子河闸站");
			b = new BigDecimal(0);
			for (StPptnR s : list) {
				if ("tzh".equals(s.getStcd())) {
					if (s.getDyp().compareTo(b) != -1) {
						map3.put("c", s.getDyp());
						if (s.getTm() != null) {
							map3.put("b", sf.format(s.getTm()));
						}
						b = s.getDyp();
					}
				}
			}

			maps.add(map);
			maps.add(map2);
			maps.add(map3);
		} else {
		}
		System.out.println(maps);
		gson = new Gson();
		String json = gson.toJson(maps);
		logger.info("this TrPumprun list strJson is " + json);
		return json;
	}

	@RequestMapping(value = "alarm", method = RequestMethod.POST)
	@ResponseBody
	public String alamInfos(HttpServletRequest request) {
		String result = "{";
		String code = "";
		AlarmInfo alarmInfo = null;
		String msg = "";
		AlarmControl alarmControl = (AlarmControl) request.getSession().getAttribute("alarmControl");
		if(alarmControl.getIsAC()){
			Date dt = new Date();
			long diff = dt.getTime()-alarmControl.getDate().getTime();
			if(diff<(1000*60*60)){
				result += "'isAlarm':false}"; 
				return result;
			}else{
				alarmControl.setIsAC(false);
				alarmControl.setDate(dt);
			}
		}
		
		@SuppressWarnings("unchecked")
		List<AlarmShip> alarmShips = (List<AlarmShip>) request.getSession().getAttribute("alarmShips");
		if(alarmShips==null){
			return result += "'isAlarm':true,'msg':'" + msg + "'}";
		}
		for (AlarmShip alarmShip : alarmShips) {
			code = alarmShip.getStationcode();
			alarmInfo = messageQueue.concurrentAlarmInfo.get(code);
			if(alarmInfo==null){
				continue;
			}
			if (alarmInfo.getIsinAlarm() || alarmInfo.getIsoutAlarm()) {
				msg += alarmInfo.alarmText() + "|";
			}
		}
		if (!msg.equals("")) {
			result += "'isAlarm':true,'msg':'" + msg + "'}";
		} else {
			result += "'isAlarm':false}";
		}
		return result;
	}
	
	@RequestMapping(value = "alarmClear", method = RequestMethod.POST)
	@ResponseBody
	public String alamClear(HttpServletRequest request) {
		String result = "{'result':true}";
		
		AlarmControl alarmControl = (AlarmControl) request.getSession().getAttribute("alarmControl");
		alarmControl.setIsAC(true);
		alarmControl.setDate(new Date());
		
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String code) {
		messageQueue.concurrentTrWarnlogs.remove(code);
		return code;
	}
}
