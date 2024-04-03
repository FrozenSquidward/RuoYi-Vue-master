package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.constants.QuestionConstants;
import com.bingfeng.pp.domain.*;
import com.bingfeng.pp.domain.vo.OptionsVo;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.*;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuestionMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 问卷问题Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-26
 */
@Service
public class TQuestionServiceImpl extends ServiceImpl<TQuestionMapper, TQuestion> implements ITQuestionService
{

    @Autowired
    private ITSurveyDirectoryService tSurveyDirectoryService;
    @Autowired
    private ITSurveyDetailService tSurveyDetailService;
    @Autowired
    private ITQuRadioService tQuRadioService;
    @Autowired
    private ITQuCheckboxService tQuCheckboxService;
    @Autowired
    private ITQuMultiFillblankService tQuMultiFillblankService;
    @Autowired
    private ITQuScoreService tQuScoreService;
    @Autowired
    private ITQuOrderbyService tQuOrderbyService;
    @Autowired
    private ITQuestionLogicService tQuestionLogicService;
    @Autowired
    private QuestionHandlerStrategyFactory questionHandlerStrategyFactory;
    @Autowired
    private ITQuestionReleasetService tQuestionReleasetService;

    @Override
    public TSurveyDirectory surveyAll(String id) {
        TSurveyDirectory byId = tSurveyDirectoryService.getById(id);
        TSurveyDetail detail = tSurveyDetailService.getOne(new LambdaQueryWrapper<TSurveyDetail>().eq(TSurveyDetail::getDirId, id));
        byId.setSurveyDetail(detail);
        List<TQuestion> list = list(new LambdaQueryWrapper<TQuestion>()
                .eq(TQuestion::getBelongId, id)
                .eq(TQuestion::getTag, 2)
                .ne(TQuestion::getQuTag, 3)
                .orderByAsc(TQuestion::getOrderById));
        for (TQuestion question : list){
            getQuestionOption(question);
        }
        byId.setQuestions(list);
        return byId;
    }

