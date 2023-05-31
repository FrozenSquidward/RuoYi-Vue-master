package com.bingfeng.pp.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 答卷-多行填空题保存对象 t_an_dfillblank
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TAnDfillblank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 答案 */
    @Excel(name = "答案")
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

    /** 对应的填空项ID */
    @Excel(name = "对应的填空项ID")
    private String quItemId;

    /** 是否显示(0不显示) */
    @Excel(name = "是否显示(0不显示)")
    private Long visibility;

}
