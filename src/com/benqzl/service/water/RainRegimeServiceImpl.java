package com.benqzl.service.water;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.StPptnRMapper;
import com.benqzl.pojo.water.StPptnR;


@Service("rainRegimeService")
public class RainRegimeServiceImpl implements RainRegimeService{

	@Autowired
	private StPptnRMapper mapper;
	
	@Override
	public List<StPptnR> findByPage(Map<String, Object> map) {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public List<Map<String, Object>> findRainWater(Map<String, Object> map1) {
		List<StPptnR> stpumpr = mapper.findRainWater(map1);
			List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
				Map<String, Object> map2 = new HashMap<>();
				Map<String,Object> map4=new HashMap<>();
				List<String> temp = new ArrayList<>();
				List<BigDecimal> dpr = new ArrayList<>();
				for(int a=0;a<stpumpr.size();a++){					   
				    	temp.add(stpumpr.get(a).getStcd());
				    	dpr.add(stpumpr.get(a).getDpr());
							map2.put("month", temp);
							map2.put("dpr", dpr);
							map4.put("st", map2);
				}
				list.add(map4);
			return list;
		
	}

	

}
