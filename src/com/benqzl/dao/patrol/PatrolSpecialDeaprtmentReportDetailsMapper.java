package com.benqzl.dao.patrol;

import java.util.List;

import com.benqzl.pojo.patrol.PatrolSpecialDeaprtmentReportDetails;

public interface PatrolSpecialDeaprtmentReportDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<PatrolSpecialDeaprtmentReportDetails> record);

    int insertSelective(PatrolSpecialDeaprtmentReportDetails record);

    PatrolSpecialDeaprtmentReportDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PatrolSpecialDeaprtmentReportDetails record);

    int updateByPrimaryKey(PatrolSpecialDeaprtmentReportDetails record);
}