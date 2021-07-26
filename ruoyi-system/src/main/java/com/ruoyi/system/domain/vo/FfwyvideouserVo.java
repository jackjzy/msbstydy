package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyvideouserVo {

    private Integer videoId;

    private Long userId;

    private String userName;

    private String photo;

    private String userType;

    private String videoIntro;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String videoPath;

    private Integer videoLove;

    private Integer videoTrans;

    /** 视频封面 */
    private String videoCover;

    /** 评论内容 */
    private String videoComment;

    /** 视频标题 */
    private String videoTitle;

    private Long productCategoryId;


    //评论数
    private Integer comments;

    public Integer getComments() {
        return comments == null?0 : comments;
    }

    //点赞数
    private Integer loveSum;

    public Integer getLoveSum() {
        return loveSum == null? 0 : loveSum;
    }

    //点赞状态
    private String isLove;

    //粉丝状态
    private String isFans;

    //是否收藏
    private Integer isStore;

    private Boolean videostuta;

    private String videoStatus;


    private Integer prouductId;


}
