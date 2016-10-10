package com.suwish.welcome.register.presenter;

import com.suwish.welcome.model.AccountService;
import com.suwish.welcome.model.HttpResponse;
import com.suwish.welcome.model.enity.AuthCodeEntity;
import com.suwish.welcome.model.enity.CountryRegionEntity;
import com.suwish.welcome.model.enity.RegisterActionEntity;
import com.suwish.welcome.register.view.RegisterView;
import com.suwish.welcome.retrofit.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author min.su on 2016/9/27.
 */

public class DefaultRegisterPresenter extends AbstractRegisterPresenter {

    private AccountService accountService;

    public DefaultRegisterPresenter(RegisterView view) {
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
    public void register(String mobile, String captcha, String password, String area) {
        baseView.onRegisterLoading();
        Observable<HttpResponse<RegisterActionEntity>> observable = accountService.register(mobile, captcha, password, area);
        addSubscription(observable, new Subscriber<HttpResponse<RegisterActionEntity>>() {
            @Override
            public void onCompleted() {
                baseView.overLoading();
            }
            @Override
            public void onError(Throwable e) {
                baseView.onFail(e.getMessage());
            }
            @Override
            public void onNext(HttpResponse<RegisterActionEntity> response) {
                if (response.getCode() != HttpResponse.CODE_SUCCESS){
                    baseView.onFail(response.getMessage());
                }else {
                    baseView.onRegisterSuccess(response.getData());
                }
            }
        });
    }

    @Override
    public void validateRegMobile(String mobile, String area) {}

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
