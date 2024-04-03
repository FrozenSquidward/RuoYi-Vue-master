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
import com.bingfeng.pp.goushi.CheckType;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuOrderbyMapper;
import com.bingfeng.pp.domain.TQuOrderby;
import com.bingfeng.pp.service.ITQuOrderbyService;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 排序题-行选项Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TQuOrderbyServiceImpl extends ServiceImpl<TQuOrderbyMapper, TQuOrderby>  implements ITQuOrderbyService, QuestionHandlerStrategy {

    @Override
    public QuType getQuType() {
        return QuType.ORDERQU;
    }

    @Override
    public JSONObject handlerSave(HttpServletRequest request, String quId, JSONObject jsonObject) throws UnsupportedEncodingException {
        jsonObject.put("quItems", saveOption(request, quId));
        return jsonObject;
    }

    private List<TQuOrderby> saveOption(HttpServletRequest request, String quId) throws UnsupportedEncodingException {
        Map<String, Object> optionNameMap = WebUtils.getParametersStartingWith(request, "optionValue_");
        List<TQuOrderby> quOrderbys = new ArrayList<>();
        for (String key : optionNameMap.keySet()) {
            String optionId = request.getParameter("optionId_" + key);
            Object optionName = optionNameMap.get(key);
            String optionNameValue = (optionName!=null) ? optionName.toString() : "";
            TQuOrderby quOrderby = new TQuOrderby();
            if("".equals(optionId)){
                optionId = null;
            }
            quOrderby.setId(optionId);
//			quRadio.setOptionTitle(key);
            optionNameValue=URLDecoder.decode(optionNameValue,"utf-8");
            quOrderby.setOptionName(optionNameValue);
            quOrderby.setOrderById(Integer.parseInt(key));
            quOrderby.setQuId(quId);
            saveOrUpdate(quOrderby);
            quOrderbys.add(quOrderby);
        }
        return quOrderbys;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuOrderby>().eq(TQuOrderby::getQuId, quId));
    }

    @Override
    public void handlerDeleteOptions(String id) {
        removeById(id);
    }
}
