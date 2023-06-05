package com.bingfeng.pp.domain.vo;

import lombok.Data;

@Data
public class AnVo {

    public String quId;
    public Integer quType;

    /**
     * 回答（多选为json）
     * */
    public Object quAn;

    /**
     * otherText??
     * */
    public String otherText;
}
