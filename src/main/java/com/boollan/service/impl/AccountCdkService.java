package com.boollan.service.impl;

import com.boollan.dao.IAccountCdkDao;
import com.boollan.domain.account_cdk;
import com.boollan.service.IAccountCdkService;
import com.boollan.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



public class AccountCdkService implements IAccountCdkService {

    private IAccountCdkDao cdkDao;

    public void setCdkDao(IAccountCdkDao cdkDao) {
        this.cdkDao = cdkDao;
    }

    @Override
    public List<account_cdk> findAllCdkInfo() {
        return cdkDao.findAllCdkInfo();
    }

    @Override
    public account_cdk finCdkInfoByCdk(String cdk) {
        return cdkDao.finCdkInfoByCdk(cdk);
    }

    @Override
    public void insertCdkInfo(account_cdk cdkinfo) {
        cdkDao.insertCdkInfo(cdkinfo);
    }

    @Override
    public void updateCdkInfo(account_cdk account_cdk) {
        cdkDao.updateCdkInfo(account_cdk);
    }
}
