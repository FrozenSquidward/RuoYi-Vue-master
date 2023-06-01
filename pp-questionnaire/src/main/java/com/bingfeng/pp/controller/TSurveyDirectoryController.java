package com.bingfeng.pp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.bingfeng.pp.domain.TSurveyDirectory;
import com.bingfeng.pp.service.ITSurveyDirectoryService;

/**
 * 我的问卷Controller
 *
 * @author bingfeng
 * @date 2023-05-25
 */
@RestController
@RequestMapping("/pp/directory")
public class TSurveyDirectoryController extends BaseController
{
    @Autowired
    private ITSurveyDirectoryService tSurveyDirectoryService;

    /**
     * 查询我的问卷列表
     */
    @PreAuthorize("@ss.hasPermi('pp:directory:list')")
    @GetMapping("/list")
    public Page<?> list(PageDomain page, TSurveyDirectory tSurveyDirectory)
    {
        return tSurveyDirectoryService.selectTSurveyDirectoryList(page, tSurveyDirectory);
    }

    /**
     * 获取我的问卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('pp:directory:query')")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(String id)
    {
        return success(tSurveyDirectoryService.getInfo(id));
    }

    /**
     * 新增我的问卷
     */
    @PreAuthorize("@ss.hasPermi('pp:directory:add')")
    @Log(title = "新增我的问卷", businessType = BusinessType.INSERT)
    @PostMapping
    public R<?> add(@RequestBody TSurveyDirectory tSurveyDirectory)
    {
        return R.ok(tSurveyDirectoryService.insertTSurveyDirectory(tSurveyDirectory));
    }

    /**
     * 删除问卷
     */
    @PreAuthorize("@ss.hasPermi('pp:directory:remove')")
    @Log(title = "删除问卷", businessType = BusinessType.INSERT)
    @DeleteMapping("/{id}")
    public R<?> del(@PathVariable("id") String id)
    {
        tSurveyDirectoryService.del(id);
        return R.ok();
    }
}
