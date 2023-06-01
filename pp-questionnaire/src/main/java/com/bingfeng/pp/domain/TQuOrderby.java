package com.bingfeng.pp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 排序题-行选项对象 t_qu_orderby
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TQuOrderby extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 选项内容 */
    private String optionName;

    /** 标识 */
    private String optionTitle;

    /** 排序号 */
    private Integer orderById;

    /** 所属题 */
    private String quId;

    /** 是否显示(0不显示) */
    private Integer visibility;

}
