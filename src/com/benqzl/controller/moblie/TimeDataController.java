package com.benqzl.controller.moblie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.StPptnR;
import com.benqzl.pojo.water.StPumpr;
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.socket.MessageQueue;
import com.google.gson.Gson;

/**
 * @ClassName: TimeDataController
 * @Description: TODO(手机端数据接口)
 * @author 冯庆国
 * @date 2016年4月27日 下午4:53:25
 * 
 */
@Controller
@RequestMapping("timeData")
public class TimeDataController extends BasicController {

	@Autowired
	private MessageQueue messageQueue;

	public TimeDataController() {
		super(TimeDataController.class);
	}
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/test");
		return mv;
	}
	
	private static SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
	private static DecimalFormat df = new DecimalFormat("#.00");
	/**
	 * @Title: valveDetails
	 * @Description: TODO(闸门数据接口)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "valveDetails", method = RequestMethod.POST)
	@ResponseBody
	public String valveDetails() {
		List<Gate> gates = new ArrayList<>();
		Iterator<String> gateiter = messageQueue.gateMap.keySet().iterator();
		while (gateiter.hasNext()) {
			Object key = gateiter.next();
			Gate gate = messageQueue.gateMap.get(key);
			if (gate != null) {
				gates.add(gate);
			}
		}
		Collections.sort(gates, new Comparator<Gate>() {
			@Override
			public int compare(Gate o1, Gate o2) {
				if(o1.getStation().getLevels()-o2.getStation().getLevels()>0){
					return 1;
				}else if(o1.getStation().getLevels()-o2.getStation().getLevels()<0){
					return -1;
				}else{
					return o1.getName().compareTo(o2.getName());
				}
			}
		});
		List<TrGaterun> list = new ArrayList<>();
		// 获取实时数据存入List中
		Iterator<String> iter = messageQueue.concurrentGateRuns.keySet()
				.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			List<TrGaterun> stPumprs = messageQueue.concurrentGateRuns.get(key);
			list.addAll(stPumprs);
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Gate gate : gates) {
			if (gate.getStation() == null) {
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("mHubName", gate.getStation().getName());
			map.put("mValveNumber", gate.getName());
			for (TrGaterun trGaterun : list) {
				if (trGaterun.getfGatecode().equals(gate.getCode())&& trGaterun.getfStationcode().equals(gate.getStation().getCode())) {
					map.put("mCollectTime",
							sf.format(trGaterun.getfTime()));
					if (trGaterun.getfRunstate() != null) {
						map.put("mOperationState", trGaterun.getfRunstate()
								.equals("1") ? "上升" : (trGaterun.getfRunstate()
								.equals("2") ? "停止" : trGaterun.getfRunstate()
								.equals("3") ? "下降" : trGaterun.getfRunstate()
								.equals("4") ? "报警" : ""));
					}
					if (trGaterun.getfPositionstate() != null) {
						map.put("mPositionState",
								trGaterun.getfPositionstate().equals("1") ? "全开"
										: (trGaterun.getfPositionstate()
												.equals("2") ? "中间"
												: (trGaterun
														.getfPositionstate()
														.equals("3") ? "全关"
														: "")));
					}
				}
			}
			maps.add(map);
		}
		gson = new Gson();
		return gson.toJson(maps);
	}

	/**
	 * @Title: unitDetails
	 * @Description: TODO(机组实时数据)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "unitDetails", method = RequestMethod.POST)
	@ResponseBody
	public String unitDetails() {
		List<Unit> units = new ArrayList<>();
		Iterator<String> unititer = messageQueue.unitMap.keySet().iterator();
		while (unititer.hasNext()) {
			Object key = unititer.next();
			Unit unit = messageQueue.unitMap.get(key);
			if(unit != null){
				units.add(unit);
			}
		}
		Collections.sort(units, new Comparator<Unit>() {
			@Override
			public int compare(Unit o1, Unit o2) {
				if(o1.getStation().getLevels()-o2.getStation().getLevels()>0){
					return 1;
				}else if(o1.getStation().getLevels()-o2.getStation().getLevels()<0){
					return -1;
				}else{
					return o1.getName().compareTo(o2.getName());
				}
			}
		});
		// 获取实时数据存入List中
		List<TrPumprun> list = new ArrayList<>();
		Iterator<String> iter = messageQueue.concurrentPumpruns.keySet()
				.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			List<TrPumprun> stPumprs = messageQueue.concurrentPumpruns.get(key);
			list.addAll(stPumprs);
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Unit unit : units) {
			if(unit.getStation()==null){
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("mHubName", unit.getStation().getName());
			map.put("mValveNumber", unit.getName());
			for (TrPumprun trPumprun : list) {
				if(unit.getCode().equals(trPumprun.getfPumpcode())&& trPumprun.getfStationcode().equals(unit.getStation().getCode())){
					if (trPumprun.getfTime() != null) {
						map.put("mCollectTime",sf.format(trPumprun.getfTime()));
					}
					if (trPumprun.getfRunstate().equals("1")) {
						map.put("mOperationState", "运行");
					} else if (trPumprun.getfRunstate().equals("2")) {
						map.put("mOperationState", "停止");
					} else if (trPumprun.getfRunstate().equals("3")) {
						map.put("mOperationState", "报警");
					}
				}
			}
			maps.add(map);
		}
		gson = new Gson();
		return gson.toJson(maps);
	}

	/**
	 * @Title: waterDetails
	 * @Description: TODO(水情实时数据)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "waterDetails", method = RequestMethod.POST)
	@ResponseBody
	public String waterDetails() {
		List<Station> stations = new ArrayList<>();
		//logger.info(messageQueue.stationMap);
		//System.out.println(messageQueue.stationMap);
		Iterator<String> stationiter = messageQueue.stationMap.keySet()
				.iterator();
		while (stationiter.hasNext()) {
			Object key = stationiter.next();
			Station station = messageQueue.stationMap.get(key);
			if(station != null){
				if(station.getCode().equals("bthcz")){
					continue;
				}
				if(station.getCode().equals("bth")){
					Station s = new Station();
					s.setId(station.getId());
					s.setName(station.getName()+ "(船闸)");
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
		}
		Collections.sort(stations, new Comparator<Station>() {
			@Override
			public int compare(Station o1, Station o2) {
				if(o1.getLevels()-o2.getLevels()>0){
					return 1;
				}else if(o1.getLevels()-o2.getLevels()<0){
					return -1;
				}else{
					return 0;
				}
			}
		});
		List<StPumpr> list = new ArrayList<>();
		// 获取实时数据存入List中
		Iterator<String> iter = messageQueue.concurrentPumprs.keySet()
				.iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			List<StPumpr> stPumprs = messageQueue.concurrentPumprs.get(key);
			if (stPumprs != null) {
				list.addAll(stPumprs);
			}
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Station station : stations) {
			Map<String, Object> map = new HashMap<>();
			map.put("HubName", station.getName());
			map.put("stationid",station.getId());
			for (StPumpr stPumpr : list) {
				if(stPumpr.getStcd().equals(station.getCode())){
					if(station.getCode().equals("zg")){
						map.put("InWaterLevel", String.format("%.2f", new BigDecimal(df.format(stPumpr.getPpupz()))));
						map.put("OutWaterLevel", String.format("%.2f", new BigDecimal(df.format(stPumpr.getPpdwz()))));
					}else{
						map.put("InWaterLevel", String.format("%.2f", new BigDecimal(df.format(stPumpr.getPpdwz()))));
						map.put("OutWaterLevel", String.format("%.2f", new BigDecimal(df.format(stPumpr.getPpupz()))));
					}
					map.put("CollectTime", sf.format(new Date()));
				}
			}
			maps.add(map);
		}
		gson = new Gson();
		return gson.toJson(maps);
	}
	
	@RequestMapping(value = "rainsDetails", method = RequestMethod.POST)
	@ResponseBody
	public String rainsDetails(){
		List<StPptnR> list = new ArrayList<>();
		Iterator<String> iter = messageQueue.concurrentStPptnR.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			List<StPptnR> stPumprs = messageQueue.concurrentStPptnR.get(key);
			if(stPumprs != null){
				list.addAll(stPumprs);
			}
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		map.put("code", "lzg");
		map.put("name", "老澡港河枢纽");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		BigDecimal b = new BigDecimal(0);
		for(StPptnR s : list){
			if("lzg".equals(s.getStcd())){
				if(s.getDyp().compareTo(b) != -1){
					map.put("total", s.getDyp());
					if(s.getTm() != null){
						map.put("time", sf.format(s.getTm()));
					}
					b = s.getDyp();
				}
			}
		}
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("code", "hthn");
		map2.put("name", "横塘河南枢纽");
		b = new BigDecimal(0);
		for(StPptnR s : list){
			if("hthn".equals(s.getStcd())){
				if(s.getDyp().compareTo(b) != -1){
					map2.put("total", s.getDyp());
					if(s.getTm() != null){
						map2.put("time", sf.format(s.getTm()));
					}
					b = s.getDyp();
				}
			}
		}
		
		Map<String, Object> map3 = new HashMap<>();
		map3.put("code", "tzh");
		map3.put("name", "童子河闸站");
		b = new BigDecimal(0);
		for(StPptnR s : list){
			if("tzh".equals(s.getStcd())){
				if(s.getDyp().compareTo(b) != -1){
					map3.put("total", s.getDyp());
					if(s.getTm() != null){
						map3.put("time", sf.format(s.getTm()));
					}
					b = s.getDyp();
				}
			}
		}
		
		maps.add(map);
		maps.add(map2);
		maps.add(map3);
		
		gson = new Gson();
		String json = gson.toJson(maps);
		return json;
	}
	
	@RequestMapping(value = "checkUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String checkUpdate(HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/") +"upload/softversion/version.txt";
		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader reader = null;
		String jsonResult=null;
		String version = null;
		try {
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			jsonResult=reader.readLine().toString();
			JSONObject object = JSONObject.parseObject(jsonResult);
			if(object.getString("version") == null){
				version = "";
			}else{
				version = object.getString("version").toString();
			} 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return version;
	}

}
