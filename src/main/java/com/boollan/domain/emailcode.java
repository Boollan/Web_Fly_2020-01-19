package com.boollan.domain;

import java.util.Date;

/**
 * 邮箱验证码实体类
 */
public class emailcode {
    private Integer id;
    private String email;
    private Integer isuse;
    private String code;
    private Date effective;

    @Override
    public String toString() {
        return "emailcode{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", isuse=" + isuse +
                ", code='" + code + '\'' +
                ", effective=" + effective +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEffective() {
        return effective;
    }

    public void setEffective(Date effective) {
        this.effective = effective;
    }
}
