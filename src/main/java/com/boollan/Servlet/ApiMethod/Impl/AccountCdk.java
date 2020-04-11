package com.boollan.Servlet.ApiMethod.Impl;

import com.boollan.Servlet.ApiMethod.IAccountCdk;
import com.boollan.domain.account_cdk;
import com.boollan.service.IAccountCdkService;
import com.boollan.util.module.RandomNumber;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;


public class AccountCdk implements IAccountCdk {


    private IAccountCdkService cdkService;

    public void setCdkService(IAccountCdkService cdkService) {
        this.cdkService = cdkService;
    }

    @Override
    public Map<String, Object> cdkInfo(String cdkname) {
        account_cdk account_cdk = cdkService.finCdkInfoByCdk(cdkname);
        Map<String, Object> map = new HashMap<String, Object>();
        if (account_cdk != null) {
            map.put("id", account_cdk.getId());
            map.put("cdk", account_cdk.getCdk());
            map.put("effective", account_cdk.getEffective());
            map.put("money", account_cdk.getMoney());
            map.put("overduetime", account_cdk.getOverduetime());
            return map;
        }
        return null;
    }

    @Override
    public boolean verifKey(account_cdk KeyInfo) {

        if (KeyInfo.getEffective() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, String>> sendCdk(int number, double money, Date endTime) {
        account_cdk cdk = new account_cdk();

        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Map<String,String> map = new HashMap<> ();
            String randomCode = RandomNumber.getRandomCode(16);
            cdk.setCdk(randomCode);
            cdk.setEffective(0);
            cdk.setMoney(money);
            cdk.setOverduetime(endTime);
            cdkService.insertCdkInfo(cdk);
            map.put("cdk",randomCode);
            list.add(map);
        }
        return list;
    }

    @Override
    public boolean deleKeyValid(account_cdk KeyInfo) {
        KeyInfo.setEffective(1);
        cdkService.updateCdkInfo(KeyInfo);
        return true;
    }
}
