package com.baidu_map.lhq.register.retrofitservice;



import com.baidu_map.lhq.models.RequestParams;
import com.baidu_map.lhq.models.RetrofitFactory;
import com.baidu_map.lhq.models.ReturnMsg;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RegisterService {
    @POST("/register/{productId}")
    Call<ReturnMsg> registerDevice(@Path("productId") String productId, @Body RequestParams params);

    @POST("/register/{productId}")
    Observable<ReturnMsg> rxjavaRegisterDevice(@Path("productId") String productId, @Body RequestParams params);

    class Factory{
        public static RegisterService create(){
            return RetrofitFactory.createRetrofit().create(RegisterService.class);
        }
    }
}
