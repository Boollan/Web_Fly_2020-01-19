package com.boollan.controller;

import com.boollan.service.IMemberSystemAdmin;
import com.boollan.service.IUserInformationSystem;
import com.boollan.util.module.LoginValidation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Boollan
 */
@RequestMapping(path = "/Admin")
public class Admin {

    public void setMemberSystemAdmin(IMemberSystemAdmin iMemberSystemAdmin) {
        this.iMemberSystemAdmin = iMemberSystemAdmin;
    }

    private IMemberSystemAdmin iMemberSystemAdmin;

    private IUserInformationSystem iUserInformationSystem;

    public void setiUserInformationSystem(IUserInformationSystem iUserInformationSystem) {
        this.iUserInformationSystem = iUserInformationSystem;
    }

    /**
     * 卡密生成VIP或OP
     *
     * @return true
     */
    @RequestMapping(value = "/generateCdk")
    public ModelAndView generateCdk() {
        return new ModelAndView("VuePanel/userPanel/module/Admin/generateviporop");
    }

    /**
     * 管理员添加VIP或OP
     *
     * @return true
     */
    @RequestMapping(value = "/addVipOrOp")
    public ModelAndView addVipOrOp() {
        return new ModelAndView("VuePanel/userPanel/module/Admin/addviporop");
    }

    /**
     * 管理员添加VIP或OP
     *
     * @return true
     */
    @RequestMapping(value = "/cancelVipOrOp")
    public ModelAndView cancelVipOrOp() {
        return new ModelAndView("VuePanel/userPanel/module/Admin/cancelviporop");
    }

    /**
     * 管理员添加VIP或OP
     *
     * @return true
     */
    @RequestMapping(value = "/viewlist")
    public ModelAndView viewlist() {
        return new ModelAndView("VuePanel/userPanel/module/Admin/viewlist");
    }


    /**
     * 卡密生成
     *
     * @return true
     */
    @RequestMapping(value = "/GenerateCdkApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getGenerateCdk(HttpServletRequest request) {

        String number = request.getParameter("number").trim();
        String datetime = request.getParameter("datetime").trim();
        String permissionsType = request.getParameter("permissionsType").trim();
        String token = request.getParameter("token").trim();
        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {

            if (null != request.getParameter("adminPassword")) {
                String adminPassword = request.getParameter("adminPassword");
                //VIP
                int vip = 1;
                if (Integer.parseInt(permissionsType) == vip) {
                    return iMemberSystemAdmin.memberSystemAdminCdkGenerateVip(Integer.parseInt(number), datetime, adminPassword).toString();
                }
                //OP
                int op = 2;
                if (Integer.parseInt(permissionsType) == op) {
                    return iMemberSystemAdmin.memberSystemAdminCdkGenerateOp(Integer.parseInt(number), datetime, adminPassword).toString();
                }
            } else {
                //用户没有输入管理员密码 判断他是否登录是否具有管理员身份
                if (iUserInformationSystem.verifyAdminRoot(request)) {
                    //VIP
                    int vip = 1;
                    if (Integer.parseInt(permissionsType) == vip) {
                        return iMemberSystemAdmin.memberSystemAdminCdkGenerateVip(Integer.parseInt(number), datetime, request).toString();
                    }
                    //OP
                    int op = 2;
                    if (Integer.parseInt(permissionsType) == op) {
                        return iMemberSystemAdmin.memberSystemAdminCdkGenerateOp(Integer.parseInt(number), datetime, request).toString();
                    }
                }
            }

        }
        return null;
    }

    /**
     * 手动添加OpOrVip
     *
     * @return true
     */
    @RequestMapping(value = "/OpOrVipAddApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOpOrVipAddApi(HttpServletRequest request) throws IOException {

        String steamid = request.getParameter("steamid").trim();
        String type = request.getParameter("type").trim();
        String dataTime = request.getParameter("dataTime").trim();

        String token = request.getParameter("token").trim();

        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {
            if (null != request.getParameter("adminPassword")){
                String adminPassword = request.getParameter("adminPassword").trim();
                JSONObject json = new JSONObject();
                boolean b = iMemberSystemAdmin.memberSystemAdminAddOpOrVipTimestamp(steamid, type, dataTime, adminPassword);
                json.put("res", b);
                return json.toString();
            }else {
                JSONObject json = new JSONObject();
                boolean b = iMemberSystemAdmin.memberSystemAdminAddOpOrVipTimestamp(steamid, type, dataTime, request);
                json.put("res", b);
                return json.toString();
            }

        }
        return null;
    }

