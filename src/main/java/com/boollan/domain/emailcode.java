package com.boollan.domain;



/**
 * @author Boollan
 */
public class emailcode {

    private Integer id;
    private String email;
    private String isuse;
    private String code;
    private String effective;

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

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    @Override
    public String toString() {
        return "emailcode{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", isuse='" + isuse + '\'' +
                ", code='" + code + '\'' +
                ", effective='" + effective + '\'' +
                '}';
    }
}