    /**
     * 得到某一题下面的选项，包含大题下面的小题
     * @param question
     */
    public void getQuestionOption(TQuestion question) {
        String quId=question.getId();
        QuType quType = question.getQuType();
        if(Objects.equals(quType, QuType.RADIO) || Objects.equals(quType, QuType.COMPRADIO)){
            List<TQuRadio> list = tQuRadioService.list(new LambdaQueryWrapper<TQuRadio>()
                    .eq(TQuRadio::getQuId, quId)
                    .eq(TQuRadio::getVisibility, 1));
            question.setQuRadios(list);
        }else if(Objects.equals(quType, QuType.CHECKBOX) || Objects.equals(quType, QuType.COMPCHECKBOX)){
            List<TQuCheckbox> list = tQuCheckboxService.list(new LambdaQueryWrapper<TQuCheckbox>()
                    .eq(TQuCheckbox::getQuId, quId)
                    .eq(TQuCheckbox::getVisibility, 1));
            question.setQuCheckboxs(list);
        }else if(Objects.equals(quType, QuType.MULTIFILLBLANK)){
            List<TQuMultiFillblank> list = tQuMultiFillblankService.list(new LambdaQueryWrapper<TQuMultiFillblank>()
                    .eq(TQuMultiFillblank::getQuId, quId)
                    .eq(TQuMultiFillblank::getVisibility, 1));
            question.setQuMultiFillblanks(list);
        }else if(Objects.equals(quType, QuType.BIGQU)){
            //根据大题ID，找出所有小题
            String parentQuId=question.getId();
            List<TQuestion> childQuList = list(new LambdaQueryWrapper<TQuestion>()
                    .eq(TQuestion::getParentQuId, quId)
                    .eq(TQuestion::getVisibility, 1));
            for (TQuestion childQu : childQuList) {
                getQuestionOption(childQu);
            }
            question.setQuestions(childQuList);
            //根据小题的类型，取选项
        }else if(Objects.equals(quType, QuType.SCORE)){
            List<TQuScore> list = tQuScoreService.list(new LambdaQueryWrapper<TQuScore>()
                    .eq(TQuScore::getQuId, quId)
                    .eq(TQuScore::getVisibility, 1));
            question.setQuScores(list);
        }else if(Objects.equals(quType, QuType.ORDERQU)){
            List<TQuOrderby> list = tQuOrderbyService.list(new LambdaQueryWrapper<TQuOrderby>()
                    .eq(TQuOrderby::getQuId, quId)
                    .eq(TQuOrderby::getVisibility, 1));
            question.setQuOrderbys(list);
        }
        List<TQuestionLogic> list = tQuestionLogicService.list(new LambdaQueryWrapper<TQuestionLogic>()
                .eq(TQuestionLogic::getCkQuId, quId)
                .eq(TQuestionLogic::getVisibility, 1));
        question.setQuestionLogics(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject saveQuestion(HttpServletRequest request, TQuestion question) throws UnsupportedEncodingException {
        String quId = request.getParameter(QuestionConstants.Question_FIELD_QUID);
        if(question.getQuTitle()!=null){
            question.setQuTitle(URLDecoder.decode(question.getQuTitle(),"utf-8"));
        }
        if("".equals(quId)){
            question.setCreateDate(new Date());
            save(question);
            // 重新排序
            update(new LambdaUpdateWrapper<TQuestion>()
                    .eq(TQuestion::getBelongId, question.getBelongId())
                    .ge(TQuestion::getOrderById, question.getOrderById())
                    .setSql("order_by_id = order_by_id + 1"));
        }else {
            // 排序策略 1.排序未调整的,不动;
            TQuestion byId = getById(quId);
            // 2.排序向前的;
            if (byId.getOrderById() > question.getOrderById()){
                update(new LambdaUpdateWrapper<TQuestion>()
                        .eq(TQuestion::getBelongId, byId.getBelongId())
                        .ge(TQuestion::getOrderById, question.getOrderById())
                        .lt(TQuestion::getOrderById, byId.getOrderById())
                        .setSql("order_by_id = order_by_id + 1"));
            // 3.排序向后的;
            }else if (byId.getOrderById() < question.getOrderById()){
                update(new LambdaUpdateWrapper<TQuestion>()
                        .eq(TQuestion::getBelongId, byId.getBelongId())
                        .gt(TQuestion::getOrderById, byId.getOrderById())
                        .le(TQuestion::getOrderById, question.getOrderById())
                        .setSql("order_by_id = order_by_id - 1"));
            }
            question.setId(quId);
            updateById(question);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", question.getId());
        jsonObject.put("orderById", question.getOrderById());
        jsonObject.put("quLogics", saveTQuestionLogic(request, question));
        return questionHandlerStrategyFactory.getStrategySave(request, question.getId(), question.getQuType(), jsonObject);
    }

    /**
     * 逻辑选项设置
     */
    private List<TQuestionLogic> saveTQuestionLogic(HttpServletRequest request, TQuestion question){
        Map<String, Object> quLogicIdMap = WebUtils.getParametersStartingWith(request, "quLogicId_");
        List<TQuestionLogic> quLogics = new ArrayList<>();
        for (String key : quLogicIdMap.keySet()) {
            String cgQuItemId = request.getParameter("cgQuItemId_" + key);
            String skQuId = request.getParameter("skQuId_" + key);
            String visibility = request.getParameter("visibility_" + key);
            String logicType = request.getParameter("logicType_" + key);
            Object quLogicId = quLogicIdMap.get(key);
            String quLogicIdValue = (quLogicId != null) ? quLogicId.toString() : null;
            TQuestionLogic quLogic = new TQuestionLogic();
            quLogic.setId(quLogicIdValue);
            quLogic.setCgQuItemId(cgQuItemId);
            quLogic.setSkQuId(skQuId);
            quLogic.setVisibility(Integer.parseInt(visibility));
            quLogic.setTitle(key);
            quLogic.setLogicType(logicType);
            quLogic.setCkQuId(question.getId());
            tQuestionLogicService.saveOrUpdate(quLogic);
            quLogics.add(quLogic);
        }
        return quLogics;
    }

    @Override
    public void releaset(String surveyId) {
        // 删除旧模型
        tQuestionReleasetService.remove(new LambdaQueryWrapper<TQuestionReleaset>()
                .eq(TQuestionReleaset::getBelongId, surveyId));
        TQuestionReleaset releaset = new TQuestionReleaset();
        releaset.setBelongId(surveyId);
        //releaset.setCreateTime(new Date());
        tQuestionReleasetService.save(releaset);
        // 更新模型关联
        TSurveyDirectory directory = new TSurveyDirectory();
        directory.setSid(releaset.getId());
        directory.setSurveyState(1);
        directory.setSurveyTag(1);
        tSurveyDirectoryService.update(directory, new LambdaQueryWrapper<TSurveyDirectory>()
                .eq(TSurveyDirectory::getId, surveyId));
        // 保存新模型
        TSurveyDirectory toJson = surveyAll(surveyId);
        releaset.setQuJson(JSONObject.toJSONString(toJson));
        tQuestionReleasetService.updateById(releaset);
        // todo 可修改为支持回档
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteQuestion(String quId) {
        TQuestion byId = getById(quId);
        questionHandlerStrategyFactory.getStrategyDelete(byId);
        // 重新排序
        update(new LambdaUpdateWrapper<TQuestion>()
                .eq(TQuestion::getBelongId, byId.getBelongId())
                .gt(TQuestion::getOrderById, byId.getOrderById())
                .setSql("order_by_id = order_by_id - 1"));
        removeById(quId);
        return true;
    }

    @Override
    public Boolean deleteQuestionOptions(OptionsVo vo) {
        questionHandlerStrategyFactory.getStrategyDeleteOptions(vo);
        return true;
    }
}
