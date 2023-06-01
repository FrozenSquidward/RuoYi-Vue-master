package com.bingfeng.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.bingfeng.pp.service.ITQuOrderbyService;

/**
 * 排序题-行选项Controller
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@RestController
@RequestMapping("/pp/orderby")
public class TQuOrderbyController extends BaseController {
    @Autowired
    private ITQuOrderbyService tQuOrderbyService;

}
