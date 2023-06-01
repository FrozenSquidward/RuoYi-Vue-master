package com.bingfeng.pp.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bingfeng.pp.domain.TSurveyDirectory;
import org.apache.ibatis.annotations.Param;

/**
 * 我的问卷Mapper接口
 *
 * @author bingfeng
 * @date 2023-05-25
 */
public interface TSurveyDirectoryMapper extends BaseMapper<TSurveyDirectory>
{
    /**
     * 查询我的问卷列表
     *
     * @param tSurveyDirectory 我的问卷
     * @return 我的问卷集合
     */
    public Page<TSurveyDirectory> selectTSurveyDirectoryList(Page<TSurveyDirectory> page, @Param(value = "tSurveyDirectory")  TSurveyDirectory tSurveyDirectory);
}
