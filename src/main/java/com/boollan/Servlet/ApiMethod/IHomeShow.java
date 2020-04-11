package com.boollan.Servlet.ApiMethod;

import com.boollan.domain.home_show;
import org.json.simple.JSONObject;

import java.util.Map;

public interface IHomeShow {

    /**
     * 获取信息
     *
     * @return
     */
    Map<String,Object> GetInfoData();

    /**
     * 更改首页信息数据
     *
     * @param homeShowData
     */
    void SetInfoData(home_show homeShowData);

}
