package com.boollan.controllerpanel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Boollan
 */
@RequestMapping(path = "/newPanel/complaints", method = {RequestMethod.GET})
public class ComplaintsInfo {
    /**
     * 网站公告
     * @return 返回页面
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView("VuePanel/userPanel/module/complaints/index");
    }

    /**
     * 投诉
     * @return 返回页面
     */
    @RequestMapping(value = "/Complaints")
    @ResponseBody
    public ModelAndView Complaints(){
        return new ModelAndView("VuePanel/userPanel/module/complaints/Complaints");
    }


}
