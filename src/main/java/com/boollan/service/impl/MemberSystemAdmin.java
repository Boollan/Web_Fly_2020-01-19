package com.boollan.service.impl;
import	java.util.Map;
import	java.util.HashMap;

import com.boollan.dao.*;
import com.boollan.domain.*;
import com.boollan.service.IMemberSystemAdmin;
import com.boollan.service.IUserInformationSystem;
import com.boollan.util.module.HttpSteamInfo;
import com.boollan.util.module.RandomNumber;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author Boollan
 */
public class MemberSystemAdmin implements IMemberSystemAdmin {

    private IL4d2OpCdkDao il4d2OpCdkDao;

    private IL4d2OpListDao il4d2OpListDao;

    private IL4d2VipCdkDao il4d2VipCdkDao;

    private IL4d2VipListDao il4d2VipListDao;

    private IUserInformationSystem userInformationSystem;

    private IL4d2BuyListDao il4D2BuyListDao;

    private IL4d2BuyCdkDao il4d2BuyCdkDao;
    /**
     * 用于读取 adminPassword 密钥
     */
    private String adminPassword;

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

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setUserInformationSystem(IUserInformationSystem userInformationSystem) {
        this.userInformationSystem = userInformationSystem;
    }

    public void setIl4D2BuyListDao(IL4d2BuyListDao il4D2BuyListDao) {
        this.il4D2BuyListDao = il4D2BuyListDao;
    }

    public void setIl4d2BuyCdkDao(IL4d2BuyCdkDao il4d2BuyCdkDao) {
        this.il4d2BuyCdkDao = il4d2BuyCdkDao;
    }

