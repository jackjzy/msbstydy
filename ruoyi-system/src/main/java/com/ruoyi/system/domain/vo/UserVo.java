package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    //id
    private Long userId;
    //用户名
    private String userName;
    //头像
    private String photo;
    //简介
    private String intro;
    //是否互相关注
    private Boolean isAttention;
}
