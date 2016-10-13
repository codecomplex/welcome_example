package com.suwish.welcome;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @author by min.su on 2016/10/13.
 */
class ExampleCallAdapterFactoryTest extends CallAdapter.Factory{

    @Override
    public CallAdapter<?> get(final Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new CallAdapter<Object>() {
            @Override
            public Type responseType() {
                return returnType;
            }

            @Override
            public <R> Object adapt(Call<R> call) {
                try {
                    return call.execute().body();
                }catch (Exception ex){
                    throw new RuntimeException();
                }
            }
        };
    }
}
