package com.bingfeng.pp.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题目逻辑设置对象 t_question_logic
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TQuestionLogic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 回答选择题的选项ID（0任意选项） */
    private String cgQuItemId;

    /** 回答选择的题ID */
    private String ckQuId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /** 评分题(ge大于,le小于) */
    private String geLe;

    /** 逻辑类型(1:跳转,2:显示) */
    private String logicType;

    /** 得分 */
    private Integer scoreNum;

    /** 要跳转的题(end1提前结束-计入结果,end2提前结束-不计结果) */
    private String skQuId;

    /** 是否显示(1显示,0不显示) */
    private Integer visibility;

    @TableField(exist = false)
    private String title;
}
