package com.bingfeng.pp.service.handlerStrategy;

import com.alibaba.fastjson2.JSONObject;
import com.bingfeng.pp.domain.TQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Component
public class QuestionHandlerStrategyFactory {

    @Autowired
    private List<QuestionHandlerStrategy> questionHandlerStrategies;

    /**
     * 保存选项策略
     * */
    public JSONObject getStrategySave(HttpServletRequest request, TQuestion entity) throws UnsupportedEncodingException {
        Optional<QuestionHandlerStrategy> first = questionHandlerStrategies.stream().filter(l -> l.getQuType().equals(entity.getQuType())).findFirst();
        assert first.isPresent();
        return first.get().handlerSave(request, entity);
    }

    /**
     * 删除选项策略
     * */
    public void getStrategyDelete(TQuestion entity) {
        Optional<QuestionHandlerStrategy> first = questionHandlerStrategies.stream().filter(l -> l.getQuType().equals(entity.getQuType())).findFirst();
        assert first.isPresent();
        first.get().handlerDelete(entity.getId());
    }
}
