package com.boollan.util.module;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class traverse {

    //遍历json

    //使用数据结果据变成JSON数据
    public JSONArray resultSetToJson(ResultSet rs) {
        try {

            // json数组
            JSONArray array = new JSONArray();

            // 获取列数
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 遍历ResultSet中的每条数据
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();

                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                array.add(jsonObj);
            }
            return array;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
