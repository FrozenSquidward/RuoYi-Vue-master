package com.bingfeng.pp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 问题发布关联对象 t_question_releaset
 *
 * @author bingfeng
 * @date 2023-06-02
 */
@Data
public class TQuestionReleaset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 所属问卷或题库 */
    private String belongId;

    /** 问题JSON */
    private String quJson;
}
