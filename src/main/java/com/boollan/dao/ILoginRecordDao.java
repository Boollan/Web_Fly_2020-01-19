package com.boollan.dao;


import com.boollan.domain.login_record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILoginRecordDao {
    /**
     * 查询所有
     *
     * @return 获取用户日志
     */
    List<login_record> findAllLoginUserLog();

    /**
     * 查询一个
     * @param username 用户名
     * @return 返回用户日志列表
     */
    List<login_record> findLoginLogbyUser(String username);

    /**
     * 保存；新增登录日志
     *
     * @param loginRecord 插入日志信息
     */
    void insertLoginUserLog(login_record loginRecord);

}
