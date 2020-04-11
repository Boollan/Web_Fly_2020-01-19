package com.boollan.service;


import com.boollan.domain.home_show;
import org.springframework.stereotype.Service;


public interface IHomeShowService {

    /**
     * 查询查询查询查询
     *
     * @return
     */
    home_show findInfoByHome();

    /**
     * 更新
     *
     * @param account
     */
    void updateHomeShow(home_show account);

}
