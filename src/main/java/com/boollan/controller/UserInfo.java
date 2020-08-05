package com.boollan.controller;

import com.alibaba.fastjson.JSONObject;

import com.boollan.domain.CommodityList;
import com.boollan.service.IEmailCode;
import com.boollan.service.IUserInformationSystem;
import com.boollan.util.module.LoginValidation;
import com.boollan.util.module.SetCookie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static java.net.URLEncoder.*;

/**
 * @author Boollan
 */
@RequestMapping(path = "/User")
public class UserInfo {

    private IUserInformationSystem userInfo;

    private IEmailCode userEmail;


    public void setUserInfo(IUserInformationSystem userInfo) {
        this.userInfo = userInfo;
    }

    public void setUserEmail(IEmailCode userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 登录接口
     *
     * @param httpServletRequest  请求头
     * @param httpServletResponse 请求体
     * @return true
     * @throws Exception 异常
     */
    @RequestMapping(path = "/Login", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, Object> map = new HashMap<>();
        //非空验证
        if (httpServletRequest.getParameter("username") != null && httpServletRequest.getParameter("password") != null && httpServletRequest.getParameter("token") != null && httpServletRequest.getParameter("inputKeep") != null) {

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("username", httpServletRequest.getParameter("username"));
            metadata.put("password", encode(httpServletRequest.getParameter("password"), "UTF-8"));
            metadata.put("inputKeep", httpServletRequest.getParameter("inputKeep"));
            LoginValidation LoginVal = new LoginValidation();

            if (LoginVal.imageVerification(httpServletRequest.getParameter("token"))) {
                if (userInfo.userLogin(httpServletRequest, httpServletResponse, metadata)) {
                    map.put("key", true);
                    map.put("message", "登录成功!");
                    return new ModelAndView(new MappingJackson2JsonView(), map);
                }
            }
        }
        map.put("key", false);
        map.put("message", "登录失败! \n原因：可能是账户或密码错误");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 邮箱发送接口
     *
     * @param request 请求头
     * @return true
     * @throws Exception 异常
     */
    @RequestMapping(path = "/SendEmailMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView sendEmailMessage(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        LoginValidation loginVal = new LoginValidation();
        if (email != null && token != null && loginVal.imageVerification(token.trim())) {
            userEmail.sendEmailCode(email.trim());
            map.put("key", true);
            map.put("message", "验证码发送成功! \n提示：如果找不到请到垃圾邮件查找！");
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        map.put("key", false);
        map.put("message", "验证码获取失败! \n原因：邮箱不正确！");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    /**
     * 注销接口
     *
     * @param response 响应体
     * @return true
     * @throws IOException 异常
     */
    @RequestMapping(value = "/Exit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView exit(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        SetCookie setCookie = new SetCookie();
        Cookie jsessionid = setCookie.setUserCookie("JSESSIONID", null, false);
        response.addCookie(jsessionid);
        map.put("key", true);
        response.sendRedirect("/");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    /**
     * 注册接口
     *
     * @param request 请求头
     * @return 信息
     * @throws UnsupportedEncodingException 异常
     * @throws ParseException               异常
     */
    @RequestMapping(path = "/Reg", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView register(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {


        Map<String, Object> map = new HashMap<>();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String password = encode(request.getParameter("password"), "UTF-8");
        String token = request.getParameter("token");
        //非空验证
        if (username != null && email != null && token != null && password != null) {
            LoginValidation loginVal = new LoginValidation();
            if (loginVal.imageVerification(request.getParameter("token")) && userEmail.emailCodeVerify(email, code)) {
                if (userInfo.userReg(username, password, email)) {
                    map.put("key", true);
                    map.put("message", "注册成功!");
                    return new ModelAndView(new MappingJackson2JsonView(), map);
                }
            }
        }
        map.put("key", false);
        map.put("message", "注册失败! \n原因：用户名或邮箱已被注册或验证码不正确！");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 重置密码
     *
     * @param request 请求头
     * @return 信息
     */
    @RequestMapping(path = "/ResetPassword", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView resetPassword(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {

        Map<String, Object> map = new HashMap<>();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String newPassword = encode(request.getParameter("newPassword"), "UTF-8");
        String token = request.getParameter("token");

        //非空验证
        if (username != null && email != null && token != null && newPassword != null) {
            LoginValidation loginVal = new LoginValidation();
            //判断是否是机器人 验证码是否正确
            if (loginVal.imageVerification(request.getParameter("token"))) {
                if (userInfo.passwordReset(username,email,code,newPassword)) {
                    map.put("key", true);
                    map.put("message", "重置密码成功!");
                    return new ModelAndView(new MappingJackson2JsonView(), map);
                }
            }
        }
        map.put("key", false);
        map.put("message", "重置密码失败! \n原因：用户名或邮箱不存在或验证码不正确！");
        return new ModelAndView(new MappingJackson2JsonView(), map);


    }


    /**
     * 绑定Steam账号
     *
     * @param request 请求头
     * @return 信息
     */
    @RequestMapping(value = "/BindingSteamId", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bindingSteamId(HttpServletRequest request) {

        String steamid = request.getParameter("steamid");
        LoginValidation loginVal = new LoginValidation();
        JSONObject json = new JSONObject();
        //判断是否是机器人 验证码是否正确
        if (loginVal.imageVerification(request.getParameter("token"))) {
           if(userInfo.bindingAccountSteamId(request, steamid.trim())){
               json.put("res",true);
               json.put("message","绑定成功!");
               return json.toJSONString();
           }else {
               json.put("res",false);
               json.put("message","绑定失败!");
               return json.toJSONString();
           }
        }
       return null;
    }

    /**
     * 获取当前用户信息
     * @param request 请求头
     * @return 信息
     */
    @RequestMapping(value = "/MeUserInfo", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String meUserInfo(HttpServletRequest request) throws IOException {
        return userInfo.getMeInfoAccount(request).toJSONString();
    }

    /**
     * 获取地图通过记录
     * @param request 请求头
     * @return 信息
     */
    @RequestMapping(value = "/MapRecordInfo", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String mapRecordInfo(HttpServletRequest request) throws IOException {
        return userInfo.getMeInfoAccount(request).toJSONString();
    }




}
