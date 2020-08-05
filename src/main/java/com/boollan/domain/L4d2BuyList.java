package com.boollan.domain;


/**
 * 兔子币商店实体类
 * @author Boolan
 */
public class L4d2BuyList {

    private Integer id;
    private String steamid;
    private String currency;
    private String intime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    @Override
    public String toString() {
        return "l4d2BuyList{" +
                "id=" + id +
                ", steamid='" + steamid + '\'' +
                ", currency='" + currency + '\'' +
                ", intime='" + intime + '\'' +
                '}';
    }
}
