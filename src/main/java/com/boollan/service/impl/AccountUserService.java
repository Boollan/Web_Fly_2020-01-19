package com.boollan.service.impl;

import com.boollan.dao.IAccountUserDao;
import com.boollan.domain.account_user;
import com.boollan.service.IAccountUserService;
import com.boollan.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public class AccountUserService implements IAccountUserService {


    private IAccountUserDao userDao;

    public void setUserDao(IAccountUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<account_user> findAllUserInfoList() {
        return userDao.findAllUserInfoList();
    }

    @Override
    public account_user findInfoByUser(String username) {
        return userDao.findInfoByUser(username);
    }

    @Override
    public void insetUserInfo(account_user account) {
        userDao.insetUserInfo(account);
    }

    @Override
    public void updateUserInfo(account_user account) {
        userDao.updateUserInfo(account);
    }

    @Override
    public void deleteUserInfo(String accountUser) {
        userDao.deleteUserInfo(accountUser);
    }

    @Override
    public account_user findInfoByEmail(String email) {
        return userDao.findInfoByEmail(email);
    }


}
