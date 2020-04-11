package com.boollan.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.boollan.Servlet.ApiMethod.IAccountCdk;
import com.boollan.Servlet.ApiMethod.IAccountLoginRecord;
import com.boollan.Servlet.ApiMethod.IAccountMethod;
import com.boollan.Servlet.ApiMethod.Impl.AccountCdk;
import com.boollan.domain.account_cdk;
import com.boollan.domain.account_user;
import com.boollan.service.IAccountCdkService;
import com.boollan.service.IAccountUserService;
import com.boollan.util.module.encryption;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Boollan
 */
@RequestMapping(path = "/account/admin/api")
public class AdminInfo {

    /**
     * 操作本类功能需要 permissions 的等级
     */
    float permissionsLive = 3;
    String message = "message";
    private IAccountMethod accountMethod;
    private IAccountUserService accountUserService;
    private IAccountCdk accountCdk;
    private IAccountCdkService accountCdkService;
    private IAccountLoginRecord accountLoginRecord;

    public void setAccountMethod(IAccountMethod accountMethod) {
        this.accountMethod = accountMethod;
    }

    public void setAccountUserService(IAccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    public void setAccountCdk(IAccountCdk accountCdk) {
        this.accountCdk = accountCdk;
    }

    public void setAccountCdkService(IAccountCdkService accountCdkService) {
        this.accountCdkService = accountCdkService;
    }

    public void setAccountLoginRecord(IAccountLoginRecord accountLoginRecord) {
        this.accountLoginRecord = accountLoginRecord;
    }

    /**
     * 获取账号信息
     *
     * @param request 请求体
     * @return 返回数据
     */
    @RequestMapping(value = "/accountMessage", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView getAccountMessage(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        //身份安全验证
        if (accountMethod.verifyAdminPermissions(request)) {
            //判定用户不为空
            String username = request.getParameter("username");
            if (username != null) {
                Map<String, Object> mapUserInfo = accountMethod.accountGetInfo(username.trim());
                if (mapUserInfo != null) {
                    map.put("return", true);
                    map.put("json_username", mapUserInfo.get("username"));
                    map.put("json_email", mapUserInfo.get("email"));
                    map.put("json_donations", mapUserInfo.get("donations"));
                    map.put("json_permissions", mapUserInfo.get("permissions"));
                    return new ModelAndView(new MappingJackson2JsonView(), map);
                }
                map.put("return", false);
                map.put(message, "查无此用户!");
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
            map.put("return", false);
            map.put(message, "用户名为空!");
            return new ModelAndView(new MappingJackson2JsonView(), map);

        }
        map.put("return", false);
        map.put(message, "您无权使用此接口!");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }


    /**
     * 更改账号密码
     *
     * @param request 请求体
     * @return 返回数据
     */
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView updatePassword(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();

        //验证管理员权限
        if (accountMethod.verifyAdminPermissions(request)) {
            //判定用户不为空
            String username = request.getParameter("username");
            String password = URLEncoder.encode(request.getParameter("password"), "utf-8");
            if (username != null) {
                if (password != null) {
                    Map<String, Object> mapUserInfo = accountMethod.accountGetInfo(username.trim());
                    if (mapUserInfo != null) {
                        //获取当前用户
                        account_user infoByUser = accountUserService.findInfoByUser(username);
                        //修改用户信息
                        infoByUser.setPassword(encryption.StringInMd5(password));
                        //更新用户信息
                        accountUserService.updateUserInfo(infoByUser);
                        //返回界面信息
                        map.put("return", true);
                        map.put(message, "强制更新密码成功!");
                        return new ModelAndView(new MappingJackson2JsonView(), map);
                    }
                    map.put("return", false);
                    map.put(message, "查无此用户!");
                    return new ModelAndView(new MappingJackson2JsonView(), map);
                }
                map.put("return", false);
                map.put(message, "密码为空!");
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
            map.put("return", false);
            map.put(message, "用户名为空!");
            return new ModelAndView(new MappingJackson2JsonView(), map);

        }
        map.put("return", false);
        map.put(message, "您无权使用此接口!");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 更改邮箱
     *
     * @param request 请求体
     * @return 返回数据
     */
    @RequestMapping(value = "/updateEmail", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView updateEmail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //验证管理员权限
        if (accountMethod.verifyAdminPermissions(request)) {
            //判定用户不为空
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            if (username != null) {
                if (email != null) {
                    Map<String, Object> mapUserInfo = accountMethod.accountGetInfo(username.trim());
                    if (mapUserInfo != null) {
                        //获取当前用户
                        account_user infoByUser = accountUserService.findInfoByUser(username);
                        //修改用户信息
                        infoByUser.setEmail(email);
                        //更新用户信息
                        accountUserService.updateUserInfo(infoByUser);
                        //返回界面信息
                        map.put("return", true);
                        map.put(message, "强制更新邮箱成功!");
                        return new ModelAndView(new MappingJackson2JsonView(), map);
                    }
                    map.put("return", false);
                    map.put(message, "查无此用户!");
                    return new ModelAndView(new MappingJackson2JsonView(), map);
                }
                map.put("return", false);
                map.put(message, "邮箱为空!");
                return new ModelAndView(new MappingJackson2JsonView(), map);
            }
            map.put("return", false);
            map.put(message, "用户名为空!");
            return new ModelAndView(new MappingJackson2JsonView(), map);

        }
        map.put("return", false);
        map.put(message, "您无权使用此接口!");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 生成CDK
     *
     * @param request 请求体
     * @return 返回数据
     */
    @RequestMapping(value = "/sendCdk", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendCdk(HttpServletRequest request) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        //验证管理员权限
        if (accountMethod.verifyAdminPermissions(request)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String number = request.getParameter("number");
            String money = request.getParameter("money");
            String endTime = request.getParameter("endTime");

            if (number != null && money != null && endTime != null) {
                JSONArray jsonArray = new JSONArray();
                List<Map<String, String>> maps = accountCdk.sendCdk(Integer.parseInt(number), Integer.parseInt(money), sdf.parse(endTime));

                for (int i = 0; i < maps.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("cdk", maps.get(i).get("cdk"));
                    jsonArray.add(jsonObject);
                }

                return jsonArray.toJSONString();
            }
            map.put("return", false);
            map.put(message, "请确保参数正确![number:数量,money:金额,endTime:过期时间]");
            return map.toString();
        }
        map.put("return", false);
        map.put(message, "您无权使用此接口!");
        return map.toString();
    }

    /**
     * 获取CDK信息
     *
     * @param request 请求体
     * @return 返回数据
     */
    @RequestMapping(value = "/cdkMessage", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelAndView cdkMessage(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //验证管理员权限
        if (accountMethod.verifyAdminPermissions(request)) {
            //判定用户不为空
            String cdk = request.getParameter("cdk").trim();
            if (cdk != null) {
                Map<String, Object> cdkInfo = new HashMap<>();
                account_cdk account_cdk = accountCdkService.finCdkInfoByCdk(cdk);
                cdkInfo.put("cdk", account_cdk.getCdk());
                cdkInfo.put("money", account_cdk.getMoney());
                cdkInfo.put("effective", account_cdk.getEffective());
                cdkInfo.put("overduetime", account_cdk.getOverduetime().toString());
                cdkInfo.put("return", true);
                cdkInfo.put(message, "获取成功!");
                System.out.println(cdkInfo);
                return new ModelAndView(new MappingJackson2JsonView(), cdkInfo);
            }
            map.put("return", false);
            map.put(message, "CDK为空!");
            return new ModelAndView(new MappingJackson2JsonView(), map);
        }
        map.put("return", false);
        map.put(message, "您无权使用此接口!");
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    /**
     * 获取登录日志
     *
     * @param request 请求体
     * @return 返回数据
     */
    @RequestMapping(value = "/sendLoginLog", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendLoginLog(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //验证管理员权限
        if (accountMethod.verifyAdminPermissions(request)) {
            String username = request.getParameter("username");
            if (username != null) {
                JSONArray loginLogbyUser = accountLoginRecord.findLoginLogbyUser(username);
                
                return loginLogbyUser.toJSONString();
            }else {
                JSONArray allLoginUserLog = accountLoginRecord.findAllLoginUserLog();
                return allLoginUserLog.toJSONString();
            }
        }
        map.put("return", false);
        map.put(message, "您无权使用此接口!");
        return map.toString();
    }


}