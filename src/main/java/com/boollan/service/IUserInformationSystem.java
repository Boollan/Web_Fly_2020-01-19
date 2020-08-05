package com.boollan.service;

import com.alibaba.fastjson.JSONObject;
import com.boollan.domain.user_info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Boollan
 */
public interface IUserInformationSystem {


    /**
     * 用户登录验证
     * @param request 请求头
     * @param response 请求体
     * @param accountLoginInfoJson 信息字符串
     * @return 身份验证是否成功
     */
    boolean userLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> accountLoginInfoJson);


    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @return 是否注册成功
     */
    boolean userReg(String username,String password,String email);


    /**
     * 重置用户密码
     * @param username 用户名
     * @param email 邮箱
     * @param code 验证码
     * @param newPassword 新密码
     * @return 重置密码是否成功
     * @throws ParseException 异常
     */
    boolean passwordReset(String username,String email,String code,String newPassword) throws ParseException;


    /**
     * 更改密码（使用密码更改密码）
     * @param username 用户名
     * @param lowPassword 旧密码
     * @param newPassword 新密码
     * @return 更新密码是否成功
     */
    boolean passwordUpdate(String username,String lowPassword,String newPassword);


    /**
     * 通过用户名获取信息
     * @param name 用户名
     * @return 返回用户信息
     */
    Map<String, Object> getInfoByName(String name);


    /**
     * 通过邮箱获取信息
     * @param email 邮箱
     * @return 返回用户信息
     */
    Map<String, Object> getInfoByEmail(String email);


    /**
     * 通过UID获取信息
     * @param uid 用户唯一UID
     * @return 返回用户信息
     */
    Map<String, Object> getInfoUid(String uid);



    /**
     * 更改绑定的邮箱
     * @param username 用户名
     * @param lowEmail 旧邮箱
     * @param newEmail 新邮箱
     * @param code 用于验证的验证码
     * @return true
     */
    boolean emailUpdate(String username, String lowEmail, String newEmail, String code) throws ParseException;

    /**
     * 获取全部用户信息（不安全）
     * @return 信息
     */
    List<user_info> accountInfo();

    /**
     * 验证管理员身份信息
     *
     * @param request 获取管理员信息
     * @return 返回管理员是否允许通过权限
     */
    boolean verifyAdminRoot(HttpServletRequest request);


    /**
     * 获取当前登录用户的信息
     * @param request 登录数据
     * @return 返回基本数据
     */
    JSONObject getMeInfoAccount(HttpServletRequest request) throws IOException;

    /**
     * 绑定SteamID
     * @param request 当前账号信息
     * @param steamid 绑定的SteamId
     * @return 返回是否绑定成功
     */
    boolean bindingAccountSteamId(HttpServletRequest request , String steamid);










}
