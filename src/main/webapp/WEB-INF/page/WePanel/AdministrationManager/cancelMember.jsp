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
        <input type="text" id="cancel_m_steamid" placeholder="请输入SteamId64">
    </div>
    <div class="ui-form-item ui-border-b">
        <label>会员类型:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="cancel_m_type">
                <option value="1">VIP</option>
                <option value="2">OP</option>
            </select>
        </div>
    </div>
    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="cancel_m_sub" style="background-color: #208DF1;">确认取消</button>
    </div>

</div>



