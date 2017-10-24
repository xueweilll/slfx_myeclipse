package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.DispatchInstructionsMapper;
import com.benqzl.dao.system.StationMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
@Service("DispatchInstructionsService")
public class DispatchInstructionsServiceImpl implements DispatchInstructionsService {
	@Autowired
	private DispatchInstructionsMapper dim;
	@Autowired
	private StationMapper station;
	@Override
	public List<SelfDispatch> findDispatch(Map<String,String> map) {		
		return dim.findDispatch(map);
	}
	@Override
	public int pageCount(Map<String,String> map) {		
		return dim.pageCount(map);
	}
	@Override
	public List<Station> findStation() {		
		return dim.findStation();
	}
	@Override
	public List<User> findUser() {	
		return dim.findUser();
	}
	@Override
	public List<String> findsidByname(Station station) {
		return dim.findsidByname(station);
	}
	@Override
	public void insertDispatch(SelfDispatch sd) {
		dim.insertDispatch(sd);		
	}
	@Override
	public void insertInstruction(SelfDispatchInstructions instruction) {
		dim.insertInstruction(instruction);		
	}
	@Override
	public void insertStation(SelfDispatchStations station) {
		dim.insertStation(station);
	}
	@Override
	public void deleteInstruction(String id) {
		dim.deleteInstruction(id);		
	}
	@Override
	public void deleteStation(String id) {
		dim.deleteStation(id);
	}
	@Override
	public void deleteDispatch(String id) {
		dim.deleteDispatch(id);
	}
	@Override
	public SelfDispatch findDispatchByid(Map<String, String> map) {
		return dim.findDispatchByid(map);
	}
	@Override
	public List<SelfDispatchInstructions> findInstructionsByid(String id) {		
		return dim.findInstructionsByid(id);
	}
	@Override
	public List<SelfDispatchStations> findStationsByid(String id) {		
		return dim.findStationsByid(id);
	}
	@Override
	public String findSnameByid(String id) {		
		return dim.findSnameByid(id);
	}
	@Override
	public void updateDispatch(SelfDispatch sd) {
		dim.updateDispatch(sd);
	}
	@Override
	public String findsidByname(String string) {
		return dim.finsidByname(string);
	}
	@Override
	public List<ReceiptDispatch> receptdispatch(Map<String, String> map) {
		return dim.receptdispatch(map);
	}
	@Override
	public List<Station> findSelfStation(Map<String, Object> map) {
		
		return station.findSelStation(map);
	}
	@Override
	public List<SelfDispatchStations> findDepartmentStation(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dim.findDepartmentStation(map);
	}

}
