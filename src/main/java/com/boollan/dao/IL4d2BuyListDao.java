package com.boollan.dao;


import com.boollan.domain.L4d2BuyList;
import com.boollan.domain.ccr_list;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boollan
 */
@Service
public interface IL4d2BuyListDao {

    /**
     * 查询玩家信息
     * @return 返回全部玩家数据
     */
    List<L4d2BuyList> findAllBuyList();


    /**
     * 查询某一个玩家信息
     * @param steamid
     * @return 返回的记录结果
     */
    L4d2BuyList findMapBySteamId(String steamid);



    /**
     * 更新记录
     * @param buyList 新的数据
     */
    void insertMapBuyBuyInfo(L4d2BuyList buyList);

    /**
     * 更新记录
     * @param buyList 新的数据
     */
    void updateMapBuySteamId(L4d2BuyList buyList);

}
