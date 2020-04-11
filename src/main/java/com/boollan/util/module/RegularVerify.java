package com.boollan.util.module;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类用于正则表达式验证
 */
@Component
public class RegularVerify {

    //验证邮箱
    public boolean VerifyEmail(String Email) {
        // 邮箱验证规则
        String regEx = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Email);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }




}
