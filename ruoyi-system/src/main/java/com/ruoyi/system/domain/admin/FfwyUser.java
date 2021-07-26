package com.ruoyi.system.domain.admin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_user
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FfwyUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 头像 */
    @Excel(name = "头像")
    private String photo;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 用户类型 */
    @Excel(name = "用户类型")
    private String userType;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String intro;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 微信id */
    @Excel(name = "微信id")
    private Integer wechatId;

    /** 苹果id */
    @Excel(name = "苹果id")
    private Integer iphoneId;

    /** 用户状态 0:正常  1:冻结 */
    @Excel(name = "用户状态")
    private String userStatus;

    /** 账号 */
    @Excel(name = "账号")
    private Long userAccount;

    /** 当前角色 */
    private FfwyRole ffwyRole;

    /** 工长图片*/
    private String workerPhoto;

    /** 工长姓名*/
    private String workerName;

    /** 性别*/
    private String sex;
    /** 粉丝*/
    private Long fans;
    /** 关注*/
    private Long attention;
    /** 点赞*/
    private Long likes;

    private Integer sum;

    private Long coun;

    private Long fansId;

    private Long shopId;

    private Long type;

    /**
     * 新加字段  根据前端传过来的age辨别是那个区间段
     * 1:18岁一下 2:  18-30岁  3: 30-40 岁 4:40-50岁 5: 50岁以上
     */
    private Integer betweenAge;

    /**
     * 环信id
     */
    private String emId;
    /**
     * 环信密码
     */
    private String emKey;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /** 默认收货地址id */
    private Long defaultAddr;
    /** 默认银行卡号 */
    private Long defaultBankCard;

    private String token;

    private String searchStr;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginLoginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endLoginTime;




    public FfwyUser(String userName, String password, String userType, String phonenumber, String userStatus) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.phonenumber = phonenumber;
        this.userStatus = userStatus;
    }

    public FfwyUser(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getSum() {
        return sum;
    }


}
