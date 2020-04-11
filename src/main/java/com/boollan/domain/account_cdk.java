package com.boollan.domain;

import java.util.Date;

/**
 * 用户CDK实体类
 */
public class account_cdk {
    private Integer id;
    private String cdk;
    private Double money;
    private Integer effective;
    private Date overduetime;

    @Override
    public String toString() {
        return "account_cdk{" +
                "id=" + id +
                ", cdk='" + cdk + '\'' +
                ", money=" + money +
                ", effective=" + effective +
                ", overduetime=" + overduetime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCdk() {
        return cdk;
    }

    public void setCdk(String cdk) {
        this.cdk = cdk;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    public Date getOverduetime() {
        return overduetime;
    }

    public void setOverduetime(Date overduetime) {
        this.overduetime = overduetime;
    }
}
