package com.bingfeng.pp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答卷保存对象 t_answer
 *
 * @author bingfeng
 * @date 2023-06-05
 */
@Data
public class TAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 所属题目ID */
    @Excel(name = "所属题目ID")
    private String quId;

    /** 结果JSON */
    @Excel(name = "结果JSON")
    private String answer;

    /** 对应的答卷信息表ID */
    @Excel(name = "对应的答卷信息表ID")
    private String belongAnswerId;

    /** 所属问卷ID */
    @Excel(name = "所属问卷ID")
    private String belongId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
