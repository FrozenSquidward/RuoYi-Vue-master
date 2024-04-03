package com.bingfeng.pp.domain.vo;

import com.bingfeng.pp.goushi.QuType;
import lombok.Data;

@Data
public class OptionsVo {
    /** 题目类型 */
    private QuType quType;

    /**
     * 选项ID
     * */
    public String id;
}
