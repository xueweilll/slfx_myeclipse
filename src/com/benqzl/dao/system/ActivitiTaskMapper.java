package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.ActivitiTask;

public interface ActivitiTaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<ActivitiTask> record);

    List<String> selectUser(Map<String, Object> map);
    List<String> selectTaskIDs(Map<String, Object> map);
    List<ActivitiTask> selectUserAll(String id);
    
    String selectMaxCode();
    
    void procedureExc();
}