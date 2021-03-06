package com.benqzl.service.water;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.PumprunItemsMapper;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.PumprunItems;

@Service("pumprunItemsService")
public class PumprunItemsServiceImpl implements PumprunItemsService {
	@Autowired
	private PumprunItemsMapper itemsMapper;

	@Override
	public int insert(List<PumprunItems> startItems, List<String> endItems) {
		if (startItems.size() != 0) {
			itemsMapper.insert(startItems);
		}
		//long long2 = new Date().getTime() + (1000 * 60 * 60 * 24*2);
		//Date date1 = new Date(long2);
		if (endItems.size() != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("endtime", new Date());
			map.put("ids", endItems);
			itemsMapper.updateByPrimaryKey(map);
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> findAllByTime(Map<String, Object> map) {
		Date starttime = (Date) map.get("starttime");
		Date endtime = (Date) map.get("endtime");
		List<Station> stations = itemsMapper.findAllByTime(map);
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Station station : stations) {
			if (station.getUnits().size() != 0) {
				for (Unit unit : station.getUnits()) {
					Map<String, Object> mapItems = new HashMap<>();
					mapItems.put("id", UUID.randomUUID().toString());
					mapItems.put("uname", unit.getName());
					mapItems.put("sname", station.getName());
					mapItems.put("uid", unit.getId());
					//mapItems.put("sid", station.getId());
					double kjtime = 0.0;
					double totalflow = 0.0;
					if (unit.getItems().size() != 0) {
						for (PumprunItems items : unit.getItems()) {
							if(starttime==null&&endtime==null){
								items.sum(null, null, unit
										.getDesigndischarge().floatValue());
							}else{
								items.sum(starttime, endtime, unit
										.getDesigndischarge().floatValue());
							}
							kjtime += items.getSumH();
							totalflow += items.getSumF();
						}
					}
					mapItems.put("kjtime", Math.round(kjtime*100)/100.0);
					mapItems.put("totalflow", Math.round(totalflow*100)/100.0);
					maps.add(mapItems);
				}
			}
		}
		return maps;
	}
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public List<Map<String, Object>> findByUnit(Map<String, Object> map) {
		List<Unit> units = itemsMapper.findByUnit(map);
		Date starttime = (Date) map.get("starttime");
		Date endtime = (Date) map.get("endtime");
		List<Map<String, Object>> maps = new ArrayList<>();
		if (units.size() != 0) {
			for (Unit unit : units) {
				if (unit.getItems().size() != 0) {
					for (PumprunItems items : unit.getItems()) {
						if(starttime==null&&endtime==null){
							items.sum(null, null, unit
									.getDesigndischarge().floatValue());
						}else{
							items.sum(starttime, endtime, unit
									.getDesigndischarge().floatValue());
						}
						Map<String, Object> mapItems = new HashMap<>();
						mapItems.put("starttime", dateFormat.format(items.getStarttime()));
						mapItems.put("endtime", dateFormat.format(items.getEndtime()));
						mapItems.put("kjtime", Math.round(items.getSumH()*100)/100.0);
						mapItems.put("totalflow", Math.round(items.getSumF()*100)/100.0);
						maps.add(mapItems);
					}
				}
			}
		}
		return maps;
	}

}
