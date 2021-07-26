package com.ruoyi.system.mapper.admin;

import com.ruoyi.system.domain.admin.FfwyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商城用户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-14
 */
public interface FfwyUserMapper 
{
    /**
     * 查询商城用户
     * 
     * @param userId 商城用户ID
     * @return 商城用户
     */
    public FfwyUser selectFfwyUserById(Long userId);


    /**
     * 查询商城用户
     *
     * @param token 商城用户token
     * @return 商城用户
     */
    public FfwyUser selectFfwyUserByToken(String token);
    /**
     * 查询粉丝用户
     *
     * @param userId 商城用户ID
     * @return 商城用户
     */

    public List<FfwyUser> selectFfwyUserByFans(Long userId);

    /**
     * 查询是否关注
     *
     * @param userId 商城用户ID
     * @return 商城用户
     */

    public FfwyUser selectFfwyUserAndFans(@Param("userId") Long userId,
                                               @Param("fansId") Long fansId);
    /**
     * 查询粉丝用户数量
     *
     * @param userId 商城用户ID
     * @return 商城用户
     */

    public int selectFfwyUserByFansCount(Long userId);
    /**
     * 查询关注用户
     *
     * @param userId 商城用户ID
     * @return 商城用户
     */
    public List<FfwyUser> selectFfwyUserByAttention(Long userId);

    /**
     * 查询粉丝用户
     *
     * @param userId 商城用户ID
     * @return 商城用户
     */

    public int selectFfwyUserByAttentionCount(Long userId);

    /**
     * 根据手机号查询商城用户
     *
     * @param phonenumber 手机号
     * @return 商城用户
     */
    public FfwyUser selectFfwyUserByPhonenumber(@Param("phonenumber") String phonenumber, @Param("password") String password);

    /**
     * 查询商城用户列表
     * 
     * @param ffwyUser 商城用户
     * @return 商城用户集合
     */
    public List<FfwyUser> selectFfwyUserList(FfwyUser ffwyUser);

    /**
     * 新增商城用户
     * 
     * @param ffwyUser 商城用户
     * @return 结果
     */
    public int insertFfwyUser(FfwyUser ffwyUser);

    /**
     * 新增商粉丝
     *
     * @param userId 用户id
     * @param fansId 粉丝id
     * @return 结果
     */
    public int insertFfwyUserFnas(@Param("userId")Long userId,@Param("fansId")Long fansId);


    /**
     * 修改商城用户
     * 
     * @param ffwyUser 商城用户
     * @return 结果
     */
    public int updateFfwyUser(FfwyUser ffwyUser);

    /**
     * 修改商城用户
     *
     * @param ffwyUser 商城用户
     * @return 结果
     */
    public int updateFfwyUserDefaultAddrIsNull(FfwyUser ffwyUser);

    /**
     * 删除商城用户
     * 
     * @param userId 商城用户ID
     * @return 结果
     */
    public int deleteFfwyUserById(Long userId);

    /**
     * 删除粉丝/取消关注
     *
     * @param userId 用户ID
     * @param fansId 粉丝ID
     * @return 结果
     */
    public int deleteFfwyUserByFans(@Param("userId")Long userId,@Param("fansId")Long fansId);

    /**
     * 批量删除商城用户
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyUserByIds(Long[] userIds);

    List<FfwyUser> selectFfwyWorkplanUserList(FfwyUser ffwyUser);

    List<FfwyUser> selectFfwyUserListByusertype(FfwyUser ffwyUser);

    int insertFfwyUserBytype(FfwyUser ffwyUser);

    List<FfwyUser> selectFfwyUserId(Long userId);

    List<FfwyUser> selectFfwyWorkplanSum(Long userId);

    List<FfwyUser> selectFfwyUserName(Long userId);

    List<FfwyUser> selectFfwyUserFans(FfwyUser ffwyUser);

}
