package com.benqzl.service.patrol;
import java.util.List;
import java.util.Map;

import com.benqzl.pojo.patrol.SzPatrol;
import com.benqzl.pojo.patrol.SzPatrolUser;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;

public interface SZPatrolService {

	List<SzPatrol> findSZPatrol(Map<String, String> map);

	int pageCount(Map<String, String> map);

	SzPatrol findSzPatrolById(String id);

	List<Station> findStation();

	List<User> findUser(String sid);

	String findSidByUserId(String userid);

	List<User> findAllUser();

	void insertSzPztrol(SzPatrol szpatrol);

	void insertSzPatrolUser(SzPatrolUser szuser);

	List<SzPatrol> findSZPatrolNotAll(Map<String, String> map);

	List<SzPatrol> findSzUserBySzId(String szid);

	void updateSzPztrol(SzPatrol szpatrol);

	void deleteSzUser(String id);

	void deleteSz(String szid);

	void report(SzPatrol szpatrol);

	List<SzPatrol> findSZPatrolApproval(Map<String, String> map);

	List<SzPatrol> findSZPatrolApprovalNotAll(Map<String, String> map);

	int pageCountNotAll(Map<String, String> map);

	int pageApprovalCount(Map<String, String> map);

	int pageApprovalCountNotAll(Map<String, String> map);

	String findStationById(String szid);

	String findStationByUserId(String userid);
	
}
