package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.constants.QuestionConstants;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.domain.TQuestionLogic;
import com.bingfeng.pp.goushi.CheckType;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuRadioMapper;
import com.bingfeng.pp.domain.TQuRadio;
import com.bingfeng.pp.service.ITQuRadioService;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 单选题选项Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TQuRadioServiceImpl extends ServiceImpl<TQuRadioMapper, TQuRadio>  implements ITQuRadioService, QuestionHandlerStrategy {

    @Override
    public QuType getQuType() {
        return QuType.RADIO;
    }

    @Override
    public JSONObject handlerSave(HttpServletRequest request, String quId, JSONObject jsonObject) throws UnsupportedEncodingException {
        jsonObject.put("quItems", saveOption(request, quId));
        return jsonObject;
    }

    private List<TQuRadio> saveOption(HttpServletRequest request, String quId) throws UnsupportedEncodingException {
        Map<String, Object> optionNameMap= WebUtils.getParametersStartingWith(request, "optionValue_");
        List<TQuRadio> quRadios= new ArrayList<>();
        for (String key : optionNameMap.keySet()) {
            String optionId=request.getParameter("optionId_"+key);
            String isNote=request.getParameter("isNote_"+key);
            String checkType=request.getParameter("checkType_"+key);
            String isRequiredFill=request.getParameter("isRequiredFill_"+key);
            Object optionName=optionNameMap.get(key);
            String optionNameValue=(optionName!=null)?optionName.toString():"";
            TQuRadio quRadio=new TQuRadio();
            if("".equals(optionId)){
                optionId=null;
            }
            quRadio.setId(optionId);
			//quRadio.setOptionTitle(key);
            optionNameValue=URLDecoder.decode(optionNameValue,"utf-8");
            quRadio.setOptionName(optionNameValue);
            quRadio.setOrderById(Integer.parseInt(key));
            isNote = (isNote == null || "".equals(isNote)) ? "0" : isNote;
            checkType = (checkType == null || "".equals(checkType)) ? "NO" : checkType;
            isRequiredFill = (isRequiredFill == null || "".equals(isRequiredFill)) ? "0" : isRequiredFill;
            quRadio.setIsNote(Integer.parseInt(isNote));
            quRadio.setCheckType(CheckType.valueOf(checkType));
            //quRadio.setCheckType(0);
            quRadio.setIsRequiredFill(Integer.parseInt(isRequiredFill));
            quRadio.setQuId(quId);
            saveOrUpdate(quRadio);
            quRadios.add(quRadio);
        }
        return quRadios;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuRadio>().eq(TQuRadio::getQuId, quId));
    }

    @Override
    public void handlerDeleteOptions(String id) {
        removeById(id);
    }
}
