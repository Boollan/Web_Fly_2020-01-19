package com.boollan.Servlet.ApiMethod;
import	java.util.Date;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IAccountLoginRecord {
    /**
     * 查询所有
     *
     * @return
     */
    JSONArray findAllLoginUserLog();

    /**
     * 查询一个
     *
     * @return
     */
    JSONArray findLoginLogbyUser(String username);

    /**
     * 获取登录日志信息
     * @param username 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param Client 登录平台
     * @return 返回数据
     */
    JSONArray finLogUserInfo(String username,Date startTime,Date endTime,String Client);

    /**
     * 保存；新增登录日志
     *
     * @param username 用户名
     * @param request  获取IP
     */
    void insertLoginUserLog(String username, HttpServletRequest request);
}
