package com.suwish.welcome.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.suwish.welcome.R;
import com.suwish.welcome.adapter.RegionAdapter;
import com.suwish.welcome.model.enity.CountryRegionEntity;
import com.suwish.welcome.mvp.AccountPresenter;

import java.util.List;

/**
 *
 *  TODO 将获取区域码逻辑封装到此控件
 *
 * @author min.su on 2016/9/29.
 */
public class CountryRegionView extends LinearLayout implements View.OnClickListener{

    private AccountPresenter presenter;

    private Spinner spinnerRegion;
    private RegionAdapter regionAdapter;

    private ImageButton buttonAction;
    private ProgressBar progressBar;

    public CountryRegionView(Context context) {
        super(context);
        setup(context);
    }

    public CountryRegionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public CountryRegionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context){
        inflate(context, R.layout.view_country_region, this);
        buttonAction = (ImageButton)findViewById(R.id.button_get_region);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar_region);

        buttonAction.setOnClickListener(this);
        spinnerRegion = (Spinner)findViewById(R.id.spinner_region);
        regionAdapter = new RegionAdapter(context);
        regionAdapter.buildDefault();
        spinnerRegion.setAdapter(regionAdapter);
    }

    public void onLoading(){
        buttonAction.setVisibility(INVISIBLE);
        progressBar.setVisibility(VISIBLE);
    }

    public void overLoading(){
        buttonAction.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }

    public void setAccountPresenter(AccountPresenter presenter){
        this.presenter = presenter;
    }

    public void reload(List<CountryRegionEntity> list){
        regionAdapter.setDataSource(list);
        regionAdapter.notifyDataSetChanged();
    }

    public CountryRegionEntity getSelectedItem(){
        return (CountryRegionEntity)spinnerRegion.getSelectedItem();
    }

    @Override
    public void onClick(View v) {
        presenter.getCountryRegionList();
    }
}
