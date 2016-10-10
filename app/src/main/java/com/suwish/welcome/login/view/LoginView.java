package com.suwish.welcome.login.view;

import com.suwish.welcome.model.enity.CountryRegionEntity;
import com.suwish.welcome.model.enity.LogInActionEntity;
import com.suwish.welcome.mvp.BaseView;

import java.util.List;

/**
 *
 * @author min.su on 2016/9/27.
 */
public interface LoginView extends BaseView {

    /**
     * 正在加载验证码
     */
    void onAuthCodeLoading();

    /**
     * 验证码请求成功，主要的作用是
     * 验证码开始倒计时，防止多次连续点击
     *
     */
    void onAuthCodeSuccess();
    /**
     * 正在加载地区信息
     */
    void onRegionLoading();

    void onCountryRegionLoaded(List<CountryRegionEntity> list);
    /**
     * 正在登录
     */
    void onLoginLoading();

    /**
     * 各类Loading完成，重置所有界面
     */
    void overLoading();

    void onLogInSuccess(LogInActionEntity entity);

    /**
     * 加载失败
     *
     * @param message 失败信息
     */
    void onFail(String message);
}
