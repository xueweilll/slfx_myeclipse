package com.benqzl.dao.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.Files;

/**
 * 
 * @ClassName: FilesMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shimh
 * @date 2015年12月16日 上午10:14:17
 * 
 */
public interface FilesMapper {
	int deleteByPrimaryKey(String id);

	int insertSelective(Files files);

	int updateByPrimaryKeySelective(Files files);

	int updateByPrimaryKey(Files files);

	List<Files> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	Files selectByPrimaryKey(String id);
}
