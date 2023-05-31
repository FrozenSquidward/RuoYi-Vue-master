package com.bingfeng.pp.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.bingfeng.pp.domain.TSurveyDetail;
import com.bingfeng.pp.service.ITSurveyDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 具体的一次调查Controller
 *
 * @author bingfeng
 * @date 2023-05-26
 */
@RestController
@RequestMapping("/pp/detail")
public class TSurveyDetailController extends BaseController {
    @Autowired
    private ITSurveyDetailService tSurveyDetailService;

}
