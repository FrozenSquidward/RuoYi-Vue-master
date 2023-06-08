package com.bingfeng.pp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 结果JSON */
    private String answer;

    /** 对应的答卷信息表ID */
    private String belongAnswerId;

    /** 所属问卷ID */
    private String belongId;

    /** 是否显示(0不显示) */
    private Integer visibility;

}
