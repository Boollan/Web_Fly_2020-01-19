package com.boollan.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Boollan
 */
public interface IL4d2GameInfo {

    /**
     * 返回全部游戏通关记录
     * @return 返回通关记录集合
     */
    List<Map<String,String>> getL4d2GameCcrList() throws IOException;


    /**
     * 通过地图名称查询记录保持者
     * @param mapName 地图名称
     * @return 地图记录信息
     */
    Map<String,String> getL4d2GameMapNameToCcr(String mapName) throws IOException;


    /**
     * 取消玩家记录
     * @param mapName 地图名称
     * @param password 管理员密码
     * @return 是否更新成功
     */
    boolean getL4d2GameMapNameToCcrCancel(String mapName,String password);

}
