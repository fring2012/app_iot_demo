package com.baidu_map.lhq.models;

import com.baidu_map.lhq.models.Utils.HashUtils;
import com.google.gson.Gson;

import java.util.Date;

public class RequestParams {
    private String mid;
    private String oem;
    private String models;
    private String platform;
    private String deviceType;
    private String version;
    private Long timestamp;
    private String sign;
    private String sdkversion;
    private String appversion;
    private String networkType;

    private RequestParams(){
        init();
    }

    public void init(){
        DeviceInfo.getInstance().init();
        Product.getInstance().init();
        mid = DeviceInfo.getInstance().getMid();
        oem = DeviceInfo.getInstance().getOem();
        models = DeviceInfo.getInstance().getModels();
        platform = DeviceInfo.getInstance().getPlatform();
        deviceType = DeviceInfo.getInstance().getDeviceType();
        version = DeviceInfo.getInstance().getVersion();

        timestamp = new Date().getTime()/1000l;
        sign = HashUtils.getHmacMd5Str(mid + Product.getInstance().getProductId() + timestamp,
                Product.getInstance().getProductSecret());
        sdkversion = "26";
        appversion = "26";
        networkType = "WIFI";
    }

    public static class Factory{
        public static RequestParams create(){
            return new RequestParams();
        }
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSdkversion() {
        return sdkversion;
    }

    public void setSdkversion(String sdkversion) {
        this.sdkversion = sdkversion;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }
}
