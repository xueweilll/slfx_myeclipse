package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.ActivitiDeployment;

public interface ActivitiDeploymentMapper {
    int deleteByPrimaryKey(String id);
    int deleteByname(String id);

    int insertSelective(ActivitiDeployment record);

    List<ActivitiDeployment> selectByPrimaryKey(String id);
    
    List<ActivitiDeployment> findByPage(Map<String,Object> map);
    
    int pageCount(Map<String,Object> map);
}