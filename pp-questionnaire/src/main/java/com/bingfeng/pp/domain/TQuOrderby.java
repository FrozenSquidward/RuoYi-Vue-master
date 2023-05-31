package com.bingfeng.pp.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评分题-行选项对象 t_qu_orderby
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TQuOrderby extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 选项内容 */
    @Excel(name = "选项内容")
    private String optionName;

    /** 标识 */
    @Excel(name = "标识")
    private String optionTitle;

    /** 排序号 */
    @Excel(name = "排序号")
    private Long orderById;

    /** 所属题 */
    @Excel(name = "所属题")
    private String quId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
