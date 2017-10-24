package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.ActorMapper;
import com.benqzl.pojo.system.Actor;
@Service("actorService")
public class ActorServiceImpl implements ActorService {
	@Autowired
	private ActorMapper actorMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return actorMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Actor record) {
		// TODO Auto-generated method stub
		return actorMapper.insert(record);
	}

	@Override
	public int insertSelective(Actor record) {
		// TODO Auto-generated method stub
		return actorMapper.insertSelective(record);
	}

	@Override
	public Actor selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return actorMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Actor record) {
		// TODO Auto-generated method stub
		return actorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Actor record) {
		// TODO Auto-generated method stub
		return actorMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Actor> selectAll() {
		// TODO Auto-generated method stub
	   List<Actor> actors=actorMapper.selectAll();
		return actors;
	}

	@Override
	public List<Actor> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return actorMapper.findByPage(map);
	}
/*
 * (非 Javadoc) 
* <p>Title: pageCount</p> 
* <p>Description: 获取总记录数</p> 
* @return 
* @see com.benqzl.service.ActorService#pageCount()
 */
	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return actorMapper.pageCount(map);
	}

     @Override
    public int exsitName(String name) {
	// TODO Auto-generated method stub
	return actorMapper.exsitName(name);
}

	@Override
	public Actor findActor(String id) {
		return actorMapper.selectAuthorityByActorId(id);
	}



}
