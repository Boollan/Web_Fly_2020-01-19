package com.boollan.service;

import org.json.simple.JSONArray;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 此类用于管理 如CDK生成和查询等功能
 * @author Boollan
 */
public interface IMemberSystemAdmin {


    /**
     * 生成CDK 配合兑换系统使用
     * @param keynumber 生成卡密数量
     * @param dataTime  给与的时间 （秒）
     * @param adminPassword 管理员密码认证
     * @return  返回Cdk列表
     */
    JSONArray memberSystemAdminCdkGenerateOp(int keynumber, String dataTime, String adminPassword);


    /**
     * 生成CDK 配合兑换系统使用
     * @param keynumber 生成卡密数量
     * @param dataTime  给与的时间 （秒）
     * @param request session验证
     * @return  返回Cdk列表
     */
    JSONArray memberSystemAdminCdkGenerateOp(int keynumber, String dataTime, HttpServletRequest request);

    /**
     * 生成CDK 配合兑换系统使用
     * @param keynumber 生成卡密数量
     * @param dataTime  给与的时间 （秒）
     * @param adminPassword 管理员密码认证
     * @return  返回Cdk列表
     */
    JSONArray memberSystemAdminCdkGenerateVip(int keynumber, String dataTime, String adminPassword);

    /**
     * 生成CDK 配合兑换系统使用
     * @param keynumber 生成卡密数量
     * @param dataTime  给与的时间 （秒）
     * @param request session验证
     * @return  返回Cdk列表
     */
    JSONArray memberSystemAdminCdkGenerateVip(int keynumber, String dataTime, HttpServletRequest request);

    /**
     * 给指定玩家添加OPOrVip权限
     * @param steamid32 steamID给与玩家
     * @param type 给与的是OP还是VIP
     * @param dataTime 给与的时间（秒）
     * @param adminPassword 管理员密码认证
     * @return 返回是否添加成功
     * @throws IOException 抛出异常
     */
    boolean memberSystemAdminAddOpOrVipTimestamp(String steamid32,String type ,String dataTime,String adminPassword) throws IOException;

    /**
     * 给指定玩家添加OPOrVip权限
     * @param steamid32 steamID给与玩家
     * @param type 给与的是OP还是VIP
     * @param dataTime 给与的时间（秒）
     * @param request session验证
     * @return 返回是否添加成功
     * @throws IOException 抛出异常
     */
    boolean memberSystemAdminAddOpOrVipTimestamp(String steamid32,String type ,String dataTime,HttpServletRequest request) throws IOException;


    /**
     * 给指定玩家取消OPOrVip权限
     * @param steamid32 steamID给与玩家
     * @param type 取消的是OP还是VIP
     * @param adminPassword 管理员密码认证
     * @return 返回是否成功取消
     */
    boolean memberSystemAdminCancelOpOrVipTimestamp(String steamid32,String type ,String adminPassword);

    /**
     * 给指定玩家取消OPOrVip权限
     * @param steamid32 steamID给与玩家
     * @param type 取消的是OP还是VIP
     * @param request session验证
     * @return 返回是否成功取消
     */
    boolean memberSystemAdminCancelOpOrVipTimestamp(String steamid32,String type ,HttpServletRequest request);

    /**
     * 查询全部管理员信息
     * @param type 取消的是OP还是VIP
     * @param adminPassword 管理员密码认证
     * @return 返回是否成功取消
     */
    JSONArray memberSystemAdminSelectOpOrVipList(String type ,String adminPassword);

    /**
     * 查询全部管理员信息
     * @param type 取消的是OP还是VIP
     * @param request session验证
     * @return 返回是否成功取消
     */
    JSONArray memberSystemAdminSelectOpOrVipList(String type ,HttpServletRequest request);


    /**
     * 给指定玩家添加兔子币
     * @param steamid32 steamID给与玩家
     * @param currency 给与的数量
     * @param adminPassword 管理员密码
     * @return 返回是否成功添加
     */
    boolean memberSystemAdminAddBuy(String steamid32,String currency ,String adminPassword);

    /**
     * 给指定玩家添加兔子币
     * @param steamid32 steamID给与玩家
     * @param currency 给与的数量
     * @param request session验证
     * @return 返回是否成功添加
     */
    boolean memberSystemAdminAddBuy(String steamid32,String currency ,HttpServletRequest request);


    /**
     * 取消指定玩家所有兔子币
     * @param steamid32 steamID给与玩家
     * @param request session验证
     * @return 返回是否成功取消
     */
    boolean memberSystemAdminCancelBuy(String steamid32,HttpServletRequest request);

    /**
     * 获取兔子币商店玩家信息
     * @param request session验证
     * @return 返回是否成功取消
     * @throws IOException 异常
     */
    JSONArray memberSystemAdminListBuy(HttpServletRequest request) throws IOException;


    /**
     * 生成兔子币CDK 配合兑换系统使用
     * @param keynumber 生成卡密数量
     * @param currency  给与的兔子币数量
     * @param request session验证
     * @return  返回Cdk列表
     */
    JSONArray memberSystemAdminCdkGenerateBuy(int keynumber, String currency, HttpServletRequest request);


    /**
     * 获取VIP有效范围
     * @param steamid 玩家SteamID
     * @return 返回信息
     */
    Map<String,Object> validTimeVip(String steamid);

    /**
     * 获取OP 有效范围
     * @param steamid 玩家SteamID
     * @return 返回信息
     */
    Map<String,Object> validTimeOp(String steamid);

    /**
     * 获取玩家兔子币
     * @param steamid 玩家SteamID
     * @return 返回信息
     */
    Map<String,Object> balanceTzb(String steamid);




}
