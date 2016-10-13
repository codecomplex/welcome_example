package com.suwish.welcome;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author by min.su on 2016/10/11.
 */
public final class RetrofitHelperTest {

    //异步
    public final static int TYPE_ASYNC = 1;
    //同步
    public final static int TYPE_SYNC = 2;
    //异步RxJava
    public final static int TYPE_RX = 3;

    private Retrofit retrofit;

    private static RetrofitHelperTest INSTANCE;


    public static RetrofitHelperTest getInstance(int type){
        return INSTANCE == null ? INSTANCE = new RetrofitHelperTest(type) : INSTANCE;
    }

    private RetrofitHelperTest(int type){
        build(type);
    }

    private void build(int type){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.connectTimeout(5, TimeUnit.SECONDS);
        Retrofit.Builder reBuilder = new Retrofit.Builder();
        reBuilder.client(builder.build()).baseUrl("https://api.github.com");
        reBuilder.addConverterFactory(ExampleJsonConverterFactory.create());
        switch (type){
            case TYPE_ASYNC:
                buildAsync(reBuilder);
                break;
            case TYPE_SYNC:
                buildSync(reBuilder);
                break;
            case TYPE_RX:
                buildRx(reBuilder);
                break;
        }
    }

    /**
     * 异步
     *
     * @param builder retrofit builder
     */
    private void buildAsync(Retrofit.Builder builder){
        retrofit = builder.build();
    }

    /**
     * 同步
     *
     * @param builder  retrofit builder
     */
    private void buildSync(Retrofit.Builder builder){
        retrofit = builder.addCallAdapterFactory(new ExampleCallAdapterFactoryTest()).build();
    }

    /**
     *
     *
     * @param builder retrofit builder
     */
    private void buildRx(Retrofit.Builder builder){
        retrofit = builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                build();
    }

    ExampleServiceTest getExampleServiceTest(){
        return INSTANCE.retrofit.create(ExampleServiceTest.class);
    }
}
