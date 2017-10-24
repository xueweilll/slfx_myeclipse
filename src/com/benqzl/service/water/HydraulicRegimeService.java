package com.benqzl.service.water;

import java.util.List;

import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;

public interface HydraulicRegimeService {

	int insertGaterun(List<TrGaterun> record);
	
	int insertPumprun(List<TrPumprun> record);
	
	
}
