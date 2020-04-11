package com.boollan.dao;

import com.boollan.domain.account_ban;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Boollan
 */
@Service
public interface IAccountBanDao {

    /**
     * 查询所有封禁用户
     *
     * @return
     */
    List<account_ban> findAllBanUser();

    /**
     * 查询某一个封禁用户
     *
     * @return
     */
    account_ban findBanUserByName(String username);

    /**
     * 保存：新增封禁用户
     *
     * @param username
     */
    void insertBanUser(account_ban username);

    /**
     * 更新：更新封禁用户信息
     *
     * @param username
     */
    void updateBanUserInfo(account_ban username);

}
