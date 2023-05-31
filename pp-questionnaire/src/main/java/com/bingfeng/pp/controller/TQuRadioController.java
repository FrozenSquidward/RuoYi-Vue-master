package com.bingfeng.pp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.page.PageDomain;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.bingfeng.pp.domain.TQuRadio;
import com.bingfeng.pp.service.ITQuRadioService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单选题选项Controller
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@RestController
@RequestMapping("/pp/radio")
public class TQuRadioController extends BaseController {
    @Autowired
    private ITQuRadioService tQuRadioService;

    @PostMapping("/save")
    public void save(TQuRadio tQuRadio) throws UnsupportedEncodingException {
        tQuRadioService.save(tQuRadio);
        System.out.println(tQuRadio);
    }
}
