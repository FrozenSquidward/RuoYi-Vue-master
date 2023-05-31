package com.bingfeng.pp.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.bingfeng.pp.mapper.TAnScoreMapper;
import com.bingfeng.pp.domain.TAnScore;
import com.bingfeng.pp.service.ITAnScoreService;

/**
 * 评分题Service业务层处理
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Service
public class TAnScoreServiceImpl extends ServiceImpl<TAnScoreMapper, TAnScore>  implements ITAnScoreService {

}
