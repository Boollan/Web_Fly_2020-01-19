package com.boollan.dao;

import com.boollan.domain.emailcode;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Boollan
 */
@Service
public interface IEmailCodeDao {

    /**
     * 查询所有
     *
     * @return
     */
    List<emailcode> findAllEmailCode();


    /**
     * @param emailDao 数据集合
     * @return 返回是否成功
     */
    emailcode findCodeByEmail(emailcode emailDao);


    /**
     * 插入邮箱验证码信息
     * @param emailDao 验证码内容
     */
    void insetCreatEmailCode(emailcode emailDao);

    /**
     * 更新:更新已验证或未验证
     * @param account 用户信息
     */
    void updateEmailCode(emailcode account);

}
