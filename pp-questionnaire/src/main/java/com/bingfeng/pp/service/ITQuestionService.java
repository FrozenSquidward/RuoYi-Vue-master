package com.bingfeng.pp.service;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bingfeng.pp.domain.TSurveyDirectory;
import com.bingfeng.pp.domain.TQuestion;

import javax.servlet.http.HttpServletRequest;

/**
 * 问卷问题Service接口
 *
 * @author bingfeng
 * @date 2023-05-26
 */
public interface ITQuestionService extends IService<TQuestion> {

    /**
     * 问卷问题列表
     * */
    public TSurveyDirectory surveyAll(String id);

    /**
     * 保存问卷问题
     */
    public JSONObject saveQuestion(HttpServletRequest request, TQuestion question) throws UnsupportedEncodingException;

    /**
     * 删除问卷问题
     * */
    public Boolean deleteQuestion(String quId);

}
