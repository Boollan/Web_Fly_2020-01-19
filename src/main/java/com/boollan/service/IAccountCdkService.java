package com.boollan.service;

import com.boollan.domain.account_cdk;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAccountCdkService {

    /**
     * 查询所有CDK(包括已经使用的)
     *
     * @return
     */
    List<account_cdk> findAllCdkInfo();

    /**
     * 查询一个CDK
     *
     * @return
     */
    account_cdk finCdkInfoByCdk(String cdk);

    /**
     * 保存:添加一张CDK
     *
     * @param account_cdk
     */
    void insertCdkInfo(account_cdk account_cdk);

    /**
     * 更新:更新CDK状态(已使用或未使用)
     *
     * @param account_cdk
     */
    void updateCdkInfo(account_cdk account_cdk);

}
