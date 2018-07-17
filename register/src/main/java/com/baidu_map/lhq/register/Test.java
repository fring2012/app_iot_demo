package com.baidu_map.lhq.register;


import com.baidu_map.lhq.models.Product;
import com.baidu_map.lhq.models.RequestParams;
import com.baidu_map.lhq.models.ReturnMsg;
import com.baidu_map.lhq.register.retrofitservice.RegisterService;
import com.google.gson.Gson;

import java.io.IOException;


import io.reactivex.Observable;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Test {
    public static void main(String[] args) {
       Test test = new Test();
        test.rxJavaRetrofit();

    }
    public void rxJava(){
        final RegisterService rs = RegisterService.Factory.create();
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                System.out.println("Observable:" + Thread.currentThread().getName());
                e.onNext("s");
            }
        }).flatMap(new Function<Object, ObservableSource<ReturnMsg>>() {
              @Override
              public ObservableSource<ReturnMsg> apply(Object o) throws Exception {
                  System.out.println(o);
                  return rs.rxjavaRegisterDevice(Product.getInstance().init().getProductId(),
                          new RequestParams.Factory().create()).subscribeOn(Schedulers.io());
              }
          }).subscribe(new Consumer<ReturnMsg>() {
            @Override
            public void accept(ReturnMsg returnMsg) throws Exception {
                System.out.println("Consumer:" + Thread.currentThread().getName());
            }
        });

    }

    public void rxJavaRetrofit(){
        RegisterService rs = RegisterService.Factory.create();
        Observable<ReturnMsg> observable = rs.rxjavaRegisterDevice(Product.getInstance().init().getProductId(),
                new RequestParams.Factory().create());

        observable.observeOn(Schedulers.io())
                .subscribe(new Consumer<ReturnMsg>() {
                    @Override
                    public void accept(ReturnMsg returnMsg) throws Exception {
                        System.out.println(returnMsg.getStatus());
                        System.out.println(Thread.currentThread().getName());
                    }
                });
    }
    public void retrofitHttp(){
        RegisterService rs = RegisterService.Factory.create();
        Call<ReturnMsg> call = rs.registerDevice(Product.getInstance().init().getProductId(),
                new RequestParams.Factory().create());

        call.enqueue(new Callback<ReturnMsg>() {
            @Override
            public void onResponse(Call<ReturnMsg> call, Response<ReturnMsg> response) {


                    System.out.println(response.body().getData().get("deviceSecret"));

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void okHttp(){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        String json = new Gson().toJson(new RequestParams.Factory().create());
        System.out.println(json);
        RequestBody requestBody = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url("https://iotapi.adups.com/register/1525594277")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });


    }

}
