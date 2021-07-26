package com.ruoyi.app.util.hz.factory.base;

import com.ruoyi.app.util.hz.factory.seals.*;

/**
 * 悟空API印章服务功能类
 * @description  悟空API印章服务功能类
 * @author  澄泓
 * @date  2020/10/26 9:55
 * @version JDK1.7
 */
public class Seals {

    /**
     * 悟空API创建个人模板印章
     * @param accountId 用户id
     * @param color 印章颜色，
     *
     * （1）RED-红色
     *
     * （2）BLUE-蓝色
     *
     * （3）BLACK-黑色
     * @param type 模板类型
     * @return
     */
    public static CreatePersonalTemplate createPersonalTemplate(String accountId, String color, String type){
        return new CreatePersonalTemplate(accountId,color,type);
    }


    /**
     * 悟空API创建机构模板印章
     * @param orgId 机构id
     * @param color 印章颜色，RED-红色，BLUE-蓝色，BLACK-黑色
     * @param type 模板类型
     * @param central 中心图案类型
     * @return
     */
    public static CreateOfficialTemplate createOfficialTemplate(String orgId, String color, String type, String central){
        return new CreateOfficialTemplate(orgId,color,type,central);
    }

    /**
     * 悟空API创建个人/机构图片印章
     * @param accountId 用户id
     * @param type 印章数据类型 BASE64：base64格式
     * @param data 印章数据
     * @return
     */
    public static CreateTemplate createTemplate(String accountId, String type, String data){
        return new CreateTemplate(accountId,type,data);
    }

    /**
     * 悟空API查询个人印章
     * @param accountId 用户id
     * @return
     */
    public static QryPersonSeals qryPersonSeals(String accountId){
        return new QryPersonSeals(accountId);
    }

    /**
     * 悟空API查询机构印章
     * @param orgId 机构id
     * @return
     */
    public static QryOrganizationsSeals qryOrganizationsSeals(String orgId){
        return new QryOrganizationsSeals(orgId);
    }

    /**
     * 悟空API删除个人印章
     * @param accountId 用户id
     * @param sealId 印章id
     * @return
     */
    public static DeletePersonSeal deletePersonSeal(String accountId, String sealId){
        return new DeletePersonSeal(accountId, sealId);
    }

    /**
     * 悟空API删除机构印章
     * @param orgId 机构id
     * @param sealId 印章id
     * @return
     */
    public static DeleteOrganizationsSeal deleteOrganizationsSeal(String orgId, String sealId){
        return new DeleteOrganizationsSeal(orgId, sealId);
    }
}