package com.boollan.Servlet.ApiMethod;

import com.boollan.domain.account_user;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * @author Boollan
 */
@Component
public interface IAccountMethod {
    /**
     * 用户登录模块
     *
     * @param response 发送Cookie到客户端
     */
    boolean accountLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> AccountLoginInfoJson);

    /**
     * 用户的登录记录
     *
     * @param username 用户
     * @param ipAddr   IP地址
     * @param Client   登录的平台
     */
    void accountLoginLog(String username, HttpServletRequest ipAddr, String Client);

    /**
     * 用户注册模块
     */
    boolean accountReg(Map<String, Object> AccountRegInfoJson);

    /**
     * 用户密码重置（通过其他途径（例如：邮箱验证）找回密码）
     */
    boolean accountPasswordReset(Map<String, Object> AccountResetInfoJson);

    /**
     * 用户密码重置（通过旧密码修改新密码）
     */
    boolean accountPasswordUpdate(Map<String, Object> AccountUpdateInfoJson);

    /**
     * 获取单个用户信息
     */
    Map<String, Object> accountGetInfo(String UserName);

    /**
     * 获取单个用户信息
     */
    Map<String, Object> accountGetInfoEmail(String email);


    /**
     * 发送邮箱验证码
     */
    boolean accountSendEmailCode(String email);


    /**
     * 更改验证码为已使用状态
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 是否成功
     */
    boolean accountEmailCodeVerify(String email, String code);

    /**
     * 更改绑定的邮箱
     * @param lowEmail 旧邮箱
     * @param newEmail 新邮箱
     * @param code 用于验证的验证码
     * @return true
     */
    boolean accountEmailUpdate(String username, String lowEmail, String newEmail, String code);

    /**
     * 获取全部用户信息（不安全）
     * @return 信息
     */
    List<String> accountInfo();

    /**
     * 验证管理员身份信息
     *
     * @param request 获取管理员信息
     * @return 返回管理员是否允许通过权限
     */
     boolean verifyAdminPermissions(HttpServletRequest request);

}
