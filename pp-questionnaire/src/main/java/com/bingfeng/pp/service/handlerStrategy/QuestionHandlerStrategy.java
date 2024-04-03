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
    JSONObject handlerSave(HttpServletRequest request, String quId, JSONObject jsonObject) throws UnsupportedEncodingException;

    /**
     * 删除所有选项
     * */
    void handlerDelete(String quId);

    /**
     * 删除指定选项
     * */
    void handlerDeleteOptions(String id);
}
