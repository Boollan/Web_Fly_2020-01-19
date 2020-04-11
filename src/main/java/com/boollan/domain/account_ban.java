package com.boollan.domain;

import java.util.Date;

/**
 * 用户封禁实体类
 */
public class account_ban {

    private Integer id;
    private String accout_ban;
    private Date ban_time;
    private Integer is_ban;

    @Override
    public String toString() {
        return "account_ban{" +
                "id=" + id +
                ", accout_ban='" + accout_ban + '\'' +
                ", ban_time=" + ban_time +
                ", is_ban=" + is_ban +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccout_ban() {
        return accout_ban;
    }

    public void setAccout_ban(String accout_ban) {
        this.accout_ban = accout_ban;
    }

    public Date getBan_time() {
        return ban_time;
    }

    public void setBan_time(Date ban_time) {
        this.ban_time = ban_time;
    }

    public Integer getIs_ban() {
        return is_ban;
    }

    public void setIs_ban(Integer is_ban) {
        this.is_ban = is_ban;
    }
}
