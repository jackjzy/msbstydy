package com.ruoyi.system.domain.search;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author 郭晓康
 * @create 2021-05-11 下午5:22
 */
//@Document(indexName = "product", type = "info", shards = 3, replicas = 2)
@Document(indexName = "product", shards = 3, replicas = 2)
@Data
public class FfwyProductSearch
{
    @Id
    private Long productId;//商品
    /**
     * 封面图
     */
    @Field(type = FieldType.Keyword,index = false)
    private String photo;
    /** 商品名称 */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String productName;
    /** 现价 */
    @Field(type = FieldType.Double)
    private Double currentPrice;
    /** 店铺id */
    @Field(type = FieldType.Keyword)
    private String shopName;

    /**
     * 评价数量
     */
    @Field(type = FieldType.Long)
    private Integer commentNumber;

}


