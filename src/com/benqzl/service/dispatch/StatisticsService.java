package com.benqzl.service.dispatch;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.dispatch.UnitExt;

public interface StatisticsService {
	List<UnitExt> UnitExts(Date dt1,Date dt2)throws Exception;
}
