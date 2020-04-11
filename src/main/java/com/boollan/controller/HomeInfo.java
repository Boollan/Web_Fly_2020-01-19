package com.boollan.controller;

import com.boollan.Servlet.ApiMethod.IAccountMethod;
import com.boollan.Servlet.ApiMethod.IHomeShow;
import com.boollan.Servlet.Cookie.SetCookie;
import com.boollan.util.module.RegularVerify;
import com.boollan.util.module.login_validation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Boollan
 */
@RequestMapping(path = "/")
public class HomeInfo {

    private IHomeShow homeShow;

    public void setHomeShow(IHomeShow homeShow) {
        this.homeShow = homeShow;
    }

    private IAccountMethod accountMethod;

    public void setAccountMethod(IAccountMethod accountMethod) {
        this.accountMethod = accountMethod;
    }

    RegularVerify regularVerify;

    public void setRegularVerify(RegularVerify regularVerify) {
        this.regularVerify = regularVerify;
    }

    /**
     * 首页
     * @return true
     */
    @RequestMapping(path = "/")
    public ModelAndView getHome() {
        Map<String, Object> stringObjectMap = homeShow.GetInfoData();

        ModelAndView modelAndView = new ModelAndView("Home");
        modelAndView.addObject("homeTite", stringObjectMap.get("homeTite"));
        modelAndView.addObject("HomeText", stringObjectMap.get("HomeText"));
        modelAndView.addObject("imge_1", stringObjectMap.get("imge_1"));
        modelAndView.addObject("imge_2", stringObjectMap.get("imge_2"));
        modelAndView.addObject("imge_3", stringObjectMap.get("imge_3"));
        return modelAndView;
    }

    /**
     * 警告页面
     * @return true
     */
    @RequestMapping(path = "/warning")
    public ModelAndView getWarning() {
        return new ModelAndView("Warning");
    }

    /**
     * 捐助页面
     * @return true
     */
    @RequestMapping(path = "/donations")
    public ModelAndView getDonations() {
        return new ModelAndView("Donations");
    }

    /**
     * 登录页面
     * @return true
     */
    @RequestMapping(path = "/login")
    public ModelAndView getLogin() {
        return new ModelAndView("Login");
    }

    /**
     * 注册页面
     * @return true
     */
    @RequestMapping(path = "/registered")
    public ModelAndView getRegistered() {
        return new ModelAndView("Reg");
    }

    /**
     * 重置密码页面
     * @return true
     */
    @RequestMapping(path = "/resetPassword")
    public ModelAndView getResetPassword() {
        return new ModelAndView("ForgotPassword");
    }

    /**
     * 发送验证码接口
     * @param request 请求头
     * @return true
     */
    @RequestMapping(path = "/SendEmail", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView SendEmail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        login_validation validation = new login_validation();
        if (validation.ImgeVerification(token)) {
            if (accountMethod.accountSendEmailCode(email)) {
                map.put("return", true);
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
        }
        map.put("return", false);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * //登录接口
     * @param httpServletRequest 请求头
     * @param httpServletResponse 请求体
     * @return true
     * @throws Exception 异常
     */
    @RequestMapping(path = "/userLogin", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, Object> map = new HashMap<>();
        //非空验证
        if (httpServletRequest.getParameter("username") != null && httpServletRequest.getParameter("password") != null && httpServletRequest.getParameter("token") != null && httpServletRequest.getParameter("inputKeep") != null) {

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("username", httpServletRequest.getParameter("username"));
            metadata.put("password", URLEncoder.encode(httpServletRequest.getParameter("password"), "UTF-8"));
            metadata.put("token", httpServletRequest.getParameter("token"));
            metadata.put("inputKeep", httpServletRequest.getParameter("inputKeep"));

            if (accountMethod.accountLogin(httpServletRequest, httpServletResponse, metadata)) {
                map.put("key", true);
                map.put("message", "登录成功!");
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
        }
        map.put("key", false);
        map.put("message", "登录失败!");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 注册接口
     * @param httpServletRequest 请求头
     * @return true
     * @throws UnsupportedEncodingException 异常
     */
    @RequestMapping(path = "/userReg", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView reg(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {

        Map<String, Object> map = new HashMap<>();
        login_validation loginValidation = new login_validation();
        if (httpServletRequest.getParameter("username") != null && httpServletRequest.getParameter("password") != null && httpServletRequest.getParameter("email") != null && httpServletRequest.getParameter("token") != null && httpServletRequest.getParameter("code") != null) {
            //获取信息
            Map<String, Object> metadata = new HashMap<>();

            metadata.put("username", httpServletRequest.getParameter("username"));
            metadata.put("password", URLEncoder.encode(httpServletRequest.getParameter("password"), "UTF-8"));
            metadata.put("email", httpServletRequest.getParameter("email"));
            metadata.put("token", httpServletRequest.getParameter("token"));
            metadata.put("code", httpServletRequest.getParameter("code"));
            //token人机验证
            if (loginValidation.ImgeVerification(metadata.get("token").toString())) {
                //验证用户是否存在
                if (accountMethod.accountGetInfo(metadata.get("username").toString()) == null) {
                    //验证邮箱格式是否正确
                    if (regularVerify.VerifyEmail(metadata.get("email").toString())) {
                        //验证码是否有效
                        if (accountMethod.accountEmailCodeVerify(metadata.get("email").toString(), metadata.get("code").toString())) {
                            //提交注册信息
                            if (accountMethod.accountReg(metadata)) {
                                map.put("key", true);
                                return new ModelAndView(new MappingJackson2JsonView(), map);
                            }
                        }
                    }
                }
            }
        }
        map.put("key", false);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 注销接口
     * @param response 响应体
     * @return true
     * @throws IOException 异常
     */
    @RequestMapping(value = "/exit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView exit(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        SetCookie setCookie = new SetCookie();
        Cookie jsessionid = setCookie.SetUserCookie("JSESSIONID", null, false);
        response.addCookie(jsessionid);
        map.put("key", true);
        response.sendRedirect("/");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 重置密码接口
     * @param httpServletRequest 请求体
     * @param httpServletResponse 请求体
     * @return true
     * @throws Exception 异常
     */
    @RequestMapping(path = "/resetpwd", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView resetpwd(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, Object> map = new HashMap<>();
        //非空验证
        if (httpServletRequest.getParameter("email") != null && httpServletRequest.getParameter("code") != null && httpServletRequest.getParameter("newpwd") != null && httpServletRequest.getParameter("token") != null) {

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("email", httpServletRequest.getParameter("email"));
            metadata.put("code", httpServletRequest.getParameter("code"));
            metadata.put("newpwd", URLEncoder.encode(httpServletRequest.getParameter("newpwd"), "utf-8"));
            metadata.put("token", httpServletRequest.getParameter("token"));
            if (accountMethod.accountPasswordReset(metadata)) {
                map.put("key", true);
                map.put("message", "重置密码成功!");
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
        }
        map.put("key", false);
        map.put("message", "重置密码失败!");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


}
