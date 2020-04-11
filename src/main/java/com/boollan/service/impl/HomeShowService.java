package com.boollan.service.impl;

import com.boollan.dao.IHomeShowDao;
import com.boollan.domain.home_show;
import com.boollan.service.IHomeShowService;
import com.boollan.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public class HomeShowService implements IHomeShowService {

    private IHomeShowDao showDao;

    public void setShowDao(IHomeShowDao showDao) {
        this.showDao = showDao;
    }

    @Override
    public home_show findInfoByHome() {
        return showDao.findInfoByHome().get(0);
    }

    @Override
    public void updateHomeShow(home_show account) {
        showDao.updateHomeShow(account);
    }
}
