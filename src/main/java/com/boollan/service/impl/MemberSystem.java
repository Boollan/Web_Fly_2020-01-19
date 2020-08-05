package com.boollan.service.impl;

import com.boollan.dao.*;
import com.boollan.domain.*;
import com.boollan.service.IMemberSystem;
import com.boollan.util.module.HttpSteamInfo;

import java.io.IOException;

/**
 * @author Boollan
 */
public class MemberSystem implements IMemberSystem {


    private IL4d2VipCdkDao il4d2VipCdkDao;

    private IL4d2VipListDao il4d2VipListDao;

    private IL4d2OpCdkDao il4d2OpCdkDao;

    private IL4d2OpListDao il4d2OpListDao;

    private IL4d2BuyCdkDao il4d2BuyCdkDao;

    private IL4d2BuyListDao il4d2BuyListDao;

    public void setIl4d2OpCdkDao(IL4d2OpCdkDao il4d2OpCdkDao) {
        this.il4d2OpCdkDao = il4d2OpCdkDao;
    }

    public void setIl4d2OpListDao(IL4d2OpListDao il4d2OpListDao) {
        this.il4d2OpListDao = il4d2OpListDao;
    }

    public void setIl4d2VipCdkDao(IL4d2VipCdkDao il4d2VipCdkDao) {
        this.il4d2VipCdkDao = il4d2VipCdkDao;
    }

    public void setIl4d2VipListDao(IL4d2VipListDao il4d2VipListDao) {
        this.il4d2VipListDao = il4d2VipListDao;
    }

    public void setIl4d2BuyListDao(IL4d2BuyListDao il4d2BuyListDao) {
        this.il4d2BuyListDao = il4d2BuyListDao;
    }

    public void setIl4d2BuyCdkDao(IL4d2BuyCdkDao il4d2BuyCdkDao) {
        this.il4d2BuyCdkDao = il4d2BuyCdkDao;
    }

