package com.benqzl.service.dispatch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchSuperviseMapper;
import com.benqzl.pojo.dispatch.ReceiptDispatchSupervise;

@Service("receiptDispatchSuperviseServise")
public class ReceiptDispatchSuperviseServiseImpl implements ReceiptDispatchSuperviseServise{

	@Autowired
	private ReceiptDispatchSuperviseMapper mapper;
	

	@Override
	public List<ReceiptDispatchSupervise> selectByRDID(String rdid) {
		return mapper.selectByRDID(rdid);
	}
	
}
