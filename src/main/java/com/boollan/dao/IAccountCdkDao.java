package com.boollan.dao;

import com.boollan.domain.account_cdk;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Boollan
 */
@Service
public interface IAccountCdkDao {
    /**
     * 查询所有CDK(包括已经使用的)
     *
     * @return
     */
    List<account_cdk> findAllCdkInfo();


    /**
     * @param cdk 输入要查询的CDK
     * @return true
     */
    account_cdk finCdkInfoByCdk(String cdk);

    /**
     * 保存:添加一张CDK
     *
     * @param cdkDao 获取CDK数据
     */
    void insertCdkInfo(account_cdk cdkDao);

    /**
     * 更新:更新CDK状态(已使用或未使用)
     *
     * @param account 获取用户CDK信息
     */
    void updateCdkInfo(account_cdk account);

}
