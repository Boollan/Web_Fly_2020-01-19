package com.boollan.service;

import java.io.IOException;

/**
 * 此类用于服务用户兑换
 *
 * @author Boollan
 */
public interface IMemberSystem {

    /**
     * 使用CDK兑换OP
     *
     * @param steamid32 用户SteamID
     * @param cdk       用户购买的CDK
     * @return 是否成功兑换
     * @throws IOException 解决异常
     */
    boolean memberOpExchange(String steamid32, String cdk) throws IOException;

    /**
     * 使用CDK兑换Vip
     *
     * @param steamid32 用户SteamID
     * @param cdk       用户购买的CDK
     * @return 是否成功兑换
     * @throws IOException 解决异常
     */
    boolean memberVipExchange(String steamid32, String cdk) throws IOException;

    /**
     * 使用CDK兑换兔子币
     *
     * @param steamid32 用户SteamID
     * @param cdk       用户购买的CDK
     * @return 是否成功兑换
     * @throws IOException 解决异常
     */
    boolean memberBuyExchange(String steamid32, String cdk) throws IOException;


}
