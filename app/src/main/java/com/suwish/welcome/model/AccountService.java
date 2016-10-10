package com.suwish.welcome.model;

import com.suwish.welcome.model.enity.AuthCodeEntity;
import com.suwish.welcome.model.enity.CountryRegionEntity;
import com.suwish.welcome.model.enity.LogInActionEntity;
import com.suwish.welcome.model.enity.MobileValidateEntity;
import com.suwish.welcome.model.enity.RegisterActionEntity;
import com.suwish.welcome.retrofit.ActionPath;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 * @author by min.su on 2016/10/5.
 */
public interface AccountService {

    @GET(ModelAction.PATH_GET_AUTH_CODE)
    Observable<HttpResponse<AuthCodeEntity>> getAuthCode(@Query("mobile") String mobile,
                                                         @Query("clientType") String clientType,
                                                         @Query("sendType") String sendType);

    @POST(ModelAction.PATH_LOGIN)
    Observable<HttpResponse<LogInActionEntity>> login(@Query("mobile") String mobile,
                                                      @Query("vCode") String vCode,
                                                      @Query("area") String area);

    @FormUrlEncoded
    @POST(ModelAction.PATH_REGISTER)
    Observable<HttpResponse<RegisterActionEntity>> register1(@Field("mobile") String mobile,
                                                             @Field("captcha") String captcha,
                                                             @Field("password") String password,
                                                             @Field("area") String area);

    @POST(ModelAction.PATH_REGISTER)
    Observable<HttpResponse<RegisterActionEntity>> register(@Query("mobile") String mobile,
                                                            @Query("captcha") String captcha,
                                                            @Query("password") String password,
                                                            @Query("area") String area);

    @ActionPath(ModelAction.PATH_REGISTER)
    @POST
    Observable<HttpResponse<RegisterActionEntity>> registerParameter(@Url String url);

    @POST(ModelAction.PATH_MOBILE_VALIDATE)
    Observable<HttpResponse<MobileValidateEntity>> validateRegMobile(@Query("mobile") String mobile,
                                                                     @Query("area") String area);

    @GET(ModelAction.PATH_COUNTRY_REGION)
    Observable<HttpResponse<List<CountryRegionEntity>>> getCountryRegionList();
}
