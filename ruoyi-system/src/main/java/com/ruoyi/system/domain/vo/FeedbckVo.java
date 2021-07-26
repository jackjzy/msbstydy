package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbckVo {

    /** 反馈id */
    private Long feedbackId;

    /** 反馈信息 */
    @Excel(name = "反馈信息")
    private String feedbackMessage;

    /** 反馈状态：0代表未回复、1代表已回复 */
    @Excel(name = "反馈状态：0代表未回复、1代表已回复")
    private String feedbackStatus;

    /** id */
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    private Data CreateTime;
}
