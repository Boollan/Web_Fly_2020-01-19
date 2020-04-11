package com.boollan.Servlet.ApiMethod;
import java.util.Date;
import	java.util.List;

import com.boollan.domain.account_cdk;
import com.boollan.util.module.RandomNumber;

import java.util.Map;

public interface IAccountCdk {
    /**
     * CDK信息获取
     *
     * @param cdkname Key的序列号
     * @return
     */
    Map<String,Object> cdkInfo(String cdkname);

    /**
     * 使用后Key信息验证
     *
     * @param KeyInfo
     * @return
     */
    boolean verifKey(account_cdk KeyInfo);

    /**
     * 生成CDK
     * @param number 数量
     * @param money 金额
     * @param endTime 过期时间
     * @return 数据
     */
    List<Map<String, String>> sendCdk(int number, double money, Date endTime);

    /**
     * 移除KEy的有效性
     *
     * @param KeyInfo
     * @return
     */
    boolean deleKeyValid(account_cdk KeyInfo);


}
