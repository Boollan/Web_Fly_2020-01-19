package com.boollan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/account", method = {RequestMethod.GET, RequestMethod.POST})
public class Account {

    /**
     * 账号首页页面
     * @return 返回页面
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("account/index");
    }

    /**
     * 更改密码页面
     * @return 返回页面
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public ModelAndView updatePassword(){
        return new ModelAndView("account/module/Change_password");
    }

    /**
     * 更改邮箱页面
     * @return 返回页面
     */
    @RequestMapping(value = "/updateEmail")
    @ResponseBody
    public ModelAndView updateEmail(){
        return new ModelAndView("account/module/Change_email");
    }

    /**
     * 兑换页面
     * @return 返回页面
     */
    @RequestMapping(value = "/conversion")
    @ResponseBody
    public ModelAndView conversion(){
        return new ModelAndView("account/module/Change_money");
    }


}
