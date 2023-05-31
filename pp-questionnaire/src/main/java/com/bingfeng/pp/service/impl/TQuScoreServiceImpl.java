package com.bingfeng.pp.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TQuScoreMapper;
import com.bingfeng.pp.domain.TQuScore;
import com.bingfeng.pp.service.ITQuScoreService;

/**
 * 评分题-行选项Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TQuScoreServiceImpl extends ServiceImpl<TQuScoreMapper, TQuScore>  implements ITQuScoreService {

}
