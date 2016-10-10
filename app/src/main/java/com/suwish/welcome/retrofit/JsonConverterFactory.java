package com.suwish.welcome.retrofit;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author by min.su on 2016/10/5.
 */

public class JsonConverterFactory extends Converter.Factory{

    private final static JsonConverterFactory INSTANCE = new JsonConverterFactory();

    static JsonConverterFactory create(){
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations){
            if (annotation instanceof GET){
                GET getMethod = (GET)annotation;
                if (TextUtils.isEmpty(getMethod.value())) continue;
                return AccountConverter.create(getMethod.value());
            }else if (annotation instanceof POST){
                POST postMethod = (POST)annotation;
                if (TextUtils.isEmpty(postMethod.value())) continue;
                return AccountConverter.create(postMethod.value());
            }else if (annotation instanceof ActionPath){
                ActionPath pathMethod = (ActionPath)annotation;
                return AccountConverter.create(pathMethod.value());
            }
        }
        return AccountConverter.create(null);
    }
}
