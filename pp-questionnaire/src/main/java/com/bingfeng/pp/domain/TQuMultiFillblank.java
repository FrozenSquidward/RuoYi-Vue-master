package com.bingfeng.pp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
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
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 说明的验证方式 */
    private Integer checkType;

    /** 选项问题 */
    private String optionName;

    /** 选项标题 */
    private String optionTitle;

    /** 排序ID */
    private Integer orderById;

    /** 所属题 */
    private String quId;

    /** 是否显示(0不显示) */
    private Integer visibility;

}
