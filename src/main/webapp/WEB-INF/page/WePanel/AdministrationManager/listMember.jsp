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
        <label>列表类型:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="list_type_m">
                <option value="1">VIP列表</option>
                <option value="2">OP列表</option>
            </select>
        </div>
    </div>
    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="sub_type_m" style="background-color: #208DF1;">查询</button>
    </div>
    <div id="list_text_m"></div>
    <br>
    <br>
    <br>
    <br>
</div>


