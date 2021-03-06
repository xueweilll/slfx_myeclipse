package com.benqzl.core;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.benqzl.dao.system.StationMapper;
import com.benqzl.pojo.system.Gate;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.service.system.GateService;
import com.benqzl.service.system.UnitService;
import com.benqzl.socket.MessageQueue;

public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UnitService unitservice;
	@Autowired
	private GateService gateservice;
	@Autowired
	private StationMapper mapper;
	@Autowired
	private MessageQueue messageQueue;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		List<Unit> units = new ArrayList<>();
		List<Gate> gates = new ArrayList<>();
		List<Station> stations = new ArrayList<>();
		try {
			stations = mapper.selectByAll();
			units=unitservice.selectAll();
			gates=gateservice.selectAll();
			for (Station station : stations) {
				messageQueue.stationMap.put(station.getId(), station);
			}
			for (Unit unit : units) {
				unit.setStates(2);
				messageQueue.unitMap.put(unit.getId(), unit);
			}
			for (Gate gate : gates) {
				messageQueue.gateMap.put(gate.getId(), gate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
