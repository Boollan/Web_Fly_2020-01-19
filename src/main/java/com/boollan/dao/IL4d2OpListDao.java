package com.boollan.dao;


import com.boollan.domain.L4d2OpList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IL4d2OpListDao {

    /**
     * 查询全部OP玩家
     *
     * @return
     */
    List<L4d2OpList> findAllL4D2OpList();

    /**
     * 查询某一个OP玩家
     *
     * @return
     */
    L4d2OpList findOpBySteamId32(String SteamId32);

    /**
     * 保存：新增一个OP玩家
     *
     * @param OpInfo
     */
    void insertOpInfo(L4d2OpList OpInfo);

    /**
     * 更新：更新一个OP玩家信息
     *
     * @param OpInfo
     */
    void upDateL4d2OpListInfo(L4d2OpList OpInfo);
}
