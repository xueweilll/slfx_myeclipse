package com.benqzl.service.dispatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchCallbackMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatchCallback;

@Service("receiptDispatchCallbackService")
public class ReceiptDispatchCallbackServiceImpl implements ReceiptDispatchCallbackService{

	@Autowired
	private ReceiptDispatchCallbackMapper mapper;
	
	@Override
	public List<ReceiptDispatchCallback> selectByRDID(String rdid) {
		return mapper.selectByRDID(rdid);
	}

}
