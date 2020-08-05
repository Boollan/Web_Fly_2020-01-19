package com.boollan.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.boollan.dao.IEmailCodeDao;
import com.boollan.dao.IUserInfoDao;
import com.boollan.domain.emailcode;
import com.boollan.domain.user_info;
import com.boollan.service.IEmailCode;
import com.boollan.service.IMemberSystemAdmin;
import com.boollan.service.IUserInformationSystem;
import com.boollan.util.module.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Boollan
 */
public class UserInformationSystem implements IUserInformationSystem {


    private IUserInfoDao userInfo;

    private IEmailCode emailCode;
    private IMemberSystemAdmin memberSystem;



    public void setUserInfo(IUserInfoDao userInfo) {
        this.userInfo = userInfo;
    }

    public void setEmailCode(IEmailCode emailCode) {
        this.emailCode = emailCode;
    }

    public void setMemberSystem(IMemberSystemAdmin memberSystem) {
        this.memberSystem = memberSystem;
    }

    @Override
    public boolean userLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> accountLoginInfoJson) {

        //查询当前用户信息
        user_info userInfoByName = userInfo.findUserInfoByName(accountLoginInfoJson.get("username").toString());
        //非空验证
        if (userInfoByName != null) {
            //将MD5加密后的密码进行比对
            if (userInfoByName.getPassword().equals(Encryption.StringInMd5(accountLoginInfoJson.get("password").toString()))) {

                //设置是否是7天登录
                SetCookie setCookie = new SetCookie();
                if (accountLoginInfoJson.get("inputKeep").equals("1")) {
                    //生成Session
                    HttpSession session = request.getSession();
                    //获取Session ID
                    String id = session.getId();
                    //存储Session
                    session.setAttribute("UserName", userInfoByName.getUsername());
                    session.setAttribute("Root", userInfoByName.getRoot());
                    //end
                    //设置Session 过期时间
                    session.setMaxInactiveInterval(60 * 60 * 24 * 7);

                    Cookie jsessionid = setCookie.setUserCookie("JSESSIONID", id, true);
                    //发送Cookie
                    response.addCookie(jsessionid);
                    return true;
                } else {
                    //生成Session
                    HttpSession session = request.getSession();
                    //获取SessionID
                    String id = session.getId();
                    session.getId();//获取Session ID
                    //存储Session
                    session.setAttribute("UserName", userInfoByName.getUsername());
                    session.setAttribute("Root", userInfoByName.getRoot());
                    //存储Cookie
                    Cookie jsessionid = setCookie.setUserCookie("JSESSIONID", id, false);
                    response.addCookie(jsessionid);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean userReg(String username, String password, String email) {

        //必须不能有中文
        if (!RegularVerify.isContainChinese(username) && !RegularVerify.isContainChinese(password) && !RegularVerify.isContainChinese(email)) {
            //通过用户名查询信息
            user_info userInfoByName = userInfo.findUserInfoByName(username.trim());
            user_info userInfoByEmail = userInfo.findUserInfoByEmail(email.trim());
            //通过uid查询的信息
            user_info userInfoByUid;
            //uid随机数
            String randomNumCode;
            //判断UID是否是唯一的 如果数据库中已经存在uid那么就重新生成
            //通过uid查询信息
            do {
                randomNumCode = RandomNumber.getRandomNumCode(8);
                userInfoByUid = userInfo.findUserInfoByUid(randomNumCode);
            } while (null != userInfoByUid);
            //判断是否有当前用户信息
            if (null == userInfoByName && userInfoByEmail == null) {

                user_info userInfoCraet = new user_info();
                userInfoCraet.setUid(randomNumCode);
                userInfoCraet.setUsername(username.trim());
                userInfoCraet.setPassword(Encryption.StringInMd5(password.trim()));
                userInfoCraet.setEmail(email.trim());
                userInfoCraet.setRoot("0");
                //添加用户信息到数据库
                userInfo.insertUserInfo(userInfoCraet);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean passwordReset(String username, String email, String code, String newPassword) throws ParseException {
        //必须不能有中文
        if (!RegularVerify.isContainChinese(username.trim()) && !RegularVerify.isContainChinese(email.trim()) && !RegularVerify.isContainChinese(newPassword.trim())) {
            user_info userInfoByName = userInfo.findUserInfoByName(username.trim());
            //判断是否存在这个用户
            if (null != userInfoByName && emailCode.emailCodeVerify(email.trim(), code.trim())) {
                userInfoByName.setPassword(Encryption.StringInMd5(newPassword.trim()));
                userInfo.updateUserInfo(userInfoByName);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean passwordUpdate(String username, String lowPassword, String newPassword) {
        //判断是否有中文
        if (!RegularVerify.isContainChinese(username.trim()) && !RegularVerify.isContainChinese(lowPassword.trim()) && !RegularVerify.isContainChinese(newPassword.trim())) {
            user_info userInfoByName = userInfo.findUserInfoByName(username.trim());
            //判断是否存在这个用户
            if (null != userInfoByName && userInfoByName.getPassword().equals(Encryption.StringInMd5(lowPassword.trim()))) {
                userInfoByName.setPassword(Encryption.StringInMd5(newPassword.trim()));
                userInfo.updateUserInfo(userInfoByName);
                return true;
            }
        }
        return false;
    }


    @Override
    public Map<String, Object> getInfoByName(String name) {
        if (!RegularVerify.isContainChinese(name.trim())) {
            Map<String, Object> map = new HashMap<>();
            user_info userInfoByName = userInfo.findUserInfoByName(name);
            map.put("Id", userInfoByName.getId().toString());
            map.put("Uid", userInfoByName.getUid());
            map.put("Username", userInfoByName.getUsername());
            map.put("Root", userInfoByName.getRoot());
            map.put("Email", userInfoByName.getEmail());
            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> getInfoByEmail(String email) {
        if (!RegularVerify.isContainChinese(email.trim())) {
            Map<String, Object> map = new HashMap<>();
            user_info userInfoByName = userInfo.findUserInfoByEmail(email);
            map.put("Id", userInfoByName.getId().toString());
            map.put("Uid", userInfoByName.getUid());
            map.put("Username", userInfoByName.getUsername());
            map.put("Root", userInfoByName.getRoot());
            map.put("Email", userInfoByName.getEmail());
            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> getInfoUid(String uid) {
        if (!RegularVerify.isContainChinese(uid.trim())) {
            Map<String, Object> map = new HashMap<>();
            user_info userInfoByName = userInfo.findUserInfoByUid(uid);
            map.put("Id", userInfoByName.getId().toString());
            map.put("Uid", userInfoByName.getUid());
            map.put("Username", userInfoByName.getUsername());
            map.put("Root", userInfoByName.getRoot());
            map.put("Email", userInfoByName.getEmail());
            return map;
        }
        return null;
    }


    @Override
    public boolean emailUpdate(String username, String lowEmail, String newEmail, String code) throws ParseException {
        //判断不包含中文
        if (!RegularVerify.isContainChinese(username.trim()) && !RegularVerify.isContainChinese(lowEmail.trim()) && !RegularVerify.isContainChinese(newEmail.trim()) && !RegularVerify.isContainChinese(code.trim())) {
            user_info userInfoByName = userInfo.findUserInfoByName(username.trim());
            //判断是否存在这个用户
            if (null != userInfoByName && emailCode.emailCodeVerify(lowEmail.trim(), code.trim())) {
                userInfoByName.setEmail(newEmail.trim());
                userInfo.updateUserInfo(userInfoByName);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<user_info> accountInfo() {
        return userInfo.findAllUserInfo();
    }

    @Override
    public boolean verifyAdminRoot(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //存在该用户
        if (session.getAttribute("UserName") != null) {
            //管理员安全验证
            user_info userInfoByName = userInfo.findUserInfoByName(session.getAttribute("UserName").toString());
            if (Integer.parseInt(userInfoByName.getRoot()) > 0) {
                return true;
            }
        }
        return false;

    }

    @Override
    public JSONObject getMeInfoAccount(HttpServletRequest request) throws IOException {

        Object attribute = request.getSession().getAttribute("UserName");

        user_info userInfoByName = userInfo.findUserInfoByName(attribute.toString());
        JSONObject result = new JSONObject();
        if(userInfoByName.getSteamid()!=null){
            HttpSteamInfo info = new HttpSteamInfo();
            //state 用来指定是否当前用户绑定SteamId
            result.put("state",true);
            result.put("SteamId",userInfoByName.getSteamid());
            result.put("GameName",info.getHttpSteamIdToName(userInfoByName.getSteamid()));
            result.put("IsVip",memberSystem.validTimeVip(userInfoByName.getSteamid()).get("TimeVipStr").toString());
            result.put("IsOp",memberSystem.validTimeOp(userInfoByName.getSteamid()).get("TimeOpStr").toString());
            result.put("Tzb",memberSystem.balanceTzb(userInfoByName.getSteamid()).get("Currency").toString());
            return result;
        }else {
            //state 用来指定是否当前用户绑定SteamId
            result.put("state",false);
            result.put("SteamId","未绑定");
            result.put("GameName","未绑定");
            result.put("IsVip","未绑定");
            result.put("IsOp","未绑定");
            result.put("Tzb","未绑定");
            return result;
        }
    }

    @Override
    public boolean bindingAccountSteamId(HttpServletRequest request, String steamid) {
        user_info userInfoByName = userInfo.findUserInfoByName(request.getSession().getAttribute("UserName").toString());
        userInfoByName.setSteamid(steamid.trim());
        userInfo.updateUserInfo(userInfoByName);
        return true;
    }


}
