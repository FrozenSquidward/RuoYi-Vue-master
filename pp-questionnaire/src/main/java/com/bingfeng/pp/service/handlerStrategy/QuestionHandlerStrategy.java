package com.bingfeng.pp.service.handlerStrategy;

import com.alibaba.fastjson2.JSONObject;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.goushi.QuType;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface QuestionHandlerStrategy {

    QuType getQuType();

    /**
     * 更新选项
     * */
    JSONObject handlerSave(HttpServletRequest request, TQuestion entity) throws UnsupportedEncodingException;

    /**
     * 删除选项
     * */
    void handlerDelete(String quId);
}
