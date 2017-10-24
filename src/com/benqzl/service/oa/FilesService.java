/**    
 * @Title: FilesService.java  
 * @Package com.benqzl.service  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author shimh    
 * @date 2015年12月16日 上午9:55:10  
 * @version V1.0    
 */
package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.core.LeeTree;
import com.benqzl.pojo.oa.FileShare;
import com.benqzl.pojo.oa.Files;
import com.benqzl.pojo.oa.Foler;

/**
 * @ClassName: FilesService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shimh
 * @date 2015年12月16日 上午9:55:10
 * 
 */
public interface FilesService {
	List<Files> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);
	
	LeeTree<Foler> selectFoler();

	int deleteFiles(String id) throws Exception;
	
	int deleteFolder(String id) throws Exception;
	
	int deleteFilesShareByFilesId(String id) throws Exception;
	
	int insertSelective(Foler foler)throws Exception;
	
	Foler selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(Foler foler);
	
	int insertSelective(Files files)throws Exception;
	
	Files selectByFilesId(String id);
	
	int insertSelective(FileShare filesShare)throws Exception;

}
