package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.oa.FileShareMapper;
import com.benqzl.pojo.oa.FileShare;

/**
 * 
* @ClassName: FilesShareServiceImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2015年12月23日 下午12:42:14  
*
 */
@Service("filesShareService")
public class FilesShareServiceImpl implements FilesShareService {

	@Autowired
	private FileShareMapper fileShareMapper;

	@Override
	public List<FileShare> findMyShare(Map<String, Object> map) {
		return fileShareMapper.findMyShare(map);
	}
	
	@Override
	public List<FileShare> findShareToMe(Map<String, Object> map) {
		return fileShareMapper.findShareToMe(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return fileShareMapper.pageCount(map);
	}

}
