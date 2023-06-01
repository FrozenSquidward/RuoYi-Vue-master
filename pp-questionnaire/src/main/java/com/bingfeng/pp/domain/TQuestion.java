package com.bingfeng.pp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bingfeng.pp.goushi.CheckType;
import com.bingfeng.pp.goushi.QuType;
import com.bingfeng.pp.goushi.YesnoOption;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷问题对象 t_question
 *
 * @author bingfeng
 * @date 2023-05-26
 */
@Data
public class TQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 填空的input */
    private Integer answerInputRow;

    /** 填空的input */
    private Integer answerInputWidth;

    /** 所属问卷或题库 */
    private String belongId;

    /** 按列显示时,列数 */
    private Integer cellCount;

    /** 说明的验证方式 */
    private CheckType checkType;

    /** 联系人属性(1:关联到联系人属性,0:不关联到联系人属性) */
    private Integer contactsAttr;

    /** 关联的联系人字段 */
    private String contactsField;

    /** 如果是复制的题，则有复制于那一题 */
    private String copyFromId;

    /** 创建时间 */
    private Date createDate;

    /** 控制性属性（1水平显示,2垂直显示） */
    private Integer hv;

    /** 是否必答(0:非必答,1:必答) */
    private Integer isRequired;

    /** 关键字 */
    private String keywords;

    /** 排序ID */
    private Integer orderById;

    /** 枚举题,枚举项数目,评分题起始分值 */
    private Integer paramInt01;

    /** 评分题,最大分值 */
    private Integer paramInt02;

    /** 所属大题(只有小题才有此属性,即quTag=3的题) */
    private String parentQuId;

    /** 题目名称 */
    private String quName;

    /** 题目说明 */
    private String quNote;

    /** 是否是大小题（1:默认题,2:大题,3:大题下面的小题） */
    private Integer quTag;

    /** 题干 */
    private String quTitle;

    /** 题目类型 */
    private QuType quType;

    /** 选项随机排列（1:随机排列,0:不随机排列） */
    private Integer randOrder;

    /** 标记（1：题库中的题，2：问卷中的题） */
    private Integer tag;

    /** 是否显示（0:不显示,1:显示） */
    private Integer visibility;

    /** 是非题的选项 */
    private Integer yesnoOption;


    /**
     * 附加属性，不作映射
     */
    @TableField(exist = false)
    private List<TQuestion> questions;
    //题选项
    @TableField(exist = false)
    private List<TQuRadio> quRadios;
    @TableField(exist = false)
    private List<TQuCheckbox> quCheckboxs;
    @TableField(exist = false)
    private List<TQuMultiFillblank> quMultiFillblanks;
    @TableField(exist = false)
    private List<TQuScore> quScores;
    @TableField(exist = false)
    private List<TQuOrderby> quOrderbys;

    @TableField(exist = false)
    private String rowContent="";
    @TableField(exist = false)
    private String colContent="";
    @TableField(exist = false)
    private String optionContent="";
    //删除的ID
    @TableField(exist = false)
    private String[] removeOptionUuIds=null;
    //题答卷
    @TableField(exist = false)
    private TAnAnswer anAnswer;
    @TableField(exist = false)
    private List<TAnCheckbox> anCheckboxs;
    @TableField(exist = false)
    private List<TAnDfillblank> anDFillblanks;
    @TableField(exist = false)
    private List<TAnEnumqu> anEnumqus;
    @TableField(exist = false)
    private TAnFillblank anFillblank;
    @TableField(exist = false)
    private TAnRadio anRadio;
    @TableField(exist = false)
    private TAnYesno anYesno;
    @TableField(exist = false)
    private List<TAnScore> anScores;
    @TableField(exist = false)
    private Integer anCount=0;
    @TableField(exist = false)
    private List<TAnOrder> anOrders;
    //逻辑设置
    @TableField(exist = false)
    private List<TQuestionLogic> questionLogics;
}
