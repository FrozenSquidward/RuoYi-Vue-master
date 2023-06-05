package com.bingfeng.pp.controller;

import com.alibaba.fastjson2.JSONObject;
import com.bingfeng.pp.domain.TQuestionReleaset;
import com.bingfeng.pp.domain.TSurveyDirectory;
import com.bingfeng.pp.service.ITAnswerService;
import com.bingfeng.pp.service.ITQuestionReleasetService;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 免校验访问问卷
 * */
@RestController
@RequestMapping("/survey/api")
public class ApiController {

    @Autowired
    private ITQuestionReleasetService tQuestionReleasetService;
    @Autowired
    private ITAnswerService tAnswerService;

    /**
     * 获取问卷详情
     */
    @GetMapping("/info")
    public R<?> survey(String sid, String surveyId) {
        if (sid != null){
            TQuestionReleaset byId = tQuestionReleasetService.getById(sid);
            return R.ok(JSONObject.parseObject(byId.getQuJson(), TSurveyDirectory.class));
        }else {
            return R.ok("123?????");
        }
    }

    /**
     * 提交问卷
     */
    @PostMapping("/submit")
    public R<?> submit(HttpServletRequest request, String surveyId) {
        tAnswerService.save(request, surveyId);
        return R.ok();
    }


}
