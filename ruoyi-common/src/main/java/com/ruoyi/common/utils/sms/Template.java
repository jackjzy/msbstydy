package com.ruoyi.common.utils.sms;

import lombok.Getter;

/**
 * @ClassName: Template
 * @Author: Zx
 * @Date: 2021/7/22 4:35 下午
 * @Version: v1.0
 *  短信模板
 */
@Getter
public enum Template {

    CODE("1041632","发送验证码"),
    ;

    private String code;
    private String msg;

    Template(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
