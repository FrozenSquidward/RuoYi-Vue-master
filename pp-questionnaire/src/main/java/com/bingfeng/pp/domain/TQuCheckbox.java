package com.bingfeng.pp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bingfeng.pp.goushi.CheckType;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 多选题-选项对象 t_qu_checkbox
 *
 * @author bingfeng
 * @date 2023-05-30
 */
@Data
public class TQuCheckbox extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 说明的验证方式 */
    private CheckType checkType;

    /** 是否带说明(0:否,1:是) */
    private Integer isNote;

    /** 说明内容是否必填 */
    private Integer isRequiredFill;

    /** 选项内容 */
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
