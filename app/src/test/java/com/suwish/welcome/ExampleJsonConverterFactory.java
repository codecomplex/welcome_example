package com.suwish.welcome;

import com.suwish.welcome.model.HttpResponse;
import com.suwish.welcome.retrofit.ActionPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author by min.su on 2016/10/12.
 */
class ExampleJsonConverterFactory extends Converter.Factory{

    private static ExampleJsonConverterFactory INSTANCE= new ExampleJsonConverterFactory();

    static ExampleJsonConverterFactory create(){
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations){
            if (annotation instanceof GET){
                GET getMethod = (GET)annotation;
                if (JUnitUtils.isEmpty(getMethod.value())) continue;
                return new JsonConverter(getMethod.value());
            }else if (annotation instanceof POST){
                POST postMethod = (POST)annotation;
                if (JUnitUtils.isEmpty(postMethod.value())) continue;
                return new JsonConverter(postMethod.value());
            }else if (annotation instanceof ActionPath){
                ActionPath actionPath = (ActionPath)annotation;
                if (JUnitUtils.isEmpty(actionPath.value())) continue;
                return new JsonConverter(actionPath.value());
            }
        }
        return new JsonConverter("");
    }

    private class JsonConverter implements Converter<ResponseBody, HttpResponse<?>> {

        private String path;
        JsonConverter(String path){
            this.path = path;
        }
        @Override
       public HttpResponse<?> convert(ResponseBody value) throws IOException {
           return ExampleJsonHelper.parse(path, value.string());
       }
   }
}
