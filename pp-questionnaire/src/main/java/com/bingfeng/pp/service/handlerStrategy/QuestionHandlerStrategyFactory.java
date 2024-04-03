package com.bingfeng.pp.service.handlerStrategy;

import com.alibaba.fastjson2.JSONObject;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.domain.vo.OptionsVo;
import com.bingfeng.pp.goushi.QuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

/**
 * 更新选项策略
 * */
@Component
public class QuestionHandlerStrategyFactory {

    @Autowired
    private List<QuestionHandlerStrategy> questionHandlerStrategies;

    /**
     * 保存选项策略
     * */
    public JSONObject getStrategySave(HttpServletRequest request, String quId, QuType quType, JSONObject jsonObject) throws UnsupportedEncodingException {
        Optional<QuestionHandlerStrategy> first = questionHandlerStrategies.stream().filter(l -> l.getQuType().equals(quType)).findFirst();
        // todo 类似填空题没有选项表，这里选项表大同小异，后续可合并处理
        if (first.isPresent()){
            return first.get().handlerSave(request, quId, jsonObject);
        }else {
            return jsonObject;
        }
    }

    /**
     * 删除问题
     * */
    public void getStrategyDelete(TQuestion entity) {
        Optional<QuestionHandlerStrategy> first = questionHandlerStrategies.stream().filter(l -> l.getQuType().equals(entity.getQuType())).findFirst();
        // todo 类似填空题没有选项表，这里选项表大同小异，后续可合并处理
        first.ifPresent(questionHandlerStrategy -> questionHandlerStrategy.handlerDelete(entity.getId()));
    }

    /**
     * 删除选项
     * */
    public void getStrategyDeleteOptions(OptionsVo optionsDTO) {
        Optional<QuestionHandlerStrategy> first = questionHandlerStrategies.stream().filter(l -> l.getQuType().equals(optionsDTO.getQuType())).findFirst();
        first.ifPresent(questionHandlerStrategy -> questionHandlerStrategy.handlerDeleteOptions(optionsDTO.getId()));
    }
}
