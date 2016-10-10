package com.suwish.welcome.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.suwish.welcome.R;
import com.suwish.welcome.log.LL;
import com.suwish.welcome.login.presenter.AbstractLoginPresenter;
import com.suwish.welcome.login.presenter.DefaultLoginPresenter;
import com.suwish.welcome.login.view.LoginView;
import com.suwish.welcome.model.enity.CountryRegionEntity;
import com.suwish.welcome.model.enity.LogInActionEntity;
import com.suwish.welcome.mvp.MvpActivity;
import com.suwish.welcome.utils.AccountUtils;
import com.suwish.welcome.utils.DialogHelper;
import com.suwish.welcome.utils.PermissionsUtils;
import com.suwish.welcome.utils.ResourcesUtils;
import com.suwish.welcome.widget.AuthCodeView;
import com.suwish.welcome.widget.CountryRegionView;

import java.util.List;

/**
 *
 * @author min.su on 2016/9/27.
 */
public class LoginActivity extends MvpActivity<AbstractLoginPresenter>
        implements LoginView, View.OnClickListener{

    private final String TAG = LL.makeLogTag(LoginActivity.class);

    private Button buttonAction;

    private EditText textPhone;
    private AuthCodeView authCodeView;

    private CountryRegionView regionView;

    private ProgressDialog progressDialog;

    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected AbstractLoginPresenter createPresenter() {
        return new DefaultLoginPresenter(this);
    }

    @Override
    protected void setupView() {
        buttonAction = (Button)findViewById(R.id.button_join);
        textPhone = (EditText)findViewById(R.id.edit_phone);

        authCodeView = (AuthCodeView)findViewById(R.id.view_auth_code);
        regionView = (CountryRegionView)findViewById(R.id.view_region);

        progressDialog = DialogHelper.createDialog(this);
        progressDialog.setTitle(getString(R.string.title_sign_in));
        progressDialog.setMessage(getString(R.string.general_wait));

        dialogHelper = DialogHelper.create(this);
    }

    @Override
    protected void initEvent() {
        ResourcesUtils.setClick(this, this, R.id.button_join, R.id.button_get_code);
    }

    @Override
    protected void loadData() {
        regionView.setAccountPresenter(basePresenter);
        basePresenter.getCountryRegionList();
    }

    @Override
    public void onAuthCodeLoading() {
        authCodeView.onLoading();
    }

    @Override
    public void onAuthCodeSuccess() {
        authCodeView.startCountDown();
    }

    @Override
    public void onRegionLoading() {
        regionView.onLoading();
    }

    @Override
    public void onLoginLoading() {
        buttonAction.setEnabled(false);
    }

    @Override
    public void overLoading() {
        authCodeView.overLoading();
        buttonAction.setEnabled(true);
        regionView.overLoading();
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFail(String message) {
        overLoading();
        dialogHelper.show(getString(R.string.title_sign_in), message, null);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onCountryRegionLoaded(List<CountryRegionEntity> list) {
        if (AccountUtils.isEmpty(list)){
            dialogHelper.create(R.string.title_region_code, R.string.dialog_message_region_code_data_error, null);
            return;
        }
        regionView.reload(list);
    }
    @Override
    protected void onResume() {
        super.onResume();
        requestPermissions();
    }

    @Override
    public void onLogInSuccess(LogInActionEntity entity) {
        try {

            //本地存储
            dialogHelper.show(R.string.title_sign_in, R.string.dialog_message_sign_in_success, new DialogHelper.DialogCallback() {
                @Override
                public void onDialogClick(DialogInterface dialogInterface, int button) {
                    //finish();
                }
            });
        }catch (Exception e){
            LL.w(TAG, "", e);
            dialogHelper.show(getString(R.string.title_sign_in), e.getMessage(), null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 1) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (!PermissionsUtils.onRequestPermissionsResult(permissions, grantResults)){
            dialogHelper.show(R.string.title_sign_up, R.string.dialog_message_permission_denied, null);
        }
    }
    private boolean requestPermissions(){
        String[] permissions = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        return PermissionsUtils.checkRequestPermission(this, permissions, 1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_wifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.button_join:
                submit();
                break;
            case R.id.button_get_code:
                if (!checkPhoneNumber()) return;
                basePresenter.getAuthCode(textPhone.getText().toString(),
                        AbstractLoginPresenter.CLIENT_ANDROID, AbstractLoginPresenter.REQUEST_LOGIN);
                break;
        }
    }
    private void submit(){
        if (!checkForm()) return;
        progressDialog.show();
        CountryRegionEntity entity = regionView.getSelectedItem();
        basePresenter.login(textPhone.getText().toString(), authCodeView.getAuthCode(), entity.getPhoneCode());
    }
    private boolean checkPhoneNumber(){
        String phoneNumber = textPhone.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)){
            dialogHelper.show(R.string.title_sign_in, R.string.dialog_message_phone_number_necessary, null);
            return false;
        }
        if (!AccountUtils.isPhoneNumber(phoneNumber)){
            dialogHelper.show(R.string.title_sign_in, R.string.dialog_message_phone_number_format_error, null);
            return false;
        }
        return true;
    }

    private boolean checkForm(){
        if (!checkPhoneNumber()){
            return false;
        }
        String authCode = authCodeView.getAuthCode();
        if (TextUtils.isEmpty(authCode)){
            dialogHelper.show(R.string.title_sign_in, R.string.dialog_message_auth_code_necessary, null);
            return false;
        }
        CountryRegionEntity entity = regionView.getSelectedItem();
        if (entity.getId() <= 0){
            dialogHelper.show(R.string.title_region_code, R.string.dialog_message_region_code_invalid, null);
            return false;
        }
        return true;
    }
}
