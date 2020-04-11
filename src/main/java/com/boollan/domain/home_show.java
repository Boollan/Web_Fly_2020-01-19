package com.boollan.domain;

import java.util.Date;

/**
 * 首页展示信息实体类
 */
public class home_show {
    private Integer id;
    private String hometite;
    private String HomeText;
    private String imge_1;
    private String imge_2;
    private String imge_3;
    private Date updata_time;

    @Override
    public String toString() {
        return "home_show{" +
                "id=" + id +
                ", hometite='" + hometite + '\'' +
                ", HomeText='" + HomeText + '\'' +
                ", imge_1='" + imge_1 + '\'' +
                ", imge_2='" + imge_2 + '\'' +
                ", imge_3='" + imge_3 + '\'' +
                ", updata_time=" + updata_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHometite() {
        return hometite;
    }

    public void setHometite(String hometite) {
        this.hometite = hometite;
    }

    public String getHomeText() {
        return HomeText;
    }

    public void setHomeText(String homeText) {
        HomeText = homeText;
    }

    public String getImge_1() {
        return imge_1;
    }

    public void setImge_1(String imge_1) {
        this.imge_1 = imge_1;
    }

    public String getImge_2() {
        return imge_2;
    }

    public void setImge_2(String imge_2) {
        this.imge_2 = imge_2;
    }

    public String getImge_3() {
        return imge_3;
    }

    public void setImge_3(String imge_3) {
        this.imge_3 = imge_3;
    }

    public Date getUpdata_time() {
        return updata_time;
    }

    public void setUpdata_time(Date updata_time) {
        this.updata_time = updata_time;
    }
}
