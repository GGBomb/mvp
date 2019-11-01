package com.jianlong.youtv.params;

public class LoginInfo {
    private String mobile;
    private String randomStr;
    private String code;
    private String grant_type;
    private String scope;

    public LoginInfo() {
    }

    public LoginInfo(String mobile, String randomStr, String code, String grant_type, String scope) {
        this.mobile = mobile;
        this.randomStr = randomStr;
        this.code = code;
        this.grant_type = grant_type;
        this.scope = scope;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRandomStr() {
        return randomStr;
    }

    public void setRandomStr(String randomStr) {
        this.randomStr = randomStr;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
