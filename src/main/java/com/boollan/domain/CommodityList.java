package com.boollan.domain;

/**
 * @author Boollan
 */
public class CommodityList {

    private Integer id;
    private String productUuid;
    private String productName;
    private String productMoney;
    private String productTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(String productUuid) {
        this.productUuid = productUuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(String productMoney) {
        this.productMoney = productMoney;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    @Override
    public String toString() {
        return "CommodityList{" +
                "id=" + id +
                ", productUuid='" + productUuid + '\'' +
                ", productName='" + productName + '\'' +
                ", productMoney='" + productMoney + '\'' +
                ", productTime='" + productTime + '\'' +
                '}';
    }
}
