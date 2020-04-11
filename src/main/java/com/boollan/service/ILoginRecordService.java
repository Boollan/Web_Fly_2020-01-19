package com.boollan.service;


import com.boollan.domain.login_record;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ILoginRecordService {
    /**
     * 查询所有
     *
     * @return
     */
    List<login_record> findAllLoginUserLog();

    /**
     * 查询一个
     *
     * @return
     */
    List<login_record> findLoginLogbyUser(String username);

    /**
     * 保存；新增登录日志
     *
     * @param account
     */
    void insertLoginUserLog(login_record account);

}
