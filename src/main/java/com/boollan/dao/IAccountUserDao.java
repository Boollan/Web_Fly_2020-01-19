package com.boollan.dao;

import com.boollan.domain.account_user;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Boollan
 */
@Service
public interface IAccountUserDao {

    /**
     * 查询所有
     *
     * @return
     */
    List<account_user> findAllUserInfoList();

    /**
     * 查询一个
     *
     * @return
     */
    account_user findInfoByUser(String username);

    /**
     * 保存:新增用户
     *
     * @param account
     */
    void insetUserInfo(account_user account);

    /**
     * 更新：更新用户信息
     *
     * @param account
     */
    void updateUserInfo(account_user account);

    /**
     * 删除：删除该用户(清理或注销)
     *
     * @param accountUser 用户信息
     */
    void deleteUserInfo(String accountUser);

    /**
     * 获取这个邮箱的Email
      * @param Email 邮箱
     */
    account_user findInfoByEmail(String Email);
}
