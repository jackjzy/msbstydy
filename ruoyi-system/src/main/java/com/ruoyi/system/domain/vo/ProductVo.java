package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductVo {
    /** 订单id */
    private Long orderId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    private String patmenttype;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 头像 */
    @Excel(name = "头像")
    private String photo;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 销量 */
    @Excel(name = "销量")
    private Long sales;

    /** 库存 */
    @Excel(name = "库存")
    private Long stock;

    /** 库存单位 */
    @Excel(name = "库存单位")
    private String stockUnit;

    /** 所属商品id */
    @Excel(name = "所属商品id")
    private Long productId;

    /** 商品图片路径 */
    @Excel(name = "商品图片路径")
    private String productPhotopath;

    /** 状态名称 */
    @Excel(name = "状态名称")
    private String statusName;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;
}
