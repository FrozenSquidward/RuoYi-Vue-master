package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.domain.TQuRadio;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuMultiFillblankMapper;
import com.bingfeng.pp.domain.TQuMultiFillblank;
import com.bingfeng.pp.service.ITQuMultiFillblankService;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 填空题Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TQuMultiFillblankServiceImpl extends ServiceImpl<TQuMultiFillblankMapper, TQuMultiFillblank>  implements ITQuMultiFillblankService, QuestionHandlerStrategy {

    @Override
    public QuType getQuType() {
        return QuType.MULTIFILLBLANK;
    }

    @Override
    public JSONObject handlerSave(HttpServletRequest request, String quId, JSONObject jsonObject) throws UnsupportedEncodingException {
        jsonObject.put("quItems", saveOption(request, quId));
        return jsonObject;
    }
    private List<TQuMultiFillblank> saveOption(HttpServletRequest request, String quId) throws UnsupportedEncodingException {
        Map<String, Object> optionNameMap = WebUtils.getParametersStartingWith(request, "optionValue_");
        List<TQuMultiFillblank> quMFillblanks = new ArrayList<>();
        for (String key : optionNameMap.keySet()) {
            String optionId = request.getParameter("optionId_"+key);
            Object optionName = optionNameMap.get(key);
            String optionNameValue = (optionName!=null)?optionName.toString():"";
            TQuMultiFillblank quMultiFillblank = new TQuMultiFillblank();
            if("".equals(optionId)){
                optionId = null;
            }
            quMultiFillblank.setId(optionId);
			//quRadio.setOptionTitle(key);
            optionNameValue = URLDecoder.decode(optionNameValue,"utf-8");
            quMultiFillblank.setOptionName(optionNameValue);
            quMultiFillblank.setOrderById(Integer.parseInt(key));
            quMultiFillblank.setQuId(quId);
            saveOrUpdate(quMultiFillblank);
            quMFillblanks.add(quMultiFillblank);
        }
        return quMFillblanks;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuMultiFillblank>().eq(TQuMultiFillblank::getQuId, quId));
    }

    @Override
    public void handlerDeleteOptions(String id) {
        removeById(id);
    }
}
