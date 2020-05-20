package com.boollan.Servlet.ApiMethod.Impl;

import com.boollan.Servlet.ApiMethod.IAccountMethod;
import com.boollan.Servlet.Cookie.SetCookie;
import com.boollan.domain.account_user;
import com.boollan.domain.emailcode;
import com.boollan.domain.login_record;
import com.boollan.service.IAccountUserService;
import com.boollan.service.IEmailCodeService;
import com.boollan.service.ILoginRecordService;
import com.boollan.util.module.RandomNumber;
import com.boollan.util.module.Encryption;
import com.boollan.util.module.LoginValidation;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此类是由注解进行装配
 *
 * @author Boollan
 */


public class AccountMethod implements IAccountMethod {


    private ILoginRecordService loginRecordService;

    private IAccountUserService userService;

    public void setLoginRecordService(ILoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    public void setUserService(IAccountUserService userService) {
        this.userService = userService;
    }

    public void setEmailCodeService(IEmailCodeService emailCodeService) {
        this.emailCodeService = emailCodeService;
    }

    public void setAccountEmail(AccountEmail accountEmail) {
        this.accountEmail = accountEmail;
    }

    private IEmailCodeService emailCodeService;

    private AccountEmail accountEmail;

    /**
     * 用户登录
     *
     * @param request              获取响应头
     * @param response             发送Cookie到客户端
     * @param AccountLoginInfoJson 需要的用户信息
     * @return 返回是否成功
     */
    @Override
    public boolean accountLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> AccountLoginInfoJson) {
        //实例化工具类
        LoginValidation loginValidation = new LoginValidation();
        boolean token = loginValidation.ImgeVerification(AccountLoginInfoJson.get("token").toString());
        //验证人机
        if (token) {
            //查询当前用户信息
            account_user account_user = userService.findInfoByUser(AccountLoginInfoJson.get("username").toString());
            //非空验证
            if (account_user != null) {
                //将MD5加密后的密码进行比对
                if (account_user.getPassword().equals(Encryption.StringInMd5(AccountLoginInfoJson.get("password").toString()))) {
                    //设置是否是7天登录
                    SetCookie setCookie = new SetCookie();
                    if (AccountLoginInfoJson.get("inputKeep").equals("1")) {
                        //生成Session
                        HttpSession session = request.getSession();
                        //获取Session ID
                        String id = session.getId();
                        //存储Session
                        session.setAttribute("UserName", account_user.getUsername());
                        session.setAttribute("permissions", account_user.getPermissions());
                        //end
                        //设置Session 过期时间
                        session.setMaxInactiveInterval(60 * 60 * 24 * 7);

                        Cookie jsessionid = setCookie.SetUserCookie("JSESSIONID", id, true);
                        //登录记录设置
                        accountLoginLog(account_user.getUsername(), request, "Web");
                        /*
                          返回值 登录成功！
                         */
                        //发送Cookie
                        response.addCookie(jsessionid);
                        return true;
                    } else {
                        //生成Session
                        HttpSession session = request.getSession();
                        //获取SessionID
                        String id = session.getId();
                        session.getId();//获取Session ID
                        session.setAttribute("UserName", account_user.getUsername());
                        session.setAttribute("permissions", account_user.getPermissions());
                        //存储Cookie
                        Cookie jsessionid = setCookie.SetUserCookie("JSESSIONID", id, false);
                        //登录记录
                        accountLoginLog(account_user.getUsername(), request, "Web");
                        response.addCookie(jsessionid);

                        /*
                          返回值 登录成功！
                         */
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 登录日志
     *
     * @param username 用户
     * @param ipAddr   IP地址
     * @param Client   登录的平台
     */
    @Override
    public void accountLoginLog(String username, HttpServletRequest ipAddr, String Client) {
        //设置登录记录

        //获取用户的IP
        String remoteAddr = Encryption.getIpAddress(ipAddr);
        //获取用户的登录时间
        Date date = new Date(System.currentTimeMillis());
        login_record login_record = new login_record();
        login_record.setUsername(username);
        login_record.setAddip(remoteAddr);
        login_record.setClient(Client);
        login_record.setDatetime(date);
        loginRecordService.insertLoginUserLog(login_record);
    }

    /**
     * 用户注册
     *
     * @param AccountRegInfoJson 注册信息
     * @return 是否成功
     */
    @Override
    public boolean accountReg(Map<String, Object> AccountRegInfoJson) {
        //实例化数据库类
        account_user account_user = new account_user();
        //设置信息
        account_user.setUsername(AccountRegInfoJson.get("username").toString());
        account_user.setPassword(Encryption.StringInMd5(AccountRegInfoJson.get("password").toString()));
        account_user.setEmail(AccountRegInfoJson.get("email").toString());
        account_user.setDonations(0);
        account_user.setPermissions(0);
        //添加用户信息
        userService.insetUserInfo(account_user);
        return true;
    }

    /**
     * 密码重置
     *
     * @param AccountResetInfoJson 用户信息Json
     * @return 是否更新成功
     */
    @Override
    public boolean accountPasswordReset(Map<String, Object> AccountResetInfoJson) {
        //通过邮箱获取用户信息
        LoginValidation login_record = new LoginValidation();
        if (login_record.ImgeVerification(AccountResetInfoJson.get("token").toString())) {
            Map<String, Object> email = this.accountGetInfoEmail(AccountResetInfoJson.get("email").toString());
            if (email != null) {
                //验证邮箱验证码是否正确
                if (this.accountEmailCodeVerify(AccountResetInfoJson.get("email").toString(), AccountResetInfoJson.get("code").toString())) {
                    //验证成功更新密码（密码已经够MD5加密）
                    account_user userInfo = userService.findInfoByEmail(AccountResetInfoJson.get("email").toString());
                    userInfo.setPassword(Encryption.StringInMd5(AccountResetInfoJson.get("newpwd").toString()));
                    //更新密码
                    userService.updateUserInfo(userInfo);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 更新用户密码
     *
     * @param AccountUpdateInfoJson 需要更新的用户数据
     * @return 返回是否成功更新
     */
    @Override
    public boolean accountPasswordUpdate(Map<String, Object> AccountUpdateInfoJson) {
        //1.获取当前用户信息
        account_user username = userService.findInfoByUser(AccountUpdateInfoJson.get("username").toString());
        //2.比对当前用户的密码
        if (username.getPassword().equals(Encryption.StringInMd5(AccountUpdateInfoJson.get("lowPassword").toString()).toString())) {
            username.setPassword(Encryption.StringInMd5(AccountUpdateInfoJson.get("newPassword").toString()));
            userService.updateUserInfo(username);
            return true;
        }
        return false;
    }

    /**
     * 获取用户信息
     *
     * @param UserName 用户名
     * @return 用户信息MAP
     */
    @Override
    public Map<String, Object> accountGetInfo(String UserName) {
        account_user infoByUser = userService.findInfoByUser(UserName);
        if (infoByUser != null) {
            //传递信息
            Map<String, Object> map = new HashMap<>();
            map.put("username", infoByUser.getUsername());
            map.put("password", infoByUser.getPassword());
            map.put("email", infoByUser.getEmail());
            map.put("donations", infoByUser.getDonations());
            map.put("permissions", infoByUser.getPermissions());
            return map;
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @param email 邮箱
     * @return 用户信息MAP
     */
    @Override
    public Map<String, Object> accountGetInfoEmail(String email) {
        account_user infoByEmail = userService.findInfoByEmail(email);
        if (infoByEmail != null) {
            //传递信息
            Map<String, Object> map = new HashMap<>();
            map.put("username", infoByEmail.getUsername());
            map.put("password", infoByEmail.getPassword());
            map.put("email", infoByEmail.getEmail());
            map.put("donations", infoByEmail.getDonations());
            map.put("permissions", infoByEmail.getPermissions());
            return map;
        }
        return null;
    }

    /**
     * 生成邮箱验证码
     *
     * @param email 邮箱
     * @return 发送是否成功
     */
    @Override
    public boolean accountSendEmailCode(String email) {
        //生成验证码
        String randomCode = RandomNumber.getRandomCode(6);
        //更改时间

        Date date = new Date(System.currentTimeMillis() + 1800000);
        //生成实体
        emailcode emailcode = new emailcode();
        emailcode.setEmail(email.trim());
        emailcode.setIsuse(0);
        emailcode.setCode(randomCode);
        emailcode.setEffective(date);
        //发送给数据库
        emailCodeService.insetCreatEmailCode(emailcode);
        //发送邮箱验证码给用户
        accountEmail.SendEmail(emailcode.getEmail(), accountEmail.getEmail_SendName(), accountEmail.getEmail_Title(), "您的验证码为：" + emailcode.getCode() + "");
        return true;
    }

    /**
     * 验证邮箱验证码
     *
     * @param email 邮箱
     * @param code  验证码
     * @return true 成功
     */
    @Override
    public boolean accountEmailCodeVerify(String email, String code) {
        emailcode codeByEamil = emailCodeService.findCodeByEamil(email, code);
        if (codeByEamil != null && codeByEamil.getIsuse() != 1) {
            codeByEamil.setIsuse(1);
            emailCodeService.updateEmailCode(codeByEamil);
            return true;
        }
        return false;
    }

    /**
     * 更改邮箱
     *
     * @param username 用户名
     * @param lowEmail 旧邮箱
     * @param newEmail 新邮箱
     * @param code     用于验证的验证码
     * @return 返回是否成功
     */
    @Override
    public boolean accountEmailUpdate(String username, String lowEmail, String newEmail, String code) {

        //非空验证
        if (username != null && lowEmail != null && newEmail != null && code != null) {
            //获取用户信息
            Map<String, Object> userInfo = this.accountGetInfo(username);
            //判定用户信息是否存在
            if (userInfo != null) {
                //邮箱比较
                if (userInfo.get("email").toString().equals(lowEmail)) {
                    //验证邮箱认证
                    if (this.accountEmailCodeVerify(lowEmail, code)) {
                        account_user infoByUser = userService.findInfoByUser(username);
                        infoByUser.setEmail(newEmail);
                        userService.updateUserInfo(infoByUser);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 验证管理员身份信息
     *
     * @param request 获取管理员信息
     * @return 返回管理员是否允许通过权限
     */
    @Override
    public boolean verifyAdminPermissions(HttpServletRequest request) {
        int permissionsLive = 3;
        HttpSession session = request.getSession();
        //存在该用户
        if (session.getAttribute("UserName") != null) {
            //管理员安全验证
            account_user userName = userService.findInfoByUser(session.getAttribute("UserName").toString());
            if (userName.getPermissions() >= permissionsLive) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> accountInfo() {
        return null;
    }

}
