package com.boollan.service.impl;

import com.boollan.dao.IEmailCodeDao;
import com.boollan.domain.emailcode;
import com.boollan.service.IEmailCodeService;
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
        emailcode emailcode = new emailcode();
        emailcode.setEmail(email);
        emailcode.setCode(code);
        return codeDao.findCodeByEmail(emailcode);
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
