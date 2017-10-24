package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.oa.DocumentMapper;
import com.benqzl.dao.oa.DocumentReceiverMapper;
import com.benqzl.dao.system.MessageCenterMapper;
import com.benqzl.pojo.oa.Document;
import com.benqzl.pojo.oa.DocumentReceiver;
import com.benqzl.pojo.system.User;
import com.benqzl.unit.SendMessageAutoUtil;
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	@Autowired
	private DocumentMapper documentMapper;
	@Autowired
	private DocumentReceiverMapper documentRecieverMapper;
	@Autowired
	private MessageCenterMapper centerMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return documentMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insertSelective(Document record) {
		// TODO Auto-generated method stub
		return documentMapper.insertSelective(record);
	}

	@Override
	public Document selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return documentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Document record) {
		// TODO Auto-generated method stub
		return documentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Document record) {
		// TODO Auto-generated method stub
		return documentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Document> selectAll() {
		// TODO Auto-generated method stub
		List<Document> documents= documentMapper.selectAll();
		return  documents;
	}

	@Override
	public List<Document> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return documentMapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return documentMapper.pageCount(map);
	}

	@Override
	public int exsitCode(String code) {
		// TODO Auto-generated method stub
		return documentMapper.exsitCode(code);
	}

	@Override
	public List<User> selectUserbyParentID(Map<String,Integer> map) {
		// TODO Auto-generated method stub
		List<User> documents=documentMapper.selectUserbyParentID(map);
		return documents;
	}

	@Override
	public int insert(Document record, List<DocumentReceiver> receiver)
			throws Exception {
		
		documentMapper.insertSelective(record);
		documentRecieverMapper.insert(receiver);
		
		return 0;
	}
	
	@Override
	public int updateState(String id,String userid) {
		
		SendMessageAutoUtil.sendMessageAuto(userid);
		centerMapper.deleteByTip(id);
		return documentRecieverMapper.updateState(id);
	}
	@Override
	public int selectCount(String id) {
		
		return documentMapper.selectCount(id);
	}
	@Override
	public Document findUser(String id) {
		
		return documentMapper.findUser(id);
	}
	@Override
	public int insertReceiver(DocumentReceiver dr) {
		
		return documentRecieverMapper.insertSelective(dr);
	}
/*	@Override
	public int delete(DocumentReceiver record) {
		// TODO Auto-generated method stub
		return documentRecieverMapper.delete(record);
	}*/
	@Override
	public int delete(String listx) {
		
		return 0;
	}
	@Override
	public int deleteByDocumentID(String id) {
		
		return documentRecieverMapper.deleteByDocumentID(id);
	}
	@Override
	public int deleteState(String id) {
		
		return documentRecieverMapper.deleteState(id);
	}
	@Override
	public List<User> selectUserbyID(String id) {
		
		List<User> documents=documentMapper.selectUserbyID(id);
		return documents;
		
	}
	@Override
	public int updateState(String id) {
		
		return 0;
	}

	@Override
	public Long selectLevel(String leve) {
		
		return documentMapper.selectLevel(leve);
	}

	@Override
	public int updateFilesAddress(Map<String, String> map) {
		
		return documentMapper.updateFilesAddress(map);
	}
	@Override
	public List<Document> findByPages(Map<String, Object> map) {
		
		return documentMapper.findByPages(map);
	}
	@Override
	public int pageCounts(Map<String, Object> map) {
		return documentMapper.pageCounts(map);
	}




}
