package com.boollan.domain;

/**
 * @author Boolan
 */
public class L4d2VipList {
    private Integer id;
    private String steamid32;
    private String gamename;

    @Override
    public String toString() {
        return "T_oplist{" +
                "id=" + id +
                ", steamid32='" + steamid32 + '\'' +
                ", gamename='" + gamename + '\'' +
                ", datetimeov='" + datetimeov + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSteamid32() {
        return steamid32;
    }

    public void setSteamid32(String steamid32) {
        this.steamid32 = steamid32;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getDatetimeov() {
        return datetimeov;
    }

    public void setDatetimeov(String datetimeov) {
        this.datetimeov = datetimeov;
    }

    private String datetimeov;
}
