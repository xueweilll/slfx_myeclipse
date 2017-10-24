package com.benqzl.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.benqzl.pojo.system.AlarmInfo;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.StPptnR;
import com.benqzl.pojo.water.StPumpr;
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.pojo.water.TrWarnlog;

@Service
@Scope("singleton")
public class MessageQueue {
	public ConcurrentHashMap<String, List<TrGaterun>> concurrentGateRuns = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<TrPumprun>> concurrentPumpruns = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<StPumpr>> concurrentPumprs = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<TrWarnlog>> concurrentTrWarnlogs = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<StPptnR>> concurrentStPptnR = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, AlarmInfo> concurrentAlarmInfo = new ConcurrentHashMap<>();

	public List<Station> list = new ArrayList<>();
	public ConcurrentHashMap<String, List<TrGaterun>> concurrentsaveGateRuns = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<TrPumprun>> concurrentsavePumpruns = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<StPumpr>> concurrentsavePumprs = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<TrWarnlog>> concurrentsaveTrWarnlogs = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, List<StPptnR>> concurrentsaveStPptnR = new ConcurrentHashMap<>();
	/**
	 * 缓存机组，枢纽，闸门
	 */
	public ConcurrentHashMap<String, Station> stationMap = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, Unit> unitMap = new ConcurrentHashMap<>();
	public ConcurrentHashMap<String, Gate> gateMap = new ConcurrentHashMap<>();

	public Station getStationByCode(String code) {
		Station station = null;

		for (Station s : stationMap.values()) {
			if(s.getCode().equals(code)){
				return s;
			}
		}

		return station;
	}
	
	public List<Station> getStations(){
		List<Station> stations = new ArrayList<>();
		for (Station s : stationMap.values()) {
			stations.add(s);
		}
		return stations;
	}
}
