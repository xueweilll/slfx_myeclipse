package com.benqzl.text;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.benqzl.pojo.system.AlarmInfo;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.PumprunItems;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.service.system.UnitService;
import com.benqzl.service.water.PumprunItemsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mybatis.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestStationAlarm {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	Station station = null;
	AlarmInfo alarmInfo = null;

	@Test
	public void name() {
		station = new Station();
		station.setId(UUID.randomUUID().toString());
		station.setName("大运河东枢纽");
		station.setInwatertop(new BigDecimal(5.0));
		station.setInwaterdown(new BigDecimal(2.0));
		station.setOutwatertop(new BigDecimal(5.0));
		station.setOutwaterdown(new BigDecimal(2.0));
		alarmInfo = station.getAlarmInfo(new BigDecimal(6.0), new BigDecimal(
				6.0));
		System.out.println(alarmInfo.alarmText());
	}

	@Autowired
	private PumprunItemsService itemsService;

	/*@Test
	public void subflowIsOverTest() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			long long2 = new Date().getTime() - (1000 * 60 * 60 * 24 * 0);
			Date date1 = new Date(long2);
			long long1 = new Date().getTime() + (1000 * 60 * 60 * 24 * 1);
			Date date2 = new Date(long1);
			//map.put("starttime", date1);
			//map.put("endtime", date2);
			List<String> sids = new ArrayList<>();
			sids.add("a4b4b311-2ed9-4037-ae19-46c24ccb9528");
			//map.put("sids", sids);
			// map.put("types", "4");
			List<String> uids = new ArrayList<>();
			uids.add("ee970720-f8e1-4bcf-b4e4-9a1c8778232c");
			map.put("uids", uids);
			map.put("uid", "9d9d0f19-2219-4259-b4b2-dec1f9c652e8");
			List<Map<String, Object>> stations = itemsService
					.findAllByTime(map);
			Gson gson = new Gson();
			System.out.println(gson.toJson(stations));
			List<Map<String, Object>> units = itemsService.findByUnit(map);
			System.out.println(gson.toJson(units));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	@Autowired
	private UnitService unitservice;

	@Test
	@Rollback(false)
	public void pumprunItemsInsert() {
		List<Unit> units = new ArrayList<>();
		units = unitservice.selectAll();
		while (true) {
			List<TrPumprun> trPumpruns = new ArrayList<>();
			for (Unit unit : units) {
				TrPumprun pumprun = new TrPumprun();
				pumprun.setfId(UUID.randomUUID().toString());
				pumprun.setfStationcode(unit.getStation().getCode());
				pumprun.setfPumpcode(unit.getCode());
				pumprun.setfRunstate(Math.round(Math.random()) + "");
				trPumpruns.add(pumprun);
			}
			insertPumprunItems(trPumpruns, units);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 插入机组运行记录值调用方法
	public int insertPumprunItems(List<TrPumprun> record, List<Unit> units) {
		List<PumprunItems> startItems = new ArrayList<>();
		List<String> endItems = new ArrayList<>();
		for (TrPumprun pumprun : record) {
			String tcode = pumprun.getfStationcode() + pumprun.getfPumpcode();
			for (Unit unit : units) {
				String ucode = unit.getStation().getCode() + unit.getCode();
				if (tcode.equals(ucode)) {
					if (unit.getStates() != Integer.parseInt(pumprun
							.getfRunstate())) {
						if (Integer.parseInt(pumprun.getfRunstate()) == 1) {// 机组运行，插入运行记录
							PumprunItems items = new PumprunItems();
							items.setId(UUID.randomUUID().toString());
							items.setPid(unit.getId());
							items.setStarttime(new Date());
							items.setEndtime(new Date());
							startItems.add(items);
						}
						if (Integer.parseInt(pumprun.getfRunstate()) == 2) {// 机组停止，插入停止记录
							endItems.add(unit.getId());
						}
						unit.setStates(Integer.parseInt(pumprun.getfRunstate()));
						// messageQueue.unitMap.put(unit.getId(), unit);
					} else {
						if (Integer.parseInt(pumprun.getfRunstate()) == 1) {// 机组运行，插入运行记录
							endItems.add(unit.getId());
						}
						unit.setStates(Integer.parseInt(pumprun.getfRunstate()));
						// messageQueue.unitMap.put(unit.getId(), unit);
					}
				}
			}
		}
		return itemsService.insert(startItems, endItems);
	}

}
