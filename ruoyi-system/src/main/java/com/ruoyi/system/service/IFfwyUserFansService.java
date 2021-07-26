package com.ruoyi.system.service;

import com.ruoyi.system.domain.admin.FfwyUserFans;

import java.util.List;

/**
 * 粉丝Service接口
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public interface IFfwyUserFansService 
{
    /**
     * 查询粉丝
     * 
     * @param userId 粉丝ID
     * @return 粉丝
     */
    public FfwyUserFans selectFfwyUserFansById(Long userId);

    /**
     * 查询粉丝列表
     * 
     * @param ffwyUserFans 粉丝
     * @return 粉丝集合
     */
    public List<FfwyUserFans> selectFfwyUserFansList(FfwyUserFans ffwyUserFans);

    /**
     * 新增粉丝
     * 
     * @param ffwyUserFans 粉丝
     * @return 结果
     */
    public int insertFfwyUserFans(FfwyUserFans ffwyUserFans);

    /**
     * 修改粉丝
     * 
     * @param ffwyUserFans 粉丝
     * @return 结果
     */
    public int updateFfwyUserFans(FfwyUserFans ffwyUserFans);

    /**
     * 批量删除粉丝
     * 
     * @param userIds 需要删除的粉丝ID
     * @return 结果
     */
    public int deleteFfwyUserFansByIds(Long[] userIds);

    /**
     * 删除粉丝信息
     * 
     * @param userId 粉丝ID
     * @return 结果
     */
    public int deleteFfwyUserFansById(Long userId);
}
