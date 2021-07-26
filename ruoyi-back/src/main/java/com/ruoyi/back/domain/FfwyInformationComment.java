package com.ruoyi.back.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】对象 ffwy_information_comment
 * 
 * @author ruoyi
 * @date 2021-04-19
 */
public class FfwyInformationComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询评论id */
    private Long informationCommentId;



    /** 咨询id */
    @Excel(name = "咨询名称")
    private String informationName;



    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 父级评论 */
    @Excel(name = "父级评论")
    private Long parentId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String informationComment;

    @Excel(name = "子评论")
    private List<FfwyInformationComment> comment;

    private FfwyUser user;
    @Excel(name = "官方回复")
    private String reply;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date replyTime;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInformationCommentId() {
        return informationCommentId;
    }

    public void setInformationCommentId(Long informationCommentId) {
        this.informationCommentId = informationCommentId;
    }

    public String getInformationId() {
        return informationName;
    }

    public void setInformationId(String informationId) {
        this.informationName = informationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getInformationComment() {
        return informationComment;
    }

    public void setInformationComment(String informationComment) {
        this.informationComment = informationComment;
    }

    public List<FfwyInformationComment> getComment() {
        return comment;
    }

    public void setComment(List<FfwyInformationComment> comment) {
        this.comment = comment;
    }

    public FfwyUser getUser() {
        return user;
    }

    public void setUser(FfwyUser user) {
        this.user = user;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FfwyInformationComment{" +
                "informationCommentId=" + informationCommentId +
                ", informationId=" + informationName +
                ", userId=" + userId +
                ", parentId=" + parentId +
                ", informationComment='" + informationComment + '\'' +
                ", comment=" + comment +
                ", user=" + user +
                ", reply='" + reply + '\'' +
                ", replyTime=" + replyTime +
                ", createTime=" + createTime +
                '}';
    }
}
