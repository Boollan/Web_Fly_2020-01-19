package com.boollan.controllerpanel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Boollan
 */
@RequestMapping(path = "/newPanel/userInfo", method = {RequestMethod.GET})
public class UserInfo {
    /**
     * 网站公告
     * @return 返回页面
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("VuePanel/userPanel/module/userInfo/index");
    }

    /**
     * 基本信息
     * @return 返回页面
     */
    @RequestMapping(value = "/infoAccount")
    @ResponseBody
    public ModelAndView accountInfo(){
        return new ModelAndView("VuePanel/userPanel/module/userInfo/AccountInfo");
    }

    /**
     * 账户余额
     * @return 返回页面
     */
    @RequestMapping(value = "/BalanceAccount")
    @ResponseBody
    public ModelAndView balanceAccount(){
        return new ModelAndView("VuePanel/userPanel/module/userInfo/BalanceAccount");
    }

    /**
     * 操作记录
     * @return 返回页面
     */
    @RequestMapping(value = "/OperatingRecord")
    @ResponseBody
    public ModelAndView operatingRecord(){
        return new ModelAndView("VuePanel/userPanel/module/userInfo/OperatingRecord");
    }

    /**
     * 账户安全
     * @return 返回页面
     */
    @RequestMapping(value = "/AccountSecurity")
    @ResponseBody
    public ModelAndView accountSecurity(){
        return new ModelAndView("VuePanel/userPanel/module/userInfo/AccountSecurity");
    }

    /**
     * 账户安全
     * @return 返回页面
     */
    @RequestMapping(value = "/TradingAccount")
    @ResponseBody
    public ModelAndView tradingAccount(){
        return new ModelAndView("VuePanel/userPanel/module/userInfo/TradingAccount");
    }

}