    @Override
    public boolean memberOpExchange(String steamid32, String cdk) throws IOException {

        //非空验证
        if (steamid32 != null && cdk != null) {
            L4d2OpCdk cdkByName = il4d2OpCdkDao.findCDKByName(cdk);
            //判断内容是否为空 或 是否已经使用
            if (cdkByName != null && cdkByName.getIsuse() == 0) {
                //查询当前用户
                L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(steamid32.trim());
                //获取卡密中的时间
                long datetime = Integer.parseInt(cdkByName.getDatetime());
                //当前时间（秒）
                long time = System.currentTimeMillis() / 1000;
                //通过Http接口获取数据
                HttpSteamInfo steamInfo = new HttpSteamInfo();
                //判断数据库是否有该用户
                if (opBySteamId32 != null) {
                    //玩家当前时间
                    long datelined = Integer.parseInt(opBySteamId32.getDatetimeov());
                    //判断当前OP用户是否已经过期
                    if (datelined > time) {

                        //当前时间 + CDK固定值 （秒）
                        String dataResult = Long.toString((datelined + datetime));

                        //给已经存在OP叠加时间
                        opBySteamId32.setDatetimeov(dataResult);
                        opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32.trim()));
                        il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);

                        //更改卡密为以使用
                        cdkByName.setIsuse(1);
                        il4d2OpCdkDao.upDateL4d2IsUse(cdkByName);

                        return true;
                    } else {

                        //当前时间 + CDK固定值 （秒）
                        String dataResult = Long.toString((time + datetime));

                        //玩家OP已过期直接覆盖不需要叠加
                        opBySteamId32.setDatetimeov(dataResult);
                        opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32.trim()));
                        il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);

                        //更改卡密为以使用
                        cdkByName.setIsuse(1);
                        il4d2OpCdkDao.upDateL4d2IsUse(cdkByName);

                        return true;
                    }
                } else {


                    //没有改OP玩家 创建一个OP玩家 并且给与时间
                    L4d2OpList newOpUser = new L4d2OpList();
                    newOpUser.setSteamid32(steamid32.trim());

                    newOpUser.setGamename(steamInfo.getHttpSteamIdToName(steamid32.trim()));
                    newOpUser.setDatetimeov(Long.toString((time + datetime)));
                    il4d2OpListDao.insertOpInfo(newOpUser);

                    //更改卡密为以使用
                    cdkByName.setIsuse(1);
                    il4d2OpCdkDao.upDateL4d2IsUse(cdkByName);

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean memberVipExchange(String steamid32, String cdk) throws IOException {
        //非空验证
        if (steamid32 != null && cdk != null) {
            L4d2VipCdk cdkByName = il4d2VipCdkDao.findCDKByName(cdk);
            //判断内容是否为空 或 是否已经使用
            if (cdkByName != null && cdkByName.getIsuse() == 0) {
                //查询当前用户
                L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(steamid32.trim());
                //获取卡密中的时间
                long datetime = Integer.parseInt(cdkByName.getDatetime());
                //当前时间（秒）
                long time = System.currentTimeMillis() / 1000;
                //通过Http接口获取数据
                HttpSteamInfo steamInfo = new HttpSteamInfo();
                //判断数据库是否有该用户
                if (vipBySteamId32 != null) {
                    //玩家当前时间
                    long datelined = Integer.parseInt(vipBySteamId32.getDatetimeov());
                    //判断当前VIP用户是否已经过期
                    if (datelined > time) {

                        //当前时间 + CDK固定值 （秒）
                        String dataResult = Long.toString((datelined + datetime));

                        //给已经存在VIP叠加时间
                        vipBySteamId32.setDatetimeov(dataResult);
                        vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32.trim()));
                        il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);

                        //更改卡密为以使用
                        cdkByName.setIsuse(1);
                        il4d2VipCdkDao.upDateL4d2IsUse(cdkByName);

                        return true;
                    } else {
                        //当前时间 + CDK固定值 （秒）
                        String dataResult = Long.toString((time + datetime));

                        //玩家VIP已过期直接覆盖不需要叠加
                        vipBySteamId32.setDatetimeov(dataResult);
                        vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32.trim()));
                        il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);

                        //更改卡密为以使用
                        cdkByName.setIsuse(1);
                        il4d2VipCdkDao.upDateL4d2IsUse(cdkByName);

                        return true;
                    }
                } else {
                    //没有改VIP玩家 创建一个Vip玩家 并且给与时间
                    L4d2VipList newVipUser = new L4d2VipList();
                    newVipUser.setSteamid32(steamid32.trim());
                    newVipUser.setGamename(steamInfo.getHttpSteamIdToName(steamid32.trim()));
                    newVipUser.setDatetimeov(Long.toString((time + datetime)));
                    il4d2VipListDao.insertVipInfo(newVipUser);

                    //更改卡密为以使用
                    cdkByName.setIsuse(1);
                    il4d2VipCdkDao.upDateL4d2IsUse(cdkByName);

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean memberBuyExchange(String steamid32, String cdk) {
        //非空验证
        if (steamid32 != null && cdk != null) {
            L4d2BuyCdk cdkByName = il4d2BuyCdkDao.findCDKByName(cdk);
            //判断内容是否为空 或 是否已经使用
            if (cdkByName != null && cdkByName.getIsuse() == 0) {
                //查询当前用户
                L4d2BuyList buyBySteamId32 = il4d2BuyListDao.findMapBySteamId(steamid32.trim());
                //获取卡密中的兔子币
                int currency = Integer.parseInt(cdkByName.getCurrency());
                //判断数据库是否有该用户
                if (buyBySteamId32 != null) {
                    //玩家当前兔子币数量
                    int currencyLined = Integer.parseInt(buyBySteamId32.getCurrency());
                    //当前兔子币 + CDK固定值兔子币
                    String currencyResult = Integer.toString((currencyLined + currency));
                    //给已经存在VIP叠加时间
                    buyBySteamId32.setCurrency(currencyResult);
                    il4d2BuyListDao.updateMapBuySteamId(buyBySteamId32);
                    //更改卡密为以使用
                    cdkByName.setIsuse(1);
                    il4d2BuyCdkDao.upDateL4d2IsUse(cdkByName);
                    return true;
                } else {
                    //没有改兔子币玩家 创建一个兔子币商店玩家 并且给与时间
                    L4d2BuyList newBuyUser = new L4d2BuyList();
                    newBuyUser.setSteamid(steamid32.trim());
                    newBuyUser.setCurrency(Integer.toString(currency));
                    il4d2BuyListDao.insertMapBuyBuyInfo(newBuyUser);
                    //更改卡密为以使用
                    cdkByName.setIsuse(1);
                    il4d2BuyCdkDao.upDateL4d2IsUse(cdkByName);

                    return true;
                }
            }
        }
        return false;
    }

}
