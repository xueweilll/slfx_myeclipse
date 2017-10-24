package com.benqzl.dao.water;

import java.util.List;

import com.benqzl.pojo.water.TbWarndict;

public interface TbWarndictMapper {
    int deleteByPrimaryKey(Long fId);

    int insert(List<TbWarndict> record);

    int insertSelective(TbWarndict record);

    TbWarndict selectByPrimaryKey(Long fId);

    int updateByPrimaryKeySelective(TbWarndict record);

    int updateByPrimaryKey(TbWarndict record);
}