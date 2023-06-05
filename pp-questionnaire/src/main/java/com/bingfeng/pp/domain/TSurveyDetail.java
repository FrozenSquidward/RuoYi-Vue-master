package com.bingfeng.pp.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷校验规则
 *
 * @author bingfeng
 * @date 2023-05-26
 */
@Data
public class TSurveyDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 可以回答的最少选项数目 */
    private Integer anItemLeastNum;

    /** 可以回答的最多选项数目 */
    private Integer anItemMostNum;

    /** 所对应的surveyDirectory的ID */
    private String dirId;

    /** 问卷有效性限制(1:不限制,2:使用Cookie技术,3:使用来源IP检测,4:每台电脑或手机只能答一次) */
    private Integer effective;

    /** 每个IP只能答一次(1:是,0:否) */
    private Integer effectiveIp;

    /** 有效性间隔时间 */
    private Integer effectiveTime;

    /** 收到的份数 */
    private Integer endNum;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 结束方式(1手动结束,2依据结束时间,3依据收到的份数) */
    private Integer endType;

    /** 只有邮件邀请唯一链接的受访者可回答(1:启用,0:不启用) */
    private Integer mailOnly;

    /** 防刷新(1:启用,0:不启用) */
    private Integer refresh;

    /** $column.columnComment */
    private Integer refreshNum;

    /** 调查规则(1:公开,2:私有,3:令牌) */
    private Integer rule;

    /** 3表示启用访问密码 */
    private String ruleCode;

    /** $column.columnComment */
    private Integer showAnswerDa;

    /** 显示分享 */
    private Integer showShareSurvey;

    /** 问卷说明 */
    private String surveyNote;

    /** 问卷下面有多少题目数 */
    private Integer surveyQuNum;

    /** 是否依据收到的份数结束 */
    private Integer ynEndNum;

    /** ynEndTime */
    private Integer ynEndTime;
}
