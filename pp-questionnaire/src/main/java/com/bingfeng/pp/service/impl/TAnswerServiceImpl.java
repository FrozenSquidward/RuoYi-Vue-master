package com.bingfeng.pp.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.domain.TSurveyAnswer;
import com.bingfeng.pp.domain.TSurveyDirectory;
import com.bingfeng.pp.domain.vo.AnVo;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.ITSurveyAnswerService;
import com.bingfeng.pp.service.ITSurveyDetailService;
import com.bingfeng.pp.service.ITSurveyDirectoryService;
import com.ruoyi.common.utils.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TAnswerMapper;
import com.bingfeng.pp.domain.TAnswer;
import com.bingfeng.pp.service.ITAnswerService;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 答卷保存Service业务层处理
 *
 * @author bingfeng
 * @date 2023-06-05
 */
@Service
public class TAnswerServiceImpl extends ServiceImpl<TAnswerMapper, TAnswer>  implements ITAnswerService {

    @Autowired
    private ITSurveyDetailService tSurveyDetailService;
    @Autowired
    private ITSurveyDirectoryService tSurveyDirectoryService;
    @Autowired
    private ITSurveyAnswerService itSurveyAnswerService;
    @Autowired
    private ITAnswerService tAnswerService;

    @Override
    public void save(HttpServletRequest request, String surveyId) {
        // todo 一些必要的校验
        TSurveyDirectory byId = tSurveyDirectoryService.getById(surveyId);

        JSONObject answerJSON = buildSaveSurveyMap(request);
        // 保存
        TSurveyAnswer surveyAnswer = new TSurveyAnswer();
        surveyAnswer.setSurveyId(surveyId);
        surveyAnswer.setBgAnDate(new Date());
        surveyAnswer.setIpAddr(IpUtils.getIpAddr());
        itSurveyAnswerService.save(surveyAnswer);
        // 保存答卷
        TAnswer answer = new TAnswer();
        answer.setAnswer(answerJSON.toJSONString());
        answer.setBelongId(surveyId);
        answer.setBelongAnswerId(surveyAnswer.getId());
        tAnswerService.save(answer);
    }

    //处理json sql
    /*SELECT
        b.an,
        COUNT( b.an ) AS c
    FROM
        ( SELECT JSON_VALUE ( quAn, '$."quAn"' ) AS an FROM
            ( SELECT JSON_VALUE ( answer, '$."1665565328511225858"' ) AS quAn FROM t_answer ) a
            ) AS b
    GROUP BY
        b.an*/


    public JSONObject buildSaveSurveyMap(HttpServletRequest request) {
        JSONObject quMaps = new JSONObject();
        JSONObject anJSON = new JSONObject();
        // 是非题
        Map<String, Object> yesnoMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.YESNO + "_");
        quMaps.put("yesnoMaps", yesnoMaps);
        for (String key : yesnoMaps.keySet()) {
            AnVo vo = new AnVo();
            vo.setQuAn(yesnoMaps.get(key));
            anJSON.put(key, vo);
        }

        // 单选题
        Map<String, Object> radioMaps = WebUtils.getParametersStartingWith(request, "qu_"+QuType.RADIO + "_");
        for (String key:radioMaps.keySet()) {
            String quItemId = radioMaps.get(key).toString();
            String otherText = request.getParameter("text_qu_" + QuType.RADIO + "_" + key + "_" + quItemId);
            AnVo vo = new AnVo();
            vo.setQuAn(quItemId);
            vo.setOtherText(otherText);
            anJSON.put(key, vo);
        }

        // 多选题
        Map<String, Object> checkboxMaps = WebUtils.getParametersStartingWith(request, "qu_"+QuType.CHECKBOX + "_");
        for (String key : checkboxMaps.keySet()) {
            String dfillValue = checkboxMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(request, dfillValue);
            Set<String> quItemIds = new HashSet<>();
            Set<String> otherTexts = new HashSet<>();
            for (String key2 : map.keySet()) {
                String quItemId = map.get(key2).toString();
                String otherText = request.getParameter("text_" + dfillValue + quItemId);
                quItemIds.add(quItemId);
                otherTexts.add(otherText);
            }
            AnVo vo = new AnVo();
            vo.setQuAn(String.join(",", quItemIds));
            vo.setOtherText(String.join(",", otherTexts));
            anJSON.put(key, vo);
        }

        // 填空题
        Map<String, Object> fillblankMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.FILLBLANK + "_");
        for (String key : fillblankMaps.keySet()) {
            AnVo vo = new AnVo();
            vo.setQuAn(fillblankMaps.get(key));
            anJSON.put(key, vo);
        }
        // 多项填空题
        Map<String, Object> dfillblankMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.MULTIFILLBLANK + "_");
        for (String key : dfillblankMaps.keySet()) {
            Map<String, Object> map = WebUtils.getParametersStartingWith(request, dfillblankMaps.get(key).toString());
            List<JSONObject> list = new ArrayList<>();
            for (String itemId : map.keySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("itemId", itemId);
                jsonObject.put("quAn", map.get(itemId));
                list.add(jsonObject);
            }
            AnVo vo = new AnVo();
            vo.setQuAn(list.toString());
            anJSON.put(key, vo);
        }

        // 多行填空题
        Map<String, Object> answerMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.ANSWER + "_");

        // 复合单选
        Map<String, Object> compRadioMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.COMPRADIO + "_");
        for (String key : compRadioMaps.keySet()) {
            String quItemId = compRadioMaps.get(key).toString();
            String otherText = request.getParameter("text_qu_" + QuType.COMPRADIO + "_" + key + "_" + quItemId);
            AnVo vo = new AnVo();
            vo.setQuAn(quItemId);
            vo.setOtherText(otherText);
            anJSON.put(key, vo);
        }

        // 复合多选
        Map<String, Object> compCheckboxMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.COMPCHECKBOX + "_");
        for (String key : compCheckboxMaps.keySet()) {
            Map<String, Object> map = WebUtils.getParametersStartingWith(request, "tag_" + compCheckboxMaps.get(key).toString());
            Set<String> quItemIds = new HashSet<>();
            Set<String> otherTexts = new HashSet<>();
            for (String key2 : map.keySet()) {
                String quItemId = map.get(key2).toString();
                String otherText = request.getParameter("text_" + compCheckboxMaps.get(key).toString() + quItemId);
                quItemIds.add(quItemId);
                otherTexts.add(otherText);
            }
            AnVo vo = new AnVo();
            vo.setQuAn(String.join(",", quItemIds));
            vo.setOtherText(String.join(",", otherTexts));
            anJSON.put(key, vo);
        }

        //枚举
        Map<String, Object> enumMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.ENUMQU + "_");

        //排序
        Map<String, Object> quOrderMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.ORDERQU + "_");
        for (String key : quOrderMaps.keySet()) {
            String tag = quOrderMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(request, tag);
            AnVo vo = new AnVo();
            vo.setQuAn(JSONObject.from(map));
            anJSON.put(key, vo);
        }

        // 评分题
        Map<String, Object> scoreMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.SCORE + "_");
        for (String key : scoreMaps.keySet()) {
            String tag = scoreMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(request, tag);
            AnVo vo = new AnVo();
            vo.setQuAn(JSONObject.from(map));
            anJSON.put(key, vo);
        }
        return anJSON;
    }
}
