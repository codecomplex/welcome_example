package com.suwish.welcome.model.enity;

/**
 * 登录信息实体对象。
 *
 * @author min.su on 2016/8/15.
 */
public class LogInActionEntity extends ResponseEntity{

    private String token;
    private String userId;
    private String userName;
    private String avatar;

    public LogInActionEntity(){}

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
