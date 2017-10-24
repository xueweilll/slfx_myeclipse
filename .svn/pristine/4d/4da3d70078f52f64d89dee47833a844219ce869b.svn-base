package com.benqzl.dao.system;

import java.util.List;

import com.benqzl.pojo.system.Authority;
import com.benqzl.pojo.system.MenuAndAuthority;

public interface AuthorityMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<Authority> authorities);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
    
    List<MenuAndAuthority> selectAll(String id);
}