package com.boollan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Boollan
 */
@RequestMapping(path = "/account/admin", method = {RequestMethod.GET, RequestMethod.POST})
public class Admin {

    /**
     * 账号首页页面
     * @return 返回页面
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("account/admin/index");
    }

    /**
     * 密码管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/passwordManagement")
    @ResponseBody
    public ModelAndView passwordManagement(){
        return new ModelAndView("account/admin/module/admin_passowrd");
    }

    /**
     * 邮箱管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/emailManagement")
    @ResponseBody
    public ModelAndView emailManagement(){
        return new ModelAndView("account/admin/module/admin_email");
    }

    /**
     * 邮箱查询管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/emailSelectManagement")
    @ResponseBody
    public ModelAndView emailSelectManagement(){
        return new ModelAndView("account/admin/module/email_module/admin_email_select");
    }

    /**
     * cdk管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/cdkManagement")
    @ResponseBody
    public ModelAndView cdkManagement(){
        return new ModelAndView("account/admin/module/admin_cdk");
    }

    /**
     * cdk查询管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/cdkSelectManagement")
    @ResponseBody
    public ModelAndView cdkSelectManagement(){
        return new ModelAndView("account/admin/module/cdk_module/admin_cdk_select");
    }

    /**
     * 登录日志管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/loginRecordManagement")
    @ResponseBody
    public ModelAndView loginRecordManagement(){
        return new ModelAndView("account/admin/module/admin_user_login_record");
    }

    /**
     * 游戏登录日志管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/loginRecordGameManagement")
    @ResponseBody
    public ModelAndView loginRecordGameManagement(){
        return new ModelAndView("account/admin/module/record_module/admin_user_login_game");
    }

    /**
     * 其他管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/otherManagement")
    @ResponseBody
    public ModelAndView otherManagement(){
        return new ModelAndView("account/admin/module/admin_other");
    }

    /**
     * 封禁系统管理页面
     * @return 返回页面
     */
    @RequestMapping(value = "/otherUserBanManagement")
    @ResponseBody
    public ModelAndView otherUserBanManagement(){
        return new ModelAndView("account/admin/module/other_module/admin_other_user_ban");
    }


}
