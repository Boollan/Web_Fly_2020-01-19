package com.boollan.domain;

import java.util.Date;

/**
 * 用户登录记录实体类
 */
public class login_record {
    private Integer id;
    private String username;
    private String addip;
    private String client;
    private Date datetime;

    @Override
    public String toString() {
        return "login_record{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", addip='" + addip + '\'' +
                ", client='" + client + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
