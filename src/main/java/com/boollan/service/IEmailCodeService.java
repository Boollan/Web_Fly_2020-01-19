package com.boollan.service;

import com.boollan.domain.emailcode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IEmailCodeService {

    /**
     * 查询所有
     *
     * @return
     */
    List<emailcode> findAllEmailCode();

    /**
     * 查询:用于验证
     *
     * @return
     */
    emailcode findCodeByEamil(String email, String code);

    /**
     * 保存：新增验证码
     *
     * @param emaildao
     */
    void insetCreatEmailCode(emailcode emaildao);

    /**
     * 更新:更新已验证或未验证
     *
     * @param account
     */
    void updateEmailCode(emailcode account);

}
