package com.ruoyi.common.enums;

public enum ContractCartType {
    IDTYPEPSN1("CRED_PSN_CH_IDCARD","大陆身份证"),
    IDTYPEPSN2("CRED_PSN_CH_TWCARD","台湾来往大陆通行证"),
    IDTYPEPSN3("CRED_PSN_CH_MACAO","澳门来往大陆通行证"),
    IDTYPEPSN4("CRED_PSN_CH_HONGKONG","香港来往大陆通行证"),
    IDTYPEPSN5("CRED_PSN_FOREIGN","外籍证件"),
    IDTYPEPSN6("CRED_PSN_PASSPORT","护照"),
    IDTYPEPSN7("CRED_PSN_CH_SOLDIER_IDCARD","军官证"),
    IDTYPEPSN8("CRED_PSN_CH_SOCIAL_SECURITY_CARD","社会保障卡"),
    IDTYPEPSN9("CRED_PSN_CH_ARMED_POLICE_IDCARD","武装警察身份证件"),
    IDTYPEPSN10("CRED_PSN_CH_RESIDENCE_BOOKLET","户口簿"),
    IDTYPEPSN11("CRED_PSN_CH_TEMPORARY_IDCARD","临时居民身份证"),
    IDTYPEPSN12("CRED_PSN_CH_GREEN_CARD","外国人永久居留证"),
    IDTYPEPSN13("CRED_PSN_SHAREHOLDER_CODE","股东代码证"),
    IDTYPEPSN14("CRED_PSN_POLICE_ID_CARD","警官证"),
    IDTYPEPSN15("CRED_PSN_UNKNOWN","位置类型"),
    IDTYPEORG1("CRED_ORG_USCC","统一社会信用代码"),
    IDTYPEORG2("CRED_ORG_CODE","组织机构代码证"),
    IDTYPEORG3("CRED_ORG_REGCODE","工商注册号"),
    IDTYPEORG4("CRED_ORG_BUSINESS_REGISTTATION_CODE","工商登记证"),
    IDTYPEORG5("CRED_ORG_TAX_REGISTTATION_CODE","税务登记证"),
    IDTYPEORG6("CRED_ORG_LEGAL_PERSON_CODE","法人代码证"),
    IDTYPEORG7("CRED_ORG_ENT_LEGAL_PERSON_CODE","事业单位法人证书"),
    IDTYPEORG8("CRED_ORG_SOCIAL_REG_CODE","社会团体登记证书"),
    IDTYPEORG9("CRED_ORG_PRIVATE_NON_ENT_REG_CODE","民办非机构登记证书"),
    IDTYPEORG10("CRED_ORG_FOREIGN_ENT_REG_CODE","外国机构常驻代表机构登记证"),
    IDTYPEORG11("CRED_ORG_GOV_APPROVAL","政府批文"),
    ;

    private String type;
    private String msg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ContractCartType(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
