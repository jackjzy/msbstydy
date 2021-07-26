package com.ruoyi.system.mapper.video;

import java.util.List;
import com.ruoyi.system.domain.FfwyVideoForward;

/**
 * 视频转发Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-12
 */
public interface FfwyVideoForwardMapper 
{
    /**
     * 查询视频转发
     * 
     * @param forwardId 视频转发ID
     * @param id
     * @return 视频转发
     */
    //public FfwyVideoForward selectFfwyVideoForwardById(Long forwardId);
    public FfwyVideoForward selectFfwyVideoForwardIdCount(Long id);

    /**
     * 查询视频转发列表
     * 
     * @param ffwyVideoForward 视频转发
     * @return 视频转发集合
     */
    public List<FfwyVideoForward> selectFfwyVideoForwardList(FfwyVideoForward ffwyVideoForward);

    /**
     * 新增视频转发
     * 
     * @param ffwyVideoForward 视频转发
     * @return 结果
     */
    public int insertFfwyVideoForward(FfwyVideoForward ffwyVideoForward);

    /**
     * 修改视频转发
     * 
     * @param ffwyVideoForward 视频转发
     * @return 结果
     */
    public int updateFfwyVideoForward(FfwyVideoForward ffwyVideoForward);

    /**
     * 删除视频转发
     * 
     * @param forwardId 视频转发ID
     * @return 结果
     */
  //  public int deleteFfwyVideoForwardById(Long forwardId);

    /**
     * 批量删除视频转发
     * 
     * @param forwardIds 需要删除的数据ID
     * @return 结果
     */
  //  public int deleteFfwyVideoForwardByIds(Long[] forwardIds);
}
