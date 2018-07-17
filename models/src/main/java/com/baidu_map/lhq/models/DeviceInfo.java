package com.baidu_map.lhq.models;

public class DeviceInfo {
    private String mid;
    private String oem;
    private String models;
    private String platform;
    private String deviceType;
    private String version;

    private String deviceId;
    private String deviceSecret;

    private static DeviceInfo mInstance;

    private DeviceInfo(){

    }

    public static DeviceInfo getInstance(){
        if (mInstance == null){
            synchronized (DeviceInfo.class){
                if (mInstance == null){
                    mInstance = new DeviceInfo();
                }
            }
        }
        return mInstance;
    }

    public void init(){

        mid = "4325";
        deviceType = "phone";
        models = "MI3C";
        oem = "212";
        platform = "BRCM23550";
        version = "0.08";

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }
}
