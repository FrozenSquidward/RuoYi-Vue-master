package com.bingfeng.pp.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 填空题对象 t_qu_multi_fillblank
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TQuMultiFillblank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 说明的验证方式 */
    @Excel(name = "说明的验证方式")
    private Long checkType;

    /** 选项问题 */
    @Excel(name = "选项问题")
    private String optionName;

    /** 选项标题 */
    @Excel(name = "选项标题")
    private String optionTitle;

    /** 排序ID */
    @Excel(name = "排序ID")
    private Long orderById;

    /** 所属题 */
    @Excel(name = "所属题")
    private String quId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
