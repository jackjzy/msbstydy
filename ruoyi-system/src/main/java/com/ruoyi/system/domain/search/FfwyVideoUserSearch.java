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

@Document(indexName = "videouser", type = "info", shards = 3, replicas = 2)
@Data
public class FfwyVideoUserSearch {

    @Id
    private Long userId;//id
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String userName;//用户名字

}
