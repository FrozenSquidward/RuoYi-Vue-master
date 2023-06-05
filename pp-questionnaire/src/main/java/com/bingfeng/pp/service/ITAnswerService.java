package com.bingfeng.pp.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.page.PageDomain;
import com.bingfeng.pp.domain.TAnswer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 答卷保存Service接口
 *
 * @author bingfeng
 * @date 2023-06-05
 */
public interface ITAnswerService extends IService<TAnswer> {

    public void save(HttpServletRequest request, String surveyId);
}
