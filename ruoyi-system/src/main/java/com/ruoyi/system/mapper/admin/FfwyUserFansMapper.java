package com.ruoyi.system.mapper.admin;

import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.admin.FfwyUserFans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 粉丝Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-12
 */
public interface FfwyUserFansMapper {
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
     * 删除粉丝
     *
     * @param userId 粉丝ID
     * @return 结果
     */
    public int deleteFfwyUserFansById(Long userId);

    /**
     * 批量删除粉丝
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyUserFansByIds(Long[] userIds);

    FfwyUserFans selectFfwyUserFansByIdCount(@Param("userId") Long vuserId);
}
