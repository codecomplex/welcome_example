package com.suwish.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.suwish.welcome.model.enity.LogInActionEntity;
import com.suwish.welcome.register.RegisterActivity;
import com.suwish.welcome.utils.ResourcesUtils;

/**
 *
 * 注册与登录分支选择界面。
 *
 * @author by min.su on 2016/10/9.
 */
public class JoinActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
    }

    @Override
    protected void initEvent() {
        ResourcesUtils.setClick(this, this, R.id.button_sign_up, R.id.button_sing_in);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_sign_up:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.button_sing_in:
                startActivity(new Intent(this, LogInActionEntity.class));
                break;
        }
    }
}
