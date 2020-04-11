package com.boollan.service;

import com.boollan.domain.account_user;
import java.util.List;


public interface IAccountUserService {

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
     * @param accountUser
     */
    void deleteUserInfo(String accountUser);

    /**
     * 查询邮箱下的用户
     * @param email 邮箱
     * @return 返回
     */
    account_user findInfoByEmail(String email);
}
