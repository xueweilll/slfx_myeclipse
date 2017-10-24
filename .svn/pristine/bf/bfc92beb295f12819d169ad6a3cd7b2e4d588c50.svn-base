package com.benqzl.service.dispatch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




















import com.benqzl.dao.dispatch.SelfDispatchDepartmentMapper;
import com.benqzl.dao.dispatch.SelfDispatchInstructionsMapper;
import com.benqzl.dao.dispatch.SelfDispatchMapper;
import com.benqzl.dao.dispatch.SelfDispatchStationsMapper;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.dispatch.SelfDispatchDepartment;
import com.benqzl.pojo.dispatch.SelfDispatchInstructions;
import com.benqzl.pojo.dispatch.SelfDispatchStations;
@Service("DispatchIssuedListService")
public class DispatchIssuedListServiceImpl implements DispatchIssuedListService {
	@Autowired
	private SelfDispatchMapper selmapper;
	@Autowired
	private SelfDispatchStationsMapper sslmapper;
	@Autowired
	private SelfDispatchInstructionsMapper silmapper;
	@Autowired
	private SelfDispatchDepartmentMapper selfdepartment;
	@Override
	public SelfDispatch selectDispatchIssuedInfo(String id) {
		// TODO Auto-generated method stub
		return selmapper.selectDispatchIssuedInfo(id);
	}



	@Override
	public void updateByPrimaryKeyIssued(Map<String, Object> map, Map<String, Object> mu) {
		// TODO Auto-generated method stub
		selmapper.updateByPrimaryKeyIssued(map);
		if(mu != null){
			selfdepartment.updateById(mu);
		}
	}



	@Override
	public List<SelfDispatch> selectDispatchIssued(String id) {
		// TODO Auto-generated method stub
		List<SelfDispatch> list=selmapper.selectDispatchIssued(id);
		return list;
	}



	@Override
	public List<SelfDispatch> selectDispatchIssuedzl(String id) {
		// TODO Auto-generated method stub
		List<SelfDispatch> zl=selmapper.selectDispatchIssuedzl(id);
		return zl;
	}



	@Override
	public List<SelfDispatch> selectDispatchIssuedmx(String id) {
		// TODO Auto-generated method stub
		List<SelfDispatch> mx=selmapper.selectDispatchIssuedmx(id);
		return mx;
	}



	@Override
	public List<SelfDispatch> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<SelfDispatch> page=selmapper.findByPage(map);
		return page;
	}



	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selmapper.pageCount(map);
	}



	@Override
	public List<SelfDispatchStations> selectSDStationBySDID(String id) {
		return sslmapper.selectSDStationBySDID(id);
	}



	@Override
	public List<SelfDispatchInstructions> selectSDInstructionsBySDID(String id) {
		return silmapper.selectBySDID(id);
	}



	@Override
	public int updateComplete(String id) {
		// TODO Auto-generated method stub
		return selmapper.updateComplete(id);
	}



	@Override
	public SelfDispatch selectByPrimaryKey(String id) {
		return selmapper.selectByPrimaryKey(id);
	}



	@Override
	public List<SelfDispatchDepartment> findByPages(Map<String, Object> map) {
		
		return selfdepartment.findByPage(map);
	}



	@Override
	public int pageCounts(Map<String, Object> map) {
		
		return selfdepartment.pageCount(map);
	}



	@Override
	public List<SelfDispatch> findByPage1(Map<String, Object> map) {
		
		return selmapper.findByPage1(map);
	}



	@Override
	public List<SelfDispatch> findByReceiptPagePrint(Map<String, Object> map) {
		return selmapper.findByReceiptPagePrint(map);
	}

}
