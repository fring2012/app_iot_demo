package com.baidu_map.lhq.models;

public class Product {
    private String productId;
    private String productSecret;

    private static Product mInstance;

    private Product(){

    }

    public static Product getInstance(){
        if (mInstance == null){
            synchronized (Product.class){
                if (mInstance == null){
                    mInstance = new Product();
                }
            }
        }
        return mInstance;
    }
    public Product init(){
        productId = "1525594277";
        productSecret = "c380eaa48f0248f9878a14857844d133";
        return this;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductSecret() {
        return productSecret;
    }

    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret;
    }
}
