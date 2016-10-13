package com.suwish.welcome;

import com.suwish.welcome.model.HttpResponse;

import org.junit.Test;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 *  同步请求
 *
 * @author by min.su on 2016/10/13.
 */
public class ExampleRetrofitSyncTest {

    @Test
    public void test_retrofit(){
        test_sync_result();
        test_sync_call();
    }

    private void test_sync_result(){
        RetrofitHelperTest helperTest = RetrofitHelperTest.getInstance(RetrofitHelperTest.TYPE_SYNC);
        ExampleServiceTest serviceTest = helperTest.getExampleServiceTest();
        HttpResponse<Map<String, String>> response = serviceTest.queryAPIS();
        assertEquals(200, response.getCode());
    }

    private void test_sync_call(){
        RetrofitHelperTest helperTest = RetrofitHelperTest.getInstance(RetrofitHelperTest.TYPE_ASYNC);
        ExampleServiceTest serviceTest = helperTest.getExampleServiceTest();
        Call<HttpResponse<Map<String, String>>> call = serviceTest.asyncQueryAPIS();
        try {
            Response<HttpResponse<Map<String, String>>> response = call.execute();
            HttpResponse<Map<String, String>> httpResponse = response.body();
            assertEquals(200, httpResponse.getCode());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
