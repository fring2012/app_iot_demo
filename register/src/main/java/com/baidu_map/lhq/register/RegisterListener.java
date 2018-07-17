package com.baidu_map.lhq.register;

public interface RegisterListener {
    void onStart();
    void onFinishRegister();
    void onFail(String errorInfo);
}
