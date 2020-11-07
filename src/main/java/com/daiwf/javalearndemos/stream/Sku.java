package com.daiwf.javalearndemos.stream;

/**
 * @version [版本号，2020-11-7]
 * @文件名 Sku
 * @作者 daiwf
 * @创建时间 2020-11-7 16:05
 * @版权 Copyright daiwf. All Rights Reserved.
 * @描述 [商品类]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Sku {
    private Integer skuId;
    private  String skuName;
    private Double skuPrice;
    private  Integer totalNum;
    private Double totalPrice;
    private Enum skuCategory;
    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Double skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Enum getSkuCategory() {
        return skuCategory;
    }

    public void setSkuCategory(Enum skuCategory) {
        this.skuCategory = skuCategory;
    }

    public Sku(Integer skuId, String skuName, Double skuPrice, Integer totalNum, Double totalPrice, Enum skuCategory) {
        this.skuId = skuId;
        this.skuName = skuName;
        this.skuPrice = skuPrice;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
        this.skuCategory = skuCategory;
    }
}
