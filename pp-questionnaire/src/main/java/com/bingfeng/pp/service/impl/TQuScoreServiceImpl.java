package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuScoreMapper;
import com.bingfeng.pp.domain.TQuScore;
import com.bingfeng.pp.service.ITQuScoreService;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 评分题-行选项Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TQuScoreServiceImpl extends ServiceImpl<TQuScoreMapper, TQuScore>  implements ITQuScoreService, QuestionHandlerStrategy {

    @Override
    public QuType getQuType() {
        return QuType.SCORE;
    }

    @Override
    public JSONObject handlerSave(HttpServletRequest request, String quId, JSONObject jsonObject) throws UnsupportedEncodingException {
        jsonObject.put("quItems", saveOption(request, quId));
        return jsonObject;
    }

    private List<TQuScore> saveOption(HttpServletRequest request, String quId) throws UnsupportedEncodingException {
        Map<String, Object> optionNameMap=WebUtils.getParametersStartingWith(request, "optionValue_");
        List<TQuScore> quScores=new ArrayList<>();
        for (String key : optionNameMap.keySet()) {
            String optionId=request.getParameter("optionId_"+key);
            Object optionName=optionNameMap.get(key);
            String optionNameValue=(optionName!=null)?optionName.toString():"";
            TQuScore quScore=new TQuScore();
            if("".equals(optionId)){
                optionId=null;
            }
            quScore.setId(optionId);
			// quRadio.setOptionTitle(key);
            optionNameValue=URLDecoder.decode(optionNameValue,"utf-8");
            quScore.setOptionName(optionNameValue);
            quScore.setOrderById(Integer.parseInt(key));
            quScore.setQuId(quId);
            saveOrUpdate(quScore);
            quScores.add(quScore);
        }
        return quScores;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuScore>().eq(TQuScore::getQuId, quId));
    }
}
