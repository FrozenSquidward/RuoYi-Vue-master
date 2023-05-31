package com.bingfeng.pp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bingfeng.pp.goushi.ConfigManager;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.service.ITQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问卷问题Controller
 *
 * @author bingfeng
 * @date 2023-05-26
 */
@RestController
@RequestMapping("/pp/question")
public class TQuestionController extends BaseController
{
    @Autowired
    private ITQuestionService tQuestionService;

    /**
     * 查询问卷问题列表
     */
    @PostMapping("/save")
    public JSONObject save(HttpServletRequest request) throws UnsupportedEncodingException {
        return tQuestionService.saveQuestion(request);
    }

    /**
     * 查询问卷问题列表
     */
    @DeleteMapping("/delete")
    public Boolean delete(String quId) {
        return tQuestionService.deleteQuestion(quId);
    }

    /**
     * 问题列表
     */
    //@PreAuthorize("@ss.hasPermi('pp:question:query')")
    @GetMapping("/surveyAll")
    public R<?> surveyAll(String surveyId, String sid) {
        return R.ok(tQuestionService.surveyAll(surveyId));
    }

    @GetMapping("/config")
    public R<?> config(String surveyId, String sid) {
        ConfigManager instance = ConfigManager.getInstance("dwfile/dwsurvey/" , "" , "/api/dwsurvey/anon/ueditor/config" , "1");
        return R.ok(instance);
    }

}