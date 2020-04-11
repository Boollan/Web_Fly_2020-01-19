package com.boollan.Servlet.ApiMethod;


import java.util.Date;
import java.util.List;
import java.util.Map;
public interface IAccountBan {


    /**
     * 设置封禁用户
     *
     * @param username 用户名
     * @param bantime  封禁时间
     */
    void setUserBan(String username, Date bantime);

    /**
     * 获取被封禁的用户信息
     *
     * @param username
     * @return
     */
    Map<String,Object> getUserBan(String username);

    /**
     * 查询所有被封禁用户的信息
     *
     * @return
     */
    List<Map<String,Object>> getBanInfo();
}
