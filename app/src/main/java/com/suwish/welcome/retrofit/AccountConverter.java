package com.suwish.welcome.retrofit;

import com.suwish.welcome.log.LL;
import com.suwish.welcome.model.HttpResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author by min.su on 2016/10/5.
 */
class AccountConverter implements Converter<ResponseBody, HttpResponse<?>>{
    private final String TAG = LL.makeLogTag(AccountConverter.class);

    private String path;

    private AccountConverter(String path){
        this.path = path;
    }

    static AccountConverter create(String path){
        return new AccountConverter(path);
    }
    @Override
    public HttpResponse<?> convert(ResponseBody value) throws IOException {
        return null;
    }
}
