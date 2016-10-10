package com.suwish.welcome;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 *
 * @author by min.su on 2016/10/5.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setup();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setup();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setup();
    }

    private void setup(){
        setupView();
        initEvent();
        loadData();
    }

    protected void setupUI(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if (toolbar == null) return;
        setSupportActionBar(toolbar);
    }

    protected void setupView(){}

    protected void initEvent(){}

    protected void loadData(){}
}
