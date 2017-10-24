package com.benqzl.service.dispatch;

import java.util.List;

import com.benqzl.pojo.dispatch.ReceiptDispatchSupervise;

public interface ReceiptDispatchSuperviseServise {

	List<ReceiptDispatchSupervise> selectByRDID(String rdid);


}
