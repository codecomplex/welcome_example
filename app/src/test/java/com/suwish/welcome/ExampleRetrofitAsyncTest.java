package com.suwish.welcome;

import com.suwish.welcome.model.HttpResponse;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 *
 *  异步请求
 *
 * @author by min.su on 2016/10/12.
 */
public class ExampleRetrofitAsyncTest {

    @Test()
    public void test_retrofit(){
        test_async();
    }

    private void test_async(){
        RetrofitHelperTest helperTest = RetrofitHelperTest.getInstance(RetrofitHelperTest.TYPE_ASYNC);
        ExampleServiceTest serviceTest = helperTest.getExampleServiceTest();
        Call<HttpResponse<Map<String, String>>> call = serviceTest.asyncQueryAPIS();
        final CountDownLatch signal = new CountDownLatch(1);
        System.out.println("Thread " + Thread.currentThread().getName());
        call.enqueue(new Callback<HttpResponse<Map<String, String>>>() {
            @Override
            public void onResponse(Call<HttpResponse<Map<String, String>>> call, Response<HttpResponse<Map<String, String>>> response) {
                System.out.println("onResponse Thread " + Thread.currentThread().getName());
                try {
                    assertEquals(200, response.code());
                }finally {
                    signal.countDown();
                }
//                HttpResponse<Map<String, String>> httpResponse = response.body();
//                    assertEquals(200, httpResponse.getCode());
            }
            @Override
            public void onFailure(Call<HttpResponse<Map<String, String>>> call, Throwable t) {
                System.out.println("onFailure Thread " + Thread.currentThread().getName());
                try {
                    assertNull("onFailure", t);
                }finally {
                    signal.countDown();
                }
            }
        });
        try {
            signal.await();
            System.out.println("Over");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
