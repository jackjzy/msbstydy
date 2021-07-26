package com.ruoyi.system.service.impl;

import java.util.*;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.ucpaasSms.SmsUtil;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.admin.FfwyUserFans;
import com.ruoyi.system.domain.admin.FfwyUserRoot;
import com.ruoyi.system.domain.vo.UserVo;
import com.ruoyi.system.mapper.admin.FfwyUserFansMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.admin.FfwyUserRootMapper;
import com.ruoyi.common.utils.sms.SendSmsUtils;
import com.ruoyi.common.utils.sms.Template;
import com.vdurmont.emoji.EmojiParser;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyUserService;

/**
 * 商城用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyUserServiceImpl implements IFfwyUserService
{
    @Autowired(required = false)
    private FfwyUserMapper ffwyUserMapper;

    @Autowired(required = false)
    private FfwyUserFansMapper ffwyUserFansMapper;

    @Autowired(required = false)
    private FfwyUserRootMapper ffwyUserRootMapper;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * 查询商城用户
     * 
     * @param userId 商城用户ID
     * @return 商城用户
     */
    @Override
    public FfwyUser selectFfwyUserById(Long userId)
    {
        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(userId);
        ffwyUser.setPhoto(CosCofig.getPrefixUrl()+ffwyUser.getPhoto());


        //return setEnjoy(ffwyUser);
        return ffwyUser;
    }


    @Override
    public FfwyUser selectFfwyUserByToken(String token) {
        return ffwyUserMapper.selectFfwyUserByToken(token);
    }

    /**
     * 查询粉丝用户
     *
     * @param userId 商城用户ID
     * @return 粉丝用户
     */
    @Override
    public List<UserVo> selectFfwyUserByFans(Long userId) {
        List<FfwyUser> ffwyUsers = ffwyUserMapper.selectFfwyUserByFans(userId);
       return chageUserFans(ffwyUsers,userId);
    }
    /**
     * 查询粉丝用户数量
     *
     * @param userId 商城用户ID
     * @return 粉丝用户id
     */
    @Override
    public int selectFfwyUserByFansCount(Long userId) {
        return ffwyUserMapper.selectFfwyUserByFansCount(userId);
    }
    /**
     * 查询关注用户
     *
     * @param userId 商城用户ID
     * @return 关注用户
     */
    @Override
    public List<UserVo> selectFfwyUserByAttention(Long userId) {
        List<FfwyUser> ffwyUsers = ffwyUserMapper.selectFfwyUserByAttention(userId);
        return chageUserAttention(ffwyUsers,userId);
    }
    /**
     * 查询关注用户数量
     *
     * @param userId 商城用户ID
     * @return 关注用户数量
     */

    @Override
    public int selectFfwyUserByAttentionCount(Long userId) {
        return ffwyUserMapper.selectFfwyUserByAttentionCount(userId);
    }

    @Override
    public FfwyUser selectFfwyUserByPhonenumber(String phonenumber,String password) {
        return ffwyUserMapper.selectFfwyUserByPhonenumber(phonenumber,password);
    }

    /**
     * 查询商城用户列表
     * 
     * @param ffwyUser 商城用户
     * @return 商城用户
     */
    @Override
    public List<FfwyUser> selectFfwyUserList(FfwyUser ffwyUser)
    {
        if (ffwyUser.getUserId() != null){
            List<FfwyUser> list = new ArrayList<>();
            this.selectFfwyUserById(ffwyUser.getUserId());
            list.add(this.selectFfwyUserById(ffwyUser.getUserId()));
          return list;
        }
        return ffwyUserMapper.selectFfwyUserList(ffwyUser);
    }

    @Override
    public FfwyUser login(String phonenumber, String password) {

        FfwyUser ffwyUser = this.selectFfwyUserByPhonenumber(phonenumber, null);
        if (null != ffwyUser){
            //进行密码匹配
            boolean matches = bCryptPasswordEncoder.matches(password, ffwyUser.getPassword());
            if (matches) {
                //登录成功
                return ffwyUser;
            }
        }

        return null;
    }

    /**
     * 新增商城用户
     * 
     * @param ffwyUser 商城用户
     * @return 结果
     */
    @Override
    public int insertFfwyUser(FfwyUser ffwyUser)
    {
        ffwyUser.setCreateTime(DateUtils.getNowDate());

        String encode = bCryptPasswordEncoder.encode(ffwyUser.getPassword());
        ffwyUser.setPassword(encode);

        return ffwyUserMapper.insertFfwyUser(ffwyUser);
    }

    @Override
    public int insertFfwyUserFnas(Long userId, Long fansId) {
        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserAndFans(userId, fansId);

        if (ObjectUtil.isNotEmpty(ffwyUser)){

            //判断是否互关
            ffwyUser = ffwyUserMapper.selectFfwyUserAndFans(fansId, userId);
            if (ObjectUtil.isNotEmpty(ffwyUser))  return 2;
        } else {
            //添加关注
            ffwyUserMapper.insertFfwyUserFnas(userId,fansId);

            //判断是否互关
            ffwyUser = ffwyUserMapper.selectFfwyUserAndFans(fansId, userId);
            if (ObjectUtil.isNotEmpty(ffwyUser))  return 2;
        }

      return 1;
    }

    /**
     * 修改商城用户
     * 
     * @param ffwyUser 商城用户
     * @return 结果
     */
    @Override
    public int updateFfwyUser(FfwyUser ffwyUser)
    {
        ffwyUser.setUpdateTime(DateUtils.getNowDate());
        ffwyUser.setPassword(bCryptPasswordEncoder.encode(ffwyUser.getPassword()));
        return ffwyUserMapper.updateFfwyUser(ffwyUser);
    }

    @Override
    public int updateFfwyUserOther(FfwyUser ffwyUser) {
        System.err.println(ffwyUser);
        ffwyUser.setUpdateTime(DateUtils.getNowDate());
        return ffwyUserMapper.updateFfwyUser(ffwyUser);
    }

    /**
     * 批量删除商城用户
     * 
     * @param userIds 需要删除的商城用户ID
     * @return 结果
     */
    @Override
    public int deleteFfwyUserByIds(Long[] userIds)
    {
        return ffwyUserMapper.deleteFfwyUserByIds(userIds);
    }

    /**
     * 删除商城用户信息
     * 
     * @param userId 商城用户ID
     * @return 结果
     */
    @Override
    public int deleteFfwyUserById(Long userId)
    {
        return ffwyUserMapper.deleteFfwyUserById(userId);
    }

    @Override
    public int deleteFfwyUserByFans(Long userId, Long fansId) {
        return ffwyUserMapper.deleteFfwyUserByFans(userId, fansId);
    }

    @Override
    public List<FfwyUser> selectFfwyUserListByusertyep(FfwyUser ffwyUser) {
        ffwyUser.setUserType("1");
        List<FfwyUser> ffwyUsers = ffwyUserMapper.selectFfwyUserList(ffwyUser);
        if (ffwyUsers !=null){
            for (FfwyUser user : ffwyUsers) {
                Long userId = user.getUserId();
                List<FfwyUser> ffwyUsers1 = ffwyUserMapper.selectFfwyWorkplanSum(userId);
                if (ffwyUsers1 != null){
                    for (FfwyUser ffwyUser1 : ffwyUsers1) {
                        Integer sum = ffwyUser1.getSum();
                        user.setSum(sum);
                    }
                }
            }
        }
        return ffwyUsers;
    }

    @Override
    public int insertFfwyUserBytype(FfwyUser ffwyUser) {
            ffwyUser.setUserType("1");
            String password = ffwyUser.getPassword();
            String encode = bCryptPasswordEncoder.encode(ffwyUser.getPassword());
            ffwyUser.setPassword(encode);
            ffwyUser.setCreateTime(DateUtils.getNowDate());
            String phonenumber = ffwyUser.getPhonenumber();

            String msg ="用户名："+phonenumber+"密码:"+password;
            SendSmsUtils.sendsms(phonenumber, Template.CODE.getCode(), msg);
            //SmsUtil.temSms(phonenumber,msg);
        return ffwyUserMapper.insertFfwyUser(ffwyUser);
    }


    /**
     * 用户对象转换
     *
     * @param ffwyUsers 粉丝数组
     * @return 结果
     */
    //给粉丝用户添加是否关注属性 以及ffwyUser和UserVo的转换
    public List<UserVo> chageUserFans(List<FfwyUser> ffwyUsers,Long userId){
        List<UserVo> list =new ArrayList<>();
        List<FfwyUser> ffwyUsers1 = ffwyUserMapper.selectFfwyUserByAttention(userId);
        for (FfwyUser ffwyUser : ffwyUsers) {
            list.add(new UserVo(
                    ffwyUser.getUserId(),
                    ffwyUser.getUserName(),
                    ffwyUser.getPhoto(),
                    ffwyUser.getIntro(),
                    ffwyUsers1.contains(ffwyUser)
                    ));
        }

        return list;
    }
    /**
     * 用户对象转换
     *
     * @param ffwyUsers 关注数组
     * @return 结果
     */
    //给关注用户添加是否关注属性 以及ffwyUser和UserVo的转换
    public List<UserVo> chageUserAttention(List<FfwyUser> ffwyUsers,Long userId){
        List<UserVo> list =new ArrayList<>();
        List<FfwyUser> ffwyUsers1 = ffwyUserMapper.selectFfwyUserByFans(userId);
        for (FfwyUser ffwyUser : ffwyUsers) {
            list.add(new UserVo(
                    ffwyUser.getUserId(),
                    ffwyUser.getUserName(),
                    ffwyUser.getPhoto(),
                    ffwyUser.getIntro(),
                    ffwyUsers1.contains(ffwyUser)
            ));
        }

        return list;
    }

    public FfwyUser setEnjoy(FfwyUser ffwyUser){
        String userName = ffwyUser.getUserName();
        String intro = ffwyUser.getIntro();

        if (!StringUtils.isEmpty(userName)){
            String toUserName = EmojiParser.parseToAliases(userName);
            ffwyUser.setUserName(toUserName);
        }
        if (!StringUtils.isEmpty(intro)){
            String toIntro = EmojiParser.parseToAliases(intro);
            ffwyUser.setIntro(toIntro);
        }

        return ffwyUser;
    }

}