    /**
     * 手动取消OpOrVip
     *
     * @return true
     */
    @RequestMapping(value = "/OpOrVipCancelApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOpOrVipCancelApi(HttpServletRequest request) {

        String steamid = request.getParameter("steamid").trim();
        String type = request.getParameter("type").trim();

        String token = request.getParameter("token").trim();

        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {
            if(null != request.getParameter("adminPassword")){
                String adminPassword = request.getParameter("adminPassword").trim();
                JSONObject json = new JSONObject();
                boolean b = iMemberSystemAdmin.memberSystemAdminCancelOpOrVipTimestamp(steamid, type, adminPassword);
                json.put("res", b);
                return json.toString();
            }else {
                JSONObject json = new JSONObject();
                boolean b = iMemberSystemAdmin.memberSystemAdminCancelOpOrVipTimestamp(steamid, type, request);
                json.put("res", b);
                return json.toString();
            }

        }
        return null;
    }

    /**
     * OpOrVip列表
     */
    @RequestMapping(value = "/OpOrVipListApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOpOrVipListApi(HttpServletRequest request) {

        String type = request.getParameter("type").trim();

        String token = request.getParameter("token").trim();

        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {
            if(null != request.getParameter("adminPassword")){
                String adminPassword = request.getParameter("adminPassword").trim();
                JSONArray result = new JSONArray();
                JSONObject json = new JSONObject();
                JSONArray jsonArray = iMemberSystemAdmin.memberSystemAdminSelectOpOrVipList(type, adminPassword);
                if (jsonArray != null) {
                    json.put("size", jsonArray.size());
                    result.add(jsonArray);
                    result.add(json);
                    return result.toJSONString();
                }
            }else {
                JSONArray result = new JSONArray();
                JSONObject json = new JSONObject();
                JSONArray jsonArray = iMemberSystemAdmin.memberSystemAdminSelectOpOrVipList(type, request);
                if (jsonArray != null) {
                    json.put("size", jsonArray.size());
                    result.add(jsonArray);
                    result.add(json);
                    return result.toJSONString();
                }
            }

        }
        return null;
    }

    /**
     * 添加兔子币
     */
    @RequestMapping(value = "/AddBuyApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAddBuyApi(HttpServletRequest request) {

        String steamid = request.getParameter("steamid").trim();
        String currency = request.getParameter("currency").trim();
        String token = request.getParameter("token").trim();

        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {
            JSONObject json = new JSONObject();
            boolean b = iMemberSystemAdmin.memberSystemAdminAddBuy(steamid, currency, request);
            if (b) {
                json.put("state","succeed");
                json.put("message","添加兔子币成功!");
                return json.toJSONString();
            }
            json.put("state","fail");
            json.put("message","添加兔子币失败!");
            return json.toJSONString();
        }
        return null;
    }

    /**
     * 取消兔子币
     */
    @RequestMapping(value = "/CancelBuyApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCancelBuyApi(HttpServletRequest request) {

        String steamid = request.getParameter("steamid").trim();
        String token = request.getParameter("token").trim();
        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {
            JSONObject json = new JSONObject();
            boolean b = iMemberSystemAdmin.memberSystemAdminCancelBuy(steamid, request);
            if (b) {
                json.put("state","succeed");
                json.put("message","取消兔子币成功!");
                return json.toJSONString();
            }
            json.put("state","fail");
            json.put("message","取消兔子币失败!");
            return json.toJSONString();
        }
        return null;
    }


    /**
     * 查询兔子币信息
     */
    @RequestMapping(value = "/BuyListApi", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getBuyListApi(HttpServletRequest request) throws IOException {
        String token = request.getParameter("token").trim();
        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        //判断是否是机器人
        if (loginValidation.imageVerification(token)) {
            return iMemberSystemAdmin.memberSystemAdminListBuy(request).toJSONString();
        }
        return null;
    }

}
