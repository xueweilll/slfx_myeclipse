package com.benqzl.dao.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.Document;
import com.benqzl.pojo.system.User;

public interface DocumentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Document record);

    int insertSelective(Document record);

    Document selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);
    
    List<Document> selectAll();
   
    List<Document> findByPage(Map<String, Object> map);

    int pageCount(Map<String, Object> map);

	int exsitCode(String code);

	List<User> selectUserbyParentID(Map<String, Integer> map);

	int deleteDocument(String id);
	
	int selectCount(String id);

	Document findUser(String id);

	List<User> selectUserbyID(String id);

	Long selectLevel(String leve);
	
	int updateFilesAddress(Map<String, String> map);

	List<Document> findByPages(Map<String, Object> map);

	int pageCounts(Map<String, Object> map);

}
