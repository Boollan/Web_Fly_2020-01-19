package com.boollan.domain;


/**
 * 通关记录排行榜
 * @author Boolan
 */
public class ccr_list {

    private Integer id;
    private String mapname;
    private String clearplay;
    private String playsteamid64;
    private String cleartime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public String getClearplay() {
        return clearplay;
    }

    public void setClearplay(String clearplay) {
        this.clearplay = clearplay;
    }

    public String getPlaysteamid64() {
        return playsteamid64;
    }

    public void setPlaysteamid64(String playsteamid64) {
        this.playsteamid64 = playsteamid64;
    }

    public String getCleartime() {
        return cleartime;
    }

    public void setCleartime(String cleartime) {
        this.cleartime = cleartime;
    }

    @Override
    public String toString() {
        return "ccr_list{" +
                "id=" + id +
                ", mapname='" + mapname + '\'' +
                ", clearplay='" + clearplay + '\'' +
                ", playsteamid64='" + playsteamid64 + '\'' +
                ", cleartime=" + cleartime +
                '}';
    }
}
