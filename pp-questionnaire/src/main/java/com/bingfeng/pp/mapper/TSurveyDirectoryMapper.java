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
     * 查询我的问卷
     *
     * @param id 我的问卷主键
     * @return 我的问卷
     */
    public TSurveyDirectory selectTSurveyDirectoryById(String id);

    /**
     * 查询我的问卷列表
     *
     * @param tSurveyDirectory 我的问卷
     * @return 我的问卷集合
     */
    public Page<TSurveyDirectory> selectTSurveyDirectoryList(Page<TSurveyDirectory> page, @Param(value = "tSurveyDirectory")  TSurveyDirectory tSurveyDirectory);

    /**
     * 新增我的问卷
     *
     * @param tSurveyDirectory 我的问卷
     * @return 结果
     */
    public int insertTSurveyDirectory(TSurveyDirectory tSurveyDirectory);

    /**
     * 修改我的问卷
     *
     * @param tSurveyDirectory 我的问卷
     * @return 结果
     */
    public int updateTSurveyDirectory(TSurveyDirectory tSurveyDirectory);

    /**
     * 删除我的问卷
     *
     * @param id 我的问卷主键
     * @return 结果
     */
    public int deleteTSurveyDirectoryById(String id);

    /**
     * 批量删除我的问卷
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSurveyDirectoryByIds(String[] ids);
}
