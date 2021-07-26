package com.ruoyi.back.service.impl;

import java.util.*;

import com.ruoyi.common.utils.sms.SendSmsUtils;
import com.ruoyi.common.utils.sms.Template;
import com.ruoyi.back.domain.FfwyAuditStatus;
import com.ruoyi.back.domain.queryentity.QueryAudti;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.ucpaasSms.SmsUtil;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyAuditMapper;
import com.ruoyi.back.domain.FfwyAudit;
import com.ruoyi.back.service.IFfwyAuditService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 审核Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyAuditServiceImpl implements IFfwyAuditService 
{
    @Autowired
    private FfwyAuditMapper ffwyAuditMapper;
    @Autowired
    private FfwyShopMapper ffwyShopMapper;
    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询审核
     * 
     * @param auditId 审核ID
     * @return 审核
     */
    @Override
    public FfwyAudit selectFfwyAuditById(Long auditId)
    {
        return ffwyAuditMapper.selectFfwyAuditById(auditId);
    }

    @Override
    public int auditByYes(FfwyAudit ffwyAudit) {
        int i=0;
        //修改审核信息
        i+= ffwyAuditMapper.updateFfwyAudit(ffwyAudit);
        FfwyAudit Audit = ffwyAuditMapper.selectFfwyAuditById(ffwyAudit.getAuditId());
        String phone = ffwyAudit.getPhone();
        FfwyShop ffwyShop = new FfwyShop();
        //判断是否审核成功 3表示审核失败，2表示审核成功
        if (Audit.getAuditStatus().getAuditStatusId()==3){
            //审核失败短信通知用户
            SendSmsUtils.sendsms(phone, Template.CODE.getCode(), "您的申请的店铺审核失败，请重新审核");
            //SmsUtil.temSms(phone,"您的申请的店铺审核失败，请重新审核");
        }else if (Audit.getAuditStatus().getAuditStatusId()==2){
            //新建店铺
            ffwyShop.setUserId(ffwyAudit.getUserId());
            ffwyShopMapper.insertFfwyShop(ffwyShop);
            //通过审核 新建后台管理的账号并设置权限
            SysUser sysUser = new SysUser();
            //设置账号密码
            String password=phone.substring(5,phone.length());
            String msg="账号："+phone+"密码："+password;
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            //新建后天管理用户并设置设置商户权限
            Long[] roleIds=new Long[1];
            roleIds[0]=(long)102;
            sysUser.setRoleIds(roleIds);
            sysUser.setUserName(phone);
            sysUser.setNickName(phone);
            sysUser.setShopId(ffwyShop.getShopId());
            userService.insertUser(sysUser);
            //透明皂
            //SmsUtil.temSms(phone,msg);
            SendSmsUtils.sendsms(phone, Template.CODE.getCode(), msg);
            /*
             *
             * 通知用户可以管理商铺
             *
             * */
//            JiGuangPushUtil.pushAndroid("alias",hash,"审核成功","您可以通过www.xxxxxx.com来管理商铺");
        }else {
            try{
                new RuntimeException("审核状态错误");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return i;
    }

    /**
     * 查询审核-根据用户ID
     *
     * @param userId
     * @return
     */
    @Override
    public FfwyAudit selectFfwyAuditByUserId(Long userId) {
        return ffwyAuditMapper.selectFfwyAuditById(userId);
    }

    /**
     * 查询审核列表
     * 
     * @param ffwyAudit 审核
     * @return 审核
     */
    @Override
    public List<FfwyAudit> selectFfwyAuditList(FfwyAudit ffwyAudit)
    {
        return ffwyAuditMapper.selectFfwyAuditList(ffwyAudit);
    }

    @Override
    public List<FfwyAudit> faindAllAuditList(QueryAudti ffwyAudit) {
        return ffwyAuditMapper.faindAllAuditList(ffwyAudit);
    }

    /**
     * 新增审核
     * 
     * @param ffwyAudit 审核
     * @return 结果
     */
    @Override
    public Map<String, Object> insertFfwyAudit(FfwyAudit ffwyAudit)
    {

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("code", 0);

        System.err.println(ffwyAudit);
        if (null == ffwyAudit.getUserId() || "".equals(ffwyAudit.getUserId())) {
            dataMap.put("resmsg", "所属用户id不能为空");
            return dataMap;
        } else {

            FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(ffwyAudit.getUserId());
            if (null == ffwyUser) {
                dataMap.put("resmsg", "该用户不存在！");
                return dataMap;
            }
            FfwyAudit ffwyAuditOld = ffwyAuditMapper.selectFfwyAuditByUserId(ffwyAudit.getUserId());
            if (null != ffwyAuditOld) {
                dataMap.put("resmsg", "请勿重复提交申请！");
                return dataMap;
            }
        }


        if (null == ffwyAudit.getName() || "".equals(ffwyAudit.getName())) {
            dataMap.put("resmsg", "店铺名称不能为空");
            return dataMap;
        }
        if (null == ffwyAudit.getShopUserName() || "".equals(ffwyAudit.getShopUserName())) {
            dataMap.put("resmsg", "联系人姓名不能为空");
            return dataMap;
        }
        if (null == ffwyAudit.getPhone() || "".equals(ffwyAudit.getPhone())) {
            dataMap.put("resmsg", "联系电话不能为空");
            return dataMap;
        }

        if (null == ffwyAudit.getEmail() || "".equals(ffwyAudit.getEmail())) {
            dataMap.put("resmsg", "联系人邮箱不能为空");
            return dataMap;
        }
        if (null == ffwyAudit.getShopUrl() || "".equals(ffwyAudit.getShopUrl())) {
            dataMap.put("resmsg", "店铺网址不能为空");
            return dataMap;
        }

        // 上传文件

        if (StringUtils.isEmpty(ffwyAudit.getIdCard())) {

            dataMap.put("resmsg", "身份证正面文件不能为空");
                return dataMap;
        }

        if (StringUtils.isEmpty(ffwyAudit.getIdCardBack())) {

            dataMap.put("resmsg", "身份证背面文件不能为空");
            return dataMap;
        }

        if (StringUtils.isEmpty(ffwyAudit.getLicence())) {


            dataMap.put("resmsg", "营业执照文件不能为空");
            return dataMap;
        }

//        if (list.size() < 3) {
//            dataMap.put("resmsg", "文件上传失败！");
//            return dataMap;
//        }
        String idCard = ffwyAudit.getIdCard();
        ffwyAudit.setIdCard(idCard.substring(idCard.indexOf("images"),idCard.length()));
        String idCardBack = ffwyAudit.getIdCardBack();
        ffwyAudit.setIdCardBack(idCardBack.substring(idCardBack.indexOf("images"),idCardBack.length()));
        String licence = ffwyAudit.getLicence();
        ffwyAudit.setLicence(licence.substring(licence.indexOf("images"),licence.length()));
        ffwyAudit.setCreateTime(DateUtils.getNowDate());

        FfwyAuditStatus ffwyAuditStatus = new FfwyAuditStatus();
        ffwyAuditStatus.setAuditStatusId(1);
        ffwyAudit.setAuditStatus(ffwyAuditStatus);

        int insertCode = ffwyAuditMapper.insertFfwyAudit(ffwyAudit);

        if (1 == insertCode) {
            dataMap.put("code", "1");
            dataMap.put("data", ffwyAudit);
//            dataMap.put("resmsg", "操作成功！");
            return dataMap;
        }

        return dataMap;
    }

    /**
     * 修改审核
     * 
     * @param ffwyAudit 审核
     * @return 结果
     */
    @Override
    public int updateFfwyAudit(FfwyAudit ffwyAudit)
    {
        ffwyAudit.setUpdateTime(DateUtils.getNowDate());
        return ffwyAuditMapper.updateFfwyAudit(ffwyAudit);
    }

    /**
     * 批量删除审核
     * 
     * @param auditIds 需要删除的审核ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAuditByIds(Long[] auditIds)
    {
        return ffwyAuditMapper.deleteFfwyAuditByIds(auditIds);
    }

    /**
     * 删除审核信息
     * 
     * @param auditId 审核ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAuditById(Long auditId)
    {
        return ffwyAuditMapper.deleteFfwyAuditById(auditId);
    }
}
