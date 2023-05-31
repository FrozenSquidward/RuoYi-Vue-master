package com.bingfeng.pp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答案-是非题结果保存对象 t_an_yesno
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TAnYesno extends BaseEntity
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

    /** 问题ID */
    @Excel(name = "问题ID")
    private String quId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

    /** 1:是,0:非 */
    @Excel(name = "1:是,0:非")
    private String yesnoAnswer;

}
