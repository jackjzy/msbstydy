package com.ruoyi.common.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
@NoArgsConstructor
@AllArgsConstructor
public class LoginBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";

    private String phone ;

    public LoginBody(String username, String password, String code, String uuid) {
        this.username = username;
        this.password = password;
        this.code = code;
        this.uuid = uuid;
    }

    public LoginBody(String username, String password, String code) {
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
