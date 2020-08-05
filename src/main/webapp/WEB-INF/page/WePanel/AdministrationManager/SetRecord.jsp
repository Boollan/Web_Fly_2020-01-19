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
    <form action="">
        <div class="ui-form-item ui-border-b">
            <label>Vip地图记录:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select>
                    <option value="1">开启记录</option>
                    <option value="2">关闭记录</option>
                </select>
            </div>
            <p style="color: red;">状态:开启</p>
        </div>
        <div class="ui-form-item ui-border-b">
            <label>OP地图记录:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select>
                    <option value="1">开启记录</option>
                    <option value="2">关闭记录</option>
                </select>
            </div>
            <p style="color: red;">状态:开启</p>
        </div>
        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary" style="background-color: #208DF1;">确认设置</button>
        </div>

    </form>
</div>
