package com.boollan.dao;

import com.boollan.domain.user_info;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Boollan
 */
@Service
public interface IUserInfoDao {


    /**
     * 查询全部用户信息
     * @return 返回用户信息
     */
    List<user_info> findAllUserInfo();


    /**
     * 通过uid查询信息
     * @param uid 唯一数字ID
     * @return 返回用户信息
     */
    user_info findUserInfoByUid(String uid);


    /**
     * 通过用户名查询信息
     * @param username 唯一用户名称
     * @return 返回用户信息
     */
    user_info findUserInfoByName(String username);


    /**
     * 通过邮箱查询用户信息
     * @param email 唯一用户名称
     * @return 返回用户信息
     */
    user_info findUserInfoByEmail(String email);


    /**
     * 添加一个新的用户信息
     * @param insertInfo 用户信息
     */
    void insertUserInfo(user_info insertInfo);


    /**
     * 更改用户信息
     * @param updateInfo 更改后的用户信息
     */
    void updateUserInfo(user_info updateInfo);

}
