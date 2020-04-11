package com.boollan.service.impl;

import com.boollan.dao.IAccountBanDao;
import com.boollan.domain.account_ban;
import com.boollan.service.IAccountBanService;
import com.boollan.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



public class AccountBanService implements IAccountBanService {

    private IAccountBanDao banDao;

    public void setBanDao(IAccountBanDao banDao) {
        this.banDao = banDao;
    }

    @Override
    public List<account_ban> findAllBanUser() {
        return banDao.findAllBanUser();
    }

    @Override
    public account_ban findBanUserByName(String username) {
        return banDao.findBanUserByName(username);
    }

    @Override
    public void insertBanUser(account_ban username) {
        banDao.insertBanUser(username);
    }

    @Override
    public void updateBanUserInfo(account_ban username) {
        banDao.updateBanUserInfo(username);
    }


}
