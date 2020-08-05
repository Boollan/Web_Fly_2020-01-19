package com.boollan.domain;



/**
 * @author Boollan
 */
public class user_info {

    private Integer id;
    private String uid;
    private String username;
    private String password;
    private String email;
    private String root;
    private String steamid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }
    public String getSteamid() {
        return steamid;
    }


    @Override
    public String toString() {
        return "user_info{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", root='" + root + '\'' +
                ", steamid='" + steamid + '\'' +
                '}';
    }
}
