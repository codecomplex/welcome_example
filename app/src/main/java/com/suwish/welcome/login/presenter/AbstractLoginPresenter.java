package com.suwish.welcome.login.presenter;

import com.suwish.welcome.login.view.LoginView;
import com.suwish.welcome.mvp.AccountPresenter;

/**
 *
 *
 * @author min.su on 2016/9/27.
 */
public abstract class AbstractLoginPresenter<V extends LoginView> extends AccountPresenter<LoginView> {

    AbstractLoginPresenter(LoginView view){
        attachView(view);
    }

    public abstract void login(String mobile, String authCode, String areaCode);
}
