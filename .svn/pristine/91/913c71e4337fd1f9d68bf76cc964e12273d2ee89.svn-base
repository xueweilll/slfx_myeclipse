package com.benqzl.service.patrol;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.patrol.SzPatrolMapper;
import com.benqzl.pojo.patrol.SzPatrol;
import com.benqzl.pojo.patrol.SzPatrolUser;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
@Service("SZPatrolService")
public class SZPatrolServiceImpl implements SZPatrolService {
	@Autowired
	private SzPatrolMapper mapper;	
	@Override
	public List<SzPatrol> findSZPatrol(Map<String, String> map) {
		return mapper.findSZPatrol(map);
	}
	@Override
	public int pageCount(Map<String, String> map) {		
		return mapper.pageCount(map);
	}
	@Override
	public SzPatrol findSzPatrolById(String id) {		
		return mapper.findSzPatrolById(id);
	}
	@Override
	public List<Station> findStation() {
		return mapper.findStation();
	}
	@Override
	public List<User> findUser(String sid) {
		return mapper.findUser(sid);
	}
	@Override
	public String findSidByUserId(String userid) {		
		return mapper.findSidByUserId(userid);
	}
	@Override
	public List<User> findAllUser() {		
		return mapper.findAllUser();
	}
	@Override
	public void insertSzPztrol(SzPatrol szpatrol) {
		mapper.insertSzPztrol(szpatrol);
	}
	@Override
	public void insertSzPatrolUser(SzPatrolUser szuser) {
		mapper.insertSzPatrolUser(szuser);
	}
	@Override
	public List<SzPatrol> findSZPatrolNotAll(Map<String, String> map) {
		return mapper.findSZPatrolNotAll(map);
	}
	@Override
	public List<SzPatrol> findSzUserBySzId(String szid) {		
		return mapper.findSzUserBySzId(szid);
	}
	@Override
	public void updateSzPztrol(SzPatrol szpatrol) {
		mapper.updateSzPztrol(szpatrol);
	}
	@Override
	public void deleteSzUser(String id) {
		mapper.deleteSzUser(id);
	}
	@Override
	public void deleteSz(String szid) {
		mapper.deleteSz(szid);
	}
	@Override
	public void report(SzPatrol szpatrol) {
		mapper.report(szpatrol);
	}
	@Override
	public List<SzPatrol> findSZPatrolApproval(Map<String, String> map) {
		return mapper.findSZPatrolApproval(map);
	}
	@Override
	public List<SzPatrol> findSZPatrolApprovalNotAll(Map<String, String> map) {
		return mapper.findSZPatrolApprovalNotAll(map);
	}
	@Override
	public int pageCountNotAll(Map<String, String> map) {		
		return mapper.pageCountNotAll(map);
	}
	@Override
	public int pageApprovalCount(Map<String, String> map) {
		return mapper.pageApprovalCount(map);
	}
	@Override
	public int pageApprovalCountNotAll(Map<String, String> map) {
		return mapper.pageApprovalCountNotAll(map);
	}
	@Override
	public String findStationById(String szid) {		
		return mapper.findStationById(szid);
	}
	@Override
	public String findStationByUserId(String userid) {		
		return mapper.findStationByUserId(userid);
	}
	
}
