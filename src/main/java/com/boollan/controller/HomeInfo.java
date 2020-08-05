package com.boollan.controller;

import com.boollan.service.IL4d2GameInfo;
import com.github.koraktor.steamcondenser.exceptions.SteamCondenserException;
import com.github.koraktor.steamcondenser.steam.servers.GoldSrcServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;


/**
 * @author Boollan
 */
@RequestMapping(path = "/")
public class HomeInfo {

    private IL4d2GameInfo l4d2GameCcrList;

    public void setL4d2GameCcrList(IL4d2GameInfo l4d2GameCcrList) {
        this.l4d2GameCcrList = l4d2GameCcrList;
    }

    /**
     * 公告页面
     *
     * @return true
     */
    @RequestMapping(path = "/")
    public ModelAndView getindex() {
        return new ModelAndView("VuePanel/userPanel/module/complaints/index");
    }


    /**
     * 求生之路2游戏服务器信息 其他服务器
     *
     * @return true
     */
    @RequestMapping(path = "/GameServerInfo", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getServerGame() throws TimeoutException, SteamCondenserException {

        GoldSrcServer server = new GoldSrcServer("47.100.39.221", 27015);
        server.initialize();

        StringBuffer substring = new StringBuffer();
        substring.append(server.getServerInfo().get("serverName") + "\n");
        substring.append("地图:" + server.getServerInfo().get("mapName") + "\n");
        substring.append("延迟:" + server.getPing() + "ms\n");
        substring.append("ip:" + "43.241.50.78:32085" + "\n");
        String str = server.getPlayers().values().toString();
        int number = 0;

        int start = 0;
        int end = 0;
        int temp = 0;

        int start1 = 0;
        int end1 = 0;
        int temp1 = 0;
        for (int i = 0; i < str.length(); i++) {
            start = str.indexOf("\"", temp);
            end = str.indexOf("\"", start + 1);
            temp = end + 1;


            start1 = str.indexOf("Time:", temp1);
            end1 = str.indexOf(".", start1 + 5);
            temp1 = end1 + 2;
            try {

                substring.append((i + 1) + "." + str.substring(start, end));
                substring.append("\t在线时间:" + (Integer.parseInt(str.substring(start1 + 6, end1)) / 60) + "分钟 \n");
                number++;
            } catch (Exception e) {
                break;
            }
        }
        substring.append("总计当前玩家人数：" + number + "/8");
        return substring.toString();
    }


    /**
     * 求生之路2游戏服务器信息
     *
     * @return true
     */
    @RequestMapping(path = "/GameServerInfoNew", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getServerGameNew() throws TimeoutException, SteamCondenserException {

        GoldSrcServer server = new GoldSrcServer("43.241.50.78", 32062);
        server.initialize();

        StringBuffer substring = new StringBuffer();
        substring.append(server.getServerInfo().get("serverName") + "\n");
        substring.append("地图:" + server.getServerInfo().get("mapName") + "\n");
        substring.append("延迟:" + server.getPing() + "ms\n");
        substring.append("ip:" + "43.241.50.78:32062" + "\n");
        String str = server.getPlayers().values().toString();
        int number = 0;

        int start = 0;
        int end = 0;
        int temp = 0;

        int start1 = 0;
        int end1 = 0;
        int temp1 = 0;
        for (int i = 0; i < str.length(); i++) {
            start = str.indexOf("\"", temp);
            end = str.indexOf("\"", start + 1);
            temp = end + 1;


            start1 = str.indexOf("Time:", temp1);
            end1 = str.indexOf(".", start1 + 5);
            temp1 = end1 + 2;
            try {

                substring.append((i + 1) + "." + str.substring(start, end));
                substring.append("\t在线时间:" + (Integer.parseInt(str.substring(start1 + 6, end1)) / 60) + "分钟 \n");
                number++;
            } catch (Exception e) {
                break;
            }
        }
        substring.append("总计当前玩家人数：" + number + "/12");

        return substring.toString();
    }


    /**
     * 跳跃服游戏服务器公告页面
     *
     * @return true
     */
    @RequestMapping(value = "motd")
    public ModelAndView getHelpMotd() {
        return new ModelAndView("motd");
    }

    /**
     * 游戏服务器提供商
     *
     * @return true
     */
    @RequestMapping(value = "title")
    public ModelAndView getHelpTitle() {
        return new ModelAndView("title");
    }


    /**
     * 求生之路2游戏查询全地图记录
     *
     * @return true
     */
    @RequestMapping(path = "/GameServerInfoCcr", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getServerGameInfoCcr(HttpServletRequest request) throws IOException {


        if (request.getParameter("mapName") != null) {
            String mapName = request.getParameter("mapName");
            Map<String, String> mapNameInfo = this.l4d2GameCcrList.getL4d2GameMapNameToCcr(mapName);

            return "♛ 兔子窝跳跃服 查询记录 ♛ \n" +
                    "地图:" + mapNameInfo.get("mapName") + "|玩家:" + mapNameInfo.get("playName") + "|通关记录:" + Integer.parseInt(mapNameInfo.get("time")) / 60 + "分" + Integer.parseInt(mapNameInfo.get("time")) % 60 + "秒\n";
        } else {
            List<Map<String, String>> l4d2GameCcrList = this.l4d2GameCcrList.getL4d2GameCcrList();
            StringBuilder substring = new StringBuilder();
            substring.append("♛ 兔子窝跳跃服 通关记录列表 ♛ \n");
            for (Map<String, String> map : l4d2GameCcrList) {
                substring.append("地图:").append(map.get("mapName")).append("|玩家:").append(map.get("playName")).append("|通关记录:").append(Integer.parseInt(map.get("time")) / 60).append("分").append(Integer.parseInt(map.get("time")) % 60).append("秒\n");
            }
            return substring.toString();
        }
    }


    /**
     * 求生之路2游戏查询全地图记录取消
     *
     * @return true
     */
    @RequestMapping(path = "/GameServerInfoCcrCancel", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getServerGameInfoCcrCancel(HttpServletRequest request) throws IOException {
        //取消记录
        if (request.getParameter("mapName") != null && request.getParameter("password") != null) {

            String mapName = request.getParameter("mapName").trim();
            String password = request.getParameter("password").trim();
            Map<String, String> mapNameInfo = this.l4d2GameCcrList.getL4d2GameMapNameToCcr(mapName);

            if (this.l4d2GameCcrList.getL4d2GameMapNameToCcrCancel(mapName, password)) {
                return "♛ 兔子窝跳跃服 取消记录 ♛ \n" +
                        "地图:" + mapNameInfo.get("mapName") + "|玩家:" + mapNameInfo.get("playName") + "|通关记录:" + Integer.parseInt(mapNameInfo.get("time")) / 60 + "分" + Integer.parseInt(mapNameInfo.get("time")) % 60 + "秒\n此地图记录取消成功!";
            } else {
                return "♛ 兔子窝跳跃服 取消记录 ♛ \n" +
                        "地图:" + mapNameInfo.get("mapName") + "|玩家:" + mapNameInfo.get("playName") + "|通关记录:" + Integer.parseInt(mapNameInfo.get("time")) / 60 + "分" + Integer.parseInt(mapNameInfo.get("time")) % 60 + "秒\n此地图记录取消失败 原因:未知!";
            }
        } else {
            return "地图名称不能为空!";
        }
    }


}
