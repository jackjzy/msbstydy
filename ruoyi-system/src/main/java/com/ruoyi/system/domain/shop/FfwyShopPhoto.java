package com.ruoyi.system.domain.shop;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 店铺图片对象 ffwy_shop_photo
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Data
public class FfwyShopPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店铺图片id */
    private Long photoId;

    /** 店铺id */
    @Excel(name = "店铺id")
    private Long shopId;
    /** 图片路径 */
    @Excel(name = "图片路径")
    private String phototPath;

    /** 图片路径 */
    @Excel(name = "图片名称")
    private String title;
    /** 图片路径 */
    @Excel(name = "时间戳")
    private Long time;
    /** 图片路径 */
    @Excel(name = "新建时间")
    private Date createTime;
    /** 图片路径 */
    @Excel(name = "图片状态")
    private String status;



    private Date beginTime;
    private Date endTime;
    private MultipartFile[] files;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public FfwyShopPhoto() {
    }

    public FfwyShopPhoto(Long shopId,String title, String phototPath, Long time, Date createTime, String status) {
        this.shopId = shopId;
        this.title=title;
        this.phototPath = phototPath;
        this.time = time;
        this.createTime = createTime;
        this.status = status;
    }



}
