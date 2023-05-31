package com.bingfeng.pp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bingfeng.pp.domain.TSurveyDetail;
import com.ruoyi.common.core.page.PageDomain;
import com.bingfeng.pp.domain.TSurveyDirectory;

/**
 * 我的问卷Service接口
 *
 * @author bingfeng
 * @date 2023-05-25
 */
public interface ITSurveyDirectoryService extends IService<TSurveyDirectory>
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
    public Page<TSurveyDirectory> selectTSurveyDirectoryList(PageDomain page, TSurveyDirectory tSurveyDirectory);

    /**
     * 新增我的问卷
     *
     * @param tSurveyDirectory 我的问卷
     * @return 结果
     */
    public TSurveyDetail insertTSurveyDirectory(TSurveyDirectory tSurveyDirectory);

    /**
     * 删除我的问卷
     * */
    public void del(String id);
}
