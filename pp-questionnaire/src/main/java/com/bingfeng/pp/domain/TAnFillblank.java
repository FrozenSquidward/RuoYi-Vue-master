package com.bingfeng.pp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答卷-多选题保存对象 t_an_fillblank
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TAnFillblank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
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

    /** 题目ID */
    @Excel(name = "题目ID")
    private String quId;

    /** 结果 */
    @Excel(name = "结果")
    private Long visibility;

}
