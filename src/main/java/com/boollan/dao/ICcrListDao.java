package com.boollan.dao;


import com.boollan.domain.ccr_list;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boollan
 */
@Service
public interface ICcrListDao {

    /**
     * 查询所有CDK
     *
     * @return 返回全部地图数据
     */
    List<ccr_list> findAllCcrList();


    /**
     * 查询某一个cdk
     * @param mapName 地图名称
     * @return 返回的记录结果
     */
    ccr_list findMapByName(String mapName);


    /**
     * 更新记录数据
     * @param ccrList 新的记录数据
     */
    void updateMapCcrCancel(ccr_list ccrList);

}
