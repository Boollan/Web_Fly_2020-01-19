package com.boollan.dao;


import com.boollan.domain.L4d2OpList;
import com.boollan.domain.L4d2VipList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IL4d2VipListDao {

    /**
     * 查询全部OP玩家
     *
     * @return
     */
    List<L4d2VipList> findAllL4D2VipList();

    /**
     * 查询某一个OP玩家
     *
     * @return
     */
    L4d2VipList findVipBySteamId32(String SteamId32);

    /**
     * 保存：新增一个OP玩家
     *
     * @param OpInfo
     */
    void insertVipInfo(L4d2VipList OpInfo);

    /**
     * 更新：更新一个OP玩家信息
     *
     * @param OpInfo
     */
    void upDateL4d2VipListInfo(L4d2VipList OpInfo);
}
