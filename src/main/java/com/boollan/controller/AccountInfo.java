package com.boollan.controller;

import com.boollan.Servlet.ApiMethod.IAccountCdk;
import com.boollan.Servlet.ApiMethod.IAccountMethod;
import com.boollan.domain.account_cdk;
import com.boollan.domain.account_user;
import com.boollan.service.IAccountCdkService;
import com.boollan.service.IAccountUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Boollan
 */
@RequestMapping(path = "/account")
public class AccountInfo {

    public void setAccountMethod(IAccountMethod accountMethod) {
        this.accountMethod = accountMethod;
    }

    IAccountMethod accountMethod;
    IAccountCdk accountCdk;
    IAccountUserService accountUserService;
    IAccountCdkService accountCdkService;

    public void setAccountCdk(IAccountCdk accountCdk) {
        this.accountCdk = accountCdk;
    }

    public void setAccountUserService(IAccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    public void setAccountCdkService(IAccountCdkService accountCdkService) {
        this.accountCdkService = accountCdkService;
    }

    //发送验证码接口
    @RequestMapping(value = "/accountMessage", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView SendEmail(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        if (username != null) {
            Map<String, Object> map_UserInfo = accountMethod.accountGetInfo(username.trim());
            if (map_UserInfo != null) {
                map.put("return", true);
                map.put("json_username", map_UserInfo.get("username"));
                map.put("json_email", map_UserInfo.get("email"));
                map.put("json_donations", map_UserInfo.get("donations"));
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
        }
        map.put("return", false);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    //更改密码接口
    @RequestMapping(value = "/updatePasswordApi", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView updatePassword(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        map.put("username", request.getParameter("username"));
        map.put("lowPassword", URLEncoder.encode(request.getParameter("lowPassword"), "UTF-8"));
        map.put("newPassword", URLEncoder.encode(request.getParameter("newPassword"), "UTF-8"));
        accountMethod.accountPasswordUpdate(map);
        //返回信息
        Map<String, Object> info = new HashMap<>();
        info.put("key", true);
        return new ModelAndView(new MappingJackson2JsonView(), info);
    }

    //邮箱更改接口
    @RequestMapping(value = "/updateEmailApi", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView updateEmail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", request.getParameter("username"));
        map.put("lowEmail", request.getParameter("lowEmail"));
        map.put("newEmail", request.getParameter("newEmail"));
        map.put("emailCode", request.getParameter("emailCode"));
        System.out.println(map.toString());
        Map<String, Object> info = new HashMap<>();
        if (accountMethod.accountEmailUpdate(map.get("username").toString(), map.get("lowEmail").toString(), map.get("newEmail").toString(), map.get("emailCode").toString())) {
            //返回信息
            info.put("key", true);
            return new ModelAndView(new MappingJackson2JsonView(), info);
        }
        //返回信息
        info.put("key", false);
        return new ModelAndView(new MappingJackson2JsonView(), info);
    }

    /**
     * CDK接口
     */
    @RequestMapping(value = "/cdkApi", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView cdkApi(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> mapinfo = new HashMap<>();
        if (request.getSession().getAttribute("UserName") != null && request.getParameter("cdk") != null && request.getParameter("token") != null) {
            //获取基本信息
            mapinfo.put("username", request.getSession().getAttribute("UserName").toString());
            mapinfo.put("cdk", request.getParameter("cdk"));
            mapinfo.put("token", request.getParameter("token"));
            //获取CDK信息
            Map<String, Object> cdkMap = accountCdk.cdkInfo(mapinfo.get("cdk").toString());
            //声明过期时间
            Date dateTime = new Date(System.currentTimeMillis());
            Date overduetime = (Date) cdkMap.get("overduetime");
            //验证CDK是否有效
            if (cdkMap != null && Integer.parseInt(cdkMap.get("effective").toString()) == 0 && dateTime.compareTo(overduetime) == -1) {
                //验证用户
                Map<String, Object> accountGetInfo = accountMethod.accountGetInfo(mapinfo.get("username").toString());
                //判定是否有该用户
                if (accountGetInfo != null) {
                    //获取余额信息和CDK金额信息
                    double money1 = (double) cdkMap.get("money");
                    int donations = (int) accountGetInfo.get("donations");
                    int money = (int) money1 + donations;
                    //更新余额信息
                    account_user userInfo = accountUserService.findInfoByUser(mapinfo.get("username").toString());
                    userInfo.setDonations(money);
                    accountUserService.updateUserInfo(userInfo);
                    //更新已使用的卡密的有效性
                    accountCdk.deleKeyValid(accountCdkService.finCdkInfoByCdk(mapinfo.get("cdk").toString()));
                    //返回
                    result.put("key", true);
                    return new ModelAndView(new MappingJackson2JsonView(), result);
                }
            }
        }
        result.put("key", false);
        return new ModelAndView(new MappingJackson2JsonView(), result);
    }



}
