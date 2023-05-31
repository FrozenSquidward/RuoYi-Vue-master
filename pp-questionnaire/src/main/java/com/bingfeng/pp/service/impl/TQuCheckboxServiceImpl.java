package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuCheckboxMapper;
import com.bingfeng.pp.domain.TQuCheckbox;
import com.bingfeng.pp.service.ITQuCheckboxService;

import javax.servlet.http.HttpServletRequest;

/**
 * 多选题-选项Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TQuCheckboxServiceImpl extends ServiceImpl<TQuCheckboxMapper, TQuCheckbox>  implements ITQuCheckboxService, QuestionHandlerStrategy {

    @Override
    public QuType getQuType() {
        return QuType.CHECKBOX;
    }

    @Override
    public JSONObject handlerSave(HttpServletRequest request, TQuestion entity) throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuCheckbox>().eq(TQuCheckbox::getQuId, quId));
    }
}
