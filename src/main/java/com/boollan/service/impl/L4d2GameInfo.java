package com.boollan.service.impl;
import java.io.IOException;
import	java.util.HashMap;
import	java.util.ArrayList;
import	java.util.List;
import java.util.Map;

import com.boollan.dao.ICcrListDao;
import com.boollan.domain.ccr_list;
import com.boollan.service.IL4d2GameInfo;
import com.boollan.util.module.HttpSteamInfo;



/**
 * @author Boollan
 */
public class L4d2GameInfo implements IL4d2GameInfo {

    private ICcrListDao crListDao;

    public void setCrListDao(ICcrListDao crListDao) {
        this.crListDao = crListDao;
    }

    private String adminPassword;

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public List<Map<String, String>> getL4d2GameCcrList() throws IOException {

        List list = new ArrayList<Map<String,String>>();
        List<ccr_list> allCcrList = crListDao.findAllCcrList();
        HttpSteamInfo steamInfo  = new HttpSteamInfo();
        for(ccr_list ccr :allCcrList){
            Map<String,String> map = new HashMap<> ();
            map.put("mapName",ccr.getMapname());
            map.put("playName",steamInfo.getHttpSteamIdToName(ccr.getPlaysteamid64()));
            map.put("time",ccr.getCleartime());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, String> getL4d2GameMapNameToCcr(String mapName) throws IOException {
        Map<String,String> map = new HashMap<> ();
        HttpSteamInfo steamInfo  = new HttpSteamInfo();
        ccr_list mapByName = crListDao.findMapByName(mapName);
        map.put("mapName",mapByName.getMapname());
        map.put("playName",steamInfo.getHttpSteamIdToName(mapByName.getPlaysteamid64()));
        map.put("time",mapByName.getCleartime());
        return map;
    }

    @Override
    public boolean getL4d2GameMapNameToCcrCancel(String mapName,String password) {
        if (password.equals(adminPassword)){
            //查询当前地图信息
            ccr_list mapByName = crListDao.findMapByName(mapName);
            if(mapByName !=null){

                mapByName.setClearplay("记录已取消");
                mapByName.setPlaysteamid64("00000000000000000");
                mapByName.setCleartime("9999");
                crListDao.updateMapCcrCancel(mapByName);
                return true;
            }
        }
        return false;
    }

}
