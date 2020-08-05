package com.boollan.service;

import java.text.ParseException;

/**
 * @author wyzao
 */
public interface IEmailCode {

    /**
     * 发送邮箱验证码到指定邮箱
     * @param email 邮箱地址
     * @throws Exception 异常
     * @return 返回是否成功发送
     */
    boolean sendEmailCode(String email) throws Exception;


    /**
     * 更改验证码为已使用状态
     * @param email 邮箱
     * @param code  验证码
     * @return 是否成功
     * @throws ParseException 异常
     */
    boolean emailCodeVerify(String email, String code) throws ParseException;
}
