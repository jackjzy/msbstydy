package com.ruoyi.app.util.em;

import lombok.Data;

@Data
public class Entitie{
        /**
         *
         */
        private Long created;

        /**
         * 昵称（可选），在 iOS Apns 推送时会使用的昵称（仅在推送通知栏内显示的昵称），并不是用户个人信息的昵称，
         * 环信是不保存用户昵称，头像等个人信息的，需要自己服务器保存并与给自己用户注册的IM用户名绑定
         */
        private String nickname;

        /**
         *
         */
        private Long modified;

        /**
         * “user”用户类型
         */
        private String type;

        /**
         * 用户的UUID，标识字段
         */
        private String uuid;

        /**
         * 用户名，也就是环信 ID
         */
        private String username;

        /**
         * 用户是否已激活，“true”已激活，“false“封禁，封禁需要通过解禁接口进行解禁，才能正常登录
         */
        private boolean activated;

    }
