package com.suwish.welcome;

import com.suwish.welcome.model.HttpResponse;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * @author by min.su on 2016/10/13.
 */
public class ExampleRetrofitRxTest {

    @Test()
    public void test_retrofit(){
        test_rxJava();
    }

    private class CallBackSubscriber extends Subscriber<HttpResponse<Map<String,String>>>{
        private CountDownLatch signal;
        CallBackSubscriber(CountDownLatch signal){
            this.signal = signal;
        }
        @Override
        public void onCompleted() {
            System.out.println("onCompleted Thread " + Thread.currentThread().getName());
            System.out.println("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError Thread " + Thread.currentThread().getName());
            System.out.println("onError");
            e.printStackTrace();
            try {
                assertNull("onError", e);
            }finally {
                    signal.countDown();
            }
        }

        @Override
        public void onNext(HttpResponse<Map<String, String>> response) {
            System.out.println("onNext Thread " + Thread.currentThread().getName());
            System.out.println("response code " + response.getCode());
            try {
                assertEquals(200, response.getCode());
            }finally {
                    signal.countDown();
            }
        }
    }


    private void test_rxJava(){
        RetrofitHelperTest helperTest = RetrofitHelperTest.getInstance(RetrofitHelperTest.TYPE_RX);
        ExampleServiceTest serviceTest = helperTest.getExampleServiceTest();
        Observable<HttpResponse<Map<String, String>>> observable = serviceTest.rxQueryAPIS();
        final CountDownLatch signal = new CountDownLatch(1);
        System.out.println("Thread " + Thread.currentThread().getName());
        observable.subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.newThread())
                .subscribe(new CallBackSubscriber(signal));
        try {
            signal.await();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
