package com.bingfeng.pp.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷详情
 *
 * @author bingfeng
 * @date 2023-05-25
 */
@Data
public class TSurveyDirectory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 问卷名称 */
    private String surveyName;

    /** 所属模块（1问卷模块） */
    private Integer surveyModel;

    /** 问卷状态（0:设计中,1:收集中,2:结束） */
    private Integer surveyState;

    /** 创建者ID */
    private long userId;
    @TableField(exist = false)
    private String userName = "bf";

    /** 用于短链接的ID */
    private String sid;

    /** 可以回答的最少选项数目 */
    private Integer anItemLeastNum;

    /** 回答次数 */
    private Integer answerNum;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /** 类型（1:目录，2:问卷） */
    private Integer dirType;

    /** 引用次数 */
    private Integer excerptNum;

    /** 静态HTML保存路径 */
    private String htmlPath;

    /**  */
    private String jsonPath;

    /** 是否共享问卷(0不共享 1共享) */
    private Integer isShare;

    /**  */
    private String parentId;

    /** 所对应的问卷详细信息表Id,当dirType=2 */
    private String surveyDetailId;

    /** 题目数 */
    private Integer surveyQuNum;

    /** 问卷标识(默认:0,待审核:1:审核通过,2:审核未通过) */
    private Integer surveyTag;

    /** 是否公开结果(0:不,1:公开) */
    private Integer viewAnswer;

    /** 是否显示(1显示,0不显示) */
    private Integer visibility;

    @TableField(exist = false)
    private List<TQuestion> questions;
    @TableField(exist = false)
    private TSurveyDetail surveyDetail;
}
