package com.benqzl.dao.patrol;

import java.util.List;
import java.util.Map;



import com.benqzl.pojo.patrol.PatrolSpecialResolveDetails;

public interface PatrolSpecialResolveDetailsMapper {
    int insert(PatrolSpecialResolveDetails record);

    int insertSelective(PatrolSpecialResolveDetails record);

	List<PatrolSpecialResolveDetails> findegpatroldepartment(Map<String, Object> map);

	int findegpatroldepartmentcount(Map<String, Object> map);
	
	//插入resolveDetail集合类型
	void insertResolveDetail(List<PatrolSpecialResolveDetails> list);
	//删除数据
	void deleteByResolvedetailsKey(String isid);
	
	//查询部门map
	List<PatrolSpecialResolveDetails> findResolveDetails(Map<String, Object> map);
}