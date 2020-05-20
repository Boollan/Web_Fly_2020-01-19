package com.boollan.service.impl;

import com.boollan.dao.ILoginRecordDao;
import com.boollan.domain.login_record;
import com.boollan.service.ILoginRecordService;
import java.util.List;


public class LoginRecordService implements ILoginRecordService {

    private ILoginRecordDao recordDao;

    public void setRecordDao(ILoginRecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public List<login_record> findAllLoginUserLog() {
        return recordDao.findAllLoginUserLog();
    }

    @Override
    public List<login_record> findLoginLogbyUser(String username) {
        return recordDao.findLoginLogbyUser(username);
    }

    @Override
    public void insertLoginUserLog(login_record account) {
        recordDao.insertLoginUserLog(account);
    }
}
