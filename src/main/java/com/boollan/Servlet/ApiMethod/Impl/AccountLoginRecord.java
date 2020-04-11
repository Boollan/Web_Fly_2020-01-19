package com.boollan.Servlet.ApiMethod.Impl;

import com.boollan.Servlet.ApiMethod.IAccountLoginRecord;
import com.boollan.domain.login_record;
import com.boollan.service.ILoginRecordService;
import com.boollan.util.module.encryption;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Boollan
 */

public class AccountLoginRecord implements IAccountLoginRecord {


    public void setILoginRecordService(com.boollan.service.ILoginRecordService ILoginRecordService) {
        this.ILoginRecordService = ILoginRecordService;
    }

    private ILoginRecordService ILoginRecordService;


    @Override
    public JSONArray findAllLoginUserLog() {
        JSONArray jsonArray = new JSONArray();
        List<login_record> allLoginUserLog = ILoginRecordService.findAllLoginUserLog();
        for (int i = 0; allLoginUserLog.size() > i; i++) {
            JSONObject jsonObject = new JSONObject();
            login_record login_record = allLoginUserLog.get(i);
            jsonObject.put("id", login_record.getId());
            jsonObject.put("username", login_record.getUsername());
            jsonObject.put("addip", login_record.getAddip());
            jsonObject.put("client", login_record.getClient());
            jsonObject.put("datetiem", login_record.getDatetime());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray findLoginLogbyUser(String username) {
        List<login_record> loginLogbyUser = ILoginRecordService.findLoginLogbyUser(username);
        JSONArray jsonArray = new JSONArray();

        for (login_record loginRecord:loginLogbyUser){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", loginRecord.getId());
            jsonObject.put("username", loginRecord.getUsername());
            jsonObject.put("addip", loginRecord.getAddip());
            jsonObject.put("client", loginRecord.getClient());
            jsonObject.put("datetiem", loginRecord.getDatetime());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray finLogUserInfo(String username, Date startTime, Date endTime, String Client) {
        System.out.println(1);
        if (username !=null&&startTime!=null &&endTime != null&&Client!=null){
            System.out.println(2);
            JSONArray jsonArray = new JSONArray();
            //获取当前用户信息
            List<login_record> loginLogbyUser = ILoginRecordService.findLoginLogbyUser(username);
            System.out.println(3);
            //数据遍历
            for (int i= 0; i < loginLogbyUser.size(); i++){

                String client = loginLogbyUser.get(i).getClient();
                //判定和用户筛选的平台是否一致
                if (client.equals(Client)) {
                    System.out.println("4"+i);
                    Date datetime = loginLogbyUser.get(i).getDatetime();
                    //判定和用户筛选的时间是否一致
                    System.out.println("datetime"+datetime.getTime());
                    System.out.println("startTime"+startTime.getTime());
                    System.out.println("endTime"+endTime.getTime());
                    if (datetime.getTime()>startTime.getTime() && datetime.getTime()<endTime.getTime()){
                        System.out.println("5"+i);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("username",loginLogbyUser.get(i).getUsername());
                        jsonObject.put("addIp",loginLogbyUser.get(i).getAddip());
                        jsonObject.put("client",loginLogbyUser.get(i).getClient());
                        jsonObject.put("dateTime",loginLogbyUser.get(i).getDatetime());
                        jsonArray.add(jsonObject);
                    }
                }
            }
            return jsonArray;
        }
        return null;
    }

    @Override
    public void insertLoginUserLog(String username, HttpServletRequest request) {
        login_record loginRecord = new login_record();
        String ipAddress = encryption.getIpAddress(request);
        Date date = new Date(System.currentTimeMillis());
        loginRecord.setUsername(username);
        loginRecord.setDatetime(date);
        loginRecord.setAddip(ipAddress);
        loginRecord.setClient("Web");
        ILoginRecordService.insertLoginUserLog(loginRecord);
    }
}
