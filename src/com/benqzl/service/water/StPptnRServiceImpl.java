package com.benqzl.service.water;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.water.StPptnRMapper;
import com.benqzl.pojo.water.StPptnR;

@Service("stPptnRService")
public class StPptnRServiceImpl implements StPptnRService {

	@Autowired
	private StPptnRMapper mapper;
	
	@Override
	public int insert(List<StPptnR> record) {
		List<StPptnR> l = new ArrayList<>();
		for(StPptnR s : record){
			StPptnR stpptnr = mapper.selectByFid(s.getId());
			if(stpptnr != null){
				//record.remove(s);
				l.add(s);
			}
		}
		if(l.size() > 0){
			record.removeAll(l);
		}
		if(record.size() > 0){
			return mapper.insert(record);
		}else{
			return 1;
		}
	}

}
