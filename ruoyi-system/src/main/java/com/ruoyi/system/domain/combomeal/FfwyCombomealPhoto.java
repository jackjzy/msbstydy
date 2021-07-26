package com.ruoyi.system.domain.combomeal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 【请填写功能名称】对象 ffwy_combomeal_photo
 * 
 * @author ruoyi
 * @date 2021-05-07
 */
public class FfwyCombomealPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐图片id */
    private Long photoId;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /** 套餐路径 */
    @Excel(name = "套餐路径")
    private String photoPath;

    /** 图片标题 */
    @Excel(name = "图片标题")
    private String titles;

    /** 图片上传时间戳 */
    @Excel(name = "图片上传时间戳")
    private Long timeing;

    /** 图片状态 */
    @Excel(name = "图片状态")
    private String status;

    @Excel(name = "新建时间")
    private Date createTime;
    /**
     * 图片类型  0：轮播图  1：详情图
     */
    private String photoType;

    private Integer sort;

    private Date beginTime;
    private Date endTime;

    public FfwyCombomealPhoto() {
    }

    public FfwyCombomealPhoto(Long combomealId,String photoPath, String titles, Long timeing,Date createTime,
                              String status) {
        this.combomealId = combomealId;
        this.photoPath = photoPath;
        this.titles = titles;
        this.timeing = timeing;
        this.createTime = createTime;
        this.status = status;
    }

    public FfwyCombomealPhoto(Long combomealId) {
        this.combomealId = combomealId;
    }

    public FfwyCombomealPhoto(Long combomealId,  String photoType) {
        this.combomealId = combomealId;
        this.photoType = photoType;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public FfwyCombomealPhoto(String photoType, Integer sort) {
        this.photoType = photoType;
        this.sort = sort;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setPhotoId(Long photoId) 
    {
        this.photoId = photoId;
    }

    public Long getPhotoId() 
    {
        return photoId;
    }
    public void setCombomealId(Long combomealId) 
    {
        this.combomealId = combomealId;
    }

    public Long getCombomealId() 
    {
        return combomealId;
    }
    public void setPhotoPath(String photoPath) 
    {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() 
    {
        return photoPath;
    }
    public void setTitles(String titles)
    {
        this.titles = titles;
    }

    public String getTitles()
    {
        return titles;
    }
    public void setTimeing(Long timeing) 
    {
        this.timeing = timeing;
    }

    public Long getTimeing() 
    {
        return timeing;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("combomealId", getCombomealId())
            .append("photoPath", getPhotoPath())
            .append("titles", getTitles())
            .append("timeing", getTimeing())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("photoType",photoType)
            .toString();
    }
}
