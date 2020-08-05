package com.boollan.util.module;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Boollan
 */
@Controller
public class SetCookie {


    /**
     * 设置Cookie Keep=true 7天免登录
     * @param cookieName 名称
     * @param cookieValue 值
     * @param keep 是否设置7天免登录
     * @return 返回Cookie信息
     */
    public Cookie setUserCookie(String cookieName, String cookieValue, boolean keep) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        if (keep) {
            cookie.setMaxAge(60 * 60 * 24 * 365);
        }
        cookie.setPath("/");
        return cookie;
    }


    /**
     * 获取客户端的Cookie
     * @param request 请求头
     * @param response 请求体
     * @return 返回JSON
     */
    public JSONObject getUserCookie(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");
        //1 通过Request对象获取上次服务器端发送的Cookie信息
        Cookie[] cookies = request.getCookies();

        JSONObject json = new JSONObject();
        //2 判断Request对象中的Cookie是否存在
        if (cookies != null) {
            //3 遍历Request对象中的所有Cookie
            for (Cookie cookie : cookies) {
                //4 获取每一个Cookie的名称
                json.put(cookie.getName(), cookie.getValue());
            }
            return json;
        }
        return null;
    }


    /**
     * 删除Cookie
     * @param delCookieName 要删除的名称
     * @return 返回Cookie
     */
    public Cookie deleUserCookie(String delCookieName) {
        Cookie cookie = new Cookie(delCookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }


}
