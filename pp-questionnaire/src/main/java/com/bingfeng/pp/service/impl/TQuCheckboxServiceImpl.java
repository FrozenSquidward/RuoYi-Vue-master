package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.goushi.CheckType;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuCheckboxMapper;
import com.bingfeng.pp.domain.TQuCheckbox;
import com.bingfeng.pp.service.ITQuCheckboxService;
import org.springframework.web.util.WebUtils;

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
    public JSONObject handlerSave(HttpServletRequest request, String quId, JSONObject jsonObject) throws UnsupportedEncodingException {
        jsonObject.put("quItems", saveOption(request,quId));
        return jsonObject;
    }

    private List<TQuCheckbox> saveOption(HttpServletRequest request, String quId) throws UnsupportedEncodingException {
        Map<String, Object> optionNameMap = WebUtils.getParametersStartingWith(request, "optionValue_");
        List<TQuCheckbox> quCheckboxs = new ArrayList<>();
        for (String key : optionNameMap.keySet()) {
            String optionId = request.getParameter("optionId_" + key);
            String isNote = request.getParameter("isNote_" + key);
            String checkType = request.getParameter("checkType_" + key);
            String isRequiredFill = request.getParameter("isRequiredFill_" + key);
            Object optionName = optionNameMap.get(key);
            String optionNameValue = (optionName!=null) ? optionName.toString() : "";
            TQuCheckbox quCheckbox = new TQuCheckbox();
            if("".equals(optionId)) {
                optionId = null;
            }
            quCheckbox.setId(optionId);
			// quRadio.setOptionTitle(key);
            optionNameValue = URLDecoder.decode(optionNameValue,"utf-8");
            quCheckbox.setOptionName(optionNameValue);
            quCheckbox.setOrderById(Integer.parseInt(key));
            isNote = (isNote == null || "".equals(isNote)) ? "0" : isNote;
            checkType = (checkType==null || "".equals(checkType)) ? "NO" : checkType;
            isRequiredFill = (isRequiredFill == null || "".equals(isRequiredFill)) ? "0" : isRequiredFill;
            quCheckbox.setIsNote(Integer.parseInt(isNote));
            quCheckbox.setCheckType(CheckType.valueOf(checkType));
            quCheckbox.setIsRequiredFill(Integer.parseInt(isRequiredFill));
            quCheckbox.setQuId(quId);
            saveOrUpdate(quCheckbox);
            quCheckboxs.add(quCheckbox);
        }
        return quCheckboxs;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuCheckbox>().eq(TQuCheckbox::getQuId, quId));
    }
}
