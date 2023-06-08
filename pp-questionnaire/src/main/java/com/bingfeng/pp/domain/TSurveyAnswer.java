package com.bingfeng.pp.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 回答者-调查回馈信息对象 t_survey_answer
 *
 * @author bingfeng
 * @date 2023-06-05
 */
@Data
public class TSurveyAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 答卷ID */
    @Excel(name = "答卷ID")
    private String surveyId;

    /** 回答者是详细地址 */
    @Excel(name = "回答者是详细地址")
    private String addr;

    /** 回答时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回答时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bgAnDate;

    /** 回答者城市 */
    @Excel(name = "回答者城市")
    private String city;

    /** 回答的题项目数,表示有些题下面会有多重回答 */
    @Excel(name = "回答的题项目数,表示有些题下面会有多重回答")
    private Long completeItemNum;

    /** 回答的题数 */
    @Excel(name = "回答的题数")
    private Long completeNum;

    /** 数据来源(0:网调,1:录入数据,2:移动数据,3:导入数据) */
    @Excel(name = "数据来源(0:网调,1:录入数据,2:移动数据,3:导入数据)")
    private Long dataSource;

    /** 回答时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回答时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endAnDate;

    /** 审核状态(0:未处理,1:通过,2:不通过) */
    @Excel(name = "审核状态(0:未处理,1:通过,2:不通过)")
    private Long handleState;

    /** 回答者IP */
    @Excel(name = "回答者IP")
    private String ipAddr;

    /** 是否完成（1完成） */
    @Excel(name = "是否完成", readConverterExp = "1=完成")
    private Long isComplete;

    /** 是否是有效数据(1有效) */
    @Excel(name = "是否是有效数据(1有效)")
    private Long isEffective;

    /** 回答者MAC */
    @Excel(name = "回答者MAC")
    private String pcMac;

    /** 回答的题数 */
    @Excel(name = "回答的题数")
    private Long quNum;

    /** 用时 */
    @Excel(name = "用时")
    private Long totalTime;

    /** 回答者ID */
    @Excel(name = "回答者ID")
    private String userId;

}