    @Override
    public JSONArray memberSystemAdminCdkGenerateOp(int keynumber, String dataTime, String adminPassword) {

        if (adminPassword.equals(this.adminPassword)) {

            JSONArray list = new JSONArray();
            for (int i = 0; i < keynumber; i++) {
                JSONObject map = new JSONObject();
                String randomCode = "OP" + RandomNumber.getRandomCode(16);
                L4d2OpCdk l4d2OpCdk = new L4d2OpCdk();
                l4d2OpCdk.setCdk(randomCode);
                l4d2OpCdk.setDatetime(dataTime);
                l4d2OpCdk.setIsuse(0);
                il4d2OpCdkDao.insertCdk(l4d2OpCdk);
                map.put("cdk", randomCode);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public JSONArray memberSystemAdminCdkGenerateOp(int keynumber, String dataTime, HttpServletRequest request) {

        if (userInformationSystem.verifyAdminRoot(request)) {
            JSONArray list = new JSONArray();
            for (int i = 0; i < keynumber; i++) {
                JSONObject map = new JSONObject();
                String randomCode = "OP" + RandomNumber.getRandomCode(16);
                L4d2OpCdk l4d2OpCdk = new L4d2OpCdk();
                l4d2OpCdk.setCdk(randomCode);
                l4d2OpCdk.setDatetime(dataTime);
                l4d2OpCdk.setIsuse(0);
                il4d2OpCdkDao.insertCdk(l4d2OpCdk);
                map.put("cdk", randomCode);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public JSONArray memberSystemAdminCdkGenerateVip(int keynumber, String dataTime, String adminPassword) {
        if (adminPassword.equals(this.adminPassword)) {

            JSONArray list = new JSONArray();
            for (int i = 0; i < keynumber; i++) {
                JSONObject map = new JSONObject();
                String randomCode = "VIP" + RandomNumber.getRandomCode(16);
                L4d2VipCdk l4d2VipCdk = new L4d2VipCdk();
                l4d2VipCdk.setCdk(randomCode);
                l4d2VipCdk.setDatetime(dataTime);
                l4d2VipCdk.setIsuse(0);
                il4d2VipCdkDao.insertCdk(l4d2VipCdk);
                map.put("cdk", randomCode);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public JSONArray memberSystemAdminCdkGenerateVip(int keynumber, String dataTime, HttpServletRequest request) {
        if (userInformationSystem.verifyAdminRoot(request)) {
            JSONArray list = new JSONArray();
            for (int i = 0; i < keynumber; i++) {
                JSONObject map = new JSONObject();
                String randomCode = "VIP" + RandomNumber.getRandomCode(16);
                L4d2VipCdk l4d2VipCdk = new L4d2VipCdk();
                l4d2VipCdk.setCdk(randomCode);
                l4d2VipCdk.setDatetime(dataTime);
                l4d2VipCdk.setIsuse(0);
                il4d2VipCdkDao.insertCdk(l4d2VipCdk);
                map.put("cdk", randomCode);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public boolean memberSystemAdminAddOpOrVipTimestamp(String steamid32, String type, String dataTime, String adminPassword) throws IOException {

        if (steamid32 != null && type != null && dataTime != null && adminPassword != null) {

            if (adminPassword.equals(this.adminPassword)) {
                HttpSteamInfo steamInfo = new HttpSteamInfo();
                if ("1".equals(type)) {
                    L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(steamid32);
                    //是否有该玩家OP
                    long dataTimeInt = Long.parseLong(dataTime) + (System.currentTimeMillis() / 1000);
                    if (vipBySteamId32 != null) {
                        //判断OP是否到期
                        if (Integer.parseInt(vipBySteamId32.getDatetimeov()) > (System.currentTimeMillis() / 1000)) {
                            int temp = (Integer.parseInt(vipBySteamId32.getDatetimeov()) + Integer.parseInt(dataTime));
                            vipBySteamId32.setDatetimeov(String.valueOf(temp));
                            vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);

                            return true;

                        } else {
                            vipBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                            vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);
                            return true;
                        }
                    } else {
                        L4d2VipList l4d2VipList = new L4d2VipList();
                        l4d2VipList.setSteamid32(steamid32);
                        l4d2VipList.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                        l4d2VipList.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2VipListDao.insertVipInfo(l4d2VipList);
                        return true;
                    }
                }
                if ("2".equals(type)) {
                    L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(steamid32);
                    //是否有该玩家OP
                    long dataTimeInt = Long.parseLong(dataTime) + (System.currentTimeMillis() / 1000);
                    if (opBySteamId32 != null) {
                        //判断OP是否到期
                        if (Integer.parseInt(opBySteamId32.getDatetimeov()) > (System.currentTimeMillis() / 1000)) {
                            int temp = (Integer.parseInt(opBySteamId32.getDatetimeov()) + Integer.parseInt(dataTime));
                            opBySteamId32.setDatetimeov(String.valueOf(temp));
                            opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);
                            return true;

                        } else {
                            opBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                            opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);
                            return true;
                        }
                    } else {
                        L4d2OpList l4d2OpList = new L4d2OpList();
                        l4d2OpList.setSteamid32(steamid32);
                        l4d2OpList.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                        l4d2OpList.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2OpListDao.insertOpInfo(l4d2OpList);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean memberSystemAdminAddOpOrVipTimestamp(String steamid32, String type, String dataTime, HttpServletRequest request) throws IOException {

        if (steamid32 != null && type != null && dataTime != null && adminPassword != null) {

            if (userInformationSystem.verifyAdminRoot(request)) {
                HttpSteamInfo steamInfo = new HttpSteamInfo();
                if ("1".equals(type)) {
                    L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(steamid32);
                    //是否有该玩家OP
                    long dataTimeInt = Long.parseLong(dataTime) + (System.currentTimeMillis() / 1000);
                    if (vipBySteamId32 != null) {
                        //判断OP是否到期
                        if (Integer.parseInt(vipBySteamId32.getDatetimeov()) > (System.currentTimeMillis() / 1000)) {
                            int temp = (Integer.parseInt(vipBySteamId32.getDatetimeov()) + Integer.parseInt(dataTime));
                            vipBySteamId32.setDatetimeov(String.valueOf(temp));
                            vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);

                            return true;

                        } else {
                            vipBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                            vipBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);
                            return true;
                        }
                    } else {
                        L4d2VipList l4d2VipList = new L4d2VipList();
                        l4d2VipList.setSteamid32(steamid32);
                        l4d2VipList.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                        l4d2VipList.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2VipListDao.insertVipInfo(l4d2VipList);
                        return true;
                    }
                }
                if ("2".equals(type)) {
                    L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(steamid32);
                    //是否有该玩家OP
                    long dataTimeInt = Long.parseLong(dataTime) + (System.currentTimeMillis() / 1000);
                    if (opBySteamId32 != null) {
                        //判断OP是否到期
                        if (Integer.parseInt(opBySteamId32.getDatetimeov()) > (System.currentTimeMillis() / 1000)) {
                            int temp = (Integer.parseInt(opBySteamId32.getDatetimeov()) + Integer.parseInt(dataTime));
                            opBySteamId32.setDatetimeov(String.valueOf(temp));
                            opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);
                            return true;

                        } else {
                            opBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                            opBySteamId32.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                            il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);
                            return true;
                        }
                    } else {
                        L4d2OpList l4d2OpList = new L4d2OpList();
                        l4d2OpList.setSteamid32(steamid32);
                        l4d2OpList.setGamename(steamInfo.getHttpSteamIdToName(steamid32));
                        l4d2OpList.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2OpListDao.insertOpInfo(l4d2OpList);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean memberSystemAdminCancelOpOrVipTimestamp(String steamid32, String type, String adminPassword) {
        if (steamid32 != null && type != null && adminPassword != null) {
            long dataTimeInt = System.currentTimeMillis() / 1000;
            if (adminPassword.equals(this.adminPassword)) {
                if ("1".equals(type)) {
                    L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(steamid32);
                    //是否有该玩家Vip
                    if (vipBySteamId32 != null) {
                        //设置VIP时间为当前时间
                        vipBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);
                        return true;
                    } else {
                        return true;
                    }
                }
                if ("2".equals(type)) {
                    L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(steamid32);
                    //是否有该玩家OP
                    if (opBySteamId32 != null) {
                        opBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);
                        return true;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean memberSystemAdminCancelOpOrVipTimestamp(String steamid32, String type, HttpServletRequest request) {
        if (steamid32 != null && type != null && adminPassword != null) {
            long dataTimeInt = System.currentTimeMillis() / 1000;
            if (userInformationSystem.verifyAdminRoot(request)) {
                if ("1".equals(type)) {
                    L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(steamid32);
                    //是否有该玩家Vip
                    if (vipBySteamId32 != null) {
                        //设置VIP时间为当前时间
                        vipBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2VipListDao.upDateL4d2VipListInfo(vipBySteamId32);
                        return true;
                    } else {
                        return true;
                    }
                }
                if ("2".equals(type)) {
                    L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(steamid32);
                    //是否有该玩家OP
                    if (opBySteamId32 != null) {
                        opBySteamId32.setDatetimeov(Long.toString(dataTimeInt));
                        il4d2OpListDao.upDateL4d2OpListInfo(opBySteamId32);
                        return true;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public JSONArray memberSystemAdminSelectOpOrVipList(String type, String adminPassword) {
        if (type != null && adminPassword != null) {
            if (adminPassword.equals(this.adminPassword)) {
                JSONArray json = new JSONArray();
                if ("1".equals(type)) {

                    List<L4d2VipList> allL4D2VipList = il4d2VipListDao.findAllL4D2VipList();
                    long time = System.currentTimeMillis() / 1000;
                    for (L4d2VipList field : allL4D2VipList) {

                        if (Long.parseLong(field.getDatetimeov()) > time) {
                            JSONObject jsonObject = new JSONObject();
                            Long remaining = Long.parseLong(field.getDatetimeov()) - time;

                            String showtime = remaining / (24 * 3600) + "天" + remaining % (24 * 3600) / 3600 + "小时" + remaining % 3600 / 60 + "分钟";
                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        } else {
                            JSONObject jsonObject = new JSONObject();


                            String showtime = "0天0小时0分钟";
                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        }

                    }
                    return json;
                }
                if ("2".equals(type)) {
                    List<L4d2OpList> allL4D2OpList = il4d2OpListDao.findAllL4D2OpList();
                    long time = System.currentTimeMillis() / 1000;
                    for (L4d2OpList field : allL4D2OpList) {
                        if (Long.parseLong(field.getDatetimeov()) > time) {
                            JSONObject jsonObject = new JSONObject();
                            Long remaining = Long.parseLong(field.getDatetimeov()) - time;
                            String showtime = remaining / (24 * 3600) + "天" + remaining % (24 * 3600) / 3600 + "小时" + remaining % 3600 / 60 + "分钟";

                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        } else {
                            JSONObject jsonObject = new JSONObject();
                            String showtime = "0天0小时0分钟";
                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        }

                    }
                    return json;
                }
            }
        }
        return null;
    }

    @Override
    public JSONArray memberSystemAdminSelectOpOrVipList(String type, HttpServletRequest request) {
        if (type != null && adminPassword != null) {
            if (userInformationSystem.verifyAdminRoot(request)) {
                JSONArray json = new JSONArray();
                if ("1".equals(type)) {

                    List<L4d2VipList> allL4D2VipList = il4d2VipListDao.findAllL4D2VipList();
                    long time = System.currentTimeMillis() / 1000;
                    for (L4d2VipList field : allL4D2VipList) {

                        if (Long.parseLong(field.getDatetimeov()) > time) {
                            JSONObject jsonObject = new JSONObject();
                            Long remaining = Long.parseLong(field.getDatetimeov()) - time;

                            String showtime = remaining / (24 * 3600) + "天" + remaining % (24 * 3600) / 3600 + "小时" + remaining % 3600 / 60 + "分钟";
                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        } else {
                            JSONObject jsonObject = new JSONObject();


                            String showtime = "0天0小时0分钟";
                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        }

                    }
                    return json;
                }
                if ("2".equals(type)) {
                    List<L4d2OpList> allL4D2OpList = il4d2OpListDao.findAllL4D2OpList();
                    long time = System.currentTimeMillis() / 1000;
                    for (L4d2OpList field : allL4D2OpList) {
                        if (Long.parseLong(field.getDatetimeov()) > time) {
                            JSONObject jsonObject = new JSONObject();
                            Long remaining = Long.parseLong(field.getDatetimeov()) - time;
                            String showtime = remaining / (24 * 3600) + "天" + remaining % (24 * 3600) / 3600 + "小时" + remaining % 3600 / 60 + "分钟";

                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        } else {
                            JSONObject jsonObject = new JSONObject();
                            String showtime = "0天0小时0分钟";
                            jsonObject.put("ID", field.getId());
                            jsonObject.put("Steamid32", field.getSteamid32());
                            jsonObject.put("Gamename", field.getGamename());
                            jsonObject.put("Datetimeov", showtime);
                            json.add(jsonObject);
                        }

                    }
                    return json;
                }
            }
        }
        return null;
    }

    @Override
    public boolean memberSystemAdminAddBuy(String steamid32, String currency, String adminPassword) {

        if (adminPassword.equals(this.adminPassword)) {
            L4d2BuyList mapBySteamId = il4D2BuyListDao.findMapBySteamId(steamid32);
            if (mapBySteamId != null) {
                mapBySteamId.setCurrency(String.valueOf(Integer.parseInt(mapBySteamId.getCurrency())+Integer.parseInt(currency)));
                il4D2BuyListDao.updateMapBuySteamId(mapBySteamId);
                return true;
            }else {
                L4d2BuyList l = new L4d2BuyList();
                l.setSteamid(steamid32.trim());
                l.setCurrency(currency);
                l.setIntime("0");
                il4D2BuyListDao.insertMapBuyBuyInfo(l);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean memberSystemAdminAddBuy(String steamid32, String currency, HttpServletRequest request) {
        if (userInformationSystem.verifyAdminRoot(request)) {
            L4d2BuyList mapBySteamId = il4D2BuyListDao.findMapBySteamId(steamid32);
            if (mapBySteamId != null) {
                mapBySteamId.setCurrency(String.valueOf(Integer.parseInt(mapBySteamId.getCurrency())+Integer.parseInt(currency)));
                il4D2BuyListDao.updateMapBuySteamId(mapBySteamId);
                return true;
            }else {
                L4d2BuyList l = new L4d2BuyList();
                l.setSteamid(steamid32.trim());
                l.setCurrency(currency);
                l.setIntime("0");
                il4D2BuyListDao.insertMapBuyBuyInfo(l);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean memberSystemAdminCancelBuy(String steamid32, HttpServletRequest request) {
        if (userInformationSystem.verifyAdminRoot(request)) {
            L4d2BuyList mapBySteamId = il4D2BuyListDao.findMapBySteamId(steamid32);
            if (mapBySteamId != null) {
                mapBySteamId.setCurrency("0");
                il4D2BuyListDao.updateMapBuySteamId(mapBySteamId);
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONArray memberSystemAdminListBuy(HttpServletRequest request) throws IOException {
        if (userInformationSystem.verifyAdminRoot(request)) {
            List<L4d2BuyList> allBuyList = il4D2BuyListDao.findAllBuyList();
            JSONArray jsonAr = new JSONArray();
            long time =  System.currentTimeMillis() / 1000;
            //通过Http接口获取数据
            HttpSteamInfo steamInfo = new HttpSteamInfo();
            for (L4d2BuyList  L4d2BuyList :allBuyList){
                JSONObject json = new JSONObject();
                json.put("SteamId",L4d2BuyList.getSteamid());
                json.put("Currency",L4d2BuyList.getCurrency());
                if( Integer.parseInt(L4d2BuyList.getIntime()) > time){
                    json.put("Intime","已领取");
                }else {
                    json.put("Intime","未领取");
                }
                json.put("GameName",steamInfo.getHttpSteamIdToName(L4d2BuyList.getSteamid().trim()));
                jsonAr.add(json);
            }
            return jsonAr;
        }
        return null;
    }

    @Override
    public JSONArray memberSystemAdminCdkGenerateBuy(int keynumber, String currency, HttpServletRequest request) {

        if (userInformationSystem.verifyAdminRoot(request)) {
            JSONArray list = new JSONArray();
            for (int i = 0; i < keynumber; i++) {
                JSONObject map = new JSONObject();
                String randomCode = "TZB" + RandomNumber.getRandomCode(16);
                L4d2BuyCdk l4d2BuyCdk = new L4d2BuyCdk();
                l4d2BuyCdk.setCdk(randomCode);
                l4d2BuyCdk.setCurrency(currency);
                l4d2BuyCdk.setIsuse(0);
                il4d2BuyCdkDao.insertCdk(l4d2BuyCdk);
                map.put("cdk", randomCode);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public Map<String, Object> validTimeVip(String steamid) {

        L4d2VipList vipBySteamId32 = il4d2VipListDao.findVipBySteamId32(steamid.trim());
        String datetimeov = vipBySteamId32.getDatetimeov();
        long indexTime = System.currentTimeMillis() / 1000;

        //判断是否过期
        Map<String, Object> list = new HashMap<> ();
        if(Integer.parseInt(datetimeov) > indexTime){
            int showtimeint = Integer.parseInt( Long.toString(Integer.parseInt(datetimeov) - indexTime));
            String showtime = showtimeint / (24 * 3600) + "天" + showtimeint % (24 * 3600) / 3600 + "小时" + showtimeint % 3600 / 60 + "分钟";

            list.put("srcVipTime",datetimeov);
            list.put("TimeVipStr",showtime);
            list.put("validVip",true);
            return list;

        }else {
            list.put("srcVipTime",datetimeov);
            list.put("TimeVipStr","0天0时0分");
            list.put("validVip",false);
            return list;
        }
    }

    @Override
    public Map<String, Object> validTimeOp(String steamid) {

        L4d2OpList opBySteamId32 = il4d2OpListDao.findOpBySteamId32(steamid.trim());
        String datetimeov = opBySteamId32.getDatetimeov();
        long indexTime = System.currentTimeMillis() / 1000;

        //判断是否过期
        Map<String, Object> list = new HashMap<> ();
        if(Integer.parseInt(datetimeov) > indexTime){
            int showtimeint = Integer.parseInt( Long.toString(Integer.parseInt(datetimeov) - indexTime));
            String showtime = showtimeint / (24 * 3600) + "天" + showtimeint % (24 * 3600) / 3600 + "小时" + showtimeint % 3600 / 60 + "分钟";
            list.put("srcOpTime",datetimeov);
            list.put("TimeOpStr",showtime);
            list.put("validOp",true);
            return list;

        }else {
            list.put("srcOpTime",datetimeov);
            list.put("TimeOpStr","0天0时0分");
            list.put("validOp",false);
            return list;
        }
    }

    @Override
    public Map<String, Object> balanceTzb(String steamid) {
        L4d2BuyList mapBySteamId = il4D2BuyListDao.findMapBySteamId(steamid);

        Map<String, Object> list = new HashMap<> ();
        list.put("Currency",mapBySteamId.getCurrency());
        list.put("Intime",mapBySteamId.getIntime());
        return list;
    }


}
