package com.benqzl.dao.system;

import java.util.List;

import com.benqzl.pojo.system.AlarmShip;

public interface AlarmShipMapper {
    int deleteByPrimaryKey(String id);

    int insert(AlarmShip record);

    int insertSelective(AlarmShip record);

    AlarmShip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AlarmShip record);

    int updateByPrimaryKey(AlarmShip record);
    
    void deleteAlarmShipsByUserId(String userId);
    
    List<AlarmShip> alarmShipsByUserId(String userId);
    
    int insertList(List<AlarmShip> list);
}