package com.bingfeng.pp.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.domain.TSurveyDetail;
import com.bingfeng.pp.service.ITSurveyDetailService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TSurveyDirectoryMapper;
import com.bingfeng.pp.domain.TSurveyDirectory;
import com.bingfeng.pp.service.ITSurveyDirectoryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 我的问卷Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-25
 */
@Service
public class TSurveyDirectoryServiceImpl extends ServiceImpl<TSurveyDirectoryMapper,TSurveyDirectory> implements ITSurveyDirectoryService
{
    @Autowired
    private ITSurveyDetailService tSurveyDetailService;

    /**
     * 查询我的问卷
     *
     * @param id 我的问卷主键
     * @return 我的问卷
     */
    @Override
    public TSurveyDirectory selectTSurveyDirectoryById(String id)
    {
        return baseMapper.selectTSurveyDirectoryById(id);
    }

    /**
     * 查询我的问卷列表
     *
     * @param tSurveyDirectory 我的问卷
     * @return 我的问卷
     */
    @Override
    public Page<TSurveyDirectory> selectTSurveyDirectoryList(PageDomain page, TSurveyDirectory tSurveyDirectory)
    {
        return baseMapper.selectTSurveyDirectoryList(new Page<>(page.getPageNum(), page.getPageSize()), tSurveyDirectory);
    }

    /**
     * 新增我的问卷
     *
     * @param tSurveyDirectory 我的问卷
     * @return 结果
     */
    @Override
    @Transactional
    public TSurveyDetail insertTSurveyDirectory(TSurveyDirectory tSurveyDirectory)
    {
        Long userId = SecurityUtils.getUserId();
        tSurveyDirectory.setDirType(2);
        tSurveyDirectory.setUserId(userId);
        save(tSurveyDirectory);

        TSurveyDetail surveyDetail=new TSurveyDetail();
        surveyDetail.setDirId(tSurveyDirectory.getId());
        surveyDetail.setSurveyNote("非常感谢您的参与！如有涉及个人信息，我们将严格保密。");
        tSurveyDetailService.save(surveyDetail);
        return surveyDetail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(String id) {
        removeById(id);
        tSurveyDetailService.remove(new LambdaQueryWrapper<TSurveyDetail>().eq(TSurveyDetail::getDirId, id));
    }
}
