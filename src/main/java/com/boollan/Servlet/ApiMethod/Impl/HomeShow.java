package com.boollan.Servlet.ApiMethod.Impl;

import com.boollan.Servlet.ApiMethod.IHomeShow;
import com.boollan.domain.home_show;
import com.boollan.service.IHomeShowService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HomeShow implements IHomeShow {


    private IHomeShowService homeShowService;

    public void setHomeShowService(IHomeShowService homeShowService) {
        this.homeShowService = homeShowService;
    }



    @Override
    public Map<String, Object> GetInfoData() {
        try {

            home_show infoByHome = homeShowService.findInfoByHome();
            if (infoByHome != null) {
                String homeTite = infoByHome.getHometite(); //标题
                String HomeText = infoByHome.getHomeText();//内容
                String imge_1 = infoByHome.getImge_1();//播放栏图片1
                String imge_2 = infoByHome.getImge_2();//播放栏图片1
                String imge_3 = infoByHome.getImge_3();//播放栏图片1
                Date updata_time = infoByHome.getUpdata_time();//更新时间

                Map<String,Object> map = new HashMap<>();

                map.put("homeTite", homeTite);
                map.put("HomeText", HomeText);
                map.put("imge_1", imge_1);
                map.put("imge_2", imge_2);
                map.put("imge_3", imge_3);
                map.put("updata_time", updata_time);



                return map;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void SetInfoData(home_show homeShowData) {
        homeShowService.updateHomeShow(homeShowData);
    }
}
