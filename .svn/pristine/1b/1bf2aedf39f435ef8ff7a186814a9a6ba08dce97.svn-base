package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Size;

public interface SizeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Size record);

    int insertSelective(Size record);

    Size selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Size record);

    int updateByPrimaryKey(Size record);

	int insertSize(Size record);

	int updateSizeByPrimaryKey(Size record);

	int deleteSizeByPrimaryKey(String id);

	int pageSizeCount(Map<String, Object> map);

	List<Size> findSizeByPage(Map<String, Object> map);

	Size selectSizeByPrimaryKey(String id);

	List<Size> findSizeName();

	int validatesize(String sizeid);
}