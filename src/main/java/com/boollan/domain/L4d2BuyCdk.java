package com.boollan.domain;

/**
 * @author Boolan
 */
public class L4d2BuyCdk {
    private Integer id;
    private String cdk;
    private String currency;
    private Integer isuse;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    @Override
    public String toString() {
        return "L4d2BuyCdk{" +
                "id=" + id +
                ", cdk='" + cdk + '\'' +
                ", currency='" + currency + '\'' +
                ", isuse=" + isuse +
                '}';
    }
}
