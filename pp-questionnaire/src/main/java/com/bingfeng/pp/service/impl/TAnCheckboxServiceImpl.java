package com.bingfeng.pp.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingfeng.pp.domain.TQuCheckbox;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.service.handlerStrategy.QuestionHandlerStrategy;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TAnCheckboxMapper;
import com.bingfeng.pp.domain.TAnCheckbox;
import com.bingfeng.pp.service.ITAnCheckboxService;

import javax.servlet.http.HttpServletRequest;

/**
 * 答卷、多选题保存Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TAnCheckboxServiceImpl extends ServiceImpl<TAnCheckboxMapper, TAnCheckbox>  implements ITAnCheckboxService{


}
