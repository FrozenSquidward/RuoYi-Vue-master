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
    public JSONObject handlerSave(HttpServletRequest request, TQuestion entity) throws UnsupportedEncodingException {
        TQuestion tQuestion = ajaxBuildSaveOption(request, entity);
        List<TQuRadio> quRadios = tQuestion.getQuRadios();
        for (TQuRadio quRadio : quRadios) {
            if (quRadio.getId() != null && !"".equals(quRadio.getId())){
                updateById(quRadio);
            }else {
                save(quRadio);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", tQuestion.getId());
        jsonObject.put("orderById", entity.getOrderById());
        jsonObject.put("quItems", entity.getQuRadios());
        jsonObject.put("quLogics", entity.getQuestionLogics());
        return jsonObject;
    }

    private TQuestion ajaxBuildSaveOption(HttpServletRequest request, TQuestion entity) throws UnsupportedEncodingException {
        String belongId = request.getParameter(QuestionConstants.Question_FIELD_BELONGID);
        String quTitle = request.getParameter(QuestionConstants.Question_FIELD_QUTITLE);
        Integer orderById = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_ORDERBYID));
        Integer tag = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_TAG));
        Integer isRequired = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_ISREQUIRED));
        Integer hv = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_HV));
        Integer randOrder = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_RANDORDRER));
        Integer cellCount = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_CELLCOUNT));
        Integer contactsAttr = Integer.valueOf(request.getParameter(QuestionConstants.Question_FIELD_CONTACTSATTR));
        String contactsField = request.getParameter(QuestionConstants.Question_FIELD_CONTACTSFIELD);

        entity.setBelongId(belongId);
        if(quTitle!=null){
            quTitle= URLDecoder.decode(quTitle, "utf-8");
            entity.setQuTitle(quTitle);
        }
        entity.setOrderById(orderById);
        entity.setTag(tag);
        entity.setIsRequired(isRequired);
        entity.setHv(hv);
        entity.setRandOrder(randOrder);
        entity.setCellCount(cellCount);
        entity.setContactsAttr(contactsAttr);
        entity.setContactsField(contactsField);

        Map<String, Object> optionNameMap= WebUtils.getParametersStartingWith(request, "optionValue_");
        List<TQuRadio> quRadios=new ArrayList<TQuRadio>();
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

            quRadio.setQuId(entity.getId());
            quRadios.add(quRadio);
        }
        entity.setQuRadios(quRadios);

        //逻辑选项设置
        Map<String, Object> quLogicIdMap = WebUtils.getParametersStartingWith(request, "quLogicId_");
        List<TQuestionLogic> quLogics = new ArrayList<TQuestionLogic>();
        for (String key : quLogicIdMap.keySet()) {
            String cgQuItemId = request.getParameter("cgQuItemId_" + key);
            String skQuId = request.getParameter("skQuId_" + key);
            String visibility = request.getParameter("visibility_" + key);
            String logicType = request.getParameter("logicType_" + key);
            Object quLogicId = quLogicIdMap.get(key);
            String quLogicIdValue = (quLogicId!=null)?quLogicId.toString():null;
            TQuestionLogic quLogic = new TQuestionLogic();
            quLogic.setId(quLogicIdValue);
            quLogic.setCgQuItemId(cgQuItemId);
            quLogic.setSkQuId(skQuId);
            quLogic.setVisibility(Integer.parseInt(visibility));
            quLogic.setTitle(key);
            quLogic.setLogicType(logicType);
            quLogics.add(quLogic);
        }
        entity.setQuestionLogics(quLogics);
        return entity;
    }

    @Override
    public void handlerDelete(String quId) {
        remove(new LambdaQueryWrapper<TQuRadio>().eq(TQuRadio::getQuId, quId));
    }

    public static void main(String[] args) {
        String jdbc = AES.encrypt("17379", "e14d1f985e52ef99");
        System.out.println(jdbc);
    }
}
