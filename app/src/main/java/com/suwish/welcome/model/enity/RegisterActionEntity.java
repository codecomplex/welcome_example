package com.suwish.welcome.model.enity;

/**
 *
 * 注册信息返回的实体对象
 *
 * @author min.su on 2016/8/15.
 */
public class RegisterActionEntity extends ResponseEntity{

    private String token;
    private String userId;
    private String userName;
    private String mobile;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
