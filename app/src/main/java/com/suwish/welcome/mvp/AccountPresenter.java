package com.suwish.welcome.mvp;

/**
 * @author min.su on 2016/9/29.
 */

public abstract class AccountPresenter<BaseView> extends BasePresenter<BaseView> {

    public static final String CLIENT_ANDROID = "ANDROID";

    public static final String REQUEST_LOGIN = "login";
    public static final String REQUEST_REGISTER = "register";

    /**
     *
     * 获取验证码
     *
     * @param mobile 手机号码
     * @param clientType 客户端类型,iOS,Android等
     * @param sendType 发送类型,注册、登陆等
     */
    public abstract void getAuthCode(String mobile, String clientType, String sendType);

    /**
     *
     * 获取国家与地区编码
     */
    public abstract void getCountryRegionList();
}
