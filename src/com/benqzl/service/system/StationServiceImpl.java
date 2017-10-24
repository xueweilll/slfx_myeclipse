package com.benqzl.service.system;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

























import com.benqzl.dao.system.CameraMapper;
import com.benqzl.dao.system.GateMapper;
import com.benqzl.dao.system.StationMapper;
import com.benqzl.dao.system.UnitMapper;
import com.benqzl.pojo.system.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


@Service("stationService")
public class StationServiceImpl implements StationService{

	@Autowired
	private StationMapper mapper;
	@Autowired
	private CameraMapper cameramapper;
	@Autowired
	private GateMapper gatemapper;
	@Autowired
	private UnitMapper unitmapper;
	@Override
	public List<Station> findByPage(Map<String, Object> map) {
		
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		
		return mapper.pageCount(map);
	}

	@Override
	public void insert(Station station) {
		mapper.insertSelective(station);
	}

	@Override
	public Station selectByPrimaryKey(String id) {
		
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(Station station) {
		
		mapper.updateByPrimaryKeySelective(station);
	}

	@Override
	public void deleteIsdel(String id) {
		cameramapper.deleteBySid(id);
		gatemapper.deleteBySid(id);
		unitmapper.deleteBySid(id);
		mapper.deleteIsdel(id);
	}

	@Override
	public List<Station> stationByFk() {
		return mapper.stationByFk();
	}

	@Override
	public int exsitCode(String code) {
		return mapper.exsitCode(code);
	}

	@Override
	public int exsitName(String name) {
		
		return mapper.exsitName(name);
	}

	@Override
	public String selectByAll() {
		List<Station> list = mapper.selectByAll();
		Gson gson = new GsonBuilder().registerTypeAdapter(Station.class,
				new JsonSerializer<Station>() {

					@Override
					public JsonElement serialize(Station src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("code", src.getCode());
						o.addProperty("Name", src.getName());
						o.addProperty("Lat", src.getLat().toString());
						o.addProperty("Lon", src.getLon().toString());					
						return o;
					}
				}).create();
		
		return gson.toJson(list);
	}

	@Override
	public List<Station> selectBYCode(Map<String, Object> map) {
		return mapper.selectBYCode(map);
	}

	@Override
	public Station findStationInformation(Map<String, Object> map) {
		return mapper.findStationInformation(map);
	}

	@Override
	public List<Station> findStations(Map<String,Object> map) {
		return mapper.findStations(map);
	}

	@Override
	public List<Station> findStation() {
		return mapper.findStation();
	}

	@Override
	public List<String> findDepartmentIdsBySids(List<String> list) {
		List<String> departments= mapper.findDepartmentIdsBySids(list);
		return departments;
	}

	@Override
	public List<Station> findStationByPAndB() {
		return mapper.findStationByPAndB();
	}

	@Override
	public Station findStationName() {		
		return mapper.findStationName();
	}

	@Override
	public int selectcount() {
		return mapper.selectcount();
	}

	@Override
	public List<Station> selectByDepartment(String sid) {
		return mapper.selectByDepartment(sid);
	}

	@Override
	public List<Station> patrolnormalstation(String userid) {
	
		return mapper.patrolnormalstation(userid);
	}

	@Override
	public List<Station> selectByAppCamera() {
		return mapper.selectByAppCamera();
	}

	@Override
	public List<Station> selectSpecialByDepartment(Map<String, Object> map) {
		return mapper.selectSpecialByDepartment(map);
	}

	
}
