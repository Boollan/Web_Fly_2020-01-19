package com.boollan.controller;

import java.io.IOException;


import com.boollan.service.IMemberSystem;
import com.boollan.util.module.LoginValidation;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Boollan
 */
@RequestMapping(path = "/Membersbuy")
public class Membersbuy {

    public void setiMemberSystem(IMemberSystem iMemberSystem) {
        this.iMemberSystem = iMemberSystem;
    }

    IMemberSystem iMemberSystem;

    /**
     * vip兑换页面
     *
     * @return true
     */
    @RequestMapping(value = "/PaymentVip")
    public ModelAndView getVip() {
        return new ModelAndView("VuePanel/userPanel/module/Membersbuy/vipbuy");
    }


    /**
     * Op兑换页面
     *
     * @return true
     */
    @RequestMapping(value = "/PaymentOp")
    public ModelAndView getOp() {
        return new ModelAndView("VuePanel/userPanel/module/Membersbuy/opbuy");
    }




    /**
     * VIP在线支付界面
     *
     * @return true
     */
    @RequestMapping(value = "/OneLinePaymentVip")
    public ModelAndView getOneLinePaymentVip() {
        return new ModelAndView("VuePanel/userPanel/module/Membersbuy/onelinevipbuy");
    }


    /**
     * OP在线支付界面
     *
     * @return true
     */
    @RequestMapping(value = "/OneLinePaymentOp")
    public ModelAndView getOneLinePaymentOp() {
        return new ModelAndView("VuePanel/userPanel/module/Membersbuy/onelineopbuy");
    }

    /**
     * 求生之路2 OP兑换
     *
     * @return true
     */
    @RequestMapping(path = "/exchangeOp", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getExchangeOp(HttpServletRequest request) throws IOException {

        String steamid = request.getParameter("steamid").trim();
        String cdk = request.getParameter("cdk").trim();
        String token = request.getParameter("token").trim();
        JSONObject map = new JSONObject();

        //判断规范
        if (steamid.length() > 0 && steamid.length() < 18) {
            //实例化工具类
            LoginValidation loginValidation = new LoginValidation();
            //判断是否是机器人
            if (loginValidation.imageVerification(token)) {
                boolean b = iMemberSystem.memberOpExchange(steamid, cdk);
                if (b){
                    map.put("res", b);
                    map.put("message","OP开通成功");
                    return map.toString();
                }else {
                    map.put("res", b);
                    map.put("message","卡密已经使用！");
                    return map.toString();
                }
            }
        }
        map.put("res", false);
        map.put("message","参数出错");
        return map.toJSONString();
    }

    /**
     * 求生之路2 Vip兑换
     *
     * @return true
     */
    @RequestMapping(path = "/exchangeVip", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getExchangeVip(HttpServletRequest request) throws IOException {

        String steamid = request.getParameter("steamid").trim();
        String cdk = request.getParameter("cdk").trim();
        String token = request.getParameter("token").trim();
        JSONObject map = new JSONObject();


        if (steamid.length() > 0 && steamid.length() < 18) {
            //实例化工具类
            LoginValidation loginValidation = new LoginValidation();
            //判断是否是机器人
            if (loginValidation.imageVerification(token)) {
                boolean b = iMemberSystem.memberVipExchange(steamid, cdk);
                if (b){
                    map.put("res", b);
                    map.put("message","VIP开通成功");
                    return map.toString();
                }else {
                    map.put("res", b);
                    map.put("message","卡密已经使用！");
                    return map.toString();
                }

            }
        }
        map.put("res", false);
        map.put("message","参数出错");
        return map.toJSONString();
    }


}
