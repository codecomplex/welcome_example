package com.suwish.welcome.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.suwish.welcome.BaseActivity;

/**
 * @author min.su on 2016/9/26.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        basePresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null){
            basePresenter.detachView();
        }
    }
}
