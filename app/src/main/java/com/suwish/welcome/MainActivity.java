package com.suwish.welcome;

import android.content.Intent;
import android.os.Bundle;

/**
 * 按照正常设计,本界面应该为欢迎界面,之后跳转到服务条款、法律条文
 * 等界面,但是此项目为示例项目因此直接跳转到登录与注册选择界面。
 *
 * author by min.su on 2016/10/5.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, JoinActivity.class));
        finish();
    }
}
