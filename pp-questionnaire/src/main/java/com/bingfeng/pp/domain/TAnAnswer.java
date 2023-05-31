package com.bingfeng.pp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答卷、问答题保存对象 t_an_answer
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TAnAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 结果 */
    @Excel(name = "结果")
    private String answer;

    /** 对应的答卷信息表ID */
    @Excel(name = "对应的答卷信息表ID")
    private String belongAnswerId;

    /** 所属问卷ID */
    @Excel(name = "所属问卷ID")
    private String belongId;

    /** 所属题目ID */
    @Excel(name = "所属题目ID")
    private String quId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
