package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MultiVo {

    /** 资讯id */
    private Long informationId;

    /** 咨询名称 */
    @Excel(name = "咨询名称")
    private String informationName;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeCount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long informationCommentCount;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    private Integer categoryId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String typeName;

    /** 标签id */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    private Integer informationTypeId;
}
