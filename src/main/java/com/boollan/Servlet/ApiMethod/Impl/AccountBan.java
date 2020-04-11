package com.boollan.Servlet.ApiMethod.Impl;

import com.boollan.Servlet.ApiMethod.IAccountBan;
import com.boollan.domain.account_ban;
import com.boollan.domain.account_user;
import com.boollan.service.IAccountBanService;
import com.boollan.service.IAccountUserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AccountBan implements IAccountBan {

    public void setAccountBanService(IAccountBanService accountBanService) {
        this.accountBanService = accountBanService;
    }

    public void setAccountUserService(IAccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    private IAccountBanService accountBanService;

    private IAccountUserService accountUserService;

    @Override
    public void setUserBan(String username, Date bantime) {

        account_ban account_ban =new account_ban();
        account_ban.setAccout_ban(username);
        account_ban.setIs_ban(1);
        account_ban.setBan_time(bantime);
        accountBanService.insertBanUser(account_ban);
    }


    @Override
    public Map<String, Object> getUserBan(String username) {
        //获取被封禁的用户
        Map<String,Object> map = new HashMap<>();
        account_user infoByUser = accountUserService.findInfoByUser(username);
        if (infoByUser != null && infoByUser.getUsername().equals(username) == true) {

            account_ban banUserByName = accountBanService.findBanUserByName(username);
            if (banUserByName!=null){
                map.put("Accout_ban",banUserByName.getAccout_ban());

                Date date = new Date();

                if (date.getTime() < banUserByName.getBan_time().getTime()) {
                    map.put("Is_ban",1);
                } else {
                    map.put("Is_ban",0);
                }
                return map;
            }
            map.put("Accout_ban",username);
            map.put("Is_ban",0);
            return map;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getBanInfo() {
        return null;
    }


}
