package com.boollan.controller.mobile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Boollan
 */
@RequestMapping(path = "/UiMobile", method = {RequestMethod.GET, RequestMethod.POST})
public class UiHome {

    /**
     * 公告首页
     * @return 页面返回
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("WePanel/index");
    }


    /**
     * 普通菜单
     * @return 页面返回
     */
    @RequestMapping(value = "/CommonManager")
    @ResponseBody
    public ModelAndView commonManager(){
        return new ModelAndView("WePanel/CommonManager/index");
    }

    /**
     * 管理菜单
     * @return 页面返回
     */
    @RequestMapping(value = "/AdministrationManager")
    @ResponseBody
    public ModelAndView administrationManager(){
        return new ModelAndView("WePanel/AdministrationManager/index");
    }

    /**
     * 管理菜单扩展
     * @return 页面返回
     */
    @RequestMapping(value = "/AdministrationManagerExtend")
    @ResponseBody
    public ModelAndView administrationManagerExtend(){
        return new ModelAndView("WePanel/AdministrationManagerExtend/index");
    }

    /**
     * 记录排行榜
     * @return 页面返回
     */
    @RequestMapping(value = "/RanKingList")
    @ResponseBody
    public ModelAndView ranKingList(){
        return new ModelAndView("WePanel/RankingList/index");
    }

    /**
     * 个人信息
     * @return 页面返回
     */
    @RequestMapping(value = "/MeUserInfo")
    @ResponseBody
    public ModelAndView meUserInfo(){
        return new ModelAndView("WePanel/MeUserInfo/index");
    }

    /**
     * 登录页面
     * @return 页面返回
     */
    @RequestMapping(value = "/Login")
    @ResponseBody
    public ModelAndView login(){
        return new ModelAndView("WePanel/Login");
    }

    /**
     * 注册页面
     * @return 页面返回
     */
    @RequestMapping(value = "/Reg")
    @ResponseBody
    public ModelAndView reg(){
        return new ModelAndView("WePanel/Reg");
    }

    /**
     * 找回密码页面
     * @return 页面返回
     */
    @RequestMapping(value = "/ResetPassword")
    @ResponseBody
    public ModelAndView resetPassword(){
        return new ModelAndView("WePanel/ResetPassword");
    }

}
