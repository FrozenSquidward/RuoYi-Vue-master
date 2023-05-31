package com.bingfeng.pp.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bingfeng.pp.domain.TQuestion;
import com.bingfeng.pp.goushi.QuType;
import com.ruoyi.common.core.page.PageDomain;
import com.bingfeng.pp.domain.TQuCheckbox;

import javax.servlet.http.HttpServletRequest;

/**
 * 多选题-选项Service接口
 *
 * @author bingfeng
 * @date 2023-05-30
 */
public interface ITQuCheckboxService extends IService<TQuCheckbox> {

}
