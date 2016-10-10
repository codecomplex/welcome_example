package com.suwish.welcome.retrofit;

import com.suwish.welcome.BuildConfig;
import com.suwish.welcome.log.LL;
import com.suwish.welcome.model.AccountService;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 *
 * @author by min.su on 2016/10/5.
 */
public final class RetrofitHelper {

    private final String TAG = LL.makeLogTag(RetrofitHelper.class);

    private final String BASE_URL = "";

    private Retrofit retrofit;

    private static class InstanceHolder{
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    private RetrofitHelper(){
        build();
    }

    public static RetrofitHelper getInstance(){
        return InstanceHolder.INSTANCE;
    }


    private void build(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.connectTimeout(5, TimeUnit.SECONDS);
        initHttps(builder);
        initHeader(builder);

        retrofit = new Retrofit.Builder().client(builder.build())
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
    }

    private void initHttps(OkHttpClient.Builder builder){
        SSLContext sslContext = null;
        try {
//            sslContext = SSLContext.getInstance("TSL");
            sslContext = SSLContext.getInstance("SSL");
        }catch (Exception ex){
            LL.w(TAG, "", ex);
        }
        if (sslContext == null){
            return;
        }

        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // ignored
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // ignored
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
        }catch (Exception ex){
            LL.w(TAG, "", ex);
        }
        builder.sslSocketFactory(sslContext.getSocketFactory());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    private void initHeader(OkHttpClient.Builder builder){
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                // add request header
                // 可以增加针对服务端的特定请求头
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
    }


    public AccountService getAccountService(){
        return InstanceHolder.INSTANCE.retrofit.create(AccountService.class);
    }
}