package com.suwish.welcome.login.presenter;

import com.suwish.welcome.login.view.LoginView;
import com.suwish.welcome.model.AccountService;
import com.suwish.welcome.model.HttpResponse;
import com.suwish.welcome.model.enity.AuthCodeEntity;
import com.suwish.welcome.model.enity.CountryRegionEntity;
import com.suwish.welcome.model.enity.LogInActionEntity;
import com.suwish.welcome.retrofit.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author min.su on 2016/9/27.
 */

public class DefaultLoginPresenter extends AbstractLoginPresenter<LoginView> {

    private AccountService accountService;

    public DefaultLoginPresenter(LoginView view) {
        super(view);
        accountService = RetrofitHelper.getInstance().getAccountService();
    }
    @Override
    public void getAuthCode(String mobile, String clientType, String sendType) {
        baseView.onAuthCodeLoading();
        Observable<HttpResponse<AuthCodeEntity>> observable = accountService.getAuthCode(mobile, clientType, sendType);
        addSubscription(observable, new Subscriber<HttpResponse<AuthCodeEntity>>() {
            @Override
            public void onCompleted() {
                baseView.onAuthCodeSuccess();
            }
            @Override
            public void onError(Throwable e) {
                baseView.onFail(e.getMessage());
            }
            @Override
            public void onNext(HttpResponse<AuthCodeEntity> response) {
                if (response.getCode() != HttpResponse.CODE_SUCCESS){
                    baseView.onFail(response.getMessage());
                }
            }
        });
    }

    @Override
    public void login(String mobile, String vCode, String area){
        baseView.onLoginLoading();
        Observable<HttpResponse<LogInActionEntity>> observable = accountService.login(mobile, vCode, area);
        addSubscription(observable, new Subscriber<HttpResponse<LogInActionEntity>>() {
            @Override
            public void onCompleted() {
                baseView.overLoading();
            }
            @Override
            public void onError(Throwable e) {
                baseView.onFail(e.getMessage());
            }
            @Override
            public void onNext(HttpResponse<LogInActionEntity> response) {
                if (response.getCode() != HttpResponse.CODE_SUCCESS){
                    baseView.onFail(response.getMessage());
                }else {
                    baseView.onLogInSuccess(response.getData());
                }
            }
        });
    }

    @Override
    public void getCountryRegionList() {
        baseView.onRegionLoading();
        Observable<HttpResponse<List<CountryRegionEntity>>> observable = accountService.getCountryRegionList();
        addSubscription(observable, new Subscriber<HttpResponse<List<CountryRegionEntity>>>() {
            @Override
            public void onCompleted() {
                baseView.overLoading();
            }
            @Override
            public void onError(Throwable e) {
                baseView.onFail(e.getMessage());
            }
            @Override
            public void onNext(HttpResponse<List<CountryRegionEntity>> response) {
                if (response.getCode() != HttpResponse.CODE_SUCCESS){
                    baseView.onFail(response.getMessage());
                }else {
                    baseView.onCountryRegionLoaded(response.getData());
                }
            }
        });
    }
}
