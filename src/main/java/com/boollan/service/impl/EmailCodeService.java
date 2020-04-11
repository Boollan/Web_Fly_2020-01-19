package com.boollan.service.impl;

import com.boollan.dao.IEmailCodeDao;
import com.boollan.domain.emailcode;
import com.boollan.service.IEmailCodeService;
import com.boollan.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



public class EmailCodeService implements IEmailCodeService {

    private IEmailCodeDao codeDao;

    public void setCodeDao(IEmailCodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Override
    public List<emailcode> findAllEmailCode() {
        return codeDao.findAllEmailCode();
    }

    @Override
    public emailcode findCodeByEamil(String email, String code) {
        return codeDao.findCodeByEamil(email, code);
    }

    @Override
    public void insetCreatEmailCode(emailcode emaildao) {
        codeDao.insetCreatEmailCode(emaildao);
    }

    @Override
    public void updateEmailCode(emailcode account) {
        codeDao.updateEmailCode(account);
    }
}
