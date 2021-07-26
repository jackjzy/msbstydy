package com.ruoyi.system.domain.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "video", type = "info", shards = 3, replicas = 2)
@Data
public class FfwyVideoSearch {

    @Id
    private Long videoId;//id

    @Field(type = FieldType.Keyword)
    private String videoPath;//视频路径

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String videoTitle;//视频文件

    @Field(type = FieldType.Integer, index = false)
    private Integer videoLove;//点赞数

    @Field(type = FieldType.Integer, index = false)
    private Integer videoTrans;//转发数

    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    @Field(type = FieldType.Date, index = false ,format = DateFormat.basic_date_time)
    private Date createTime;//发货时间

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String videoIntro;//简介


    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String userName;//用户名字

    @Field(type = FieldType.Keyword, index = false)
    private String videoComment;//评价

    @Field(type = FieldType.Date, index = false,format = DateFormat.basic_date_time)
    private Date commentTime;//评价时间

    @Field(type = FieldType.Text)
    private String commentname;//评价名字

    @Field(type = FieldType.Keyword)
    private String photo;//图片

    @Field(type = FieldType.Integer)
    private Integer countcomment;//评论

    @Field(type = FieldType.Long)
    private Long fans;//粉丝数量

    @Field(type = FieldType.Text)
    private String headPhoto;//头像

    @Field(type = FieldType.Long)
    private Long fansId;//用户id

    @Field(type = FieldType.Long)
    private Long userId;//粉丝id

    private String isLove;

    private Integer countId;

}
