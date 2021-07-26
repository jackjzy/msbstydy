package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyFeedbackPhoto;
import org.apache.ibatis.annotations.Param;

/**
 * 反馈图片Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
public interface FfwyFeedbackPhotoMapper 
{
    /**
     * 查询反馈图片
     * 
     * @param photoId 反馈图片ID
     * @return 反馈图片
     */
    public FfwyFeedbackPhoto selectFfwyFeedbackPhotoById(Long photoId);

    /**
     * 查询反馈图片列表
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 反馈图片集合
     */
    public List<FfwyFeedbackPhoto> selectFfwyFeedbackPhotoList(FfwyFeedbackPhoto ffwyFeedbackPhoto);

    /**
     * 新增反馈图片
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 结果
     */
    public int insertFfwyFeedbackPhoto(@Param("list")List<FfwyFeedbackPhoto>  ffwyFeedbackPhoto);

    /**
     * 修改反馈图片
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 结果
     */
    public int updateFfwyFeedbackPhoto(FfwyFeedbackPhoto ffwyFeedbackPhoto);

    /**
     * 删除反馈图片
     * 
     * @param photoId 反馈图片ID
     * @return 结果
     */
    public int deleteFfwyFeedbackPhotoById(Long photoId);

    /**
     * 批量删除反馈图片
     * 
     * @param photoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyFeedbackPhotoByIds(Long[] photoIds);
}
