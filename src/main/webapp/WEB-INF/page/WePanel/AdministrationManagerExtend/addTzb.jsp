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
        <input type="text" id="addTzb_steamid" placeholder="请输入SteamId64">
    </div>

    <div class="ui-form-item ui-border-b">
        <label>兔子币:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="addTzw_select">
                <option value="10">10兔子币</option>
                <option value="50">50兔子币</option>
                <option value="100">100兔子币</option>
                <option value="200">200兔子币</option>
                <option value="500">500兔子币</option>
                <option value="1000">1000兔子币</option>
                <option value="2000">2000兔子币</option>
                <option value="5000">5000兔子币</option>
            </select>
        </div>
    </div>

    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="addTzb_sub"  style="background-color: #208DF1;">确认添加兔子币</button>
    </div>

</div>
