package com.ruoyi.system.domain.to;

import com.ruoyi.system.domain.combomeal.CombomealCategory;
import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.combomeal.FfwyCombomealCategory;
import lombok.Data;

import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/16
 **/
@Data
public class SignInCategoryCombomeal {
    /**
     * 套餐
     */
    private List<FfwyCombomeal> combomeals;
    /**
     *分类
     */
    private List<CombomealCategory> combomealCategories;
}
