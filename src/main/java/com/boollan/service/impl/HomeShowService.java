package com.boollan.service.impl;

import com.boollan.dao.IHomeShowDao;
import com.boollan.domain.home_show;
import com.boollan.service.IHomeShowService;



/**
 * @author Boollan
 */
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
