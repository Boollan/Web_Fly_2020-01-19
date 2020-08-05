<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/7/7
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui-form ui-border-t" style="height: 5000px;">
    <br>
    <div class="ui-form-item ui-border-b">
        <label>SteamID:</label>
        <input type="text" id="add_m_steamid" placeholder="请输入SteamId64">
    </div>
    <div class="ui-form-item ui-border-b">
        <label>会员类型:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="m_add_type">
                <option value="1">VIP</option>
                <option value="2">OP</option>
            </select>
        </div>
    </div>
    <div class="ui-form-item ui-border-b">
        <label>会员时间:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="m_add_time">
                <option value="3600">1小时</option>
                <option value="86400">1天</option>
                <option value="1296000">15天</option>
                <option value="2592000">30天</option>
                <option value="5184000">60天</option>
                <option value="7776000">90天</option>
                <option value="15552000">180天</option>
                <option value="31104000">365天</option>
            </select>
        </div>
    </div>

    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="m_add_sub" style="background-color: #208DF1;">确认添加</button>
    </div>
</div>
