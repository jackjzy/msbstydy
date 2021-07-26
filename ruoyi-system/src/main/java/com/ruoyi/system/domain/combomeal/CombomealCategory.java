package com.ruoyi.system.domain.combomeal;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/16
 **/
public class CombomealCategory extends BaseEntity {

    /** 套餐分类id */
    private Long categoryId;

    /** 二级分类id */
    @Excel(name = "二级分类id")
    private Long parentId;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String combomaealName;
}
