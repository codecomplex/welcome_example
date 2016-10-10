package com.suwish.welcome.register.presenter;

import com.suwish.welcome.mvp.AccountPresenter;
import com.suwish.welcome.register.view.RegisterView;

/**
 *
 * @author min.su on 2016/9/27.
 */
public abstract class AbstractRegisterPresenter extends AccountPresenter<RegisterView> {

    AbstractRegisterPresenter(RegisterView view){
        attachView(view);
    }

    public abstract void getAuthCode(String mobile, String clientType, String sendType);

    public abstract void register(String mobile, String captcha, String password, String area);

    public abstract void validateRegMobile(String mobile, String area);
}
