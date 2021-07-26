package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/4/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchVo {
    private Long id;
    private String phone;
    private String searchField;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginLoginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endLoginTime;

}
