package com.ruoyi.system.domain.aftersale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 【请填写功能名称】对象 ffwy_after_photo
 * 
 * @author ruoyi
 * @date 2021-05-28
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyAfterPhoto
{
    private static final long serialVersionUID = 1L;

    /** 售后图片id */
    private Long afterPhotoId;

    private Long afterSaleId;

    /** 售后图片一 */
    @Excel(name = "售后图片一")
    private String afterPhoto;

    private Date createTime;

    private Date updateTime;


}
