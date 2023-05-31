package com.bingfeng.pp.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 具体的一次调查对象 t_survey_detail
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
    @Excel(name = "可以回答的最少选项数目")
    private Long anItemLeastNum;

    /** 可以回答的最多选项数目 */
    @Excel(name = "可以回答的最多选项数目")
    private Long anItemMostNum;

    /** 所对应的surveyDirectory的ID */
    @Excel(name = "所对应的surveyDirectory的ID")
    private String dirId;

    /** 问卷有效性限制(1:不限制,2:使用Cookie技术,3:使用来源IP检测,4:每台电脑或手机只能答一次) */
    @Excel(name = "问卷有效性限制(1:不限制,2:使用Cookie技术,3:使用来源IP检测,4:每台电脑或手机只能答一次)")
    private Long effective;

    /** 每个IP只能答一次(1:是,0:否) */
    @Excel(name = "每个IP只能答一次(1:是,0:否)")
    private Long effectiveIp;

    /** 有效性间隔时间 */
    @Excel(name = "有效性间隔时间")
    private Long effectiveTime;

    /** 收到的份数 */
    @Excel(name = "收到的份数")
    private Long endNum;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 结束方式(1手动结束,2依据结束时间,3依据收到的份数) */
    @Excel(name = "结束方式(1手动结束,2依据结束时间,3依据收到的份数)")
    private Long endType;

    /** 只有邮件邀请唯一链接的受访者可回答(1:启用,0:不启用) */
    @Excel(name = "只有邮件邀请唯一链接的受访者可回答(1:启用,0:不启用)")
    private Long mailOnly;

    /** 防刷新(1:启用,0:不启用) */
    @Excel(name = "防刷新(1:启用,0:不启用)")
    private Long refresh;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long refreshNum;

    /** 调查规则(1:公开,2:私有,3:令牌) */
    @Excel(name = "调查规则(1:公开,2:私有,3:令牌)")
    private Long rule;

    /** 3表示启用访问密码 */
    @Excel(name = "3表示启用访问密码")
    private String ruleCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long showAnswerDa;

    /** 显示分享 */
    @Excel(name = "显示分享")
    private Long showShareSurvey;

    /** 问卷说明 */
    @Excel(name = "问卷说明")
    private String surveyNote;

    /** 问卷下面有多少题目数 */
    @Excel(name = "问卷下面有多少题目数")
    private Long surveyQuNum;

    /** 是否依据收到的份数结束 */
    @Excel(name = "是否依据收到的份数结束")
    private Long ynEndNum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long ynEndTime;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setAnItemLeastNum(Long anItemLeastNum)
    {
        this.anItemLeastNum = anItemLeastNum;
    }

    public Long getAnItemLeastNum()
    {
        return anItemLeastNum;
    }
    public void setAnItemMostNum(Long anItemMostNum)
    {
        this.anItemMostNum = anItemMostNum;
    }

    public Long getAnItemMostNum()
    {
        return anItemMostNum;
    }
    public void setDirId(String dirId)
    {
        this.dirId = dirId;
    }

    public String getDirId()
    {
        return dirId;
    }
    public void setEffective(Long effective)
    {
        this.effective = effective;
    }

    public Long getEffective()
    {
        return effective;
    }
    public void setEffectiveIp(Long effectiveIp)
    {
        this.effectiveIp = effectiveIp;
    }

    public Long getEffectiveIp()
    {
        return effectiveIp;
    }
    public void setEffectiveTime(Long effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    public Long getEffectiveTime()
    {
        return effectiveTime;
    }
    public void setEndNum(Long endNum)
    {
        this.endNum = endNum;
    }

    public Long getEndNum()
    {
        return endNum;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setEndType(Long endType)
    {
        this.endType = endType;
    }

    public Long getEndType()
    {
        return endType;
    }
    public void setMailOnly(Long mailOnly)
    {
        this.mailOnly = mailOnly;
    }

    public Long getMailOnly()
    {
        return mailOnly;
    }
    public void setRefresh(Long refresh)
    {
        this.refresh = refresh;
    }

    public Long getRefresh()
    {
        return refresh;
    }
    public void setRefreshNum(Long refreshNum)
    {
        this.refreshNum = refreshNum;
    }

    public Long getRefreshNum()
    {
        return refreshNum;
    }
    public void setRule(Long rule)
    {
        this.rule = rule;
    }

    public Long getRule()
    {
        return rule;
    }
    public void setRuleCode(String ruleCode)
    {
        this.ruleCode = ruleCode;
    }

    public String getRuleCode()
    {
        return ruleCode;
    }
    public void setShowAnswerDa(Long showAnswerDa)
    {
        this.showAnswerDa = showAnswerDa;
    }

    public Long getShowAnswerDa()
    {
        return showAnswerDa;
    }
    public void setShowShareSurvey(Long showShareSurvey)
    {
        this.showShareSurvey = showShareSurvey;
    }

    public Long getShowShareSurvey()
    {
        return showShareSurvey;
    }
    public void setSurveyNote(String surveyNote)
    {
        this.surveyNote = surveyNote;
    }

    public String getSurveyNote()
    {
        return surveyNote;
    }
    public void setSurveyQuNum(Long surveyQuNum)
    {
        this.surveyQuNum = surveyQuNum;
    }

    public Long getSurveyQuNum()
    {
        return surveyQuNum;
    }
    public void setYnEndNum(Long ynEndNum)
    {
        this.ynEndNum = ynEndNum;
    }

    public Long getYnEndNum()
    {
        return ynEndNum;
    }
    public void setYnEndTime(Long ynEndTime)
    {
        this.ynEndTime = ynEndTime;
    }

    public Long getYnEndTime()
    {
        return ynEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("anItemLeastNum", getAnItemLeastNum())
            .append("anItemMostNum", getAnItemMostNum())
            .append("dirId", getDirId())
            .append("effective", getEffective())
            .append("effectiveIp", getEffectiveIp())
            .append("effectiveTime", getEffectiveTime())
            .append("endNum", getEndNum())
            .append("endTime", getEndTime())
            .append("endType", getEndType())
            .append("mailOnly", getMailOnly())
            .append("refresh", getRefresh())
            .append("refreshNum", getRefreshNum())
            .append("rule", getRule())
            .append("ruleCode", getRuleCode())
            .append("showAnswerDa", getShowAnswerDa())
            .append("showShareSurvey", getShowShareSurvey())
            .append("surveyNote", getSurveyNote())
            .append("surveyQuNum", getSurveyQuNum())
            .append("ynEndNum", getYnEndNum())
            .append("ynEndTime", getYnEndTime())
            .toString();
    }
}
