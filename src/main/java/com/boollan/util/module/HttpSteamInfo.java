package com.boollan.util.module;



import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author Boollan
 */
public class HttpSteamInfo {

    public String getHttpRequestData(String urlPath) {

        // 首先抓取异常并处理
        String returnString = null;
        try {
            /* 1 GET方式请求数据 start*/
            // 1 创建URL对象,接收用户传递访问地址对象链接
            URL url = new URL(urlPath);
            // 2 打开用户传递URL参数地址
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            // 3 设置HTTP请求的一些参数信息
            connect.setRequestMethod("GET");
            connect.connect();

            // 4 获取URL请求到的数据，并创建数据流接收
            InputStream isString = connect.getInputStream();

            // 5 构建一个字符流缓冲对象,承载URL读取到的数据
            BufferedReader isRead = new BufferedReader(new InputStreamReader(isString));

            // 6 输出打印获取到的文件流
            String str = "";
            while ((str = isRead.readLine()) != null) {
                //解决中文乱码问题
                str = new String(str.getBytes(), StandardCharsets.UTF_8);
                returnString = str;
            }
            // 7 关闭流
            isString.close();
            connect.disconnect();
            // 8 JSON转List对象
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnString;
    }


    /**
     * @param steamId64 玩家的Steam64ID
     * @return 返回玩家游戏名称
     * @throws IOException 处理异常
     */
    public String getHttpSteamIdToName(String steamId64) throws IOException {
        try {
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = LoginValidation.class.getClassLoader().getResourceAsStream("db.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //拼接访问字符串
            String apiWeb = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?";
            String urlPath = apiWeb + "key=" + properties.getProperty("SteamIdKey") + "&steamids=" + steamId64;
            //访问URL获取数据
            String responseData = getHttpRequestData(urlPath);

            //json转换可选深度对象
            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(responseData);
            JsonNode response = root.path("response");
            JsonNode players = response.path("players").get(0);
            return players.path("personaname").asText();
        }catch (Exception e){
            return null;
        }
    }




}
