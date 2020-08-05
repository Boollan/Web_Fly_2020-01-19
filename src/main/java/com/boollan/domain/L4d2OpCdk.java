package com.boollan.domain;

/**
 * @author Boolan
 */
public class L4d2OpCdk {
    private Integer id;
    private String cdk;
    private String datetime;
    private Integer isuse;
    @Override
    public String toString() {
        return "T_L4d2Opcdk{" +
                "id=" + id +
                ", cdk='" + cdk + '\'' +
                ", datetime='" + datetime + '\'' +
                ", isuse=" + isuse +
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }


}
