package com.benqzl.dao.dispatch;

import java.util.List;

import com.benqzl.pojo.dispatch.ReceiptDispatchInstructions;

public interface ReceiptDispatchInstructionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<ReceiptDispatchInstructions> record);

    ReceiptDispatchInstructions selectByPrimaryKey(String id);

	List<ReceiptDispatchInstructions> findInstructions(String id);

	void deleteByRPID(String id);
    
	List<ReceiptDispatchInstructions> selectByRDID(String id);

	void insertInstructions(
			List<ReceiptDispatchInstructions> receiptDispatchInstructions);

	List<ReceiptDispatchInstructions> selectInstructionsByRPID(String rpid);

	List<ReceiptDispatchInstructions> findReceiptInstructions(String id);

	void deleteAreaInstruction(String reid);

	void updateInstructionsMapper(
			List<ReceiptDispatchInstructions> receiptDispatchInstructions1);

}