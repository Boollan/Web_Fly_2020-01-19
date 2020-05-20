package com.boollan.controllerpanel;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Boollan
 */
@RequestMapping(path = "/newPanel/admin", method = {RequestMethod.GET})
public class AdminInfo {

    /**
     * 管理面板
     * @return 返回页面
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("VuePanel/userPanel/admin/index");
    }


}
