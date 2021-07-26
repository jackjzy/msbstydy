package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductByUserVo {
    private Long userId;
    private Long shopId;
    private Long productId;
    private Long orderId;
    private String orderNumber;
    /** 下单状态 */
    private Integer orderStatus;
    private Integer timeDay;

    /** 套餐id */
    private Long combomealId;

    /** 评价图片 */
    private  String appraiseFile;


    private Date commrnyBegintTime;

    private Date commrnyEndtTime;
}
