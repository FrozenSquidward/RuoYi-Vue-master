package com.bingfeng.pp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答卷-枚举题答案对象 t_an_enumqu
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TAnEnumqu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 结果 */
    @Excel(name = "结果")
    private String answer;

    /** 对应的答卷信息表 */
    @Excel(name = "对应的答卷信息表")
    private String belongAnswerId;

    /** 所属问卷ID */
    @Excel(name = "所属问卷ID")
    private String belongId;

    /** 第几个枚举项 */
    @Excel(name = "第几个枚举项")
    private Long enumItem;

    /** 题目ID */
    @Excel(name = "题目ID")
    private String quId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
