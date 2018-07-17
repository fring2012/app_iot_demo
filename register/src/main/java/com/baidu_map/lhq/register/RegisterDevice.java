package com.baidu_map.lhq.register;

import com.baidu_map.lhq.models.DeviceInfo;
import com.baidu_map.lhq.models.Product;
import com.baidu_map.lhq.models.RequestParams;
import com.baidu_map.lhq.models.RetrofitFactory;
import com.baidu_map.lhq.models.ReturnMsg;
import com.baidu_map.lhq.register.retrofitservice.RegisterService;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RegisterDevice {
    private RegisterListener mRegisterListener;
    private RegisterService mRegisterService;

    public RegisterDevice(){
        mRegisterService = RetrofitFactory.createRetrofit().create(RegisterService.class);
    }

    public void registerDeviceAsync(){
        if (null != mRegisterListener) {
            mRegisterListener.onStart();
        }
        chainObservable().observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ReturnMsg>() {
            @Override
            public void accept(ReturnMsg returnMsg) throws Exception {
                if (returnMsg.getStatus() == 1000){
                    if (mRegisterListener != null){
                        mRegisterListener.onFinishRegister();
                    }
                    DeviceInfo.getInstance().setDeviceId(returnMsg.getData().get("deviceId"));
                    DeviceInfo.getInstance().setDeviceSecret(returnMsg.getData().get("deviceSecret"));
                }else {
                    if (mRegisterListener != null){
                        mRegisterListener.onFail(returnMsg.getMsg());
                    }
                }
            }
        });
    }
    /**
     * 同步
     */
    public void registerDeviceSync(){
        chainObservable().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ReturnMsg>() {

            @Override
            public void accept(ReturnMsg returnMsg) throws Exception {
                if (returnMsg.getStatus() == 1000){
                    if (mRegisterListener != null){
                        mRegisterListener.onFinishRegister();
                    }
                    DeviceInfo.getInstance().setDeviceId(returnMsg.getData().get("deviceId"));
                    DeviceInfo.getInstance().setDeviceSecret(returnMsg.getData().get("deviceSecret"));
                }else {
                    if (mRegisterListener != null){
                        mRegisterListener.onFail(returnMsg.getMsg());
                    }
                }
            }
        });
    }

    private Observable chainObservable(){
        return mRegisterService.rxjavaRegisterDevice(Product.getInstance().getProductId()
                , RequestParams.Factory.create());
    }
}
