package com.ruoyi.system.domain.search;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
@Data
public class SearchVideo {
    private String type;

    private  String userId;

    private String videoIntro;

    private  Integer pageNum;
    private Integer pageSize;
}
