package com.benqzl.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.AlarmShipMapper;
import com.benqzl.pojo.system.AlarmShip;

@Service("alarmShipService")
public class AlarmShipServiceImpl implements AlarmShipService {
	@Autowired
	private AlarmShipMapper alarmShipmapper;
	
	@Override
	public List<AlarmShip> alarmShipsByUserId(String userId) {
		return alarmShipmapper.alarmShipsByUserId(userId);
	}

	@Override
	public void deleteAlarmShipsByUserId(String userId) {
		alarmShipmapper.deleteAlarmShipsByUserId(userId);
	}

	@Override
	public void saveAlarmShips(List<AlarmShip> alarmShips) {
		if(alarmShips==null){
			return;
		}
		if(alarmShips.size()==0){
			return;
		}
		alarmShipmapper.insertList(alarmShips);
	}

}
