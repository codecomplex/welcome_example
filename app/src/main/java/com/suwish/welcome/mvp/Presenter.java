package com.suwish.welcome.mvp;

/**
 * @author min.su on 2016/9/26.
 */

public interface Presenter<BaseView> {

    void attachView(BaseView view);

    void detachView();
}
