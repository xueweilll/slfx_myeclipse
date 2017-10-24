package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.PatrolSpecialFolw;

public interface PatrolSpecialFolwMapper {
    int deleteByPrimaryKey(String id);

    int insert(PatrolSpecialFolw record);
   
    int updateFlow(PatrolSpecialFolw record);
   
    int deleteflow(String isid );

    int insertSelective(PatrolSpecialFolw record);

    PatrolSpecialFolw selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolSpecialFolw record);

    int updateByPrimaryKey(PatrolSpecialFolw record);
    
    void updateByIsId(Map<String,Object> map);

	List<String> findDetailsByRid(String rid);
}