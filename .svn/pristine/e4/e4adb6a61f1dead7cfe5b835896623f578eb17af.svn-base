package com.benqzl.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.benqzl.dao.system.ActivitiTaskMapper;
import com.benqzl.service.material.StockService;

public class QuartzByPringMvc {
	
	@Autowired
	StockService stockService;
	
	@Autowired
	ActivitiTaskMapper atmapper;
	
	public void execute() {
		try {
			stockService.monthReceipts();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void procedureQuartz(){
		try {
			atmapper.procedureExc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
