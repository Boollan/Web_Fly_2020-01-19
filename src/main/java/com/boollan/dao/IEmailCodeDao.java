package com.boollan.dao;

import com.boollan.domain.emailcode;
import org.springframework.stereotype.Service;


/**
 * @author Boollan
 */
@Service
public interface IEmailCodeDao {


    /**
     * 通关邮箱和验证码查询
     * @param emailCodeInfo 邮箱
     * @return 返回的验证码信息
     */
    emailcode findEmailCodeByEmail(emailcode emailCodeInfo);

    /**
     * 添加一个验证码信息
     * @param emailCodeInfo 验证码信息
     */
    void insertEmailCode(emailcode emailCodeInfo);


    /**
     * 更改验证码信息
     * @param emailCodeInfo 新的验证码信息
     */
    void updateEmailCode(emailcode emailCodeInfo);

}
