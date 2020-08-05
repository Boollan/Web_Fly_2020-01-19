package com.boollan.dao;


import com.boollan.domain.L4d2BuyCdk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IL4d2BuyCdkDao {

    /**
     * 查询所有CDK
     *
     * @return
     */
    List<L4d2BuyCdk> findAllL4D2BuyCdk();

    /**
     * 查询某一个cdk
     *
     * @return
     */
    L4d2BuyCdk findCDKByName(String cdk);

    /**
     * 保存：新增一个CDK
     *
     * @param cdkinfo
     */
    void insertCdk(L4d2BuyCdk cdkinfo);

    /**
     * 更新：更新是是否已经使用
     *
     * @param cdkinfo
     */
    void upDateL4d2IsUse(L4d2BuyCdk cdkinfo);
}
