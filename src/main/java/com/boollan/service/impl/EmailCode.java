package com.boollan.service.impl;

import com.boollan.dao.IEmailCodeDao;
import com.boollan.domain.emailcode;
import com.boollan.service.IEmailCode;
import com.boollan.service.IEmailMessageSend;
import com.boollan.util.module.RandomNumber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Boollan
 */

public class EmailCode implements IEmailCode {

    private IEmailCodeDao emailCodeDao;
    private IEmailMessageSend emailMessageSend;

    public void setEmailMessageSend(IEmailMessageSend emailMessageSend) {
        this.emailMessageSend = emailMessageSend;
    }

    public void setEmailCodeDao(IEmailCodeDao emailCodeDao) {
        this.emailCodeDao = emailCodeDao;
    }

    @Override
    public boolean sendEmailCode(String email) throws Exception {
        //生成验证码
        String randomCode = RandomNumber.getRandomCode(6);
        //更改时间
        Date date = new Date(System.currentTimeMillis() + 1800000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        //生成实体
        emailcode echinoderm = new emailcode();
        echinoderm.setEmail(email.trim());
        echinoderm.setCode(randomCode);
        echinoderm.setEffective(dateString);
        echinoderm.setIsuse("0");
        //发送给数据库
        emailCodeDao.insertEmailCode(echinoderm);
        //发送邮箱验证码给用户
        emailMessageSend.sendEmail(email.trim(), "兔子窝", "兔子窝验证码", "您的验证码为:" + randomCode);
        return true;
    }

    @Override
    public boolean emailCodeVerify(String email, String code) throws ParseException {

        emailcode echinoderm = new emailcode();
        echinoderm.setEmail(email);
        echinoderm.setCode(code);
        emailcode emailCodeByEmail = emailCodeDao.findEmailCodeByEmail(echinoderm);
        if (emailCodeByEmail != null) {
            //是否已经使用
            String isuse = emailCodeByEmail.getIsuse();
            //当前时间
            Date dateTime = new Date(System.currentTimeMillis());
            //过期时间
            Date overtime = new Date(emailCodeByEmail.getEffective().replace('-', '/'));
            //判断是否使用 并且 过期时间判断
            if ("0".equals(isuse) && dateTime.compareTo(overtime) < 0) {
                //修改状态为已使用
                emailCodeByEmail.setIsuse("1");
                emailCodeDao.updateEmailCode(emailCodeByEmail);
                return true;
            }
        }
        return false;
    }
}
