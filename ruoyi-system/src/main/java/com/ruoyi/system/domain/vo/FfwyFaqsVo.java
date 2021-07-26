package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.FfwyFaqs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyFaqsVo {

    /** 常见问题 */
    private Long faqsId;

    //常见问题回复
    private String faqReply;

    /** 常见问题描述 */
    @Excel(name = "常见问题描述")
    private String faqsMsg;

    private Date updateTime;

    private Date createTime;

    private String replyMsg;
}
