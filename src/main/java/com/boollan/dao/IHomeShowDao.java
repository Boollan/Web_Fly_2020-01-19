package com.boollan.dao;


import com.boollan.domain.home_show;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IHomeShowDao {


    /**
     * 获取首页信息更新
     * @return 获取首页信息内容
     */
    List<home_show> findInfoByHome();

    /**
     * 更新首页信息内容
     * @param homeShowInfoBy 获取信息内容
     */
    void updateHomeShow(home_show homeShowInfoBy);


}
