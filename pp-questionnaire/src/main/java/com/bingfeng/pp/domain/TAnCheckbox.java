package com.bingfeng.pp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答卷、多选题保存对象 t_an_checkbox
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TAnCheckbox extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 对应的答卷信息表 */
    @Excel(name = "对应的答卷信息表")
    private String belongAnswerId;

    /** 所属问卷ID */
    @Excel(name = "所属问卷ID")
    private String belongId;

    /** 复合的说明 */
    @Excel(name = "复合的说明")
    private String otherText;

    /** 题目ID */
    @Excel(name = "题目ID")
    private String quId;

    /** 对应的结果ID */
    @Excel(name = "对应的结果ID")
    private String quItemId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
