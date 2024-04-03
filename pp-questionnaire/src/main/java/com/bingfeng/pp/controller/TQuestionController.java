package com.bingfeng.pp.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson2.JSONObject;
import com.bingfeng.pp.domain.vo.OptionsVo;
import com.bingfeng.pp.goushi.ConfigManager;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.service.ITQuestionService;

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
     * 问卷问题查询
     */
    @GetMapping("/surveyAll")
    public R<?> surveyAll(String surveyId, String sid) {
        return R.ok(tQuestionService.surveyAll(surveyId));
    }

    /**
     * 问卷问题保存
     */
    @PostMapping("/save")
    public JSONObject save(HttpServletRequest request, TQuestion question) throws UnsupportedEncodingException {
        return tQuestionService.saveQuestion(request, question);
    }

    /**
     * 问卷问题发布
     */
    @PostMapping("/releaset")
    public R<?> releaset(String surveyId) {
        tQuestionService.releaset(surveyId);
        return R.ok();
    }

    /**
     * 问卷问题删除
     */
    @DeleteMapping("/delete")
    public Boolean delete(String quId) {
        return tQuestionService.deleteQuestion(quId);
    }

    /**
     * 问卷问题删除选项
     */
    @DeleteMapping("/deleteOptions")
    public Boolean deleteOptions(OptionsVo dto) {
        return tQuestionService.deleteQuestionOptions(dto);
    }

    /**
     * ???
     * */
    @GetMapping("/config")
    public R<?> config(String surveyId, String sid) {
        ConfigManager instance = ConfigManager.getInstance("dwfile/dwsurvey/" , "" , "/api/dwsurvey/anon/ueditor/config" , "1");
        return R.ok(instance);
    }

}
