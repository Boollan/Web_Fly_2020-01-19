package com.boollan.dao;


import com.boollan.domain.L4d2OpCdk;
import com.boollan.domain.L4d2VipCdk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IL4d2VipCdkDao {

    /**
     * 查询所有CDK
     *
     * @return
     */
    List<L4d2VipCdk> findAllL4D2VipCdk();

    /**
     * 查询某一个cdk
     *
     * @return
     */
    L4d2VipCdk findCDKByName(String cdk);

    /**
     * 保存：新增一个CDK
     *
     * @param cdkinfo
     */
    void insertCdk(L4d2VipCdk cdkinfo);

    /**
     * 更新：更新是是否已经使用
     *
     * @param cdkinfo
     */
    void upDateL4d2IsUse(L4d2VipCdk cdkinfo);
}
