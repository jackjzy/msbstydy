package com.ruoyi.system.service;

import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商城用户Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyUserService 
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
     * @return 粉丝用户
     */

    public List<UserVo> selectFfwyUserByFans(Long userId);
    /**
     * 查询粉丝用户数量
     *
     * @param userId 商城用户ID
     * @return 粉丝用户id
     */

    public int selectFfwyUserByFansCount(Long userId);
    /**
     * 查询关注用户
     *
     * @param userId 商城用户ID
     * @return 关注用户
     */
    public List<UserVo> selectFfwyUserByAttention(Long userId);

    /**
     * 查询关注用户数量
     *
     * @param userId 商城用户ID
     * @return 关注用户数量
     */

    public int selectFfwyUserByAttentionCount(Long userId);

    /**
     * 根据手机号查询商城用户
     *
     * @param phonenumber 手机号
     * @return 商城用户
     */
    public FfwyUser selectFfwyUserByPhonenumber(String phonenumber,String password);

    /**
     * 查询商城用户列表
     * 
     * @param ffwyUser 商城用户
     * @return 商城用户集合
     */
    public List<FfwyUser> selectFfwyUserList(FfwyUser ffwyUser);

    /**
     * 手机号密码
     * @param phonenumber
     * @param password
     * @return
     */
    public FfwyUser login(String phonenumber, String password);


    /**
     * 新增商城用户
     * 
     * @param ffwyUser 商城用户
     * @return 结果
     */
    public int insertFfwyUser(FfwyUser ffwyUser);

    /**
     * 新增粉丝
     *
     * @param userId 用户id
     * @param fansId 粉丝id
     * @return 结果
     */
    public int insertFfwyUserFnas(Long userId, Long fansId);

    /**
     * 修改商城用户
     * 
     * @param ffwyUser 商城用户
     * @return 结果
     */
    public int updateFfwyUser(FfwyUser ffwyUser);

    /**
     * 修改商城用户除密码外其他属性
     *
     * @param ffwyUser 商城用户
     * @return 结果
     */
    public int updateFfwyUserOther(FfwyUser ffwyUser);

    /**
     * 批量删除商城用户
     * 
     * @param userIds 需要删除的商城用户ID
     * @return 结果
     */
    public int deleteFfwyUserByIds(Long[] userIds);

    /**
     * 删除商城用户信息
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
    public int deleteFfwyUserByFans(Long userId, Long fansId);

    List<FfwyUser> selectFfwyUserListByusertyep(FfwyUser ffwyUser);

    int insertFfwyUserBytype(FfwyUser ffwyUser);

}
