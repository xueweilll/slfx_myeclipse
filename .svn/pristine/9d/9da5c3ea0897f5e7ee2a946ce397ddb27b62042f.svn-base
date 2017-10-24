package com.benqzl.service.dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchDepartmentMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatchDepartment;

@Service("receiptDispatchDepartmentService")
public class ReceiptDispatchDepartmentServiceImpl implements ReceiptDispatchDepartmentService{
	@Autowired
	private ReceiptDispatchDepartmentMapper ddm;
	@Override
	public int selectByPk(HashMap<String,Object> map) {
		return ddm.selectByPk(map);
	}
	@Override
	public void update(HashMap<String, Object> map) {
		ddm.update(map);
		
	}
	@Override
	public List<ReceiptDispatchDepartment> findRdSendList(
			Map<String, Object> map) {
		return ddm.findRdSendList(map);
	}
	@Override
	public int findRdSendCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ddm.findRdSendCount(map);
	}

}
