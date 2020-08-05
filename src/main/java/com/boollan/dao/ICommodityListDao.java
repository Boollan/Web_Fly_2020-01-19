package com.boollan.dao;


import com.boollan.domain.CommodityList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boollan
 */
@Service
public interface ICommodityListDao {


    /**
     * 查询全部商品信息
     * @return 商品信息集合
     */
    List<CommodityList> findAllProductList();


    /**
     * 通关商品ID查询信息
     * @param productId 商品ID
     * @return 商品信息
     */
    CommodityList findProductByList(String productId);

}
